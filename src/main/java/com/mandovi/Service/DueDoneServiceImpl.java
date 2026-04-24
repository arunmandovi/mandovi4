package com.mandovi.Service;

import com.mandovi.DTO.DueDoneSummaryDTO;
import com.mandovi.Entity.DueDone;
import com.mandovi.Repository.DueDoneRepository;
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
public class DueDoneServiceImpl implements  DueDoneService{
    private final DueDoneRepository dueDoneRepository;

    public DueDoneServiceImpl(DueDoneRepository dueDoneRepository) {
        this.dueDoneRepository = dueDoneRepository;
    }

    @Override
    public void saveDataFromExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook  workbook = WorkbookFactory.create(inputStream)){
             Sheet sheet = workbook.getSheetAt(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            String uploadMonth = firstRow.getCell(4).getStringCellValue().trim();
            int numYear = firstRow.getCell(3).getCellType() == CellType.NUMERIC
                    ? (int) firstRow.getCell(3).getNumericCellValue()
                    : Integer.parseInt(firstRow.getCell(3).getStringCellValue());
            String uploadYear = String.valueOf(numYear);
            dueDoneRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i=1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if (row == null)continue;

                DueDone dueDone = new DueDone();

                dueDone.setCity(row.getCell(0).getStringCellValue());
                dueDone.setBranch(row.getCell(1).getStringCellValue());
                dueDone.setChannel(row.getCell(2).getStringCellValue());

                Cell yearCell = row.getCell(3);
                int numberYear = yearCell.getCellType() == CellType.NUMERIC
                        ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
                String year = String.valueOf(numberYear);
                dueDone.setYear(year);
                dueDone.setMonth(row.getCell(4).getStringCellValue());

                String charMonth = dueDone.getMonth();
                Month m = Month.from(formatter.parse(charMonth));
                int monthNum = m.getValue();
                if (monthNum >= 4){
                    dueDone.setFinancialYear(numberYear + "-" + (numberYear+1));
                } else {
                    dueDone.setFinancialYear((numberYear-1) + "-" + numberYear);
                }

                dueDone.setTotalDue((int)row.getCell(5).getNumericCellValue());
                dueDone.setTotalDone((int) row.getCell(6).getNumericCellValue());

                switch (dueDone.getMonth().trim().toUpperCase()){
                    case "APR","MAY","JUN" -> { dueDone.setQtrWise("Qtr1"); dueDone.setHalfYear("H1"); }
                    case "JUL","AUG","SEP" -> { dueDone.setQtrWise("Qtr2"); dueDone.setHalfYear("H1"); }
                    case "OCT","NOV","DEC" -> { dueDone.setQtrWise("Qtr3"); dueDone.setHalfYear("H2"); }
                    case "JAN","FEB","MAR" -> { dueDone.setQtrWise("Qtr4"); dueDone.setHalfYear("H2"); }
                }

                dueDoneRepository.save(dueDone);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DueDone> getALlDueDoneData() {
        return dueDoneRepository.findAll();
    }

    @Override
    public List<DueDone> getDueDoneByMonthYear(List<String> months, List<String> years, List<String> financialYears) {
        return dueDoneRepository.getDueDoneByMonthYear(months, years, financialYears);
    }

    @Override
    public List<DueDoneSummaryDTO> getDueDoneSummary(
            List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear, List<String> financialYears) {
        return dueDoneRepository.getDueDoneSummaryByCity(months, channels, qtrWise, halfYear,financialYears);
    }

    @Override
    public List<DueDoneSummaryDTO> getDueDoneSummaryByBranchWise(
            List<String> months, List<String> cities, List<String> channels,
            List<String> qtrWise, List<String> halfYear, List<String> financialYears) {
        return dueDoneRepository.getDueDoneSummaryByBranch(months, cities, channels, qtrWise, halfYear,financialYears);
    }

    @Override
    public void deleteDueDoneAll() {
        dueDoneRepository.deleteDueDoneAll();
    }

}
