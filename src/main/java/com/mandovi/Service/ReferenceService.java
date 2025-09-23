package com.mandovi.Service;

import com.mandovi.Entity.Reference;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReferenceService {
    void saveReferenceFromExcel(MultipartFile file);

    public List<Reference> getAllReference();
}
