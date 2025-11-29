package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface HoldUpService {
    void saveHoldUpFromExcel(MultipartFile file);
}
