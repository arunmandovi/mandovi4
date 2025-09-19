package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface VASService {
    void saveVASFromExcel(MultipartFile file);
}
