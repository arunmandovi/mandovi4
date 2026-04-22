package com.mandovi.Service;

import com.mandovi.DTO.ReferenceeSummaryDTO;
import com.mandovi.DTO.ReferenceeTableDTO;
import com.mandovi.Entity.Referencee;
import com.mandovi.Repository.ReferenceeRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
                Sheet sheet = workbook.getSheetAt(0);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);

                referenceeRepository.deleteReferenceeAll();

                for(int i = 1; i <= sheet.getLastRowNum(); i++){
                    Row row = sheet.getRow(i);
                    if(row == null)continue;

                    Referencee reference = new Referencee();

                    reference.setCity(row.getCell(0).getStringCellValue());
                    reference.setBranch(row.getCell(1).getStringCellValue());
                    reference.setGroupDesignation(row.getCell(2).getStringCellValue());
                    reference.setYear(row.getCell(3).getStringCellValue());
                    reference.setMonth(row.getCell(4).getStringCellValue());
                    reference.setChannel(row.getCell(5).getStringCellValue());
                    reference.setReferencee(row.getCell(6) == null ? 0 : (int) row.getCell(6).getNumericCellValue());
                    reference.setEnquiry(row.getCell(7) == null ? 0 : (int) row.getCell(7).getNumericCellValue());
                    reference.setBooking(row.getCell(8) == null ? 0 : (int) row.getCell(8).getNumericCellValue());
                    reference.setInvoice(row.getCell(9) == null ? 0 : (int) row.getCell(9).getNumericCellValue());

                    String charMonth = reference.getMonth();
                    int year = Integer.parseInt(reference.getYear());
                    Month m = Month.from(formatter.parse(charMonth));
                    int monthNum = m.getValue();
                    if (monthNum >= 4) {
                        reference.setFinancialYear(year + "-" + (year+1));
                    } else {
                        reference.setFinancialYear((year-1) + "-" + year);
                    }

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
    public List<Referencee> getReferenceeByMonthYear(List<String> months, List<String> years, List<String> financialYears) {
        return referenceeRepository.getReferenceeByMonthYear(months, years, financialYears);
    }

    @Override
    public List<ReferenceeSummaryDTO> getReferenceeSummary(
            List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear, List<String> financialYears) {
        return referenceeRepository.getReferenceeSummaryByCity(months, channels, qtrWise, halfYear,financialYears);
    }


    @Override
    public List<ReferenceeSummaryDTO> getReferenceeSummaryBranchWise(
            List<String> months, List<String> cities, List<String> channels,
            List<String> qtrWise, List<String> halfYear, List<String> financialYears) {
        return referenceeRepository.getReferenceeSummaryBranchWise(months, cities, channels, qtrWise, halfYear,financialYears);
    }

    @Override
    public void deleteReferenceeAll() {
        referenceeRepository.deleteReferenceeAll();
    }

    @Override
    public List<ReferenceeTableDTO> getReferenceeTable(List<String> months, List<String> cities, List<String> financialYears) {
        return referenceeRepository.getReferenceeTableCityWise(months, cities, financialYears);
    }
}
