package com.mandovi.Service;

import com.mandovi.DTO.ReferenceeSummaryDTO;
import com.mandovi.DTO.ReferenceeTableDTO;
import com.mandovi.Entity.Referencee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReferenceeService {
    void saveReferenceFromExcel(MultipartFile file);

    public List<Referencee> getAllReference();

    public List<Referencee> getReferenceeByMonthYear(List<String> months, List<String> years, List<String> financialYears);

    public List<ReferenceeSummaryDTO> getReferenceeSummary (
            List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear, List<String> financialYears);

    public List<ReferenceeSummaryDTO> getReferenceeSummaryBranchWise(
            List<String> months, List<String> cities, List<String> channels,
            List<String> qtrWise, List<String> halfYear, List<String> financialYears );

    void deleteReferenceeAll ();

    public List<ReferenceeTableDTO> getReferenceeTable (List<String> months, List<String> cities, List<String> financialYears );
}
