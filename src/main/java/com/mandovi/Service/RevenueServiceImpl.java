package com.mandovi.Service;

import com.mandovi.DTO.RevenueSummaryDTO;
import com.mandovi.Entity.Revenue;
import com.mandovi.Repository.RevenueRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {
    private final RevenueRepository revenueRepository;

    public RevenueServiceImpl(RevenueRepository revenueRepository) {
        this.revenueRepository = revenueRepository;
    }
    private Double getNumericCellValue (Row row, int index){
        if (row == null || row.getCell(index) == null )return  0.0;
        try {
            return row.getCell(index).getNumericCellValue();
        }catch (Exception e){
            return 0.0;
        }
    }
    private Double growth (Double last, Double current){
        if (last == null || last == 0) return 100.0;
        return (current - last) / last;
    }

    @Override
    public void saveRevenueFromExcel(MultipartFile file) {

        try{
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Revenue revenue = new Revenue();

                revenue.setCity(row.getCell(0).getStringCellValue());
                revenue.setMonth(row.getCell(1).getStringCellValue());
                int year = (int) row.getCell(2).getNumericCellValue();
                revenue.setYear(String.valueOf(year));

                //Updating period column by concating columns month & year
                revenue.setPeriod(revenue.getMonth()+"-"+revenue.getYear());

                revenue.setBranchSINo((int)row.getCell(4).getNumericCellValue());
                revenue.setBranch(row.getCell(5).getStringCellValue());


                revenue.setSrLabourLastYear(getNumericCellValue(row, 6));
                revenue.setSrLabourCurrentYear(getNumericCellValue(row, 7));
                revenue.setBrLabourLastYear(getNumericCellValue(row, 9));
                revenue.setBrLabourCurrentYear(getNumericCellValue(row,10));
                revenue.setSrAndBrLabourLastYear(getNumericCellValue(row, 12));
                revenue.setSrAndBrLabourCurrentYear(getNumericCellValue(row, 13));
                revenue.setSrSparesLastYear(getNumericCellValue(row, 15));
                revenue.setSrSparesCurrentYear(getNumericCellValue(row, 16));
                revenue.setBrSparesLastYear(getNumericCellValue(row, 18));
                revenue.setBrSparesCurrentYear(getNumericCellValue(row, 19));
                revenue.setSrAndBrSparesLastYear(getNumericCellValue(row, 21));
                revenue.setSrAndBrSparesCurrentYear(getNumericCellValue(row, 22));
                revenue.setSrAndBrTotalLastYear(getNumericCellValue(row, 24));
                revenue.setSrAndBrTotalCurrentYear(getNumericCellValue(row, 25));

                //Updating ALlGrowth columns by calculating the values from last & current year columns
                revenue.setSrLabourGrowth(growth(revenue.getSrLabourLastYear(), revenue.getSrLabourCurrentYear()));
                revenue.setBrLabourGrowth(growth(revenue.getBrLabourLastYear(), revenue.getBrLabourCurrentYear()));
                revenue.setSrAndBrLabourGrowth(growth(revenue.getSrAndBrLabourLastYear(), revenue.getSrAndBrLabourCurrentYear()));
                revenue.setSrSparesGrowth(growth(revenue.getSrSparesLastYear(), revenue.getSrSparesCurrentYear()));
                revenue.setBrSparesGrowth(growth(revenue.getBrSparesLastYear(), revenue.getBrSparesCurrentYear()));
                revenue.setSrAndBrSparesGrowth(growth(revenue.getSrAndBrSparesLastYear(), revenue.getSrAndBrSparesCurrentYear()));
                revenue.setSrAndBrTotalGrowth(growth(revenue.getSrAndBrTotalLastYear(), revenue.getSrAndBrTotalCurrentYear()));

                //Updating qtr_wise & half_year column by checking month
                switch (revenue.getMonth().trim().toUpperCase()){
                    case "APR", "MAY", "JUN" -> { revenue.setQtrWise("Qtr1"); revenue.setHalfYear("H1"); }
                    case "JUL", "AUG", "SEP" -> { revenue.setQtrWise("Qtr2"); revenue.setHalfYear("H1"); }
                    case "OCT", "NOV", "DEC" -> { revenue.setQtrWise("Qtr3"); revenue.setHalfYear("H2"); }
                    case "JAN", "FEB", "MAR" -> { revenue.setQtrWise("Qtr4"); revenue.setHalfYear("H2"); }
                }


                revenueRepository.save(revenue);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Revenue> getAllRevenue() {
        return revenueRepository.findAll();
    }

    @Override
    public List<Revenue> getRevenueByMonthYear(String month, String year) {
        String formattedMonth = month.trim().toUpperCase();
        return revenueRepository.getRevenueByMonthYear(formattedMonth, year);
    }

    @Override
    public List<RevenueSummaryDTO> getRevenueSummary(String groupBy, List<String> months, String qtrWise, String halfYear) {
        if (groupBy == null || groupBy.isEmpty()) {
            throw new IllegalArgumentException("groupBy Parameter is Required");
        }
        switch (groupBy.toLowerCase()){
            case "city" : return revenueRepository.getRevenueSummaryByCity(months, qtrWise, halfYear);
            case "branch" : return revenueRepository.getRevenueSummaryByBranch(months, qtrWise, halfYear);
            default: throw new IllegalArgumentException("groupBy Parameter is Invalid");
        }
    }

    @Override
    public List<RevenueSummaryDTO> getRevenueSummaryBranchWise(List<String> cities, List<String> months) {
        return revenueRepository.getRevenueSummaryBranchWise(cities, months);
    }
}
