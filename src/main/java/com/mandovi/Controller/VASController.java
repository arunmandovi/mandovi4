package com.mandovi.Controller;

import com.mandovi.DTO.VASSummaryDTO;
import com.mandovi.Entity.VAS;
import com.mandovi.Service.VASService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/vas")
public class VASController {
    private final VASService vas;
    private final VASService vasService;

    public VASController(VASService vas, VASService vasService) {
        this.vas = vas;
        this.vasService = vasService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVASFromExcel(MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            vasService.saveVASFromExcel(file);
            return ResponseEntity.ok().body("VAS File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallvas")
    public List<VAS> getAllVas(){
        return vasService.getAllVas();
    }

    @GetMapping("/getvas/{month}/{year}")
    public ResponseEntity<List<VAS>> getVASByMonthYear (@PathVariable String month, @PathVariable String year){
        List<VAS> vasRecords = vasService.getVASByMonthYear(month, year);
        if (vasRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vasRecords);
    }

    @GetMapping("/vas_summary")
    public ResponseEntity<?> getVASSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<VASSummaryDTO> listVAS = vasService.getVASSummary(months, qtrWise , halfYear );
            if (listVAS.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listVAS);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/vas_branch_summary")
    public ResponseEntity<?> getVASSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<VASSummaryDTO> listVASSummaryBranchWise = vasService.getVASSummaryBranchWise(months, cities, qtrWise, halfYear);
            if (listVASSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listVASSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
