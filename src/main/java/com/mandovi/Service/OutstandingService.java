package com.mandovi.Service;

import com.mandovi.Entity.Outstanding;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OutstandingService {
    void saveOutstandingFromExcel (MultipartFile file);

    List<Outstanding> getOutstandingAll ();

    public List<Outstanding> getDifferentOutstanding (List<String> types);

    void deleteOutstandingAll ();
}
