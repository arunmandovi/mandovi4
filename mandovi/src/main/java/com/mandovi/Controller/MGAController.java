package com.mandovi.Controller;

import com.mandovi.Service.MGAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mga")
public class MGAController {
    private final MGAService mgaService;

    public MGAController(MGAService mgaService) {
        this.mgaService = mgaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMGAExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            mgaService.saveMGAFromExcel(file);
            return ResponseEntity.ok().body("MGA File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
