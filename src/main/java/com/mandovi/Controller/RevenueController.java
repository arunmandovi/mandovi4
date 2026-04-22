package com.mandovi.Controller;

import com.mandovi.DTO.RevenueSummaryDTO;
import com.mandovi.Entity.Revenue;
import com.mandovi.Service.RevenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/revenue")
public class RevenueController {
    private final RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadRevenueExcel(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            revenueService.saveRevenueFromExcel(file);
            return ResponseEntity.ok().body("Revenue File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallrevenue")
    public List<Revenue> getAllRevenue(){
        return revenueService.getAllRevenue();
    }

    @GetMapping("/getrevenue")
    public ResponseEntity<?> getRevenueByMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> financialYears){
        try {
            List<Revenue> revenueRecords = revenueService.getRevenueByMonthYear(months, years, financialYears);
            if (revenueRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(revenueRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/revenue_summary")
    public ResponseEntity<?> getRevenueSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<RevenueSummaryDTO> listRevenueSummary = revenueService.getRevenueSummary(months, qtrWise, halfYear, financialYears);
            if (listRevenueSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listRevenueSummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/revenue_branch_summary")
    public ResponseEntity<?> getRevenueSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<RevenueSummaryDTO> listRevenueSummaryBranchWise = revenueService.getRevenueSummaryBranchWise(
                    months, cities, qtrWise, halfYear, financialYears );
            if (listRevenueSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listRevenueSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteRevenueAll (){
        try {
            revenueService.deleteRevenueAll();
            return ResponseEntity.ok().body(" ALL DATA deleted from Revenue");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }
}
