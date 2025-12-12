package com.mandovi.Service;

import com.mandovi.DTO.ProductivitySummaryDTO;
import com.mandovi.Entity.Productivity;
import com.mandovi.Repository.ProductivityRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class ProductivityServiceImpl implements ProductivityService{
    private final ProductivityRepository productivityRepository;

    public ProductivityServiceImpl(ProductivityRepository productivityRepository) {
        this.productivityRepository = productivityRepository;
    }

    private Double round2Decimals(Double value) {
        return Math.round(value * 100.0) / 100.0;
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

                productivity.setMonth(row.getCell(4).getStringCellValue());
                productivity.setWorkedDays((int)row.getCell(5).getNumericCellValue());
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

    private Integer getWorkedDays(List<Productivity> list, String month) {
        return list.stream()
                .filter(p -> p.getMonth().equalsIgnoreCase(month))
                .map(Productivity::getWorkedDays)
                .findFirst()
                .orElse(null);
    }

    public int getWorkingDays(String month, String year) {

        List<Productivity> getProductivity = productivityRepository.findAll();
        Integer jan = getWorkedDays(getProductivity, "Jan");
        Integer feb = getWorkedDays(getProductivity, "Feb");
        Integer mar = getWorkedDays(getProductivity, "Mar");
        Integer apr = getWorkedDays(getProductivity, "Apr");
        Integer may = getWorkedDays(getProductivity, "May");
        Integer jun = getWorkedDays(getProductivity, "Jun");
        Integer jul = getWorkedDays(getProductivity, "Jul");
        Integer aug = getWorkedDays(getProductivity, "Aug");
        Integer sep = getWorkedDays(getProductivity, "Sep");
        Integer oct = getWorkedDays(getProductivity, "Oct");
        Integer nov = getWorkedDays(getProductivity, "Nov");
        Integer dec = getWorkedDays(getProductivity, "Dec");

        Map<String, Integer> monthDays = Map.ofEntries(
                Map.entry("Jan", jan),
                Map.entry("Feb", feb),
                Map.entry("Mar", mar),
                Map.entry("Apr", apr),
                Map.entry("May", may),
                Map.entry("Jun", jun),
                Map.entry("Jul", jul),
                Map.entry("Aug", aug),
                Map.entry("Sep", sep),
                Map.entry("Oct", oct),
                Map.entry("Nov", nov),
                Map.entry("Dec", dec)
        );

        int totalDays = monthDays.getOrDefault(month, 26);

        return totalDays;
    }

    @Override
    public List<ProductivitySummaryDTO> getProductivitySummaryCityWise(
            List<String> months, List<String> years) {

        List<ProductivitySummaryDTO> cityResult =
                productivityRepository.getProductSummaryCityWise(months, years);

        for (ProductivitySummaryDTO dto : cityResult) {

            int totalWorkingDays = 0;
            for (String month : months) {
                int wd = getWorkingDays(month, years.get(0));
                totalWorkingDays += wd;
            }

            if (totalWorkingDays == 0) totalWorkingDays = 1;

            if (dto.getServiceUtilizedBay() != null) {

                dto.setServiceProductivity(round2Decimals(dto.getServiceLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
                dto.setFreeServiceProductivity(round2Decimals(dto.getFreeServiceLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
                dto.setPmsProductivity(round2Decimals(dto.getPmsLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
                dto.setRrProductivity(round2Decimals(dto.getRrLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
                dto.setOthersProductivity(round2Decimals(dto.getOthersLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));

            }

            if (dto.getBodyShopUtilizedBay() != null) {

                dto.setBodyShopProductivity(round2Decimals(dto.getBodyShopLoadd() * 1.0 / dto.getBodyShopUtilizedBay()));
            }
            dto.setWorkingDays(totalWorkingDays);
        }

        return cityResult;
    }

    @Override
    public List<ProductivitySummaryDTO> getProductivitySummaryBranchWise(
            List<String> months, List<String> years, List<String> cities) {

        List<ProductivitySummaryDTO> branchResult =
                productivityRepository.getProductSummaryBranchWise(months, years, cities);

        for (ProductivitySummaryDTO dto : branchResult) {

            int totalWorkingDays = 0;
            for (String month : months) {
                int wd = getWorkingDays(month, years.get(0));
                totalWorkingDays += wd;
            }

            if (totalWorkingDays == 0) totalWorkingDays = 1;

            if (dto.getServiceUtilizedBay() != null) {

                dto.setServiceProductivity(round2Decimals(dto.getServiceLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
                dto.setFreeServiceProductivity(round2Decimals(dto.getFreeServiceLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
                dto.setPmsProductivity(round2Decimals(dto.getPmsLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
                dto.setRrProductivity(round2Decimals(dto.getRrLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
                dto.setOthersProductivity(round2Decimals(dto.getOthersLoadd() * 1.0 / dto.getServiceUtilizedBay() / totalWorkingDays ));
            }

            if (dto.getBodyShopUtilizedBay() != null) {

                dto.setBodyShopProductivity(round2Decimals(dto.getBodyShopLoadd() * 1.0 / dto.getBodyShopUtilizedBay()));
            }
            dto.setWorkingDays(totalWorkingDays);
        }

        return branchResult;
    }

    @Override
    public int updateWorkingDays(String month, Integer workingDays) {
        return productivityRepository.updateWorkingDays(month, workingDays);
    }
}
