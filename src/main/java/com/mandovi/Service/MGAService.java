package com.mandovi.Service;

import com.mandovi.DTO.MGASummaryDTO;
import com.mandovi.Entity.MGA;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MGAService {
    void saveMGAFromExcel(MultipartFile file);

    public List<MGA> getAllMGA();

    public List<MGA> getMGAMonthYear(List<String> months, List<String> years, List<String> financialYears);

    public List<MGASummaryDTO> getMGASummary (
            List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear, List<String> financialYears);

    public List<MGASummaryDTO> getMGASummaryBranchWise (
            List<String> months, List<String> cities, List<String> channels,
            List<String> qtrWise, List<String> halfYear, List<String> financialYears );

     void deleteMGAAll ();
}
