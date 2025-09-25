package com.mandovi.Service;

import com.mandovi.Entity.Tat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TatService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;

    public List<Tat> getAllTat();

    public List<Tat> getTATByMonthYear( String month, String year);
}
