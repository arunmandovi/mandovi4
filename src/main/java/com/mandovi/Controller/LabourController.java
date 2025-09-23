package com.mandovi.Controller;

import com.mandovi.Entity.Labour;
import com.mandovi.Service.LabourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/getalllabour")
    public List<Labour> getAllLabour(){
        return labourService.getAllLabour();
    }
}
