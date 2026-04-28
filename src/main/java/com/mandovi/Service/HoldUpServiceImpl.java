package com.mandovi.Service;

import com.mandovi.DTO.HoldUpDTO;
import com.mandovi.Entity.HoldUp;
import com.mandovi.Entity.HoldUpDay;
import com.mandovi.Repository.HoldUpDayRepository;
import com.mandovi.Repository.HoldUpRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class HoldUpServiceImpl implements  HoldUpService{
    private final HoldUpRepository holdUpRepository;
    private final HoldUpDayRepository holdUpDayRepository;

    public HoldUpServiceImpl(HoldUpRepository holdUpRepository, HoldUpDayRepository holdUpDayRepository) {
        this.holdUpRepository = holdUpRepository;
        this.holdUpDayRepository = holdUpDayRepository;
    }
    private String buildKey(String city, String branch, String service, String regNo) {
        return city + "|" + branch + "|" + service + "|" + regNo;
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

            LocalDate checkLocalDate = firstRow.getCell(6).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            boolean exists = holdUpRepository.existsByHoldUpDate(checkLocalDate);

            if (exists){
                throw new RuntimeException("Data Already Exists for the Date : "+checkLocalDate);
            } else {
                holdUpDayRepository.deleteHoldUpDayAll();

                List<String> holdUpListBefore = holdUpRepository.getRegNo();
                List<HoldUpDay> holdUpDaysBefore = new ArrayList<>();

                for (String record : holdUpListBefore) {
                    String[] parts = record.split(",");

                    if (parts.length < 4) {
                        continue;
                    }

                    HoldUpDay holdUpDay = new HoldUpDay();
                    holdUpDay.setCity(parts[0]);
                    holdUpDay.setBranch(parts[1]);
                    holdUpDay.setService(parts[2]);
                    holdUpDay.setRegNo(parts[3]);
                    holdUpDay.setTillPreviousDay(1);
                    holdUpDay.setClearedPreviousDay(0);
                    holdUpDay.setAddedToday(0);

                    holdUpDaysBefore.add(holdUpDay);
                    holdUpDayRepository.saveAll(holdUpDaysBefore);
                }

                holdUpRepository.deleteHoldUpAll();

                for (int i=1; i<= sheet.getLastRowNum(); i++){
                    Row row = sheet.getRow(i);
                    if (row == null) continue;

                    HoldUp holdUp = new HoldUp();
                    holdUp.setCity(row.getCell(0).getStringCellValue());
                    holdUp.setBranch(row.getCell(1).getStringCellValue());
                    CellType cellType = row.getCell(2).getCellType();
                    switch (cellType){
                        case STRING -> holdUp.setRegNo(row.getCell(2).getStringCellValue());
                        case NUMERIC -> {
                            int numReg = (int) row.getCell(2).getNumericCellValue();
                            String RegNo = String.valueOf(numReg);
                            holdUp.setRegNo(RegNo);
                        }
                    }

                    holdUp.setServiceType(row.getCell(3).getStringCellValue());
                    holdUp.setService(row.getCell(4).getStringCellValue());
                    holdUp.setChannel(row.getCell(5).getStringCellValue());
                    holdUp.setHoldUpDate(row.getCell(6).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    holdUp.setDays(row.getCell(7).getStringCellValue());
                    holdUp.setCount((int) row.getCell(8).getNumericCellValue());

                    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");
                    holdUp.setMonth(monthFormatter.format(holdUp.getHoldUpDate()));
                    DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
                    holdUp.setDay(dayFormatter.format(holdUp.getHoldUpDate()));
                    DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("YYYY");
                    holdUp.setYear(yearFormatter.format(holdUp.getHoldUpDate()));


                    holdUpRepository.save(holdUp);
                }

                List<String> holdUpListAfter = holdUpRepository.getRegNo();
                Set<String> holdUpSet = new HashSet<>(holdUpListAfter);

                List<HoldUpDay> existingHoldUpDays = holdUpDayRepository.findAllHoldUpDays();

                Map<String, HoldUpDay> holdUpDayMap = new HashMap<>();
                for (HoldUpDay day : existingHoldUpDays) {
                    String key = buildKey(
                            day.getCity(),
                            day.getBranch(),
                            day.getService(),
                            day.getRegNo()
                    );
                    holdUpDayMap.put(key, day);
                }

                List<HoldUpDay> toSave = new ArrayList<>();


                for (HoldUpDay day : existingHoldUpDays) {

                    String recordKey = buildKey(
                            day.getCity(),
                            day.getBranch(),
                            day.getService(),
                            day.getRegNo()
                    );

                    if (holdUpSet.contains(
                            day.getCity() + "," + day.getBranch() + "," + day.getService() + "," + day.getRegNo()
                    )) {
                        day.setClearedPreviousDay(0);
                    } else {
                        day.setClearedPreviousDay(1);
                    }

                    day.setTillPreviousDay(1);
                    day.setAddedToday(0);

                    toSave.add(day);
                }


                for (String record : holdUpListAfter) {

                    String[] parts = record.split(",");
                    if (parts.length < 4) continue;

                    String key = buildKey(parts[0], parts[1], parts[2], parts[3]);

                    if (holdUpDayMap.containsKey(key)) {
                        continue;
                    }

                    HoldUpDay newDay = new HoldUpDay();
                    newDay.setCity(parts[0]);
                    newDay.setBranch(parts[1]);
                    newDay.setService(parts[2]);
                    newDay.setRegNo(parts[3]);

                    newDay.setTillPreviousDay(0);
                    newDay.setAddedToday(1);
                    newDay.setClearedPreviousDay(0);

                    toSave.add(newDay);
                }


                holdUpDayRepository.saveAll(toSave);

                holdUpRepository.insertServiceSummary();
                holdUpRepository.insertBodyShopSummary();
                holdUpRepository.insertPMSSummary();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HoldUpDTO> getHoldUpDTOCityWise() {
        return holdUpRepository.getHoldUpDTOCityWise();
    }

    @Override
    public List<HoldUpDTO> getHoldUpDTOBranchWise(List<String> cities) {
        return holdUpRepository.getHoldUpDTOBranchWise(cities);
    }

    @Override
    public List<String> getAllOldRegNo() {
        return holdUpRepository.getRegNo();
    }
}