package com.mandovi.Service;

import com.mandovi.DTO.BRConversionSummaryDTO;
import com.mandovi.Entity.BRConversion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BRConversionService {
    void saveBR_ConversionDataFromExcel(MultipartFile file) throws IOException;

    public List<BRConversion> getAllBRConversion();

    public List<BRConversion> getBRConversionByMonthYear(String month, String year);

    public List<BRConversionSummaryDTO> getBRConversionSummary (String groupBy, String month, String qtrWise, String halfYear);
}
