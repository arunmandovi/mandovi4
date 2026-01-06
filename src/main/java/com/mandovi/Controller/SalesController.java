package com.mandovi.Controller;

import com.mandovi.DTO.SalesSummaryDTO;
import com.mandovi.Service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/upload")
    ResponseEntity<?> saveSales (MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid Excel");
        }
        try {
            salesService.saveSalesFromExcel(file);
            return ResponseEntity.ok("Sales Data Uploaded Successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/sales_summary")
    ResponseEntity<?> getSalesSummaryCityWise (
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> channels){
        try {
            List<SalesSummaryDTO> listSalesSummary = salesService.getSalesSummaryCityWise(years, months, channels );
            if (listSalesSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listSalesSummary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/sales_branch_summary")
    ResponseEntity<?> getSalesSummaryBranchWise (
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> channels ){
        try {
            List<SalesSummaryDTO> listSalesBranchWise = salesService.getSalesSummaryBranchWise(years, months, cities, channels );
            if (listSalesBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listSalesBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

}
