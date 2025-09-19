package com.mandovi.Controller;

import com.mandovi.Service.OilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/oil")
public class OilController {
    private final OilService oilService;

    public OilController(OilService oilService) {
        this.oilService = oilService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadOilExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            oilService.saveOilFromExcel(file);
            return ResponseEntity.ok("Oil File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
