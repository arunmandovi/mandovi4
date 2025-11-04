package com.mandovi.Service;

import com.mandovi.Entity.MGAProfit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MGAProfitService {

    void saveMGAProfitFromExcel (MultipartFile file);

    public List<MGAProfit> getALLMGAProfit ();

    public List<MGAProfit> getMGAProfitMonthYear (List<String> months, List<String> years);
}
