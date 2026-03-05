package com.mandovi.Service;

import com.mandovi.DTO.SparesSummaryDTO;
import com.mandovi.Entity.Spares;
import com.mandovi.Repository.SparesRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class SparesServiceImpl implements SparesService{
    private final SparesRepository sparesRepository;

    public SparesServiceImpl(SparesRepository sparesRepository) {
        this.sparesRepository = sparesRepository;
    }

    private Double getNumericCellValue (Row row, int index){
        if (row == null || row.getCell(index) == null) return 0.0;
        try {
            return row.getCell(index).getNumericCellValue();
        }catch (Exception e){
            return 0.0;
        }
    }

    private Double growth(Double last, Double current) {
        if (last == null || last == 0) return 100.0;
        return (current - last) / last;
    }


    @Override
    public void saveSparesDataFromExcel(MultipartFile file) throws IOException {
        try{
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RemoteException("No Data from Excel");

            String uploadMonth = firstRow.getCell(2).getStringCellValue().trim();
            Cell yearCell = firstRow.getCell(0);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue()
                    : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);

            sparesRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if(row == null) continue;

                Spares spares = new Spares();

                Cell cell = row.getCell(0);
                int num_year = (cell.getCellType() == CellType.NUMERIC)
                        ? (int) cell.getNumericCellValue(): Integer.parseInt(cell.getStringCellValue());
                spares.setYear(String.valueOf(num_year));

                spares.setCity(row.getCell(1).getStringCellValue());
                spares.setMonth(row.getCell(2).getStringCellValue());
                spares.setBranch(row.getCell(4).getStringCellValue());
                spares.setSrSparesLastYear(getNumericCellValue(row,5));
                spares.setSrSparesCurrentYear(getNumericCellValue(row, 6));
                spares.setSrSparesGrowth(growth(spares.getSrSparesLastYear(), spares.getSrSparesCurrentYear()));

                spares.setBrSparesLastYear(getNumericCellValue(row, 8));
                spares.setBrSparesCurrentYear(getNumericCellValue(row, 9));
                spares.setBrSparesGrowth(growth(spares.getBrSparesLastYear(), spares.getBrSparesCurrentYear()));

                spares.setSrBrSparesLastYear(spares.getSrSparesLastYear()+spares.getBrSparesLastYear());
                spares.setSrBrSparesCurrentYear(spares.getSrSparesCurrentYear()+spares.getBrSparesCurrentYear());
                spares.setSrBrSparesGrowth(growth(spares.getSrBrSparesLastYear(), spares.getSrBrSparesCurrentYear()));

                spares.setBatteryLastYear(getNumericCellValue(row, 14));
                spares.setBatteryCurrentYear(getNumericCellValue(row, 15));
                spares.setBatteryGrowth(growth(spares.getBatteryLastYear(), spares.getBatteryCurrentYear()));

                spares.setTyreLastYear(getNumericCellValue(row, 17));
                spares.setTyreCurrentYear(getNumericCellValue(row, 18));
                spares.setTyreGrowth(growth(spares.getTyreLastYear(), spares.getTyreCurrentYear()));


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
    public List<Spares> getSparesByMonthYear(List<String> months, List<String> years) {
        return sparesRepository.getSparedByMonthYear(months, years);
    }

    @Override
    public List<SparesSummaryDTO> getSparesSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return sparesRepository.getSparesSummaryDTOByCity(months, qtrWise, halfYear);
    }

    @Override
    public List<SparesSummaryDTO> getSparesSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return sparesRepository.getSparesSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

    @Override
    public void deleteSparesAll() {
        sparesRepository.deleteSparesAll();;
    }
}
