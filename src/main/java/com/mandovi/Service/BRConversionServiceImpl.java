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

                BRConversion br_conversion = new BRConversion();

                br_conversion.setCity(row.getCell(0).getStringCellValue());
                br_conversion.setBranch(row.getCell(1).getStringCellValue());
                br_conversion.setMonth(row.getCell(2).getStringCellValue());

                //Checking the year column has string value ot numeric value
                switch (row.getCell(3).getCellType()){
                    case NUMERIC :
                        int num_year = (int)row.getCell(3).getNumericCellValue();
                        br_conversion.setYear(String.valueOf(num_year));
                        break;
                    case STRING:
                        br_conversion.setYear(row.getCell(3).getStringCellValue());
                        break;
                    default:
                        br_conversion.setYear("");
                        break;
                }

                br_conversion.setChannel(row.getCell(4).getStringCellValue());
                br_conversion.setLabourAmt(row.getCell(5).getNumericCellValue());
                br_conversion.setPartAmount(row.getCell(6).getNumericCellValue());
                br_conversion.setBillAmount(row.getCell(7).getNumericCellValue());

                //Checking if the columns no,br_conversion and grand_total having empty cell then it'll update 0 otherwise update the exact values
                String novalue =  dataFormatter.formatCellValue(row.getCell(8));
                br_conversion.setNo(novalue.isEmpty() ? 0 : Integer.parseInt(novalue));
                String br_conversionvalue = dataFormatter.formatCellValue(row.getCell(9));
                br_conversion.setBrConversion(br_conversionvalue.isEmpty() ? 0 : Integer.parseInt(br_conversionvalue));
                String grand_totalvalue = dataFormatter.formatCellValue(row.getCell(10));
                br_conversion.setGrandTotal(grand_totalvalue.isEmpty() ? 0 : Integer.parseInt(grand_totalvalue));


                //Updating the columns period,Qtr_wise and Half_Year Columns Using the value from month column
                String month = br_conversion.getMonth();
                String period = "1-"+month;
                br_conversion.setPeriod(period);
                switch (month) {
                    case "Apr", "May", "Jun" -> { br_conversion.setQtrWise("Qtr1"); br_conversion.setHalfYear("H1"); }
                    case "Jul", "Aug", "Sep" -> { br_conversion.setQtrWise("Qtr2"); br_conversion.setHalfYear("H1"); }
                    case "Oct", "Nov", "Dec" -> { br_conversion.setQtrWise("Qtr3"); br_conversion.setHalfYear("H2"); }
                    case "Jan", "Feb", "Mar" -> { br_conversion.setQtrWise("Qtr4"); br_conversion.setHalfYear("H2"); }
                }
                brConversionRepository.save(br_conversion);

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
    public List<BRConversionSummaryDTO> getBRConversionSummary(String groupBy, String month, String qtrWise, String halfYear) {
        if (groupBy == null || groupBy.isEmpty()) {
            throw new IllegalArgumentException("groupBy Parameter is Required");
        }
        switch (groupBy.toLowerCase()){
            case "city" : return brConversionRepository.getBRConversionSummaryByCity(month, qtrWise, halfYear);
            case "branch" : return brConversionRepository.getBRConversionSummaryByBranch(month, qtrWise, halfYear);
            case "city_branch" : return brConversionRepository.getBRConversionSummaryByCityAndBranch(month, qtrWise, halfYear);
            default: throw new IllegalArgumentException("groupBy Parameter is Invalid");
        }
    }


}
