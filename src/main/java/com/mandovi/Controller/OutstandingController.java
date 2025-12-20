package com.mandovi.Controller;

import com.mandovi.Service.OutstandingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/outstanding")
public class OutstandingController {
    private final OutstandingService outstandingService;

    public OutstandingController(OutstandingService outstandingService) {
        this.outstandingService = outstandingService;
    }

    @PostMapping("/upload")
    ResponseEntity<?> saveOutstandingFromExcel(MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload valid Excel");
        }
        try {
            outstandingService.saveOutstandingFromExcel(file);
            return ResponseEntity.ok("Outstanding Excel uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
