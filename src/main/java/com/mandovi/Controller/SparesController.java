package com.mandovi.Controller;

import com.mandovi.DTO.SparesSummaryDTO;
import com.mandovi.Entity.Spares;
import com.mandovi.Service.SparesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/spares")
public class SparesController {
    private final SparesService sparesService;

    public SparesController(SparesService sparesService) {
        this.sparesService = sparesService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadSparesExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            sparesService.saveSparesDataFromExcel(file);
            return ResponseEntity.ok("Spares File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallspares")
    public List<Spares> getAllSpares(){
        return sparesService.getAllSpares();
    }

    @GetMapping("/getspares")
    public ResponseEntity<?> getSparesByMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<Spares> sparesRecords = sparesService.getSparesByMonthYear(months, years);
            if (sparesRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(sparesRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/spares_summary")
    public ResponseEntity<?> getSparesSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<SparesSummaryDTO> listSparesSummary = sparesService.getSparesSummary(months, qtrWise, halfYear);
            if (listSparesSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listSparesSummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/spares_branch_summary")
    public ResponseEntity<?> getSparesSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<SparesSummaryDTO> listSparesSummaryBranchWise = sparesService.getSparesSummaryBranchWise(months, cities, qtrWise, halfYear);
            if (listSparesSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listSparesSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteSparesAll (){
        try {
            sparesService.deleteSparesAll();
            return ResponseEntity.ok().body("ALL DATA deleted from Spares");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
