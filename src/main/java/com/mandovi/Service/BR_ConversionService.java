package com.mandovi.Service;

import com.mandovi.DTO.BR_ConversionSummaryDTO;
import com.mandovi.Entity.BR_Conversion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BR_ConversionService {
    void saveBR_ConversionDataFromExcel(MultipartFile file) throws IOException;

    public List<BR_Conversion> getAllBR_Conversion();

    public List<BR_Conversion> getBR_ConversionByMonthYear(String month, String year);

    public List<BR_ConversionSummaryDTO> getBR_ConversionArenaSummary (String groupBy, String month, String year, String qtr_wise, String half_year);
}
