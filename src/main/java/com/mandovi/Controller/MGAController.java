package com.mandovi.Controller;

import com.mandovi.DTO.MGASummaryDTO;
import com.mandovi.Entity.MGA;
import com.mandovi.Service.MGAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mga")
public class MGAController {
    private final MGAService mgaService;

    public MGAController(MGAService mgaService) {
        this.mgaService = mgaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMGAExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            mgaService.saveMGAFromExcel(file);
            return ResponseEntity.ok().body("MGA File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallmga")
    public List<MGA> getAllMGA(){
        return mgaService.getAllMGA();
    }

    @GetMapping("/getmga")
    public ResponseEntity<?> getMGA (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> financialYears){
        try {
            List<MGA> mgaRecords = mgaService.getMGAMonthYear(months, years, financialYears);
            if (mgaRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(mgaRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("mga_summary")
    public ResponseEntity<?> getMGASummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<MGASummaryDTO> listMGASummary = mgaService.getMGASummary(months, channels, qtrWise, halfYear, financialYears);
            if (listMGASummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMGASummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/mga_branch_summary")
    public ResponseEntity<?> getMGASummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<MGASummaryDTO> listMGASummaryBranchWise = mgaService.getMGASummaryBranchWise(
                    months, cities, channels, qtrWise, halfYear, financialYears);
            if (listMGASummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMGASummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/delete_all")
    public ResponseEntity<?> deleteMGAAll (){
        try {
            mgaService.deleteMGAAll();
            return ResponseEntity.ok().body("ALL DATA deleted from MGA");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }
}
