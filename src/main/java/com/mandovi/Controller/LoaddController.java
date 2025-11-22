package com.mandovi.Controller;

import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.Entity.Loadd;
import com.mandovi.Service.LoaddService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/loadd")
public class LoaddController {
    private final LoaddService loaddService;

    public LoaddController(LoaddService loaddService) {
        this.loaddService = loaddService;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadLoadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            loaddService.saveLoadDataFromExcel(file);
            return ResponseEntity.ok("Load File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallloadd")
    public ResponseEntity<?> getAllLoadData(){

        try {
            List<Loadd> loaddRecords = loaddService.getAllLoadData();
            if (loaddRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(loaddRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }

    @GetMapping("/getloadd")
    public ResponseEntity<?> getLoaddMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<Loadd> loaddRecords = loaddService.getLoadByMonthYear(months, years);
            if (loaddRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(loaddRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/loadd_summary")
    public ResponseEntity<?> getLoaddServiceSummary(
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<LoaddSummaryDTO> listLoaddServiceSummary = loaddService.getLoaddSummary(months, channels, qtrWise, halfYear);
            if (listLoaddServiceSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddServiceSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("ERROR :"+ e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server ERROR : "+ e.getMessage());
        }
    }

    @GetMapping("loadd_branch_summary")
    public ResponseEntity<?> getLoaddSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> branches,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear){
        try {
            List<LoaddSummaryDTO> listLoaddSummaryBranchWise = loaddService.getLoaddSummaryBranchWise(months, cities, branches, channels, qtrWise, halfYear);
            if (listLoaddSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listLoaddSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal Server Error : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteLoaddAll (){
        try {
            loaddService.deleteLoaddAll();
            return ResponseEntity.ok("All Data Deleted from Load");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error :"+e.getMessage());
        }
    }

}
