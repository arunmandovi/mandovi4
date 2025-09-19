package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

public interface MCPService {
    void saveMCPGFromExcel(MultipartFile file);
}
