package com.mandovi.Service;

import com.mandovi.DTO.CCConversionDTO;
import com.mandovi.Entity.CCConversion;
import com.mandovi.Repository.CCConversionRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CCConversionServiceImpl implements CCConversionService {
    private final CCConversionRepository ccConversionRepository;

    public CCConversionServiceImpl(CCConversionRepository ccConversionRepository) {
        this.ccConversionRepository = ccConversionRepository;
    }

    private static final Map<String, Map<String, LocalDate>> DOJ_MAP = Map.ofEntries(

            Map.entry("BALMATTA", Map.ofEntries(
                    Map.entry("RITHESH", LocalDate.of(2016, 8, 1)),
                    Map.entry("MANASA", LocalDate.of(2022, 4, 11)),
                    Map.entry("HARSHITHA", LocalDate.of(2022, 8, 22)),
                    Map.entry("DEEPA", LocalDate.of(2022, 11, 21)),
                    Map.entry("VIDHYA", LocalDate.of(2023, 11, 15)),
                    Map.entry("RESHMA", LocalDate.of(2023, 11, 20)),
                    Map.entry("TEENA", LocalDate.of(2024, 7, 8)),
                    Map.entry("SMRITI", LocalDate.of(2024, 8, 5)),
                    Map.entry("ASHWINI", LocalDate.of(2024, 8, 5)),
                    Map.entry("BINDHIYA", LocalDate.of(2024, 9, 2)),
                    Map.entry("ANITHA", LocalDate.of(2024, 10, 14)),
                    Map.entry("GAYATHRI", LocalDate.of(2025, 4, 16)),
                    Map.entry("VISHAKA", LocalDate.of(2025, 4, 16)),
                    Map.entry("SOUJANYA", LocalDate.of(2025, 6, 16)),
                    Map.entry("VIDHYASHREE", LocalDate.of(2026, 1, 21)),
                    Map.entry("NAMITHA", LocalDate.of(2025, 7, 28))
            )),

            Map.entry("ADYAR", Map.of(
                    "KUSUMA", LocalDate.of(2024, 8, 5),
                    "SUSHMA", LocalDate.of(2025, 10, 7),
                    "KAVITHA", LocalDate.of(2025, 12, 22)
            )),

            Map.entry("BANTWAL", Map.of(
                    "SWATHI L", LocalDate.of(2014, 10, 14),
                    "AKSHATHA", LocalDate.of(2024, 2, 19),
                    "RAKSHA", LocalDate.of(2022, 6, 6)
            )),

            Map.entry("KADABA", Map.of(
                    "DIVYASHREE", LocalDate.of(2020, 2, 10),
                    "CHAITHANYA", LocalDate.of(2024, 6, 3)
            )),

            Map.entry("NARAVI", Map.of(
                    "SURAKSHA", LocalDate.of(2023, 6, 12),
                    "RASHMI", LocalDate.of(2024, 4, 8)
            )),

            Map.entry("NEXA", Map.of(
                    "MANVIKA", LocalDate.of(2022, 10, 10),
                    "THEJASHWINI", LocalDate.of(2022, 12, 5),
                    "AMRITH KIRAN", LocalDate.of(2025, 10, 27)
            )),

            Map.entry("SUJITH BAGH", Map.of(
                    "NISHMITHA", LocalDate.of(2017, 12, 12),
                    "KAVYA", LocalDate.of(2023, 12, 11)
            )),

            Map.entry("SULLIA", Map.of(
                    "SHILPA", LocalDate.of(2018, 8, 9),
                    "SUMALATHA", LocalDate.of(2024, 6, 3),
                    "DHANYASHREE", LocalDate.of(2025, 7, 28),
                    "SHUKALATHA", LocalDate.of(2025, 8, 4)
            )),

            Map.entry("SURATHKAL", Map.of(
                    "DHANYA", LocalDate.of(2022, 6, 6),
                    "KRITHIKA", LocalDate.of(2023, 11, 20),
                    "PRATHIKSHA", LocalDate.of(2024, 6, 3),
                    "HARSHITHA", LocalDate.of(2025, 8, 18)
            )),

            Map.entry("UPPINANGADY", Map.of(
                    "SHWETHA", LocalDate.of(2013, 3, 1),
                    "DEVIKRIPA", LocalDate.of(2022, 6, 13),
                    "SHRADHA", LocalDate.of(2024, 6, 3),
                    "HARSHITHA", LocalDate.of(2025, 4, 7)
            )),

            Map.entry("VITTLA", Map.of(
                    "DIVYASHREE", LocalDate.of(2022, 4, 6),
                    "RASHMITHA", LocalDate.of(2025,4,16)
            )),
            Map.entry("YEYYADI", Map.of(
                    "SWATHI G", LocalDate.of(2017,2,4)
            ))
    );

    @Override
    public void saveCCConversionFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row firstRow = sheet.getRow(1);
            String uploadMonth = firstRow.getCell(2).getStringCellValue();
            Cell yearCell = firstRow.getCell(3);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);

            ccConversionRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if (row == null)continue;

                CCConversion ccConversion = new CCConversion();

                ccConversion.setBranch(row.getCell(0).getStringCellValue());
                ccConversion.setCceName(row.getCell(1).getStringCellValue());
                LocalDate doj = DOJ_MAP
                        .getOrDefault(ccConversion.getBranch(), Map.of())
                        .get(ccConversion.getCceName());
                if (doj != null) {
                    ccConversion.setDateOfJoin(doj);
                }

                ccConversion.setMonth(row.getCell(2).getStringCellValue());

                Cell year_cell = row.getCell(3);
                int num_year = (year_cell.getCellType() == CellType.NUMERIC)
                        ? (int) yearCell.getNumericCellValue() : Integer.parseInt(year_cell.getStringCellValue());
                ccConversion.setYear(String.valueOf(num_year));

                ccConversion.setPmsAppt((int)row.getCell(4).getNumericCellValue());
                ccConversion.setPmsConversion((int)row.getCell(5).getNumericCellValue());
                Double percentagePMS = 0.0;
                if (ccConversion.getPmsAppt() != null && ccConversion.getPmsAppt() > 0){
                    percentagePMS = ccConversion.getPmsConversion() * 1.0 / ccConversion.getPmsAppt() * 100;
                }
                ccConversion.setPercentagePMSConversion(percentagePMS);
                ccConversion.setFrsAppt((int)row.getCell(7).getNumericCellValue());
                ccConversion.setFrsConversion((int)row.getCell(8).getNumericCellValue());
                Double percentageFRS = 0.0;
                if (ccConversion.getFrsAppt() != null && ccConversion.getFrsAppt() > 0){
                    percentageFRS = ccConversion.getFrsConversion() * 1.0 / ccConversion.getFrsAppt() / 100;
                }
                ccConversion.setPercentageFRSConversion(percentageFRS);
                ccConversionRepository.save(ccConversion);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CCConversion> getAllCCConversion() {
        return ccConversionRepository.findAll();
    }

    @Override
    public List<CCConversion> getCCConversionByMonth(List<String> months) {
        return ccConversionRepository.getCCConversionByMonth(months);
    }

    @Override
    public List<CCConversionDTO> getCCConversionSummary(List<String> months, List<String> branches, List<String> cceNames) {

        List<CCConversionDTO> resultFromDB =
                ccConversionRepository.getCCConversionSummary(months, branches, cceNames);

        List<CCConversionDTO> response = new ArrayList<>();

        for (CCConversionDTO dbDto : resultFromDB) {

            CCConversionDTO dto = new CCConversionDTO();
            dto.setBranch(dbDto.getBranch());
            dto.setCceName(dbDto.getCceName());
            dto.setExperience(dbDto.getExperience());

            if (dbDto.getExperience() != null) {

                Long experienceDays =
                        ChronoUnit.DAYS.between(dbDto.getExperience(), LocalDate.now());

                Double exp = experienceDays / 365.25 ;
                dto.setExperienceDays(exp);

            } else {
                dto.setExperienceDays(null);
            }



            dto.setPmsAppointment(dbDto.getPmsAppointment());
            dto.setPmsConversion(dbDto.getPmsConversion());
            dto.setPercentagePMS(dbDto.getPercentagePMS());

            response.add(dto);
        }

        return response;
    }

    @Override
    public void deleteCCConversionAll() {
        ccConversionRepository.deleteCCConversionAll();
    }


}
