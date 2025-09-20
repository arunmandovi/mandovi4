package com.mandovi.Controller;

import com.mandovi.Entity.Load;
import com.mandovi.Service.LoadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/load")
public class LoadController {
    private final LoadService loadService;

    public LoadController(LoadService loadService) { this.loadService = loadService; }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadLoadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            loadService.saveLoadDataFromExcel(file);
            return ResponseEntity.ok("Load File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallload")
    public List<Load> getAllLoadData(){
        return loadService.getAllLoadData();
    }

}
