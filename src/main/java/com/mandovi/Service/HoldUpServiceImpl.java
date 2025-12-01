package com.mandovi.Service;

import com.mandovi.Entity.HoldUp;
import com.mandovi.Repository.HoldUpRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class HoldUpServiceImpl implements  HoldUpService{
    private final HoldUpRepository holdUpRepository;

    public HoldUpServiceImpl(HoldUpRepository holdUpRepository) {
        this.holdUpRepository = holdUpRepository;
    }



    @Override
    public void saveHoldUpFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            LocalDate checkLocalDate = firstRow.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            boolean exists = holdUpRepository.existsByHoldUpDate(checkLocalDate);

            if (exists){
                throw new RuntimeException("Data Already Exists for the Date : "+checkLocalDate);
            } else {


                holdUpRepository.deleteHoldUpAll();

                for (int i=1; i<= sheet.getLastRowNum(); i++){
                    Row row = sheet.getRow(i);
                    if (row == null) continue;

                    HoldUp holdUp = new HoldUp();
                    holdUp.setCity(row.getCell(0).getStringCellValue());
                    holdUp.setBranch(row.getCell(1).getStringCellValue());
                    holdUp.setServiceType(row.getCell(2).getStringCellValue());
                    holdUp.setService(row.getCell(3).getStringCellValue());
                    holdUp.setHoldUpDate(row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    holdUp.setDays(row.getCell(5).getStringCellValue());
                    holdUp.setCount((int) row.getCell(6).getNumericCellValue());

                    DateTimeFormatter monthformatter = DateTimeFormatter.ofPattern("MMM");
                    holdUp.setMonth(monthformatter.format(holdUp.getHoldUpDate()));
                    DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
                    holdUp.setDay(dayFormatter.format(holdUp.getHoldUpDate()));

                    holdUpRepository.save(holdUp);
                }
                holdUpRepository.insertServiceSummary();
                holdUpRepository.insertBodyShopSummary();
                holdUpRepository.insertPMSSummary();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
