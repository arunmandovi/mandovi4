package com.mandovi.Service;

import com.mandovi.Entity.BR_Conversion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BR_ConversionService {
    void saveBR_ConversionDataFromExcel(MultipartFile file) throws IOException;

    public List<BR_Conversion> getAllBR_Conversion();
}
