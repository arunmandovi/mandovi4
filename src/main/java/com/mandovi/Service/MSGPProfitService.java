package com.mandovi.Service;

import com.mandovi.DTO.MSGPProfitSummaryDTO;
import com.mandovi.Entity.MSGPProfit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MSGPProfitService {
    void saveMSGPProfitFromExcel(MultipartFile file) ;

    public List<MSGPProfit> getAllMSGP_Profit();

    public List<MSGPProfit> getMSGPProfitByMonthYear(String month, String year);

    public List<MSGPProfitSummaryDTO> getMSGPProfitSummary (String groupBy, List<String> months, String qtrWise, String halfYear);
}
