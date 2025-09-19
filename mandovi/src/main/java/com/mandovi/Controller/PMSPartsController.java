package com.mandovi.Controller;

import com.mandovi.Service.PMSPartsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pmsparts")
public class PMSPartsController {
    private final PMSPartsService pmsPartsService;

    public PMSPartsController(PMSPartsService pmsPartsService) {
        this.pmsPartsService = pmsPartsService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPMSPartsExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            pmsPartsService.savePMSPartsFromExcel(file);
            return ResponseEntity.ok().body("PMS Parts File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
