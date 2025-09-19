package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BatteryTyreService {
    void saveBatteryTyreDataFromExcel(MultipartFile file) throws IOException;
}
