package com.mandovi.Service;

import com.mandovi.DTO.LabourSummaryDTO;
import com.mandovi.Entity.Labour;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LabourService {
    void saveLabourFromExcel(MultipartFile file);

    public List<Labour> getAllLabour();

    public List<Labour> getLabourByMonthYear(String month, String year);

    public List<LabourSummaryDTO> getLabourServiceSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    public List<LabourSummaryDTO> getLabourBodyShopSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    public List<LabourSummaryDTO> getLabourSrBrSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    public List<LabourSummaryDTO> getLabourFreeServiceSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    public List<LabourSummaryDTO> getLabourPMSSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    public List<LabourSummaryDTO> getLabourFPRSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    public List<LabourSummaryDTO> getLabourRunningRepairSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    public List<LabourSummaryDTO> getLabourOthersSummary (String groupBy, String month, String year, String qtrWise, String halfYear);
}
