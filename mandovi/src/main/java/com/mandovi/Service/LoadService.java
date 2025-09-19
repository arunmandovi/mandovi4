package com.mandovi.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LoadService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;
}
