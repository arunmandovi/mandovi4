package com.mandovi.Service;

import com.mandovi.Entity.VAS;
import com.mandovi.Repository.VASRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

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

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                VAS vas = new VAS();

                vas.setCity(row.getCell(0).getStringCellValue());
                vas.setBranch(row.getCell(1).getStringCellValue());
                vas.setLabourType(row.getCell(2).getStringCellValue());
                vas.setMonth(row.getCell(3).getStringCellValue());

                //Converting Integer year to String
                Cell cell = row.getCell(4);
                String year = "UNKNOWN";
                if(cell != null){
                    switch (cell.getCellType()){
                        case STRING:
                            vas.setYear(row.getCell(4).getStringCellValue());
                            break;
                        case NUMERIC:
                            year = String.valueOf((int)row.getCell(4).getNumericCellValue());
                            vas.setYear(year);
                            break;
                    }
                }

                vas.setJobCardNo((int)row.getCell(5).getNumericCellValue());
                vas.setBasicAmt(round2Decimals(row.getCell(6).getNumericCellValue()));

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
}
