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

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            String uploadMonth = firstRow.getCell(2).getStringCellValue().trim();
            Cell yearCell = firstRow.getCell(3);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);

            tatRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;

                TAT tat = new TAT();

                tat.setCity(row.getCell(0).getStringCellValue());
                tat.setBranch(row.getCell(1).getStringCellValue());
                tat.setMonth(row.getCell(2).getStringCellValue());

                Cell year_cell = row.getCell(3);
                int num_year = (year_cell.getCellType() == CellType.NUMERIC)
                        ? (int) year_cell.getNumericCellValue() : Integer.parseInt(year_cell.getStringCellValue());
                tat.setYear(String.valueOf(num_year));

                tat.setLinkServiceType(row.getCell(4).getStringCellValue());

                Cell cell = row.getCell(5);
                DataFormatter formatter = new DataFormatter();
                String raw = formatter.formatCellValue(cell);

                if(raw != null && !raw.isBlank()){
                    String timeFormatted = raw.replace(".", ":");
                    tat.setAverageOfOpenToClose(timeFormatted);
                }

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
    public List<TAT> getTATByMonthYear(List<String> months, List<String> years) {
        return tatRepository.getTATByMonthYear(months, years);
    }

    @Override
    public List<TATSummaryDTO> getTATSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        List<TAT> allTAT = tatRepository.findAll();

        // Apply Filters
        List<TAT> filtered = allTAT.stream()
                .filter(tat -> months == null || months.stream().anyMatch(m -> m.equalsIgnoreCase(tat.getMonth())))
                .filter(tat -> qtrWise == null || qtrWise.stream().anyMatch(q -> q.equalsIgnoreCase(tat.getQtrWise())))
                .filter(tat -> halfYear == null || halfYear.stream().anyMatch(h -> h.equalsIgnoreCase(tat.getHalfYear())))
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
        if (timeStr == null || timeStr.isBlank()) return 0;

        String[] parts = timeStr.split(":");

        long hours = 0, minutes = 0, seconds = 0;

        try {
            if (parts.length >= 1) hours = Long.parseLong(parts[0]);
            if (parts.length >= 2) minutes = Long.parseLong(parts[1]);
            if (parts.length >= 3) seconds = Long.parseLong(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("⚠ Invalid time format: " + timeStr);
        }

        return hours * 3600 + minutes * 60 + seconds;
    }

    // Convert seconds -> "HH:mm:ss"
    private String secondsToTimeString(long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public List<TATSummaryDTO> getTATSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        List<TAT> allTAT = tatRepository.findAll();

        // Apply Filters
        List<TAT> filtered = allTAT.stream()
                .filter(tat -> months == null || months.stream().anyMatch(m -> m.equalsIgnoreCase(tat.getMonth())))
                .filter(tat -> cities == null || cities.stream().anyMatch(m -> m.equalsIgnoreCase(tat.getCity())))
                .filter(tat -> qtrWise == null || qtrWise.stream().anyMatch(m -> m.equalsIgnoreCase(tat.getQtrWise())))
                .filter(tat -> halfYear == null || halfYear.stream().anyMatch(m -> m.equalsIgnoreCase(tat.getHalfYear())))
                .toList();

        // Group by CITY then BRANCH
        return filtered.stream()
                .collect(Collectors.groupingBy(TAT::getCity, Collectors.groupingBy(TAT::getBranch)))
                .entrySet().stream() // city level
                .flatMap(cityEntry -> {
                    String city = cityEntry.getKey();
                    return cityEntry.getValue().entrySet().stream().map(branchEntry -> {
                        String branch = branchEntry.getKey();
                        List<TAT> groupList = branchEntry.getValue();

                        String firstFreeService = calculateAverageTime(groupList, "FR1");
                        String secondFreeService = calculateAverageTime(groupList, "FR2");
                        String thirdFreeService = calculateAverageTime(groupList, "FR3");
                        String pms = calculateAverageTime(groupList, "PMS");

                        return new TATSummaryDTO(
                                city,
                                branch,
                                firstFreeService,
                                secondFreeService,
                                thirdFreeService,
                                pms
                        );
                    });
                })
                .toList();
    }

    @Override
    public void deleteTATAll() {
        tatRepository.deleteTATAll();
    }

}
