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

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            String uploadMonth = firstRow.getCell(2).getStringCellValue().trim();
            Cell yearCell = firstRow.getCell(3);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);

            batteryTyreRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;

                BatteryTyre batteryTyre = new BatteryTyre();

                batteryTyre.setCity(row.getCell(0).getStringCellValue());
                batteryTyre.setBranch(row.getCell(1).getStringCellValue());
                batteryTyre.setMonth(row.getCell(2).getStringCellValue());

                Cell cell = row.getCell(3);
                int num_year = (cell.getCellType() == CellType.NUMERIC)
                        ? (int) cell.getNumericCellValue() : Integer.parseInt(cell.getStringCellValue());
                batteryTyre.setYear(String.valueOf(num_year));

                batteryTyre.setOilType(row.getCell(4).getStringCellValue());
                batteryTyre.setSumOfNetRetailQTY((int)   row.getCell(5).getNumericCellValue());
                batteryTyre.setSumOfNetRetailDDL(row.getCell(6).getNumericCellValue());
                batteryTyre.setSumOfNetRetailSelling(row.getCell(7).getNumericCellValue());

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
    public List<BatteryTyre> getBattery_TyreByMonthYear(List<String> months, List<String> years) {
        return batteryTyreRepository.getBatteryTyreByMonthYear(months, years);
    }


    @Override
    public List<BatteryTyreSummaryDTO> getBatteryTyreSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return batteryTyreRepository.getBatteryTyreSummaryByCity(months, qtrWise, halfYear);
    }

    @Override
    public List<BatteryTyreSummaryDTO> getBatteryTyreSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return batteryTyreRepository.getBatteryTyreSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

    @Override
    public void deleteBatteryTyreAll() {
        batteryTyreRepository.deleteBatteryTyreAll();
    }

}
