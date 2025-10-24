package com.mandovi.Service;

import com.mandovi.DTO.TATSummaryDTO;
import com.mandovi.Entity.TAT;
import com.mandovi.Repository.TATRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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

                Cell cell = row.getCell(5);
                DataFormatter formatter = new DataFormatter();
                String raw = formatter.formatCellValue(cell); // ALWAYS STRING

                if(raw != null && !raw.isBlank()){
                    String timeFormatted = raw.replace(".", ":"); // 100.05.02 -> 100:05:02
                    tat.setAverageOfOpenToClose(timeFormatted);
                }


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
    public List<TATSummaryDTO> getTATSummary(String groupBy, List<String> months, String qtrWise, String halfYear) {
        List<TAT> allTAT = tatRepository.findAll();

        // Apply Filters
        List<TAT> filtered = allTAT.stream()
                .filter(tat -> months == null || months.stream().anyMatch(m -> m.equalsIgnoreCase(tat.getMonth())))
                .filter(tat -> qtrWise == null || qtrWise.equalsIgnoreCase(tat.getQtrWise()))
                .filter(tat -> halfYear == null || halfYear.equalsIgnoreCase(tat.getHalfYear()))
                .toList();

        // Group by city
        return filtered.stream()
                .collect(Collectors.groupingBy(TAT::getCity))
                .entrySet().stream()
                .map(entry -> {
                    String city = entry.getKey();
                    List<TAT> groupList = entry.getValue();

                    String firstFreeService = calculateAverageTime(groupList, "FR1");
                    String secondFreeService = calculateAverageTime(groupList, "FR2");
                    String thirdFreeService = calculateAverageTime(groupList, "FR3");
                    String pms = calculateAverageTime(groupList, "PMS");

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

    private String calculateAverageTime(List<TAT> list, String linkType) {
        List<String> times = list.stream()
                .filter(t -> linkType.equals(t.getLinkServiceType()))
                .map(TAT::getAverageOfOpenToClose)
                .filter(Objects::nonNull)
                .toList();

        if (times.isEmpty()) return null;

        // Convert all times to total seconds
        long totalSeconds = times.stream()
                .mapToLong(this::timeStringToSeconds)
                .sum();

        long avgSeconds = totalSeconds / times.size();

        return secondsToTimeString(avgSeconds);
    }

    // Convert "100:05:02" -> seconds
    private long timeStringToSeconds(String timeStr) {
        String[] parts = timeStr.split(":");
        long hours = Long.parseLong(parts[0]);
        long minutes = Long.parseLong(parts[1]);
        long seconds = Long.parseLong(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    // Convert seconds -> "HH:mm:ss"
    private String secondsToTimeString(long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }
}
