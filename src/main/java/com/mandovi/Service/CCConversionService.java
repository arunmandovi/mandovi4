package com.mandovi.Service;

import com.mandovi.DTO.CCConversionDTO;
import com.mandovi.Entity.CCConversion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CCConversionService {
    void saveCCConversionFromExcel (MultipartFile file);

    public List<CCConversion> getAllCCConversion ();

    public List<CCConversion> getCCConversionByMonth (List<String> months, List<String> financialYears);

    public List<CCConversionDTO> getCCConversionSummary (
            List<String> months, List<String> branches, List<String> cceNames, List<String> financialYears);

    void deleteCCConversionAll ();
}
