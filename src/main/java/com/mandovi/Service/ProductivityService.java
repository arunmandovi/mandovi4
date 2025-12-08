package com.mandovi.Service;

import com.mandovi.DTO.ProductivitySummaryDTO;
import com.mandovi.Entity.Productivity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductivityService {
    void saveProductivityFromExcel(MultipartFile file);

    public List<Productivity> getAllProductivity ();

    public int updateServiceUtilizedBay(String branch, Integer newServiceUtilizedBay);

    public int updateBodyShopUtilizedBay (String branch, Integer newBodyShopUtilizedBay);

    public List<ProductivitySummaryDTO> getProductivitySummaryCityWise (List<String> months, List<String> years);

    public List<ProductivitySummaryDTO> getProductivitySummaryBranchWise (List<String> months, List<String> years, List<String> cities );
}
