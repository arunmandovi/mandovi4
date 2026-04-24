package com.mandovi.Controller;

import com.mandovi.DTO.DueDoneSummaryDTO;
import com.mandovi.Entity.DueDone;
import com.mandovi.Service.DueDoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/due_done")
public class DueDoneController {
    private final DueDoneService dueDoneService;

    public DueDoneController(DueDoneService dueDoneService) {
        this.dueDoneService = dueDoneService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadExcel (MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            dueDoneService.saveDataFromExcel(file);
            return ResponseEntity.ok("DueVsDone File has been uploaded Successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getalldue_done")
    public ResponseEntity<?> getAllDueDone (){
        try {
            List<DueDone> dueDoneRecords = dueDoneService.getALlDueDoneData();
            if (dueDoneRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(dueDoneRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getdue_done")
    public ResponseEntity<?> getDueDoneByMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<DueDone> dueDoneRecords = dueDoneService.getDueDoneByMonthYear(months, years, financialYears);
            if (dueDoneRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(dueDoneRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public void deleteDueDoneAll (){
        dueDoneService.deleteDueDoneAll();
    }

    @GetMapping("/due_done_summary")
    public ResponseEntity<?> getDueDoneSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<DueDoneSummaryDTO> listDueDoneSummary = dueDoneService.getDueDoneSummary(
                    months, channels, qtrWise, halfYear, financialYears);
            if (listDueDoneSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listDueDoneSummary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/due_done_branch_summary")
    public ResponseEntity<?> getDueDoneSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<DueDoneSummaryDTO> listDueDoneSummaryBranchWise = dueDoneService.getDueDoneSummaryByBranchWise(
                    months, cities, channels, qtrWise, halfYear,financialYears );
            if (listDueDoneSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listDueDoneSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
