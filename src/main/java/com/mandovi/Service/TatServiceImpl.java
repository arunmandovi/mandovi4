package com.mandovi.Service;

import com.mandovi.Entity.Tat;
import com.mandovi.Repository.TatRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class TatServiceImpl implements TatService {
    private final TatRepository tatRepository;

    public TatServiceImpl(TatRepository tatRepository) {
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

                Tat tat = new Tat();

                tat.setCity(row.getCell(0).getStringCellValue());
                tat.setBranch(row.getCell(1).getStringCellValue());
                tat.setMonth(row.getCell(2).getStringCellValue());
                //Converting Integer cell's  year into String
                int num_year = (int) row.getCell(3).getNumericCellValue();
                String year = String.valueOf(num_year);
                tat.setYear(year);
                tat.setLink_service_type(row.getCell(4).getStringCellValue());
                tat.setAverage_of_open_to_close(round2Decimal(row.getCell(5).getNumericCellValue()));

                //Updating the column period by concating the columns month & year and "-" in between
                String period = tat.getMonth()+"-"+tat.getYear();
                tat.setPeriod(period);

                //Updating the column Qtr_Wise & Half-Year by comparing the values from colum Month
                String month = tat.getMonth().trim().toUpperCase();
                switch (month) {
                    case "APR", "MAY", "JUN" -> { tat.setQtr_wise("Qtr1"); tat.setHalf_year("H1"); }
                    case "JUL", "AUG", "SEP" -> { tat.setQtr_wise("Qtr2"); tat.setHalf_year("H1"); }
                    case "OCT", "NOV", "DEC" -> { tat.setQtr_wise("Qtr3"); tat.setHalf_year("H2"); }
                    case "JAN", "FEB", "MAR" -> { tat.setQtr_wise("Qtr4"); tat.setHalf_year("H2"); }
                }
                tatRepository.save(tat);

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Tat> getAllTat() {
        return tatRepository.findAll();
    }
}
