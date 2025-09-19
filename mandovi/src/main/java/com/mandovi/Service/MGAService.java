package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface MGAService {
    void saveMGAFromExcel(MultipartFile file);
}
