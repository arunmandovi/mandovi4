package com.mandovi.Service;

import com.mandovi.DTO.BRConversionSummaryDTO;
import com.mandovi.Entity.BRConversion;
import com.mandovi.Repository.BRConversionRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;

                BRConversion brConversion = new BRConversion();

                brConversion.setCity(row.getCell(0).getStringCellValue());
                brConversion.setBranch(row.getCell(1).getStringCellValue());
                brConversion.setMonth(row.getCell(2).getStringCellValue());

                //Checking the year column has string value ot numeric value
                switch (row.getCell(3).getCellType()){
                    case NUMERIC :
                        int num_year = (int)row.getCell(3).getNumericCellValue();
                        brConversion.setYear(String.valueOf(num_year));
                        break;
                    case STRING:
                        brConversion.setYear(row.getCell(3).getStringCellValue());
                        break;
                    default:
                        brConversion.setYear("");
                        break;
                }

                brConversion.setChannel(row.getCell(4).getStringCellValue());
                brConversion.setLabourAmt(row.getCell(5).getNumericCellValue());
                brConversion.setPartAmount(row.getCell(6).getNumericCellValue());
                brConversion.setBillAmount(row.getCell(7).getNumericCellValue());

                //Checking if the columns no,br_conversion and grand_total having empty cell then it'll update 0 otherwise update the exact values
                brConversion.setNo(getIntCellValue(row, 8, dataFormatter));
                brConversion.setBrConversion(getIntCellValue(row, 9, dataFormatter));

                brConversion.setGrandTotal(brConversion.getNo() + brConversion.getBrConversion());


                //Updating the columns period,Qtr_wise and Half_Year Columns Using the value from month column
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
    public List<BRConversion> getBRConversionByMonthYear(String month, String year) {
        String formattedMonth = month.substring(0, 1).toUpperCase() +month.substring(1).toLowerCase();
        return brConversionRepository.getBR_ConversionByMonthYear(formattedMonth, year);
    }

    @Override
    public List<BRConversionSummaryDTO> getBRConversionSummary(String groupBy, List<String> months, String qtrWise, String halfYear) {
        if (groupBy == null || groupBy.isEmpty()) {
            throw new IllegalArgumentException("groupBy Parameter is Required");
        }
        switch (groupBy.toLowerCase()){
            case "city" : return brConversionRepository.getBRConversionSummaryByCity(months, qtrWise, halfYear);
            case "branch" : return brConversionRepository.getBRConversionSummaryByBranch(months, qtrWise, halfYear);
            case "city_branch" : return brConversionRepository.getBRConversionSummaryByCityAndBranch(months, qtrWise, halfYear);
            default: throw new IllegalArgumentException("groupBy Parameter is Invalid");
        }
    }


}
