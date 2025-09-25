package com.mandovi.Service;

import com.mandovi.Entity.MSGP;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MSGPService {
    void saveMSGPFromExcel(MultipartFile file);

    public List<MSGP> getAllMSGP();

    public List<MSGP> getMSGPByMonthYear(String month, String year);
}
