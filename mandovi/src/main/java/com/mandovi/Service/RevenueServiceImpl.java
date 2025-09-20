package com.mandovi.Service;

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


                revenue.setSr_labour_last_year(round2Decimal(row.getCell(6).getNumericCellValue()));
                revenue.setSr_labour_current_year(round2Decimal(row.getCell(7).getNumericCellValue()));
                revenue.setBr_labour_last_year(round2Decimal(row.getCell(9).getNumericCellValue()));
                revenue.setBr_labour_current_year(round2Decimal(row.getCell(10).getNumericCellValue()));
                revenue.setSr_and_br_labour_last_year(round2Decimal(row.getCell(12).getNumericCellValue()));
                revenue.setSr_and_br_labour_current_year(round2Decimal(row.getCell(13).getNumericCellValue()));
                revenue.setSr_spares_last_year(round2Decimal(row.getCell(15).getNumericCellValue()));
                revenue.setSr_spares_current_year(round2Decimal(row.getCell(16).getNumericCellValue()));
                revenue.setBr_spares_last_year(round2Decimal(row.getCell(18).getNumericCellValue()));
                revenue.setBr_spares_current_year(round2Decimal(row.getCell(19).getNumericCellValue()));
                revenue.setSr_and_br_spares_last_year(round2Decimal(row.getCell(21).getNumericCellValue()));
                revenue.setSr_and_br_spares_current_year(round2Decimal(row.getCell(22).getNumericCellValue()));
                revenue.setSr_and_br_total_last_year(round2Decimal(row.getCell(24).getNumericCellValue()));
                revenue.setSr_and_br_total_current_year(round2Decimal(row.getCell(25).getNumericCellValue()));

                //Updating ALlGrowth columns by calculating the values from last & current year columns
                revenue.setSr_labour_growth(round2Decimal((revenue.getSr_labour_current_year()-revenue.getSr_labour_last_year())/revenue.getSr_labour_last_year()));
                revenue.setBr_labour_growth(round2Decimal((revenue.getBr_labour_current_year()-revenue.getBr_labour_last_year())/revenue.getBr_labour_last_year()));
                revenue.setSr_and_br_labour_growth(round2Decimal((revenue.getSr_and_br_labour_current_year()-revenue.getSr_and_br_labour_last_year())/revenue.getSr_and_br_labour_last_year()));
                revenue.setSr_spares_growth(round2Decimal((revenue.getSr_spares_current_year()-revenue.getSr_spares_last_year())/revenue.getSr_spares_last_year()));
                revenue.setBr_spares_growth(round2Decimal((revenue.getBr_spares_current_year()-revenue.getBr_spares_last_year())/revenue.getBr_spares_last_year()));
                revenue.setSr_and_br_spares_growth(round2Decimal((revenue.getSr_and_br_spares_current_year()-revenue.getSr_and_br_spares_last_year())/revenue.getSr_and_br_spares_last_year()));
                revenue.setSr_and_br_total_growth(round2Decimal((revenue.getSr_and_br_total_current_year()-revenue.getSr_and_br_total_last_year())/revenue.getSr_and_br_total_last_year()));

                //Updating qtr_wise & half_year column by checking month
                switch (revenue.getMonth().trim().toUpperCase()){
                    case "APR", "MAY", "JUN" -> { revenue.setQtr_wise("Qtr1"); revenue.setHalf_year("H1"); }
                    case "JUL", "AUG", "SEP" -> { revenue.setQtr_wise("Qtr2"); revenue.setHalf_year("H1"); }
                    case "OCT", "NOV", "DEC" -> { revenue.setQtr_wise("Qtr3"); revenue.setHalf_year("H2"); }
                    case "JAN", "FEB", "MAR" -> { revenue.setQtr_wise("Qtr4"); revenue.setHalf_year("H2"); }
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
}
