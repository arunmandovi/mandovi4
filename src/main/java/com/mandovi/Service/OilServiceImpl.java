package com.mandovi.Service;

import com.mandovi.DTO.OilSummaryDTO;
import com.mandovi.Entity.Oil;
import com.mandovi.Repository.OilRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class OilServiceImpl implements OilService {
    private final OilRepository oilRepository;

    public OilServiceImpl(OilRepository oilRepository) {
        this.oilRepository = oilRepository;
    }

    private Double round2Decimals(Double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    @Transactional
    public void saveOilFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook  workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel") ;

            String uploadMonth = firstRow.getCell(2).getStringCellValue().trim();
            Cell yearCell = firstRow.getCell(3);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);

            oilRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Oil oil = new Oil();

                oil.setCity(row.getCell(0).getStringCellValue());
                oil.setBranch(row.getCell(1).getStringCellValue());
                oil.setMonth(row.getCell(2).getStringCellValue());

                Cell cell = row.getCell(3);
                int num_year = (cell.getCellType() == CellType.NUMERIC)
                        ? (int) cell.getNumericCellValue() : Integer.parseInt(cell.getStringCellValue());
                oil.setYear(String.valueOf(num_year));

                String charMonth  = oil.getMonth();
                Month m = Month.from(dateTimeFormatter.parse(charMonth));
                int monthNum = m.getValue();
                if (monthNum >= 4){
                    oil.setFinancialYear(num_year + "-" + (num_year+1));
                } else {
                    oil.setFinancialYear((num_year - 1) + "-" + num_year );
                }

                oil.setOilType(row.getCell(4).getStringCellValue());
                oil.setNetRetailQty(round2Decimals(row.getCell(5).getNumericCellValue()));
                oil.setNetRetailDDL(round2Decimals(row.getCell(6).getNumericCellValue()));
                oil.setNetRetailSelling(round2Decimals(row.getCell(7).getNumericCellValue()));

                String month = oil.getMonth().trim().toUpperCase();
                switch (month) {
                    case "APR", "MAY", "JUN" ->{ oil.setQtrWise("Qtr1"); oil.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" ->{ oil.setQtrWise("Qtr2"); oil.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" ->{ oil.setQtrWise("Qtr3"); oil.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" ->{ oil.setQtrWise("Qtr4"); oil.setHalfYear("H2");}
                }
                oilRepository.save(oil);
                oilRepository.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Oil> getAllOil() {
        return oilRepository.findAll();
    }

    @Override
    public List<Oil> getOilByMonthYear(List<String> months, List<String> years, List<String> financialYears) {
        return oilRepository.getOilByMonthYear(months, years, financialYears);
    }

    @Override
    public List<OilSummaryDTO> getOilSummary(
            List<String> months, List<String> qtrWise, List<String> halfYear, List<String> financialYears) {
        return oilRepository.getOilSummaryByCity(months, qtrWise, halfYear, financialYears);
    }

    @Override
    public List<OilSummaryDTO> getOilSummaryBranchWise(
            List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear, List<String> financialYears) {
        return oilRepository.getOilSummaryBranchWise(months, cities, qtrWise, halfYear, financialYears);
    }

    @Override
    public void deleteOilALL() {
        oilRepository.deleteOilAll();;
    }

}

