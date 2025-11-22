package com.mandovi.Service;


import com.mandovi.DTO.DueDoneSummaryDTO;
import com.mandovi.Entity.DueDone;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DueDoneService {

    void saveDataFromExcel (MultipartFile file);

    public List<DueDone> getALlDueDoneData ();

    public List<DueDone> getDueDoneByMonthYear (List<String> months, List<String> years);

    public List<DueDoneSummaryDTO> getDueDoneSummary (List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear);

    public List<DueDoneSummaryDTO> getDueDoneSummaryByBranchWise (List<String> months, List<String> cities, List<String> channels, List<String> qtrWise, List<String> halfYear);

    public void deleteDueDoneAll ();

}
