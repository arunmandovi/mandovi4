package com.mandovi.Service;

import com.mandovi.Entity.Spares;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SparesService{
    void saveSparesDataFromExcel(MultipartFile file)  throws IOException;

    public List<Spares> getAllSpares();
}
