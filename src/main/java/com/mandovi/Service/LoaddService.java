package com.mandovi.Service;


import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.Entity.Loadd;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LoaddService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;

    public List<Loadd> getAllLoadData();

    public List<Loadd> getLoadByMonthYear(List<String> months, List<String> years, List<String> financialYears );

    public List<LoaddSummaryDTO> getLoaddSummary (List<String> months, List<String> channels, List<String> qtrWise,
                                                  List<String> halfYear, String selectedFinancialYear );

    public List<LoaddSummaryDTO> getLoaddSummaryBranchWise (List<String> months, List<String> cities, List<String> branches,
                  List<String> channels, List<String> qtrWise, List<String> halfYear, String selectedFinancialYear);

    public void deleteLoaddAll ();

}
