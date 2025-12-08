package com.mandovi.Service;

import com.mandovi.DTO.ProductivitySummaryDTO;
import com.mandovi.Entity.Productivity;
import com.mandovi.Repository.ProductivityRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ProductivityServiceImpl implements ProductivityService{
    private final ProductivityRepository productivityRepository;

    public ProductivityServiceImpl(ProductivityRepository productivityRepository) {
        this.productivityRepository = productivityRepository;
    }

    @Override
    public void saveProductivityFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i=1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Productivity productivity = new Productivity();

                productivity.setCity(row.getCell(0).getStringCellValue());
                productivity.setBranch(row.getCell(1).getStringCellValue());
                if (row.getCell(2) == null){
                    productivity.setServiceUtilizedBay(0);
                } else {
                    productivity.setServiceUtilizedBay((int) row.getCell(2).getNumericCellValue());
                }
                if (row.getCell(3) == null){
                    productivity.setBodyShopUtilizedBay(0);
                } else {
                    productivity.setBodyShopUtilizedBay((int) row.getCell(3).getNumericCellValue());
                }
                productivityRepository.save(productivity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Productivity> getAllProductivity() {
        return productivityRepository.findAll();
    }

    @Override
    public int updateServiceUtilizedBay(String branch, Integer newServiceUtilizedBay) {
        return productivityRepository.updateServiceUtilizedBay(branch, newServiceUtilizedBay);
    }

    @Override
    public int updateBodyShopUtilizedBay(String branch, Integer newBodyShopUtilizedBay) {
        return productivityRepository.updateBodyShopUtilizedBay(branch, newBodyShopUtilizedBay);
    }

    @Override
    public List<ProductivitySummaryDTO> getProductivitySummaryCityWise(List<String> months, List<String> years) {
        return productivityRepository.getProductSummaryCityWise(months, years);
    }

    @Override
    public List<ProductivitySummaryDTO> getProductivitySummaryBranchWise(List<String> months, List<String> years, List<String> cities) {
        return productivityRepository.getProductSummaryBranchWise(months, years, cities);
    }
}
