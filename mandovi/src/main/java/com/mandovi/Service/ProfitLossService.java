package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ProfitLossService {
    void saveProfitLossExcel(MultipartFile file);
}
