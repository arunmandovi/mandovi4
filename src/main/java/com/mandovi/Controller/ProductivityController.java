package com.mandovi.Controller;

import com.mandovi.DTO.ProductivitySummaryDTO;
import com.mandovi.Entity.Productivity;
import com.mandovi.Service.ProductivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/productivity")
public class ProductivityController {
        private final ProductivityService productivityService;

    public ProductivityController(ProductivityService productivityService) {
        this.productivityService = productivityService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadProductivityFromExcel (@RequestParam ("file") MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            productivityService.saveProductivityFromExcel(file);
            return ResponseEntity.ok("Productivity File Uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getallproductivity")
    public ResponseEntity<?> getAllProductivity (){
        try {
            List<Productivity> recordsProductivity = productivityService.getAllProductivity();
            if (recordsProductivity.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(recordsProductivity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @PutMapping("/update_service_utilized_bay")
    public ResponseEntity<?> updateServiceUtilizedBay (
            @RequestParam String branch,
            @RequestParam Integer newServiceUtilizedBay ){
        try {
            int rowsUpdated = productivityService.updateServiceUtilizedBay(branch.trim(), newServiceUtilizedBay );
            if (rowsUpdated == 0) {
                return ResponseEntity.badRequest().body("No branch found matching: " + branch);
            }
            String result = "For branch "+ branch+ " Service Utilized bay updated to "+newServiceUtilizedBay;
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @PutMapping("/update_bodyshop_utilized_bay")
    public ResponseEntity<?> updateBodyShopUtilizedBay (
            @RequestParam String branch,
            @RequestParam Integer newBodyShopUtilizedBay ){
        try {
            int rowsUpdated = productivityService.updateBodyShopUtilizedBay(branch.trim(), newBodyShopUtilizedBay);
            if (rowsUpdated == 0){
                return ResponseEntity.badRequest().body("No Branch found Matching: "+branch);
            }
            String result = "For branch "+ branch+" BodyShop Utilized bay updated to "+newBodyShopUtilizedBay;
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR :"+e.getMessage());
        }
    }

    @PutMapping("/update_working_days")
    public ResponseEntity<?> updateWorkingDays (
            @RequestParam String month,
            @RequestParam Integer workingDays ){
        try {
            int rowsUpdated = productivityService.updateWorkingDays(month.trim(), workingDays );
            if (rowsUpdated == 0) {
                return ResponseEntity.badRequest().body("No branch found matching: " + month);
            }
            String result = "For month "+ month+ " Service Utilized bay updated to "+workingDays;
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/productivity_summary")
    public ResponseEntity<?> getProductSummaryCityWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<ProductivitySummaryDTO> listProductivitySummaryCityWise = productivityService.getProductivitySummaryCityWise(months, years);
            if (listProductivitySummaryCityWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listProductivitySummaryCityWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/productivity_branch_summary")
    public ResponseEntity<?> getProductSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> cities ){
        try {
            List<ProductivitySummaryDTO> listProductivitySummaryBranchWise = productivityService.getProductivitySummaryBranchWise(months, years, cities);
            if (listProductivitySummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listProductivitySummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
