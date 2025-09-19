package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface PMSPartsService {
    void savePMSPartsFromExcel(MultipartFile file);
}
