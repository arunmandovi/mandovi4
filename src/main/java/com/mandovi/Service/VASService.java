package com.mandovi.Service;

import com.mandovi.DTO.VASSummaryDTO;
import com.mandovi.Entity.VAS;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VASService {
    void saveVASFromExcel(MultipartFile file);

    public List<VAS> getAllVas();

    public List<VAS> getVASByMonthYear (String month, String year);

    public List<VASSummaryDTO> getVAS (String groupBy, String month);


}
