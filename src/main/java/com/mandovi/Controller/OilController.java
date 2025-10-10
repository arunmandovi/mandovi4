package com.mandovi.Controller;

import com.mandovi.DTO.OilSummaryDTO;
import com.mandovi.Entity.Oil;
import com.mandovi.Service.OilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/oil")
public class OilController {
    private final OilService oilService;

    public OilController(OilService oilService) {
        this.oilService = oilService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadOilExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            oilService.saveOilFromExcel(file);
            return ResponseEntity.ok("Oil File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getalloil")
    public List<Oil> getAllOil(){
        return oilService.getAllOil();
    }

    @GetMapping("/getoil/{month}/{year}")
    public ResponseEntity<List<Oil>> getOilByMonthYear(@PathVariable String month, @PathVariable String year){
        List<Oil> oilRecords = oilService.getOilByMonthYear(month, year);
        if (oilRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(oilRecords);
    }

    @GetMapping("/oil_summary")
    public ResponseEntity<?> getOilSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<OilSummaryDTO> listOilQtySummary = oilService.getOilSummary(groupBy, month, qtrWise, halfYear);
            if (listOilQtySummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listOilQtySummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("ERROR : "+ e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server ERROR : 0"+e.getMessage());
        }
    }

}
