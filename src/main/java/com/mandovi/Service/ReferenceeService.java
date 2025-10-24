package com.mandovi.Service;

import com.mandovi.DTO.ReferenceeSummaryDTO;
import com.mandovi.Entity.Referencee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReferenceeService {
    void saveReferenceFromExcel(MultipartFile file);

    public List<Referencee> getAllReference();

    public List<Referencee> getReferenceeByMonthYear(String month, String year);

    public List<ReferenceeSummaryDTO> getReferenceeSUmmary (String groupBy, List<String> months, String qtrWise, String halfYear);
}
