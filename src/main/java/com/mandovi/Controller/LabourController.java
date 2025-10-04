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

    @GetMapping("/labour_service")
    public ResponseEntity<List<LabourSummaryDTO>> getLabourServiceSummary (
            @RequestParam String groupBy,
            @RequestParam(required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourServiceSummary = labourService.getLabourServiceSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLabourServiceSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourServiceSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/labour_bodyshop")
    public ResponseEntity<List<LabourSummaryDTO>> getLabourBodyShopSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourBodyShopSummary = labourService.getLabourBodyShopSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLabourBodyShopSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourBodyShopSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/labour_srbr")
    public ResponseEntity<List<LabourSummaryDTO>> getLabourSrBrSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourSrBrSummary = labourService.getLabourSrBrSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLabourSrBrSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourSrBrSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/labour_freeservice")
    public ResponseEntity<List<LabourSummaryDTO>> getLabourFreeServiceSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourFreeServiceSummary = labourService.getLabourFreeServiceSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLabourFreeServiceSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourFreeServiceSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/labour_pms")
    public ResponseEntity<List<LabourSummaryDTO>> getLabourPMSSummary(
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourPMSSummary = labourService.getLabourPMSSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLabourPMSSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourPMSSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/labour_fpr")
    public ResponseEntity<List<LabourSummaryDTO>> getLabourFPRSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourFPRSummary = labourService.getLabourFPRSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLabourFPRSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourFPRSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/labour_running_repair")
    public ResponseEntity<List<LabourSummaryDTO>> getLabourRunningRepairSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourRunningRepairSummary = labourService.getLabourRunningRepairSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLabourRunningRepairSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLabourRunningRepairSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/labour_others")
    public ResponseEntity<List<LabourSummaryDTO>> getLabourOthersSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LabourSummaryDTO> listLabourOthersSummary = labourService.getLabourOthersSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLabourOthersSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return  ResponseEntity.ok(listLabourOthersSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
