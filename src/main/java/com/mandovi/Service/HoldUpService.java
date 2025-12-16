package com.mandovi.Service;

import com.mandovi.DTO.HoldUpDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HoldUpService {
    void saveHoldUpFromExcel(MultipartFile file);

    List<HoldUpDTO> getHoldUpDTOCityWise ();

    List<HoldUpDTO> getHoldUpDTOBranchWise (List<String> cities);

    List<String> getAllOldRegNo ();
}
