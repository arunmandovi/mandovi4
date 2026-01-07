package com.mandovi.Service;

import com.mandovi.DTO.SAConversionDTO;
import com.mandovi.Entity.SAConversion;
import com.mandovi.Repository.SAConversionRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SAConversionServiceImpl implements SAConversionService {
    private final SAConversionRepository saConversionRepository;

    public SAConversionServiceImpl(SAConversionRepository saConversionRepository) {
        this.saConversionRepository = saConversionRepository;
    }

    @Override
    public void saveSAConversionFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);

                SAConversion saConversion = new SAConversion();
                Cell cell = row.getCell(0);
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
                saConversion.setSaConversionDate(localDate);
                DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");
                saConversion.setMonth(monthFormatter.format(saConversion.getSaConversionDate()));
                DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
                saConversion.setYear(yearFormatter.format(saConversion.getSaConversionDate()));

                saConversion.setBranch(row.getCell(1).getStringCellValue());
                saConversion.setSaName(row.getCell(2).getStringCellValue());
                saConversion.setPmsAppt((int)row.getCell(3).getNumericCellValue());
                saConversion.setPmsConversion((int)row.getCell(4).getNumericCellValue());
                if (saConversion.getPmsAppt() == null || Objects.equals(saConversion.getPmsAppt(), 0)) {
                    saConversion.setPercentagePMSConversion(0.0);
                } else {
                    saConversion.setPercentagePMSConversion(saConversion.getPmsAppt() * 1.0 / saConversion.getPmsAppt() * 100);
                }
                saConversion.setFrsAppt((int) row.getCell(6).getNumericCellValue());
                saConversion.setFrsConversion((int)row.getCell(7).getNumericCellValue());
                if (saConversion.getFrsAppt() == null || Objects.equals(saConversion.getFrsAppt(), 0)){
                    saConversion.setPercentageFRSConversion(0.0);
                }else {
                    saConversion.setPercentageFRSConversion(saConversion.getFrsConversion() * 1.0 / saConversion.getFrsAppt() * 100);
                }

                saConversionRepository.save(saConversion);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SAConversion> getSAConversionALl() {
        return saConversionRepository.findAll();
    }

    @Override
    public List<SAConversion> getSAConversionByMonth(List<String> months) {
        return saConversionRepository.getSAConversionByMonth(months);
    }

    @Override
    public List<SAConversionDTO> getSAConversionSummary(List<String> months, List<String> branches, List<String> saNames) {
        return saConversionRepository.getSAConversionSummary(months, branches, saNames);
    }

    @Override
    public void deleteSAConversionALL() {
        saConversionRepository.deleteSAConversionALl();
    }
}
