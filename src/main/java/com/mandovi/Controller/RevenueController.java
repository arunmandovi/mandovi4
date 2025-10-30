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

    @GetMapping("/getrevenue/{month}/{year}")
    public ResponseEntity<List<Revenue>> getRevenueByMonthYear (@PathVariable String month, @PathVariable String year){
        List<Revenue> revenueRecords = revenueService.getRevenueByMonthYear(month,year);
        if (revenueRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(revenueRecords);
    }

    @GetMapping("/revenue_summary")
    public ResponseEntity<?> getRevenueSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<RevenueSummaryDTO> listRevenueSummary = revenueService.getRevenueSummary(months, qtrWise, halfYear);
            if (listRevenueSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listRevenueSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("ERROR : "+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/revenue_branch_summary")
    public ResponseEntity<?> getRevenueSummaryBranchWise (
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> months ){
        try {
            List<RevenueSummaryDTO> listRevenueSummaryBranchWise = revenueService.getRevenueSummaryBranchWise(cities, months);
            if (listRevenueSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listRevenueSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal Server ERROR : "+e.getMessage());
        }
    }
}
