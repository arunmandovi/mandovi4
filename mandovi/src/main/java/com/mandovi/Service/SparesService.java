package com.mandovi.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SparesService{
    void saveSparesDataFromExcel(MultipartFile file)  throws IOException;
}
