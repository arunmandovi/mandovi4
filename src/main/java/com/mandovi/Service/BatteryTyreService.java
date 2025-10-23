package com.mandovi.Service;

import com.mandovi.DTO.BatteryTyreSummaryDTO;
import com.mandovi.Entity.BatteryTyre;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BatteryTyreService {
    void saveBatteryTyreDataFromExcel(MultipartFile file) throws IOException;

    public List<BatteryTyre> getAllBattery_Tyre();

    public List<BatteryTyre> getBattery_TyreByMonthYear(String month, String year);

    public List<BatteryTyreSummaryDTO> getBatteryTyreSummary (String groupBy, List<String> months, String qtrWise, String halfYear);

}
