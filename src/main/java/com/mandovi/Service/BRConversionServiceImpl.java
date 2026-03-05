package com.mandovi.Service;

import com.mandovi.DTO.BRConversionSummaryDTO;
import com.mandovi.Entity.BRConversion;
import com.mandovi.Repository.BRConversionRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BRConversionServiceImpl implements BRConversionService {

    private final BRConversionRepository brConversionRepository;
    public BRConversionServiceImpl(BRConversionRepository brConversionRepository) {
        this.brConversionRepository = brConversionRepository;
    }

    private int getIntCellValue (Row row, int cellIndex, DataFormatter dataFormatter){
        Cell cell = row.getCell(cellIndex);
        if (cell == null) return 0;

        switch (cell.getCellType()){
            case STRING :
                String value = dataFormatter.formatCellValue(cell);
                return value.isEmpty() ? 0 : Integer.parseInt(value);
            case NUMERIC: return (int) row.getCell(cellIndex).getNumericCellValue();
            default: return 0;
        }
    }
    @Override
    public void saveBR_ConversionDataFromExcel(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            Row firstDataRow = sheet.getRow(1);
            if (firstDataRow == null)
                throw new RuntimeException("No data found in Excel");

            String uploadMonth = firstDataRow.getCell(2).getStringCellValue().trim();
            Cell yearCell = firstDataRow.getCell(3);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) firstDataRow.getCell(3).getNumericCellValue()
                    : Integer.parseInt(yearCell.getStringCellValue().trim());
            String uploadYear = String.valueOf(numYear);

            brConversionRepository.deleteByMonthYear(uploadMonth, uploadYear);


            System.out.println("Deleted existing records for: " + uploadMonth );

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;

                BRConversion brConversion = new BRConversion();

                brConversion.setCity(row.getCell(0).getStringCellValue());
                brConversion.setBranch(row.getCell(1).getStringCellValue());
                brConversion.setMonth(row.getCell(2).getStringCellValue());

                Cell cell = row.getCell(3);
                int numberYear = (cell.getCellType() == CellType.NUMERIC)
                        ? (int) cell.getNumericCellValue() : Integer.parseInt(cell.getStringCellValue());
                brConversion.setYear(String.valueOf(numberYear));

                brConversion.setChannel(row.getCell(4).getStringCellValue());
                brConversion.setLabourAmt(row.getCell(5).getNumericCellValue());
                brConversion.setPartAmount(row.getCell(6).getNumericCellValue());
                brConversion.setBillAmount(row.getCell(7).getNumericCellValue());

                brConversion.setNo(getIntCellValue(row, 8, dataFormatter));
                brConversion.setBrConversion(getIntCellValue(row, 9, dataFormatter));

                brConversion.setGrandTotal(brConversion.getNo() + brConversion.getBrConversion());


                String month = brConversion.getMonth();
                String period = "1-"+month;
                brConversion.setPeriod(period);
                switch (month) {
                    case "Apr", "May", "Jun" -> { brConversion.setQtrWise("Qtr1"); brConversion.setHalfYear("H1"); }
                    case "Jul", "Aug", "Sep" -> { brConversion.setQtrWise("Qtr2"); brConversion.setHalfYear("H1"); }
                    case "Oct", "Nov", "Dec" -> { brConversion.setQtrWise("Qtr3"); brConversion.setHalfYear("H2"); }
                    case "Jan", "Feb", "Mar" -> { brConversion.setQtrWise("Qtr4"); brConversion.setHalfYear("H2"); }
                }
                brConversionRepository.save(brConversion);

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<BRConversion> getAllBRConversion() {
        return brConversionRepository.findAll();
    }

    @Override
    public List<BRConversion> getBRConversionByMonthYear(List<String> months, List<String> years) {
        return brConversionRepository.getBR_ConversionByMonthYear(months, years);
    }

    @Override
    public List<BRConversionSummaryDTO> getBRConversionSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return brConversionRepository.getBRConversionSummaryByCity(months, qtrWise, halfYear);
    }

    @Override
    public List<BRConversionSummaryDTO> getBRConversionSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return brConversionRepository.getBRConversionSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

    @Override
    public void deleteBRConversionAll() {
        brConversionRepository.deleteBRConversionAll();
    }


}
