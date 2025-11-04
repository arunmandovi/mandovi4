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

    @GetMapping("/getlabour")
    public ResponseEntity<?> getLabourByMonthYear(
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<Labour> labourRecords = labourService.getLabourByMonthYear(months, years);
            if (labourRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(labourRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("labour_summary")
     public ResponseEntity<?> getLabourSummary (
             @RequestParam (required = false) List<String> months,
             @RequestParam (required = false) List<String> channels,
             @RequestParam (required = false) List<String> qtrWise,
             @RequestParam (required = false) List<String> halfYear ){
        try {
            List<LabourSummaryDTO> list = labourService.getLabourSummary( months, channels , qtrWise, halfYear);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/labour_branch_summary")
    public ResponseEntity<?> getLabourSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<LabourSummaryDTO> listLabourSummaryBranchWise = labourService.getLabourSummaryBranchWise(months, cities, channels, qtrWise, halfYear);
            if (listLabourSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }


}
