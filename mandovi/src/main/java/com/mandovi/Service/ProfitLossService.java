package com.mandovi.Service;

import com.mandovi.Entity.ProfitLoss;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProfitLossService {
    void saveProfitLossExcel(MultipartFile file);

    public List<Map<String, Object>> getAllProfit_Loss();
}