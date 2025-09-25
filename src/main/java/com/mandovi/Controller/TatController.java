package com.mandovi.Controller;

import com.mandovi.Entity.Tat;
import com.mandovi.Service.TatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/getalltat")
    public List<Tat> getAllTat(){
        return tatService.getAllTat();
    }

    @GetMapping("/gettat/{month}/{year}")
    public ResponseEntity<List<Tat>> getTATByMonthYear (@PathVariable String month, @PathVariable String year){
        List<Tat> tatRecords = tatService.getTatByMonthYear(month, year);
        if (tatRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tatRecords);
    }
}
