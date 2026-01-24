package com.mandovi.Service;

import com.mandovi.DTO.ServiceeSummaryDTO;
import com.mandovi.Entity.Servicee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceeService {
    public void saveServiceExcel (MultipartFile file);

    public List<Servicee> getServiceeAll ();

    public List<Servicee> getService (List<String> months, List<String> years);

    public List<ServiceeSummaryDTO> getServiceeSummaryBranchWise (
            List<String> months,List<String> years, List<String> branches,List<String> channels, List<String> serviceCodes
    );
}
