package com.mandovi.Controller;

import com.mandovi.Service.SparesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/spares")
public class SparesController {
    private final SparesService sparesService;

    public SparesController(SparesService sparesService) {
        this.sparesService = sparesService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadSparesExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            sparesService.saveSparesDataFromExcel(file);
            return ResponseEntity.ok("Spares File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
