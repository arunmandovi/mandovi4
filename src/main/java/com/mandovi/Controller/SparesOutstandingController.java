package com.mandovi.Controller;

import com.mandovi.Service.SparesOutstandingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/spares_outstanding")
public class SparesOutstandingController {
    private final SparesOutstandingService sparesOutstandingService;

    public SparesOutstandingController(SparesOutstandingService sparesOutstandingService) {
        this.sparesOutstandingService = sparesOutstandingService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadExcel(@RequestParam("file")MultipartFile file){
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Uplaod a valid file");
            }
            sparesOutstandingService.saveSparesOutstanding(file);
            return ResponseEntity.ok("SparesOutstanding file Uploaded Successfully");
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
