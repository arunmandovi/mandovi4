package com.mandovi.Service;

import com.mandovi.DTO.BatteryTyreSummaryDTO;
import com.mandovi.Entity.BatteryTyre;
import com.mandovi.Repository.BatteryTyreRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BatteryTyreServiceImpl implements BatteryTyreService{
    private final BatteryTyreRepository batteryTyreRepository;

    public BatteryTyreServiceImpl(BatteryTyreRepository batteryTyreRepository) {
        this.batteryTyreRepository = batteryTyreRepository;
    }

    @Override
    public void saveBatteryTyreDataFromExcel(MultipartFile file) throws IOException {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter  formatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;

                BatteryTyre batteryTyre = new BatteryTyre();

                batteryTyre.setCity(row.getCell(0).getStringCellValue());
                batteryTyre.setBranch(row.getCell(1).getStringCellValue());
                batteryTyre.setMonth(row.getCell(2).getStringCellValue());

                //Converting Integer cell's year into String
                switch (row.getCell(3).getCellType()){
                    case NUMERIC -> batteryTyre.setYear(String.valueOf((int) row.getCell(3).getNumericCellValue()));
                    case STRING -> batteryTyre.setYear(row.getCell(3).getStringCellValue());
                    default -> batteryTyre.setYear("UNKNOWN");
                }
                batteryTyre.setOilType(row.getCell(4).getStringCellValue());

                batteryTyre.setSumOfNetRetailQTY((int)   row.getCell(5).getNumericCellValue());
                batteryTyre.setSumOfNetRetailDDL(row.getCell(6).getNumericCellValue());
                batteryTyre.setSumOfNetRetailSelling(row.getCell(7).getNumericCellValue());

                //Updating the column period from Concating columns Month & Year
                batteryTyre.setPeriod(batteryTyre.getMonth()+"-"+batteryTyre.getYear());


                //Updating the column Qtr_Wise & half-Year by comparing the values from month column
                String month = batteryTyre.getMonth();
                switch (month){
                    case "Apr", "May", "Jun" -> { batteryTyre.setQtrWise("Qtr1"); batteryTyre.setHalfYear("H1"); }
                    case "Jul", "Aug", "Sep" -> { batteryTyre.setQtrWise("Qtr2"); batteryTyre.setHalfYear("H1"); }
                    case "Oct", "Nov", "Dec" -> { batteryTyre.setQtrWise("Qtr3"); batteryTyre.setHalfYear("H2"); }
                    case "Jan", "Feb", "Mar" -> { batteryTyre.setQtrWise("Qtr4"); batteryTyre.setHalfYear("H2"); }
                }

                batteryTyreRepository.save(batteryTyre);

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<BatteryTyre> getAllBattery_Tyre() {
        return batteryTyreRepository.findAll();
    }

    @Override
    public List<BatteryTyre> getBattery_TyreByMonthYear(String month, String year) {
        // normalize month input (e.g., make first letter uppercase, rest lowercase)
        String formattedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        return batteryTyreRepository.getBatteryTyreByMonthYear(formattedMonth, year);
    }

    @Override
    public List<BatteryTyreSummaryDTO> getBatteryTyreSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return batteryTyreRepository.getBatteryTyreSummaryByCity(months, qtrWise, halfYear);
    }

    @Override
    public List<BatteryTyreSummaryDTO> getBatteryTyreSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return batteryTyreRepository.getBatteryTyreSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

}
