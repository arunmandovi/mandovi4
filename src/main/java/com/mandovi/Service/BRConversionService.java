package com.mandovi.Service;

import com.mandovi.DTO.BRConversionSummaryDTO;
import com.mandovi.Entity.BRConversion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BRConversionService {
    void saveBR_ConversionDataFromExcel(MultipartFile file) throws IOException;

    public List<BRConversion> getAllBRConversion();

    public List<BRConversion> getBRConversionByMonthYear(List<String> months, List<String> years, List<String> financialYears);

    public List<BRConversionSummaryDTO> getBRConversionSummary (List<String> months, List<String> qtrWise,
                                                                List<String> halfYear, List<String> financialYears);


    public List<BRConversionSummaryDTO> getBRConversionSummaryBranchWise (List<String> months, List<String> cities, List<String> qtrWise,
                                                                          List<String> halfYear, List<String> financialYears);

    void deleteBRConversionAll ();
}
