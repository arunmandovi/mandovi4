package com.mandovi.Controller;

import com.mandovi.Service.BR_ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/br_conversion")
public class BR_ConversionController {
    private final BR_ConversionService br_conversionService;

    public BR_ConversionController(BR_ConversionService brConversionService) {
        br_conversionService = brConversionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBR_ConversionExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            br_conversionService.saveBR_ConversionDataFromExcel(file);
            return ResponseEntity.ok(" BR Conversion File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}