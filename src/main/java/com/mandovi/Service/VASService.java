package com.mandovi.Service;

import com.mandovi.DTO.VASSummaryDTO;
import com.mandovi.Entity.VAS;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VASService {
    void saveVASFromExcel(MultipartFile file);

    public List<VAS> getAllVas();

    public List<VAS> getVASByMonthYear (List<String> months, List<String> years, List<String> financialYears);

    public List<VASSummaryDTO> getVASSummary(List<String> months, List<String> qtrWise,
                                             List<String> halfYear, String financialYear);

    public List<VASSummaryDTO> getVASSummaryBranchWise (List<String> months, List<String> cities, List<String> qtrWise,
                                                        List<String> halfYear, List<String> financialYears );

    void deleteVASAll ();
}
