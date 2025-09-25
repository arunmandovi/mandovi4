package com.mandovi.Service;

import com.mandovi.Entity.Oil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OilService {
    void saveOilFromExcel(MultipartFile file);

    public List<Oil> getAllOil();

    public List<Oil> getOilByMonthYear(String month, String year);
}
