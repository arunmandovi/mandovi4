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
            Sheet sheet = workbook.getSheetAt(0);

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            String uploadMonth = firstRow.getCell(2).getStringCellValue().trim();
            Cell yearCell = firstRow.getCell(0);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);

            revenueRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Revenue revenue = new Revenue();

                Cell cell = row.getCell(0);
                int num_year = (cell.getCellType() == CellType.NUMERIC)
                        ? (int) cell.getNumericCellValue() : Integer.valueOf(cell.getStringCellValue());
                revenue.setYear(String.valueOf(num_year));

                revenue.setCity(row.getCell(1).getStringCellValue());
                revenue.setMonth(row.getCell(2).getStringCellValue());
                revenue.setBranchSINo((int)row.getCell(4).getNumericCellValue());
                revenue.setBranch(row.getCell(5).getStringCellValue());


                revenue.setSrLabourLastYear(getNumericCellValue(row, 6));
                revenue.setSrLabourCurrentYear(getNumericCellValue(row, 7));
                revenue.setBrLabourLastYear(getNumericCellValue(row, 9));
                revenue.setBrLabourCurrentYear(getNumericCellValue(row,10));
                revenue.setSrAndBrLabourLastYear(revenue.getSrLabourLastYear()+revenue.getBrLabourLastYear());
                revenue.setSrAndBrLabourCurrentYear(revenue.getSrLabourCurrentYear()+revenue.getBrLabourCurrentYear());
                revenue.setSrSparesLastYear(getNumericCellValue(row, 15));
                revenue.setSrSparesCurrentYear(getNumericCellValue(row, 16));
                revenue.setBrSparesLastYear(getNumericCellValue(row, 18));
                revenue.setBrSparesCurrentYear(getNumericCellValue(row, 19));
                revenue.setSrAndBrSparesLastYear(revenue.getSrSparesLastYear()+revenue.getBrSparesLastYear());
                revenue.setSrAndBrSparesCurrentYear(revenue.getSrSparesCurrentYear()+revenue.getBrSparesCurrentYear());
                revenue.setSrAndBrTotalLastYear(revenue.getSrLabourLastYear()+revenue.getBrLabourLastYear()+revenue.getSrSparesLastYear()+revenue.getBrSparesLastYear());
                revenue.setSrAndBrTotalCurrentYear(revenue.getSrLabourCurrentYear()+revenue.getBrLabourCurrentYear()+revenue.getSrSparesCurrentYear()+revenue.getBrSparesCurrentYear());

                revenue.setSrLabourGrowth(growth(revenue.getSrLabourLastYear(), revenue.getSrLabourCurrentYear()));
                revenue.setBrLabourGrowth(growth(revenue.getBrLabourLastYear(), revenue.getBrLabourCurrentYear()));
                revenue.setSrAndBrLabourGrowth(growth(revenue.getSrAndBrLabourLastYear(), revenue.getSrAndBrLabourCurrentYear()));
                revenue.setSrSparesGrowth(growth(revenue.getSrSparesLastYear(), revenue.getSrSparesCurrentYear()));
                revenue.setBrSparesGrowth(growth(revenue.getBrSparesLastYear(), revenue.getBrSparesCurrentYear()));
                revenue.setSrAndBrSparesGrowth(growth(revenue.getSrAndBrSparesLastYear(), revenue.getSrAndBrSparesCurrentYear()));
                revenue.setSrAndBrTotalGrowth(growth(revenue.getSrAndBrTotalLastYear(), revenue.getSrAndBrTotalCurrentYear()));

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
    public List<Revenue> getRevenueByMonthYear(List<String> months, List<String> years) {
        return revenueRepository.getRevenueByMonthYear(months, years);
    }

    @Override
    public List<RevenueSummaryDTO> getRevenueSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return revenueRepository.getRevenueSummaryByCity(months, qtrWise, halfYear);
    }

    @Override
    public List<RevenueSummaryDTO> getRevenueSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return revenueRepository.getRevenueSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

    @Override
    public void deleteRevenueAll() {
        revenueRepository.deleteRevenueAll();
    }
}
