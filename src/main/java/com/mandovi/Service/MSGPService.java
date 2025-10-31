package com.mandovi.Service;

import com.mandovi.DTO.MSGPSummaryDTO;
import com.mandovi.Entity.MSGP;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MSGPService {
    void saveMSGPFromExcel(MultipartFile file);

    public List<MSGP> getAllMSGP();

    public List<MSGP> getMSGPByMonthYear(String month, String year);

    public List<MSGPSummaryDTO> getMSGPSummary (List<String> months, List<String> qtrWise, List<String> halfYear);

    public List<MSGPSummaryDTO> getMSGPSummaryBranchWise (List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear);

}
