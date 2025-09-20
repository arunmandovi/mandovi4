package com.mandovi.Service;

import com.mandovi.Entity.Spares;
import com.mandovi.Repository.SparesRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class SparesServiceImpl implements SparesService{
    private final SparesRepository sparesRepository;

    public SparesServiceImpl(SparesRepository sparesRepository) {
        this.sparesRepository = sparesRepository;
    }

    @Override
    public void saveSparesDataFromExcel(MultipartFile file) throws IOException {
        try{
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if(row == null) continue;

                Spares spares = new Spares();

                spares.setCity(row.getCell(0).getStringCellValue());
                spares.setMonth(row.getCell(1).getStringCellValue());

                //Converting Integer cell's year Column's value into string
                int num_year = (int)  row.getCell(2).getNumericCellValue();
                String year = String.valueOf(num_year);
                spares.setYear(year);

                //Updating the column period by concating the columns month & year
                spares.setPeriod(spares.getMonth()+"-"+spares.getYear());

                spares.setBranch(row.getCell(4).getStringCellValue());
                spares.setSr_spares_last_year(row.getCell(5).getNumericCellValue());
                spares.setSr_spares_current_year(row.getCell(6).getNumericCellValue());

                //Updating GROWTH columns by calculating the values from last year & current year
                spares.setSr_spares_growth((spares.getSr_spares_current_year()- spares.getSr_spares_last_year())/spares.getSr_spares_last_year());

                spares.setBr_spares_last_year(row.getCell(8).getNumericCellValue());
                spares.setBr_spares_current_year(row.getCell(9).getNumericCellValue());

                //Updating GROWTH columns by calculating the values from last year & current year
                spares.setBr_spares_growth((spares.getBr_spares_current_year()-spares.getBr_spares_last_year())/spares.getBr_spares_last_year());

                //Adding srbr columns by adding sr & btr columns for respective years
                spares.setSrbr_spares_last_year(spares.getSr_spares_last_year()+spares.getBr_spares_last_year());
                spares.setSrbr_spares_current_year(spares.getSr_spares_current_year()+spares.getBr_spares_current_year());

                //Updating GROWTH columns by calculating the values from last year & current year
                spares.setSrbr_spares_growth((spares.getSrbr_spares_current_year()-spares.getSrbr_spares_last_year())/spares.getSrbr_spares_last_year());

                spares.setBattery_last_year(row.getCell(14).getNumericCellValue());
                spares.setBattery_current_year(row.getCell(15).getNumericCellValue());

                //Updating Battery growth after checking that the last yeat column has any 0 value
                if (spares.getBattery_last_year() == 0){
                    spares.setBattery_growth(100.0);
                }else {
                    spares.setBattery_growth((spares.getBattery_current_year()-spares.getBattery_last_year())/spares.getBattery_last_year());
                }
                spares.setTyre_last_year(row.getCell(17).getNumericCellValue());
                spares.setTyre_current_year(row.getCell(18).getNumericCellValue());

                //Updating Tyre growth after checking that the last yeat column has any 0 value
                if (spares.getTyre_last_year() == 0){
                    spares.setTyre_growth(100.0);
                }else {
                    spares.setTyre_growth((spares.getTyre_current_year()-spares.getTyre_last_year())/spares.getTyre_last_year());
                }


                //Updating the column Qtr_Wise & Half-Year by comparing the values from colum Month
                String month = spares.getMonth();
                switch (month){
                    case "Apr", "May", "Jun", "APR", "MAY", "JUN" -> { spares.setQtr_wise("Qtr1"); spares.setHalf_year("H1"); }
                    case "Jul", "Aug", "Sep", "JUL", "AUG", "SEP" -> { spares.setQtr_wise("Qtr2"); spares.setHalf_year("H1"); }
                    case "Oct", "Nov", "Dec", "OCT", "NOV", "DEC" -> { spares.setQtr_wise("Qtr3"); spares.setHalf_year("H2"); }
                    case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR" -> { spares.setQtr_wise("Qtr4"); spares.setHalf_year("H2"); }
                }

                sparesRepository.save(spares);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Spares> getAllSpares() {
        return sparesRepository.findAll();
    }
}
