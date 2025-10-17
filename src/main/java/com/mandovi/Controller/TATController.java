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

    @GetMapping("/gettat/{month}/{year}")
    public ResponseEntity<List<TAT>> getTATByMonthYear (@PathVariable String month, @PathVariable String year){
        List<TAT> TATRecords = tatService.getTATByMonthYear(month, year);
        if (TATRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(TATRecords);
    }

    @GetMapping("/tat_summary")
    public ResponseEntity<?> getTATSummary (
            @RequestParam (required = false, defaultValue = "city") String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<TATSummaryDTO> listTATSummary = tatService.getTATSummary(groupBy, month, qtrWise, halfYear);
            if (listTATSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listTATSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("ERROR : "+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Sever ERROR : "+e.getMessage());
        }
    }
}
