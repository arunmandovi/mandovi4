package com.mandovi.Controller;

import com.mandovi.Service.TatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tat")
public class TatController {
    private final TatService tatService;

    public TatController(TatService tatService) {
        this.tatService = tatService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTatExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            tatService.saveLoadDataFromExcel(file);
            return ResponseEntity.ok("Tat File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
