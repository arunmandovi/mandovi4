package com.mandovi.Service;

import com.mandovi.DTO.SparesSummaryDTO;
import com.mandovi.Entity.Spares;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SparesService{
    void saveSparesDataFromExcel(MultipartFile file)  throws IOException;

    public List<Spares> getAllSpares();

    public List<Spares> getSparesByMonthYear(String month, String year);

    public List<SparesSummaryDTO> getSparesSummary (String groupBy, List<String> months, String qtrWise, String halfYear);
}
