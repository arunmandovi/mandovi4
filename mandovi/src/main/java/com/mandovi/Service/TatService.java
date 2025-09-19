package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TatService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;
}
