package com.mandovi.Service;

import com.mandovi.Entity.BR_Conversion;
import com.mandovi.Repository.BR_ConversionRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class BR_ConversionServiceImpl implements BR_ConversionService{

    private final BR_ConversionRepository br_conversionRepository;
    public BR_ConversionServiceImpl(BR_ConversionRepository br_conversionRepository) {
        this.br_conversionRepository = br_conversionRepository;
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

                BR_Conversion br_conversion = new BR_Conversion();

                br_conversion.setCity(row.getCell(0).getStringCellValue());
                br_conversion.setBranch(row.getCell(1).getStringCellValue());
                br_conversion.setMonth(row.getCell(2).getStringCellValue());
                br_conversion.setChannel(row.getCell(3).getStringCellValue());
                br_conversion.setLabour_amt(row.getCell(4).getNumericCellValue());
                br_conversion.setPart_amount(row.getCell(5).getNumericCellValue());
                br_conversion.setBill_amount(row.getCell(6).getNumericCellValue());

                //Checking if the columns no,br_conversion and grand_total having empty cell then it'll update 0 otherwise update the exact values
                String novalue =  dataFormatter.formatCellValue(row.getCell(7));
                br_conversion.setNo(novalue.isEmpty() ? 0 : Integer.parseInt(novalue));
                String br_conversionvalue = dataFormatter.formatCellValue(row.getCell(8));
                br_conversion.setBr_conversion(br_conversionvalue.isEmpty() ? 0 : Integer.parseInt(br_conversionvalue));
                String grand_totalvalue = dataFormatter.formatCellValue(row.getCell(9));
                br_conversion.setGrand_total(grand_totalvalue.isEmpty() ? 0 : Integer.parseInt(grand_totalvalue));


                //Updating the columns period,Qtr_wise and Half_Year Columns Using the value from month column
                String month = br_conversion.getMonth();
                String period = "1-"+month;
                br_conversion.setPeriod(period);
                switch (month) {
                    case "Apr", "May", "Jun" -> { br_conversion.setQtr_wise("Qtr1"); br_conversion.setHalf_year("H1"); }
                    case "Jul", "Aug", "Sep" -> { br_conversion.setQtr_wise("Qtr2"); br_conversion.setHalf_year("H1"); }
                    case "Oct", "Nov", "Dec" -> { br_conversion.setQtr_wise("Qtr3"); br_conversion.setHalf_year("H2"); }
                    case "Jan", "Feb", "Mar" -> { br_conversion.setQtr_wise("Qtr4"); br_conversion.setHalf_year("H2"); }
                }
                br_conversionRepository.save(br_conversion);

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
