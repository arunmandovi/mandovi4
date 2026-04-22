package com.mandovi.Service;

import com.mandovi.DTO.MSGPProfitSummaryDTO;
import com.mandovi.Entity.MSGPProfit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MSGPProfitService {
    void saveMSGPProfitFromExcel(MultipartFile file) ;

    public List<MSGPProfit> getAllMSGP_Profit();

    public List<MSGPProfit> getMSGPProfitByMonthYear(List<String> months, List<String> years, List<String> financialYears );

    public List<MSGPProfitSummaryDTO> getMSGPProfitSummary (List<String> months, List<String> qtrWise,
                                                            List<String> halfYear, List<String> financialYears);

    public List<MSGPProfitSummaryDTO> getMSGPProfitSummaryBranchWise (List<String> months,
                                   List<String> cities, List<String> qtrWise, List<String> halfYear, List<String> financialYears );

    void deleteMSGPProfitAll ();
}
