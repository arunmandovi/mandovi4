package com.mandovi.Service;

import com.mandovi.DTO.LabourSummaryDTO;
import com.mandovi.Entity.Labour;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LabourService {
    void saveLabourFromExcel(MultipartFile file);

    public List<Labour> getAllLabour();

    public List<Labour> getLabourByMonthYear(String month, String year);

    public List<LabourSummaryDTO> getLabourSummary (String groupBy, String month, String qtrWise, String halfYear);
}
