package com.mandovi.Service;

import com.mandovi.DTO.SAConversionDTO;
import com.mandovi.Entity.SAConversion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SAConversionService {
    void saveSAConversionFromExcel (MultipartFile file);

    public List<SAConversion> getSAConversionALl ();

    public List<SAConversion> getSAConversionByMonth (List<String> months,List<String> financialYears);

    public List<SAConversionDTO> getSAConversionSummary (
            List<String> months, List<String> branches, List<String> saNames, List<String> financialYears );

    void deleteSAConversionALL ();
}
