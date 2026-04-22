package com.mandovi.Controller;

import com.mandovi.DTO.OilSummaryDTO;
import com.mandovi.Entity.Oil;
import com.mandovi.Service.OilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/oil")
public class OilController {
    private final OilService oilService;

    public OilController(OilService oilService) {
        this.oilService = oilService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadOilExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            oilService.saveOilFromExcel(file);
            return ResponseEntity.ok("Oil File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getalloil")
    public List<Oil> getAllOil(){
        return oilService.getAllOil();
    }

    @GetMapping("/getoil")
    public ResponseEntity<?> getOilByMonthYear(
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<Oil> oilRecords = oilService.getOilByMonthYear(months, years, financialYears);
            if (oilRecords.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(oilRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/oil_summary")
    public ResponseEntity<?> getOilSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<OilSummaryDTO> listOilQtySummary = oilService.getOilSummary(months, qtrWise, halfYear, financialYears);
            if (listOilQtySummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listOilQtySummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/oil_branch_summary")
    public ResponseEntity<?> getOilSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears){
        try {
            List<OilSummaryDTO> listOilSummaryBranchWise = oilService.getOilSummaryBranchWise(
                    months, cities, qtrWise, halfYear, financialYears);
            if (listOilSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listOilSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteOilAll (){
        try {
            oilService.deleteOilALL();
            return ResponseEntity.ok().body("ALL DATA deleted from Oil");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR :"+e.getMessage());
        }
    }

}
