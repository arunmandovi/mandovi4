package com.mandovi.Controller;

import com.mandovi.DTO.ReferenceeSummaryDTO;
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

    @GetMapping("/getreferencee/{month}/{year}")
    public ResponseEntity<List<Referencee>> getReferenceeByMonthYear (@PathVariable String month, @PathVariable String year ){
        List<Referencee> referencesRecords = referenceeService.getReferenceeByMonthYear(month, year);
        if(referencesRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(referencesRecords);
    }

    @GetMapping("/referencee_summary")
    public ResponseEntity<?> getReferenceeSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<ReferenceeSummaryDTO> listReferenceeSummary = referenceeService.getReferenceeSUmmary(groupBy, months, qtrWise, halfYear);
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

}
