package com.mandovi.Service;

import com.mandovi.Entity.TAT;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TATService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;

    public List<TAT> getAllTat();

    public List<TAT> getTATByMonthYear(String month, String year);
}
