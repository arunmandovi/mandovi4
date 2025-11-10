package com.mandovi.Service;

import com.mandovi.DTO.BatteryTyreSummaryDTO;
import com.mandovi.Entity.BatteryTyre;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BatteryTyreService {
    void saveBatteryTyreDataFromExcel(MultipartFile file) throws IOException;

    public List<BatteryTyre> getAllBattery_Tyre();

    public List<BatteryTyre> getBattery_TyreByMonthYear(List<String> months, List<String> years);

    public List<BatteryTyreSummaryDTO> getBatteryTyreSummary (List<String> months, List<String> qtrWise, List<String> halfYear);

    public List<BatteryTyreSummaryDTO> getBatteryTyreSummaryBranchWise (List<String> months, List<String> cities,List<String> qtrWise, List<String> halfYear);

    void deleteBatteryTyreAll ();

}
