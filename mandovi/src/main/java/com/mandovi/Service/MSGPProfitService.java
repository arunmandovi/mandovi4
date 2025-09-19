package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MSGPProfitService {
    void saveMSGPProfitFromExcel(MultipartFile file) ;
}
