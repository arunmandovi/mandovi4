package com.mandovi.Service;

import com.mandovi.DTO.RevenueSummaryDTO;
import com.mandovi.Entity.Revenue;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RevenueService {
    void saveRevenueFromExcel(MultipartFile file);

    public List<Revenue> getAllRevenue();

    public List<Revenue> getRevenueByMonthYear (String month, String year);

    public List<RevenueSummaryDTO> getRevenueSummary (List<String> months, List<String> qtrWise, List<String> halfYear );

    public List<RevenueSummaryDTO> getRevenueSummaryBranchWise (List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear);
}
