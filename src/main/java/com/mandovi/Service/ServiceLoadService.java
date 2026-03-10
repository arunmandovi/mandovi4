package com.mandovi.Service;

import com.mandovi.Entity.ServiceLoad;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceLoadService {
    public void saveServiceLoad (MultipartFile file);

    public List<ServiceLoad> getServiceLoadAll ();

    public List<ServiceLoad> getServiceLoadByMonthYear(List<String> months, List<String> years);

    public void deleteServiceLoadAll ();
}
