package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface OilService {
    void saveOilFromExcel(MultipartFile file);
}
