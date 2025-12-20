package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface OutstandingService {
    void saveOutstandingFromExcel (MultipartFile file);
}
