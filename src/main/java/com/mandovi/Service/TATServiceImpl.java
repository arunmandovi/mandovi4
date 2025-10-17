package com.mandovi.Service;

import com.mandovi.DTO.TATSummaryDTO;
import com.mandovi.Entity.TAT;
import com.mandovi.Repository.TATRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TATServiceImpl implements TATService {
    private final TATRepository tatRepository;

    public TATServiceImpl(TATRepository tatRepository) {
        this.tatRepository = tatRepository;
    }

    private Double round2Decimal(Double value){
        return Math.round(value*100.0)/100.0;
    }

    @Override
    public void saveLoadDataFromExcel(MultipartFile file) throws IOException {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;

                TAT tat = new TAT();

                tat.setCity(row.getCell(0).getStringCellValue());
                tat.setBranch(row.getCell(1).getStringCellValue());
                tat.setMonth(row.getCell(2).getStringCellValue());
                //Converting Integer cell's  year into String
                int num_year = (int) row.getCell(3).getNumericCellValue();
                String year = String.valueOf(num_year);
                tat.setYear(year);
                tat.setLinkServiceType(row.getCell(4).getStringCellValue());

                // Checking and Updating Time
                Cell timeCell = row.getCell(5);
                LocalTime timeValue = null;

                if (timeCell != null) {
                    try {
                        if (timeCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(timeCell)) {
                            // Excel stored as date+time (e.g. 31-Dec-1899 01:53:49)
                            Date date = timeCell.getDateCellValue();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            timeValue = LocalTime.of(
                                    calendar.get(Calendar.HOUR_OF_DAY),
                                    calendar.get(Calendar.MINUTE),
                                    calendar.get(Calendar.SECOND));
                        } else {
                            // Handle string like "1.53.49 AM"
                            DataFormatter formatter = new DataFormatter();
                            String cellText = formatter.formatCellValue(timeCell).trim();

                            if (!cellText.isEmpty()) {
                                String normalized = cellText.replace('.', ':')
                                        .replaceAll("\\s+", " ")
                                        .toUpperCase()
                                        .replaceAll("(?i)(AM|PM)$", " $1");

                                DateTimeFormatter[] formats = {
                                        DateTimeFormatter.ofPattern("h:mm:ss a", Locale.ENGLISH),
                                        DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH),
                                        DateTimeFormatter.ofPattern("H:mm:ss", Locale.ENGLISH),
                                        DateTimeFormatter.ofPattern("H:mm", Locale.ENGLISH)
                                };

                                for (DateTimeFormatter fmt : formats) {
                                    try {
                                        timeValue = LocalTime.parse(normalized, fmt);
                                        break;
                                    } catch (DateTimeParseException ignored) {}
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("⚠️ Error parsing time: " + e.getMessage());
                    }
                }

// ✅ finally set the parsed value
                tat.setAverageOfOpenToClose(timeValue);

                //Updating the column period by concating the columns month & year and "-" in between
                String period = tat.getMonth()+"-"+tat.getYear();
                tat.setPeriod(period);

                //Updating the column Qtr_Wise & Half-Year by comparing the values from colum Month
                String month = tat.getMonth().trim().toUpperCase();
                switch (month) {
                    case "APR", "MAY", "JUN" -> { tat.setQtrWise("Qtr1"); tat.setHalfYear("H1"); }
                    case "JUL", "AUG", "SEP" -> { tat.setQtrWise("Qtr2"); tat.setHalfYear("H1"); }
                    case "OCT", "NOV", "DEC" -> { tat.setQtrWise("Qtr3"); tat.setHalfYear("H2"); }
                    case "JAN", "FEB", "MAR" -> { tat.setQtrWise("Qtr4"); tat.setHalfYear("H2"); }
                }
                tatRepository.save(tat);

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<TAT> getAllTat() {
        return tatRepository.findAll();
    }

    @Override
    public List<TAT> getTATByMonthYear(String month, String year) {
        String formattedMonth = month.substring(0,1).toUpperCase()+month.substring(1).toLowerCase();
        return tatRepository.getTATByMonthYear(formattedMonth, year);
    }

    @Override
    public List<TATSummaryDTO> getTATSummary(String groupBy, String month, String qtrWise, String halfYear) {
        List<TAT> allTAT = tatRepository.findAll();

        //Apply Filters
        List<TAT> filterd = allTAT.stream()
                .filter(tat -> (month == null ||  month.equalsIgnoreCase(tat.getMonth())))
                .filter(tat -> (qtrWise == null || qtrWise.equalsIgnoreCase(tat.getQtrWise())))
                .filter(tat -> (halfYear == null || halfYear.equalsIgnoreCase(tat.getHalfYear())))
                .toList();

        //Group by city
       return filterd.stream()
               .collect(Collectors.groupingBy(TAT::getCity))
               .entrySet().stream()
               .map(entry -> {
                   String city = entry.getKey();
                   List<TAT> groupList = entry.getValue();

                   LocalTime firstFreeService = calculateAverageTime(groupList, "FR1");
                   LocalTime secondFreeService = calculateAverageTime(groupList, "FR2");
                   LocalTime thirdFreeService = calculateAverageTime(groupList, "FR3");
                   LocalTime pms = calculateAverageTime(groupList, "PMS");

                   return new TATSummaryDTO(
                           city,
                           null,
                           firstFreeService,
                           secondFreeService,
                           thirdFreeService,
                           pms
                   );
               })
               .toList();
    }

    private LocalTime calculateAverageTime(List<TAT> list, String linkType) {
        List<LocalTime> times = list.stream()
                .filter(t -> linkType.equals(t.getLinkServiceType()))
                .map(TAT::getAverageOfOpenToClose)
                .filter(Objects::nonNull)
                .toList();

        if (times.isEmpty()) return null;

        double avgSeconds = times.stream()
                .mapToDouble(time -> time.toSecondOfDay())
                .average()
                .orElse(0);

        return LocalTime.ofSecondOfDay((long) avgSeconds);
    }


}
