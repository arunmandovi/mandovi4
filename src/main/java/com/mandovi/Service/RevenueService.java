package com.mandovi.Service;

import com.mandovi.DTO.RevenueSummaryDTO;
import com.mandovi.Entity.Revenue;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RevenueService {
    void saveRevenueFromExcel(MultipartFile file);

    public List<Revenue> getAllRevenue();

    public List<Revenue> getRevenueByMonthYear (String month, String year);

    public List<RevenueSummaryDTO> getRevenueSummary (String groupBy, String month, String qtrWise, String halfYear );
}
