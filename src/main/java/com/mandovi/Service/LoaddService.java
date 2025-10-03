package com.mandovi.Service;


import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.Entity.Loadd;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LoaddService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;

    public List<Loadd> getAllLoadData();

    List<Loadd> getLoadByMonthYear(String month, String year);

    List<LoaddSummaryDTO> getLoaddServiceSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    List<LoaddSummaryDTO> getLoaddBodyShopSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    List<LoaddSummaryDTO> getLoaddFreeServiceSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

    List<LoaddSummaryDTO> getLoaddPMSSummary (String groupBy, String month, String year, String qtrWise, String halfYear);

}
