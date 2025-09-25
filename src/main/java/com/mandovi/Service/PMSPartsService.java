package com.mandovi.Service;

import com.mandovi.Entity.PMSParts;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface PMSPartsService {
    void savePMSPartsFromExcel(MultipartFile file);

    public List<PMSParts> getAllPMS_Parts();

    public List<PMSParts> getPMSPartsByPMSDate(LocalDate pmsDate);
}
