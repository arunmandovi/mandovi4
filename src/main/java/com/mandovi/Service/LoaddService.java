package com.mandovi.Service;


import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.Entity.Loadd;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LoaddService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;

    public List<Loadd> getAllLoadData();

    List<Loadd> getLoadByMonthYear(String month, String year);

    List<LoaddSummaryDTO> getLoaddSummary (String groupBy, String month, String qtrWise, String halfYear);

}
