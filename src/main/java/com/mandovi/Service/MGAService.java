package com.mandovi.Service;

import com.mandovi.Entity.MGA;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface MGAService {
    void saveMGAFromExcel(MultipartFile file);

    public List<MGA> getAllMGA();

    public List<MGA> getMGAByMGADate(LocalDate mgaDate);
}
