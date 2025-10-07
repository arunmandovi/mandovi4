package com.mandovi.Service;

import com.mandovi.DTO.MSGPSummaryDTO;
import com.mandovi.Entity.MSGP;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MSGPService {
    void saveMSGPFromExcel(MultipartFile file);

    public List<MSGP> getAllMSGP();

    public List<MSGP> getMSGPByMonthYear(String month, String year);

    public List<MSGPSummaryDTO> getMSGPServiceBodyShopSummary (String groupBy, String month, String qtrWise, String halfYear);

    public List<MSGPSummaryDTO> getMSGPServiceSummary (String groupBy, String month, String qtrWise, String halfYear);

    public List<MSGPSummaryDTO> getMSGPBodyShopSummary (String groupBy, String month, String qtrWise, String halfYear);

    public List<MSGPSummaryDTO> getMSGPFreeServiceSummary (String groupBy, String month, String qtrWise, String halfYear);

    public List<MSGPSummaryDTO> getMSGPPMSSummary (String groupBy, String month, String qtrWise, String halfYear);

    public List<MSGPSummaryDTO> getMSGPRunningRepairSummary (String groupBy, String month, String qtrWise, String halfYear);

    public List<MSGPSummaryDTO> getMSGPOthersSummary (String groupBy, String month, String qtrWise, String halfYear);

}
