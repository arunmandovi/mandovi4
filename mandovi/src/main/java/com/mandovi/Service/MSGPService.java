package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface MSGPService {
    void saveMSGPFromExcel(MultipartFile file);
}
