package com.mandovi.Service;

import com.mandovi.DTO.OilSummaryDTO;
import com.mandovi.Entity.Oil;
import com.mandovi.Repository.OilRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel") ;

            String uploadMonth = firstRow.getCell(2).getStringCellValue().trim();
            oilRepository.deleteByMonth(uploadMonth);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Oil oil = new Oil();

                oil.setCity(row.getCell(0).getStringCellValue());
                oil.setBranch(row.getCell(1).getStringCellValue());
                oil.setMonth(row.getCell(2).getStringCellValue());

                //Converting Integer year into String
                int num_year = (int) row.getCell(3).getNumericCellValue();
                String year = String.valueOf(num_year);
                oil.setYear(year);

                oil.setOilType(row.getCell(4).getStringCellValue());
                oil.setNetRetailQty(round2Decimals(row.getCell(5).getNumericCellValue()));
                oil.setNetRetailDDL(round2Decimals(row.getCell(6).getNumericCellValue()));
                oil.setNetRetailSelling(round2Decimals(row.getCell(7).getNumericCellValue()));

                //Updating period based on month & year
                oil.setPeriod(oil.getMonth()+"-"+oil.getYear());

                //Updating qtr_wise & half_year based on month
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
    public List<Oil> getOilByMonthYear(List<String> months, List<String> years) {
        return oilRepository.getOilByMonthYear(months, years);
    }

    @Override
    public List<OilSummaryDTO> getOilSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return oilRepository.getOilSummaryByCity(months, qtrWise, halfYear);
    }

    @Override
    public List<OilSummaryDTO> getOilSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return oilRepository.getOilSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

    @Override
    public void deleteOilALL() {
        oilRepository.deleteOilAll();;
    }

}

