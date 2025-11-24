package com.mandovi.Service;

import com.mandovi.DTO.DueDoneSummaryDTO;
import com.mandovi.Entity.DueDone;
import com.mandovi.Repository.DueDoneRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            String uploadMonth = firstRow.getCell(4).getStringCellValue().trim();
            dueDoneRepository.deleteByMonth(uploadMonth);

            for (int i=1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if (row == null)continue;

                DueDone dueDone = new DueDone();

                dueDone.setCity(row.getCell(0).getStringCellValue());
                dueDone.setBranch(row.getCell(1).getStringCellValue());
                dueDone.setChannel(row.getCell(2).getStringCellValue());

                Cell cell = row.getCell(3);
                if (cell!= null){
                    CellType cellType = cell.getCellType();

                    switch (cellType){
                        case STRING -> dueDone.setYear(row.getCell(3).getStringCellValue());
                        case NUMERIC -> {
                            int numYear = (int) row.getCell(3).getNumericCellValue();
                            String year = String.valueOf(numYear);
                            dueDone.setYear(year);
                        }default -> dueDone.setYear("UNKNOWN");
                    }
                }

                dueDone.setMonth(row.getCell(4).getStringCellValue());
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
    public List<DueDone> getDueDoneByMonthYear(List<String> months, List<String> years) {
        return dueDoneRepository.getDueDoneByMonthYear(months, years);
    }

    @Override
    public List<DueDoneSummaryDTO> getDueDoneSummary(List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear) {
        return dueDoneRepository.getDueDoneSummaryByCity(months, channels, qtrWise, halfYear);
    }

    @Override
    public List<DueDoneSummaryDTO> getDueDoneSummaryByBranchWise(List<String> months, List<String> cities, List<String> channels, List<String> qtrWise, List<String> halfYear) {
        return dueDoneRepository.getDueDoneSummaryByBranch(months, cities, channels, qtrWise, halfYear);
    }

    @Override
    public void deleteDueDoneAll() {
        dueDoneRepository.deleteDueDoneAll();
    }

}
