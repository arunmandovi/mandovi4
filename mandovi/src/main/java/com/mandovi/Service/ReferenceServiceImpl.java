package com.mandovi.Service;

import com.mandovi.Entity.Reference;
import com.mandovi.Repository.ReferenceRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ReferenceServiceImpl implements ReferenceService {
    private final ReferenceRepository referenceRepository;
    public ReferenceServiceImpl(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }
    @Override
    public void saveReferenceFromExcel(MultipartFile file) {
        try {
            {
                InputStream inputStream = file.getInputStream();
                Workbook workbook = WorkbookFactory.create(inputStream);
                DataFormatter dataFormatter = new DataFormatter();
                Sheet sheet = workbook.getSheetAt(0);

                for(int i = 1; i < sheet.getLastRowNum(); i++){
                    Row row = sheet.getRow(i);
                    if(row == null)continue;

                    Reference reference = new Reference();

                    reference.setCity(row.getCell(0).getStringCellValue());
                    reference.setBranch(row.getCell(1).getStringCellValue());
                    reference.setGroup_designation(row.getCell(2).getStringCellValue());
                    reference.setYear(row.getCell(3).getStringCellValue());
                    reference.setMonth(row.getCell(4).getStringCellValue());

                    //Updating column period from concating columns month and year respectively
                    reference.setPeriod(reference.getMonth()+"-"+reference.getYear());

                    reference.setChannel(row.getCell(5).getStringCellValue());


                    reference.setReference(row.getCell(6) == null ? 0 : (int) row.getCell(6).getNumericCellValue());
                    reference.setEnquiry(row.getCell(7) == null ? 0 : (int) row.getCell(7).getNumericCellValue());
                    reference.setBooking(row.getCell(8) == null ? 0 : (int) row.getCell(8).getNumericCellValue());
                    reference.setInvoice(row.getCell(9) == null ? 0 : (int) row.getCell(9).getNumericCellValue());


                    //Updating the financial year by checking month & year column
                    String month = reference.getMonth();
                    int year = Integer.parseInt(reference.getYear().trim());
                    switch (month){
                        case "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
                             "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" ->{ reference.setFinancial_year(year+"-"+(year+1)); }
                        case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR"->{
                            reference.setFinancial_year((year-1)+"-"+year);
                        }


                    }
                    //Updating the column Qtr_Wise & Half-year column by checking Column month
                    String monthh = reference.getMonth();
                    switch (monthh){
                        case "Apr", "May", "Jun", "APR", "MAY", "JUN" -> { reference.setQtr_wise("Qtr1"); reference.setHalf_year("H1"); }
                        case "Jul", "Aug", "Sep", "JUL", "AUG", "SEP" -> { reference.setQtr_wise("Qtr2"); reference.setHalf_year("H1"); }
                        case "Oct", "Nov", "Dec", "OCT", "NOV", "DEC" -> { reference.setQtr_wise("Qtr3"); reference.setHalf_year("H2"); }
                        case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR" -> { reference.setQtr_wise("Qtr4"); reference.setHalf_year("H2"); }
                    }
                    referenceRepository.save(reference);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
