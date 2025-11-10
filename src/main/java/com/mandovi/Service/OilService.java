package com.mandovi.Service;

import com.mandovi.DTO.OilSummaryDTO;
import com.mandovi.Entity.Oil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OilService {
    void saveOilFromExcel(MultipartFile file);

    public List<Oil> getAllOil();

    public List<Oil> getOilByMonthYear(List<String> months, List<String> years);

    public List<OilSummaryDTO> getOilSummary (List<String> months, List<String> qtrWise, List<String> halfYear);

    public List<OilSummaryDTO> getOilSummaryBranchWise (List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear);

    void deleteOilALL ();
}
