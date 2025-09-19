package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BR_ConversionService {
    void saveBR_ConversionDataFromExcel(MultipartFile file) throws IOException;
}
