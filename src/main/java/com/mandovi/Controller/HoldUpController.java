package com.mandovi.Controller;

import com.mandovi.Service.HoldUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/hold_up")
public class HoldUpController {
    private final HoldUpService holdUpService;

    public HoldUpController(HoldUpService holdUpService) {
        this.holdUpService = holdUpService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadHoldUpExcel (@RequestParam ("file")MultipartFile file){
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
            }
        try {
            holdUpService.saveHoldUpFromExcel(file);
            return ResponseEntity.ok("HoldUp File Uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
