package com.mandovi.Service;

import com.mandovi.DTO.SalesSummaryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SalesService {
    void saveSalesFromExcel (MultipartFile file);

    public List<SalesSummaryDTO> getSalesSummaryCityWise (List<String> years, List<String> months, List<String> channels);

    public List<SalesSummaryDTO> getSalesSummaryBranchWise (List<String> years, List<String> months, List<String> cities, List<String> channels );
}
