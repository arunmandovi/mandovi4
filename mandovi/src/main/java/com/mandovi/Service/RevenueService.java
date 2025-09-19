package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface RevenueService {
    void saveRevenueFromExcel(MultipartFile file);
}
