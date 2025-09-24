package com.mandovi.Controller;

import com.mandovi.Entity.Loadd;
import com.mandovi.Service.LoaddService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/loadd")
public class LoaddController {
    private final LoaddService loaddService;

    public LoaddController(LoaddService loaddService) {
        this.loaddService = loaddService;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadLoadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            loaddService.saveLoadDataFromExcel(file);
            return ResponseEntity.ok("Load File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallloadd")
    public List<Loadd> getAllLoadData(){
        return loaddService.getAllLoadData();
    }

    @GetMapping("/getloadd/{month}/{year}")
    public ResponseEntity<List<Loadd>> getLoadByMonthYear(@PathVariable String month, @PathVariable String year){
        List<Loadd> loaddRecords = loaddService.getLoadByMonthYear(month, year);
        if (loaddRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(loaddRecords);
    }

}
