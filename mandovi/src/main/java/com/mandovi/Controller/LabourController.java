package com.mandovi.Controller;

import com.mandovi.Service.LabourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/labour")
public class LabourController {
    private final LabourService labourService;

    public LabourController(LabourService labourService) {
        this.labourService = labourService;
    }

    @PostMapping("upload")
    public ResponseEntity<String> uploadLabourExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            labourService.saveLabourFromExcel(file);
            return ResponseEntity.ok("Labour File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
