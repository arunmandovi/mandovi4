package com.mandovi.Controller;

import com.mandovi.DTO.TATSummaryDTO;
import com.mandovi.Entity.TAT;
import com.mandovi.Service.TATService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/tat")
public class TATController {
    private final TATService tatService;

    public TATController(TATService tatService) {
        this.tatService = tatService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTatExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            tatService.saveLoadDataFromExcel(file);
            return ResponseEntity.ok("Tat File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getalltat")
    public List<TAT> getAllTat(){
        return tatService.getAllTat();
    }

    @GetMapping("/gettat")
    public ResponseEntity<?> getTATByMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<TAT> tatRecords = tatService.getTATByMonthYear(months, years);
            if (tatRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(tatRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/tat_summary")
    public ResponseEntity<?> getTATSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<TATSummaryDTO> listTATSummary = tatService.getTATSummary(months, qtrWise, halfYear);
            if (listTATSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listTATSummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/tat_branch_summary")
    public ResponseEntity<?> getTATSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<TATSummaryDTO> listTATSummaryBranchWise = tatService.getTATSummaryBranchWise(months, cities, qtrWise, halfYear);
            if (listTATSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listTATSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteTATAll (){
        try {
            tatService.deleteTATAll();
            return ResponseEntity.ok().body("ALL DATA deleted from TAT");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }
}
