package com.mandovi.Controller;

import com.mandovi.DTO.BatteryTyreSummaryDTO;
import com.mandovi.Entity.BatteryTyre;
import com.mandovi.Service.BatteryTyreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/getallbattery_tyre")
    public List<BatteryTyre> getAllBattery_Tyre(){
        return batteryTyreService.getAllBattery_Tyre();
    }

    @GetMapping("/getbattery_tyre/{month}/{year}")
    public ResponseEntity<List<BatteryTyre>> getBatteryTyreByMonthYear(@PathVariable String month, @PathVariable String year){
        List<BatteryTyre> batteryTyreRecords = batteryTyreService.getBattery_TyreByMonthYear(month, year);
        if (batteryTyreRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(batteryTyreRecords);
    }


    @GetMapping("/battery_tyre_summary")
    public ResponseEntity<List<BatteryTyreSummaryDTO>> getBatterySummary(
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam(required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<BatteryTyreSummaryDTO> listBattery = batteryTyreService.getBatteryTyreSummary(groupBy, month, qtrWise, halfYear);
            if (listBattery.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listBattery);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }


}
