package com.mandovi.Controller;

import com.mandovi.Service.ServiceLoadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/service_load")
public class ServiceLoadController {
    private final ServiceLoadService serviceLoadService;

    public ServiceLoadController(ServiceLoadService serviceLoadService) {
        this.serviceLoadService = serviceLoadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> saveServiceLoad (MultipartFile file){
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Upload a valid Excel");
            }
            serviceLoadService.saveServiceLoad(file);
            return ResponseEntity.ok("ServiceLoad File uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
