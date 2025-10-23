package com.mandovi.Controller;

import com.mandovi.DTO.LabourSummaryDTO;
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

    @GetMapping("/getlabour/{month}/{year}")
    public ResponseEntity<List<Labour>> getLabourByMonthYear(@PathVariable String month, @PathVariable String year){
        List<Labour> labourRecords = labourService.getLabourByMonthYear(month, year);
        if (labourRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(labourRecords);
    }

    @GetMapping("/labour_summary")
    public ResponseEntity<?> getLabourSummary (
            @RequestParam String groupBy,
            @RequestParam(required = false) List<String> months,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourServiceSummary = labourService.getLabourSummary(groupBy, months, qtrWise, halfYear);
            if (listLabourServiceSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourServiceSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("ERROR :" + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server ERROR :"+ e.getMessage());
        }
    }

}
