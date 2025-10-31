package com.mandovi.Service;

import com.mandovi.DTO.PMSPartsSummaryDTO;
import com.mandovi.Entity.PMSParts;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface PMSPartsService {
    void savePMSPartsFromExcel(MultipartFile file);

    public List<PMSParts> getAllPMS_Parts();

    public List<PMSParts> getPMSPartsByPMSDate(LocalDate pmsDate);

    public List<PMSPartsSummaryDTO> getPMSPartsSummary (List<String> months, List<String> qtrWise, List<String> halfYear);

    public List<PMSPartsSummaryDTO> getPMSPartsSummaryBranchWise (List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear);
}
