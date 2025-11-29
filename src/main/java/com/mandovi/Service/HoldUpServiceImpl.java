package com.mandovi.Service;

import com.mandovi.Entity.HoldUp;
import com.mandovi.Repository.HoldUpRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

            Cell checkCell = firstRow.getCell(4);
            LocalDate checkLocalDate = null;

            if (checkCell != null) {
                if (checkCell.getCellType() == CellType.NUMERIC) {
                    Date date = checkCell.getDateCellValue();
                    checkLocalDate = date.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                } else if (checkCell.getCellType() == CellType.STRING) {
                    String dateStr = checkCell.getStringCellValue();
                    checkLocalDate = LocalDate.parse(dateStr);
                }
            }

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

                    Cell cell = row.getCell(4);
                    LocalDate localDate = null;

                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            Date date = cell.getDateCellValue();
                            localDate = date.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                        } else if (cell.getCellType() == CellType.STRING) {
                            String dateStr = cell.getStringCellValue();
                            localDate = LocalDate.parse(dateStr);
                        }
                    }

                    holdUp.setHoldUpDate(localDate);
                    holdUp.setDays(row.getCell(5).getStringCellValue());
                    holdUp.setCount((int) row.getCell(6).getNumericCellValue());

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
