package com.mandovi.Service;

import com.mandovi.DTO.ReferenceeSummaryDTO;
import com.mandovi.Entity.Referencee;
import com.mandovi.Repository.ReferenceeRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ReferenceeServiceImpl implements ReferenceeService {
    private final ReferenceeRepository referenceeRepository;
    public ReferenceeServiceImpl(ReferenceeRepository referenceeRepository) {
        this.referenceeRepository = referenceeRepository;
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

                    Referencee reference = new Referencee();

                    reference.setCity(row.getCell(0).getStringCellValue());
                    reference.setBranch(row.getCell(1).getStringCellValue());
                    reference.setGroupDesignation(row.getCell(2).getStringCellValue());
                    reference.setYear(row.getCell(3).getStringCellValue());
                    reference.setMonth(row.getCell(4).getStringCellValue());

                    //Updating column period from concating columns month and year respectively
                    reference.setPeriod(reference.getMonth()+"-"+reference.getYear());

                    reference.setChannel(row.getCell(5).getStringCellValue());


                    reference.setReferencee(row.getCell(6) == null ? 0 : (int) row.getCell(6).getNumericCellValue());
                    reference.setEnquiry(row.getCell(7) == null ? 0 : (int) row.getCell(7).getNumericCellValue());
                    reference.setBooking(row.getCell(8) == null ? 0 : (int) row.getCell(8).getNumericCellValue());
                    reference.setInvoice(row.getCell(9) == null ? 0 : (int) row.getCell(9).getNumericCellValue());


                    //Updating the financial year by checking month & year column
                    String month = reference.getMonth();
                    int year = Integer.parseInt(reference.getYear().trim());
                    switch (month){
                        case "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
                             "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" ->{ reference.setFinancialYear(year+"-"+(year+1)); }
                        case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR"->{
                            reference.setFinancialYear((year-1)+"-"+year);
                        }


                    }
                    //Updating the column Qtr_Wise & Half-year column by checking Column month
                    String monthh = reference.getMonth();
                    switch (monthh){
                        case "Apr", "May", "Jun", "APR", "MAY", "JUN" -> { reference.setQtrWise("Qtr1"); reference.setHalfYear("H1"); }
                        case "Jul", "Aug", "Sep", "JUL", "AUG", "SEP" -> { reference.setQtrWise("Qtr2"); reference.setHalfYear("H1"); }
                        case "Oct", "Nov", "Dec", "OCT", "NOV", "DEC" -> { reference.setQtrWise("Qtr3"); reference.setHalfYear("H2"); }
                        case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR" -> { reference.setQtrWise("Qtr4"); reference.setHalfYear("H2"); }
                    }
                    referenceeRepository.save(reference);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Referencee> getAllReference() {
        return referenceeRepository.findAll();
    }

    @Override
    public List<Referencee> getReferenceeByMonthYear(String month, String year) {
        String formattedMonth = month.substring(0,1).toUpperCase()+month.substring(1).toLowerCase();
        return referenceeRepository.getReferenceeByMonthYear(formattedMonth, year);
    }

    @Override
    public List<ReferenceeSummaryDTO> getReferenceeSUmmary(String groupBy, String month, String qtrWise, String halfYear) {
        if (groupBy == null || groupBy.isEmpty()) {
            throw new IllegalArgumentException("groupBy Paramter is Required");
        }
        switch (groupBy.toLowerCase()){
            case "city" : return referenceeRepository.getReferenceeSummaryByCity(month, qtrWise, halfYear);
            case "branch" : return referenceeRepository.getReferenceeSummaryByBranch(month, qtrWise, halfYear);
            case "city_branch" : return referenceeRepository.getReferenceeSummaryByCityAndBranch(month, qtrWise, halfYear);
            default: throw new IllegalArgumentException("groupBy Parameter is Invalid");
        }
    }
}
