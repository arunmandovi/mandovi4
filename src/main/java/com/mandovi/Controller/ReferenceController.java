package com.mandovi.Controller;

import com.mandovi.Entity.Reference;
import com.mandovi.Service.ReferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/reference")
public class ReferenceController {
    private final ReferenceService referenceService;

    public ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            referenceService.saveReferenceFromExcel(file);
            return ResponseEntity.ok("Reference File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallreference")
    public List<Reference> getAllReference(){
        return referenceService.getAllReference();
    }
}
