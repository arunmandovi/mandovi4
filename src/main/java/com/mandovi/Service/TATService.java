package com.mandovi.Service;

import com.mandovi.DTO.TATSummaryDTO;
import com.mandovi.Entity.TAT;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TATService {
    void saveLoadDataFromExcel(MultipartFile file) throws IOException;

    public List<TAT> getAllTat();

    public List<TAT> getTATByMonthYear(List<String> months, List<String> years);

    public List<TATSummaryDTO> getTATSummary (List<String> months, List<String> qtrWise, List<String> halfYear);

    public List<TATSummaryDTO> getTATSummaryBranchWise (List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear);
}
