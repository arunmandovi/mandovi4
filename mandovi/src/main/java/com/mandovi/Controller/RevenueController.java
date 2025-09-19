package com.mandovi.Controller;

import com.mandovi.Entity.Revenue;
import com.mandovi.Service.RevenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/revenue")
public class RevenueController {
    private final RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadRevenueExcel(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            revenueService.saveRevenueFromExcel(file);
            return ResponseEntity.ok().body("Revenue File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
