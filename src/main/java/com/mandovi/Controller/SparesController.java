package com.mandovi.Controller;

import com.mandovi.Entity.Spares;
import com.mandovi.Service.SparesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/getallspares")
    public List<Spares> getAllSpares(){
        return sparesService.getAllSpares();
    }

    @GetMapping("/getspares/{month}/{year}")
    public ResponseEntity<List<Spares>> getSparesByMonthYear(@PathVariable String month, @PathVariable String year){
        List<Spares> sparesRecords = sparesService.getSparesByMonthYear(month, year);
        if (sparesRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sparesRecords);
    }
}
