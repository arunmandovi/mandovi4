package com.mandovi.Controller;

import com.mandovi.DTO.HoldUpDTO;
import com.mandovi.Service.HoldUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/hold_up")
public class HoldUpController {
    private final HoldUpService holdUpService;

    public HoldUpController(HoldUpService holdUpService) {
        this.holdUpService = holdUpService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadHoldUpExcel (@RequestParam ("file")MultipartFile file){
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
            }
        try {
            holdUpService.saveHoldUpFromExcel(file);
            return ResponseEntity.ok("HoldUp File Uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/hold_up_table_summary")
    public ResponseEntity<?> getHoldUpDTO (){
        try {
            List<HoldUpDTO> listHoldUpDTOCityWise = holdUpService.getHoldUpDTOCityWise();
            if (listHoldUpDTOCityWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listHoldUpDTOCityWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/hold_up_table_branch_summary")
    public ResponseEntity<?> getHoldUpDTOBranchWise (@RequestParam (required = false) List<String> cities){
        try {
            List<HoldUpDTO> listHoldUpDTOBranchWise = holdUpService.getHoldUpDTOBranchWise(cities);
            if (listHoldUpDTOBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listHoldUpDTOBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }
}
