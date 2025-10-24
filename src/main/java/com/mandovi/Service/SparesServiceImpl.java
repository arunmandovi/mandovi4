package com.mandovi.Service;

import com.mandovi.DTO.SparesSummaryDTO;
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

    private Double round2Decimal(Double value){ return Math.round(value*100.0)/100.0; }

    private Double getNumericCellValue (Row row, int index){
        if (row == null || row.getCell(index) == null) return 0.0;
        try {
            return row.getCell(index).getNumericCellValue();
        }catch (Exception e){
            return 0.0;
        }
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
                spares.setSrSparesLastYear(round2Decimal(getNumericCellValue(row,5)));
                spares.setSrSparesCurrentYear(round2Decimal(getNumericCellValue(row, 6)));

                //Updating GROWTH columns by calculating the values from last year & current year
                spares.setSrSparesGrowth(round2Decimal((spares.getSrSparesCurrentYear()- spares.getSrSparesLastYear())/spares.getSrSparesLastYear()));

                spares.setBrSparesLastYear(round2Decimal(getNumericCellValue(row, 8)));
                spares.setBrSparesCurrentYear(round2Decimal(getNumericCellValue(row, 9)));

                //Updating GROWTH columns by calculating the values from last year & current year
                spares.setBrSparesGrowth(round2Decimal((spares.getBrSparesCurrentYear()-spares.getBrSparesLastYear())/spares.getBrSparesLastYear()));

                //Adding srbr columns by adding sr & btr columns for respective years
                spares.setSrBrSparesLastYear(round2Decimal(spares.getSrSparesLastYear()+spares.getBrSparesLastYear()));
                spares.setSrBrSparesCurrentYear(round2Decimal(spares.getSrSparesCurrentYear()+spares.getBrSparesCurrentYear()));

                //Updating GROWTH columns by calculating the values from last year & current year
                spares.setSrBrSparesGrowth(round2Decimal((spares.getSrBrSparesCurrentYear()-spares.getSrBrSparesLastYear())/spares.getSrBrSparesLastYear()));

                spares.setBatteryLastYear(round2Decimal(getNumericCellValue(row, 14)));
                spares.setBatteryCurrentYear(round2Decimal(getNumericCellValue(row, 15)));

                //Updating Battery growth after checking that the last yeat column has any 0 value
                if (spares.getBatteryLastYear() == 0){
                    spares.setBatteryGrowth(100.0);
                }else {
                    spares.setBatteryGrowth((spares.getBatteryCurrentYear()-spares.getBatteryLastYear())/spares.getBatteryLastYear());
                }
                spares.setTyreLastYear(round2Decimal(getNumericCellValue(row, 17)));
                spares.setTyreCurrentYear(round2Decimal(getNumericCellValue(row, 18)));

                //Updating Tyre growth after checking that the last yeat column has any 0 value
                if (spares.getTyreLastYear() == 0){
                    spares.setTyreGrowth(100.0);
                }else {
                    spares.setTyreGrowth(round2Decimal((spares.getTyreCurrentYear()-spares.getTyreLastYear())/spares.getTyreLastYear()));
                }


                //Updating the column Qtr_Wise & Half-Year by comparing the values from colum Month
                String month = spares.getMonth();
                switch (month){
                    case "Apr", "May", "Jun", "APR", "MAY", "JUN" -> { spares.setQtrWise("Qtr1"); spares.setHalfYear("H1"); }
                    case "Jul", "Aug", "Sep", "JUL", "AUG", "SEP" -> { spares.setQtrWise("Qtr2"); spares.setHalfYear("H1"); }
                    case "Oct", "Nov", "Dec", "OCT", "NOV", "DEC" -> { spares.setQtrWise("Qtr3"); spares.setHalfYear("H2"); }
                    case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR" -> { spares.setQtrWise("Qtr4"); spares.setHalfYear("H2"); }
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

    @Override
    public List<Spares> getSparesByMonthYear(String month, String year) {
        String formattedMonth = month.trim().toUpperCase();
        return sparesRepository.getSparedByMonthYear(formattedMonth, year);
    }

    @Override
    public List<SparesSummaryDTO> getSparesSummary(String groupBy, List<String> months, String qtrWise, String halfYear) {
        if (groupBy == null || groupBy.isEmpty()){
            throw new IllegalArgumentException("groupBy Parameter is Required");
        }
        switch (groupBy.toLowerCase()){
            case "city" : return sparesRepository.getSparesSummaryDTOByCity(months, qtrWise, halfYear);
            case "branch" : return sparesRepository.getSparesSummaryDTOByBranch(months, qtrWise, halfYear);
            case "city_branch" : return sparesRepository.getSparesSummaryDTOByCityAndBranch(months, qtrWise, halfYear);
            default: throw new IllegalArgumentException("groupBy Parameter is Invalid");
        }
    }
}
