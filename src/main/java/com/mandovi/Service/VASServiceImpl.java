package com.mandovi.Service;

import com.mandovi.DTO.VASSummaryDTO;
import com.mandovi.Entity.VAS;
import com.mandovi.Repository.VASRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class VASServiceImpl implements VASService {
    private final VASRepository vasRepository;

    public VASServiceImpl(VASRepository vasRepository) {
        this.vasRepository = vasRepository;
    }
    private Double round2Decimals(Double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public void saveVASFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            String uploadMonth = firstRow.getCell(5).getStringCellValue().trim();
            Cell yearCell = firstRow.getCell(4);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue()
                    : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);

            vasRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                VAS vas = new VAS();

                vas.setCity(row.getCell(0).getStringCellValue());
                vas.setBranch(row.getCell(1).getStringCellValue());
                vas.setLabourType(row.getCell(2).getStringCellValue());

                String labourType = vas.getLabourType();
                if (labourType.toUpperCase().contains("WHEEL BALANCING")){
                    String wheels = "1";
                    java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("-\\s*(\\d+)").matcher(labourType);
                    if (matcher.find()) {
                        wheels = matcher.group(1).trim();
                        int num = Integer.parseInt(wheels);
                        vas.setWheels(num);
                    }
                } else if (labourType.toUpperCase().contains("DYNAMIC BALANCING")) {
                    String wheelsDynamic = "5";
                    int numDynamic = Integer.parseInt(wheelsDynamic);
                    vas.setWheels(numDynamic);
                } else {
                    vas.setWheels(1);
                }

                vas.setVas(row.getCell(3).getStringCellValue());

                //Converting Integer year to String
                int year = row.getCell(4).getCellType() == CellType.NUMERIC ? (int) row.getCell(4).getNumericCellValue()
                        : Integer.parseInt(row.getCell(4).getStringCellValue());
                vas.setYear(String.valueOf(year));

                String month = row.getCell(5).getStringCellValue();
                if (month != null && !month.isEmpty()) {
                    month = month.toLowerCase();
                    month = month.substring(0,1).toUpperCase() + month.substring(1);
                }
                vas.setMonth(month);

                String charMonth = vas.getMonth();
                Month m = Month.from(dateTimeFormatter.parse(charMonth));
                int numMonth = m.getValue();
                if (numMonth >= 4) {
                    vas.setFinancialYear(year + "-" + (year+1));
                } else {
                    vas.setFinancialYear((year-1) + "-" + year);
                }

                vas.setExcelJobCardNo((int)row.getCell(6).getNumericCellValue());
                vas.setJobCardNo(vas.getExcelJobCardNo() * vas.getWheels());
                vas.setBasicAmt(round2Decimals(row.getCell(7).getNumericCellValue()));

                switch (vas.getMonth().trim().toUpperCase()){
                    case "APR", "MAY", "JUN" -> { vas.setQtrWise("Qtr1"); vas.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" -> { vas.setQtrWise("Qtr2"); vas.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" -> { vas.setQtrWise("Qtr3"); vas.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" -> { vas.setQtrWise("Qtr4"); vas.setHalfYear("H2");}
                    }
                vasRepository.save(vas);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in VASServiceImpl"+e);
        }
    }

    @Override
    public List<VAS> getAllVas() {
        return vasRepository.findAll();
    }

    @Override
    public List<VAS> getVASByMonthYear(List<String> months, List<String> years, List<String> financialYears) {
        return vasRepository.getVASByMonthYear(months, years, financialYears);
    }

    @Override
    public List<VASSummaryDTO> getVASSummary(List<String> months, List<String> qtrWise, List<String> halfYear, String financialYear) {
        return vasRepository.getVASSummaryByCity(months, qtrWise, halfYear, financialYear);
    }

    @Override
    public List<VASSummaryDTO> getVASSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear, List<String> financialYears) {
        return vasRepository.getVASSummaryBranchWise(months, cities, qtrWise, halfYear, financialYears);
    }

    @Override
    public void deleteVASAll() {
        vasRepository.deleteVASAll();
    }

}
