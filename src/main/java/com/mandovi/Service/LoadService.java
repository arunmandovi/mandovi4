package com.mandovi.Service;

import com.mandovi.Entity.Load;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LoadService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;

    public List<Load> getAllLoadData();
}
