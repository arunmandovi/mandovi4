package com.mandovi.Service;

import com.mandovi.Entity.MSGPProfit;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MSGPProfitService {
    void saveMSGPProfitFromExcel(MultipartFile file) ;

    public List<MSGPProfit> getAllMSGP_Profit();
}
