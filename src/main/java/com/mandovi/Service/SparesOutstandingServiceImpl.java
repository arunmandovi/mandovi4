package com.mandovi.Service;

import com.mandovi.Entity.SparesOutstanding;
import com.mandovi.Repository.SparesOutstandingRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SparesOutstandingServiceImpl implements SparesOutstandingService {

    private final SparesOutstandingRepository sparesOutstandingRepository;

    public SparesOutstandingServiceImpl(SparesOutstandingRepository sparesOutstandingRepository) {
        this.sparesOutstandingRepository = sparesOutstandingRepository;
    }

    @Override
    public void saveSparesOutstanding(MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);


            // 🔹 Step 1: Group by partyName
            Map<String, List<SparesOutstanding>> groupedData = new HashMap<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                SparesOutstanding so = new SparesOutstanding();

                so.setSegment(getStringCell(row.getCell(0)));
                so.setLedgerName(getStringCell(row.getCell(1)));
                so.setPartyName(getStringCell(row.getCell(2)));

                // 🔹 Date Handling
                Cell dateCell = row.getCell(3);
                String partyDate = "";
                LocalDate parsedDate = null;

                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                        parsedDate = dateCell.getLocalDateTimeCellValue().toLocalDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        partyDate = parsedDate.format(formatter);
                    } else {
                        partyDate = dateCell.toString().trim();
                        try {
                            parsedDate = LocalDate.parse(partyDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        } catch (Exception ignored) {}
                    }
                }

                so.setPartyOutstandingDate(partyDate);

                so.setBillNo(getStringCell(row.getCell(4)));

                so.setBillAmt(getNumericCell(row.getCell(5)));
                so.setPaidAmt(getNumericCell(row.getCell(6)));
                so.setBalanceAmt(getNumericCell(row.getCell(7)));

                so.setDueSince((int) getNumericCell(row.getCell(8)));

                so.setUpToSeven(getNumericCell(row.getCell(9)));
                so.setEightToThirty(getNumericCell(row.getCell(10)));
                so.setThirtyOneToNinty(getNumericCell(row.getCell(11)));
                so.setMoreThanNinty(getNumericCell(row.getCell(12)));

                so.setSalesMan(getStringCell(row.getCell(13)));

                // store date separately for comparison
                so.setTempDate(parsedDate);

                groupedData.computeIfAbsent(so.getPartyName(), k -> new ArrayList<>()).add(so);
            }

            // 🔹 Step 2: Process each party
            for (List<SparesOutstanding> partyRows : groupedData.values()) {

                double totalBillAmt = 0;
                double totalPaidAmt = 0;
                double totalBalanceAmt = 0;
                double upTo7 = 0, eightTo30 = 0, thirty1to90 = 0, moreThan90 = 0;

                SparesOutstanding latestRow = null;

                for (SparesOutstanding row : partyRows) {

                    totalBillAmt += safe(row.getBillAmt());
                    totalPaidAmt += safe(row.getPaidAmt());
                    totalBalanceAmt += safe(row.getBalanceAmt());

                    upTo7 += safe(row.getUpToSeven());
                    eightTo30 += safe(row.getEightToThirty());
                    thirty1to90 += safe(row.getThirtyOneToNinty());
                    moreThan90 += safe(row.getMoreThanNinty());

                    // 🔹 Find latest date row
                    if (latestRow == null ||
                            (row.getTempDate() != null &&
                                    (latestRow.getTempDate() == null ||
                                            row.getTempDate().isAfter(latestRow.getTempDate())))) {
                        latestRow = row;
                    }
                }

                // 🔹 Condition: skip if balance <= 0
                if (totalBalanceAmt <= 1) continue;

                // 🔹 Create final record
                SparesOutstanding finalRow = new SparesOutstanding();

                // Non-numeric from latest row
                finalRow.setSegment(latestRow.getSegment());
                finalRow.setLedgerName(latestRow.getLedgerName());
                finalRow.setPartyName(latestRow.getPartyName());
                finalRow.setPartyOutstandingDate(latestRow.getPartyOutstandingDate());
                finalRow.setBillNo(latestRow.getBillNo());
                finalRow.setSalesMan(latestRow.getSalesMan());
                finalRow.setDueSince(latestRow.getDueSince());

                // Aggregated numeric values
                finalRow.setBillAmt(totalBillAmt);
                finalRow.setPaidAmt(totalPaidAmt);
                finalRow.setBalanceAmt(totalBalanceAmt);
                finalRow.setUpToSeven(upTo7);
                finalRow.setEightToThirty(eightTo30);
                finalRow.setThirtyOneToNinty(thirty1to90);
                finalRow.setMoreThanNinty(moreThan90);

                sparesOutstandingRepository.save(finalRow);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 🔹 Helper methods

    private String getStringCell(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        if (cell.getCellType() == CellType.NUMERIC) return String.valueOf((long) cell.getNumericCellValue());
        return cell.toString().trim();
    }

    private double getNumericCell(Cell cell) {
        if (cell == null) return 0.0;
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
        try {
            return Double.parseDouble(cell.toString());
        } catch (Exception e) {
            return 0.0;
        }
    }

    private double safe(Double val) {
        return val == null ? 0.0 : val;
    }
}