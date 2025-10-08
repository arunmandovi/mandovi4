package com.mandovi.Controller;

import com.mandovi.DTO.PMSPartsSummaryDTO;
import com.mandovi.Entity.PMSParts;
import com.mandovi.Service.PMSPartsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pms_parts")
public class PMSPartsController {
    private final PMSPartsService pmsPartsService;

    public PMSPartsController(PMSPartsService pmsPartsService) {
        this.pmsPartsService = pmsPartsService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPMSPartsExcel(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            pmsPartsService.savePMSPartsFromExcel(file);
            return ResponseEntity.ok().body("PMS Parts File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallpms_parts")
    public List<PMSParts> getAllPMS_Parts(){
        return pmsPartsService.getAllPMS_Parts();
    }

    @GetMapping("/getpms_parts/{pmsDate}")
    public ResponseEntity<List<PMSParts>> getPMSPartsByPMSDate(@PathVariable LocalDate pmsDate){
        List<PMSParts> pmsPartsRecords = pmsPartsService.getPMSPartsByPMSDate(pmsDate);
        if (pmsPartsRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pmsPartsRecords);
    }

    @GetMapping("/pms_parts_summary")
    public ResponseEntity<List<PMSPartsSummaryDTO>> getPMSPartsSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<PMSPartsSummaryDTO> listPMSPartsSummary = pmsPartsService.getPMSPartsSummary(groupBy, month, qtrWise, halfYear);
            if (listPMSPartsSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listPMSPartsSummary);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
