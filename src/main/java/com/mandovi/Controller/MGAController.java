package com.mandovi.Controller;

import com.mandovi.DTO.MGASummaryDTO;
import com.mandovi.Entity.MGA;
import com.mandovi.Service.MGAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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

    @GetMapping("/getmga/{mgaDate}")
    public ResponseEntity<List<MGA>> getMGAByMGADate (@PathVariable LocalDate mgaDate){
        List<MGA> mgaRecords = mgaService.getMGAByMGADate(mgaDate);
        if (mgaRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mgaRecords);
    }

    @GetMapping("mga_summary")
    public ResponseEntity<?> getMGASummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MGASummaryDTO> listMGASummary = mgaService.getMGASummary(months, qtrWise, halfYear);
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
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MGASummaryDTO> listMGASummaryBranchWise = mgaService.getMGASummaryBranchWise(months, cities, qtrWise, halfYear);
            if (listMGASummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMGASummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
