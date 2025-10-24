package com.mandovi.Service;

import com.mandovi.DTO.MGASummaryDTO;
import com.mandovi.Entity.MGA;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface MGAService {
    void saveMGAFromExcel(MultipartFile file);

    public List<MGA> getAllMGA();

    public List<MGA> getMGAByMGADate(LocalDate mgaDate);

    public List<MGASummaryDTO> getMGASummary (String groupBy, List<String> months, String qtrWise, String halfYear);
}
