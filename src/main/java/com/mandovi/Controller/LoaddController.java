package com.mandovi.Controller;

import com.mandovi.DTO.LoaddSummaryDTO;
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

    @GetMapping("/loadd_service")
    public ResponseEntity<List<LoaddSummaryDTO>> getLoaddServiceSummary(
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddServiceSummary = loaddService.getLoaddServiceSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLoaddServiceSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddServiceSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/loadd_bodyshop")
    public ResponseEntity<List<LoaddSummaryDTO>> getLoaddBodyShopSummary(
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddBodyShopSummary = loaddService.getLoaddBodyShopSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLoaddBodyShopSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddBodyShopSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/loadd_freeservice")
    public ResponseEntity<List<LoaddSummaryDTO>> getLoaddFreeServiceSummary(
            @RequestParam String groupBy,
            @RequestParam(required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddFreeServiceSummary = loaddService.getLoaddFreeServiceSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLoaddFreeServiceSummary.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddFreeServiceSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/loadd_pms")
    public ResponseEntity<List<LoaddSummaryDTO>> getLoaddPMSSummary(
            @RequestParam String groupBy,
            @RequestParam(required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddPMSSummary = loaddService.getLoaddPMSSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLoaddPMSSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddPMSSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/loadd_fpr")
    public ResponseEntity<List<LoaddSummaryDTO>> getLoaddFPRSummary(
            @RequestParam String groupBy,
            @RequestParam(required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddFPRSummary = loaddService.getLoaddFPRSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLoaddFPRSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddFPRSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/loadd_running_repair")
    public ResponseEntity<List<LoaddSummaryDTO>> getLoaddRunningRepairSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddRunningRepairSummary = loaddService.getLoaddRunningRepairSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLoaddRunningRepairSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddRunningRepairSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/loadd_others")
    public ResponseEntity<List<LoaddSummaryDTO>> getLoaddOthersSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddOthersSummary = loaddService.getLoaddOthersSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLoaddOthersSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddOthersSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/loadd_bs_fpr")
    public ResponseEntity<List<LoaddSummaryDTO>> getLoaddBSAndFPRSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String year,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddBSAndFPRSummary = loaddService.getLoaddBSAndFPRSummary(groupBy, month, year, qtrWise, halfYear);
            if (listLoaddBSAndFPRSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddBSAndFPRSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
