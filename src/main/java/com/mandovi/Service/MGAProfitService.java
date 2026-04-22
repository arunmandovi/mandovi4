package com.mandovi.Service;

import com.mandovi.DTO.MGAProfitSummaryDTO;
import com.mandovi.Entity.MGAProfit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MGAProfitService {

    void saveMGAProfitFromExcel (MultipartFile file);

    public List<MGAProfit> getALLMGAProfit ();

    public List<MGAProfit> getMGAProfitMonthYear (List<String> months, List<String> years, List<String> financialYears);

    public List<MGAProfitSummaryDTO> getMGAProfitSummary (
            List<String> months, List<String> qtrWise, List<String> halfYear, List<String> financialYears );

    public List<MGAProfitSummaryDTO> getMGAProfitSummaryBranchWise (
            List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear, List<String> financialYears );

    void deleteMGAAll ();
}
