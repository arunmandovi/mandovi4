package com.mandovi.Controller;

import com.mandovi.DTO.MGAProfitSummaryDTO;
import com.mandovi.Entity.MGAProfit;
import com.mandovi.Service.MGAProfitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mga_profit")
public class MGAProfitController {
    private final MGAProfitService mgaProfitService;

    public MGAProfitController(MGAProfitService mgaProfitService) {
        this.mgaProfitService = mgaProfitService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadMGAProfitExcel (@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Upload a valid file");
        } try {
            mgaProfitService.saveMGAProfitFromExcel(file);
            return ResponseEntity.ok().body("MGA Profit file Uploaded Successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }

    @GetMapping("/getallmga_profit")
    public ResponseEntity<?> getAllMGAProfit (){
        List<MGAProfit> listMGAProfit = mgaProfitService.getALLMGAProfit();
        if (listMGAProfit.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listMGAProfit);
    }

    @GetMapping ("/getmga_profit")
    public ResponseEntity<?> getMGAMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<MGAProfit> mgaProfitRecords = mgaProfitService.getMGAProfitMonthYear(months, years);
            if (mgaProfitRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(mgaProfitRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping ("/mga_profit_summary")
    public ResponseEntity<?> getMGAProfitSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MGAProfitSummaryDTO> listMGAProfitSummary = mgaProfitService.getMGAProfitSummary(months, qtrWise, halfYear);
            if (listMGAProfitSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMGAProfitSummary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/mga_profit_branch_summary")
    public ResponseEntity<?> getMGAProfitSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MGAProfitSummaryDTO> listMGAProfitSummaryBranchWise = mgaProfitService.getMGAProfitSummaryBranchWise(months, cities, qtrWise, halfYear);
            if (listMGAProfitSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMGAProfitSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteMGAProfitALL (){
        try {
            mgaProfitService.deleteMGAAll();
            return ResponseEntity.ok().body("ALL DATA deleted from MGA Profit");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }
}
