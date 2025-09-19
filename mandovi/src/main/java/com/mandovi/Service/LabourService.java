package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface LabourService {
    void saveLabourFromExcel(MultipartFile file);
}
