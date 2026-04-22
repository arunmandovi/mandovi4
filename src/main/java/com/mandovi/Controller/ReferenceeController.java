package com.mandovi.Controller;

import com.mandovi.DTO.ReferenceeSummaryDTO;
import com.mandovi.DTO.ReferenceeTableDTO;
import com.mandovi.Entity.Referencee;
import com.mandovi.Service.ReferenceeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/referencee")
public class ReferenceeController {
    private final ReferenceeService referenceeService;

    public ReferenceeController(ReferenceeService referenceeService) {
        this.referenceeService = referenceeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            referenceeService.saveReferenceFromExcel(file);
            return ResponseEntity.ok("Reference File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallreferencee")
    public List<Referencee> getAllReference(){
        return referenceeService.getAllReference();
    }

    @GetMapping("/getreferencee")
    public ResponseEntity<?> getReferenceeByMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<Referencee> referenceeRecords = referenceeService.getReferenceeByMonthYear(months, years, financialYears);
            if (referenceeRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(referenceeRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/referencee_summary")
    public ResponseEntity<?> getReferenceeSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<ReferenceeSummaryDTO> listReferenceeSummary = referenceeService.getReferenceeSummary(
                    months, channels, qtrWise, halfYear, financialYears);
            if (listReferenceeSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listReferenceeSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("ERROR : "+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server ERROR : "+e.getMessage());
        }
    }

    @GetMapping("referencee_branch_summary")
    public ResponseEntity<?> getReferenceeSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam (required = false) List<String> financialYears){
        try {
            List<ReferenceeSummaryDTO> listReferenceeSummaryBranchWise = referenceeService.getReferenceeSummaryBranchWise(
                    months, cities, channels, qtrWise, halfYear, financialYears);
            if (listReferenceeSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listReferenceeSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/delete_all")
    public ResponseEntity<?> deleteReferenceeALl (){
        try {
            referenceeService.deleteReferenceeAll();
            return ResponseEntity.ok().body("All data deleted from Referencee");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }

    @GetMapping("/referencee_table_summary")
    public ResponseEntity<?> getReferenceeTableCityWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> financialYears ){
        try {
            List<ReferenceeTableDTO> listReferenceeTableCityWise = referenceeService.getReferenceeTable(months, cities, financialYears);
            if (listReferenceeTableCityWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listReferenceeTableCityWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

}
