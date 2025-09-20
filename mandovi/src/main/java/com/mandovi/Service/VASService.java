package com.mandovi.Service;

import com.mandovi.Entity.VAS;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VASService {
    void saveVASFromExcel(MultipartFile file);

    public List<VAS> getAllVas();
}
