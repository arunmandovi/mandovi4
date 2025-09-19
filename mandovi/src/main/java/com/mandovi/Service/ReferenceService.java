package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ReferenceService {
    void saveReferenceFromExcel(MultipartFile file);
}
