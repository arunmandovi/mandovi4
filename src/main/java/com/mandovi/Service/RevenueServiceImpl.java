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
    private Double round2Decimal(Double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    private Double getNumericCellValue (Row row, int index){
        if (row == null || row.getCell(index) == null )return  0.0;
        try {
            return row.getCell(index).getNumericCellValue();
        }catch (Exception e){
            return 0.0;
        }
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


                revenue.setSrLabourLastYear(round2Decimal(getNumericCellValue(row, 6)));
                revenue.setSrLabourCurrentYear(round2Decimal(getNumericCellValue(row, 7)));
                revenue.setBrLabourLastYear(round2Decimal(getNumericCellValue(row, 9)));
                revenue.setBrLabourCurrentYear(round2Decimal(getNumericCellValue(row,10)));
                revenue.setSrAndBrLabourLastYear(round2Decimal(getNumericCellValue(row, 12)));
                revenue.setSrAndBrLabourCurrentYear(round2Decimal(getNumericCellValue(row, 13)));
                revenue.setSrSparesLastYear(round2Decimal(getNumericCellValue(row, 15)));
                revenue.setSrSparesCurrentYear(round2Decimal(getNumericCellValue(row, 16)));
                revenue.setBrSparesLastYear(round2Decimal(getNumericCellValue(row, 18)));
                revenue.setBrSparesCurrentYear(round2Decimal(getNumericCellValue(row, 19)));
                revenue.setSrAndBrSparesLastYear(round2Decimal(getNumericCellValue(row, 21)));
                revenue.setSrAndBrSparesCurrentYear(round2Decimal(getNumericCellValue(row, 22)));
                revenue.setSrAndBrTotalLastYear(round2Decimal(getNumericCellValue(row, 24)));
                revenue.setSrAndBrTotalCurrentYear(round2Decimal(getNumericCellValue(row, 25)));

                //Updating ALlGrowth columns by calculating the values from last & current year columns
                revenue.setSrLabourGrowth(round2Decimal((revenue.getSrLabourCurrentYear()-revenue.getSrLabourLastYear())/revenue.getSrLabourLastYear()));
                revenue.setBrLabourGrowth(round2Decimal((revenue.getBrLabourCurrentYear()-revenue.getBrLabourLastYear())/revenue.getBrLabourLastYear()));
                revenue.setSrAndBrLabourGrowth(round2Decimal((revenue.getSrAndBrLabourCurrentYear()-revenue.getSrAndBrLabourLastYear())/revenue.getSrAndBrLabourLastYear()));
                revenue.setSrSparesGrowth(round2Decimal((revenue.getSrSparesCurrentYear()-revenue.getSrSparesLastYear())/revenue.getSrSparesLastYear()));
                revenue.setBrSparesGrowth(round2Decimal((revenue.getBrSparesCurrentYear()-revenue.getBrSparesLastYear())/revenue.getBrSparesLastYear()));
                revenue.setSrAndBrSparesGrowth(round2Decimal((revenue.getSrAndBrSparesCurrentYear()-revenue.getSrAndBrSparesLastYear())/revenue.getSrAndBrSparesLastYear()));
                revenue.setSrAndBrTotalGrowth(round2Decimal((revenue.getSrAndBrTotalCurrentYear()-revenue.getSrAndBrTotalLastYear())/revenue.getSrAndBrTotalLastYear()));

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
    public List<RevenueSummaryDTO> getRevenueSummary(String groupBy, String month, String qtrWise, String halfYear) {
        if (groupBy == null || groupBy.isEmpty()) {
            throw new IllegalArgumentException("groupBy Parameter is Required");
        }
        switch (groupBy.toLowerCase()){
            case "city" : return revenueRepository.getRevenueSummaryByCity(month, qtrWise, halfYear);
            case "branch" : return revenueRepository.getRevenueSummaryByBranch(month, qtrWise, halfYear);
            case "city_branch" : return revenueRepository.getRevenueSummaryByCityAndBranch(month, qtrWise, halfYear);
            default: throw new IllegalArgumentException("groupBy Parameter is Invalid");
        }
    }
}
