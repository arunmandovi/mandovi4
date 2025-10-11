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
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<RevenueSummaryDTO> listRevenueSummary = revenueService.getRevenueSummary(groupBy, month, qtrWise, halfYear);
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
}
