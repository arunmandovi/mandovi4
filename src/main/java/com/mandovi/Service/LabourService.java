package com.mandovi.Service;

import com.mandovi.DTO.LabourSummaryDTO;
import com.mandovi.Entity.Labour;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LabourService {
    void saveLabourFromExcel(MultipartFile file);

    public List<Labour> getAllLabour();

    public List<Labour> getLabourByMonthYear(List<String> months, List<String> years, List<String> deleteYears);

    public List<LabourSummaryDTO> getLabourSummary ( List<String> months, List<String> channels, List<String> qtrWise,
                                                     List<String> halfYear, String selectedFinancialYear);

    public List<LabourSummaryDTO> getLabourSummaryBranchWise (List<String> months, List<String> cities, List<String> channels,
                                                              List<String> qtrWise, List<String> halfYear, String selectedFinancialYear);

    void deleteLabourAll ();

}
