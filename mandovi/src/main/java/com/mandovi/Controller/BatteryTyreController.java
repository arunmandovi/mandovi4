package com.mandovi.Controller;

import com.mandovi.Service.BatteryTyreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/battery_tyre")
public class BatteryTyreController {
    private final BatteryTyreService batteryTyreService;

    public BatteryTyreController(BatteryTyreService batteryTyreService) {
        this.batteryTyreService = batteryTyreService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBatteryTyreExcel(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            batteryTyreService.saveBatteryTyreDataFromExcel(file);
            return ResponseEntity.ok("Battery and Tyre File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e);
        }
    }

}
