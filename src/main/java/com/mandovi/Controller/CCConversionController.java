package com.mandovi.Controller;

import com.mandovi.DTO.CCConversionDTO;
import com.mandovi.Entity.CCConversion;
import com.mandovi.Service.CCConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cc")
public class CCConversionController {
    private final CCConversionService ccConversionService;

    public CCConversionController(CCConversionService ccConversionService) {
        this.ccConversionService = ccConversionService;
    }

    @PostMapping("/upload")
    ResponseEntity<?> saveCCConversion (MultipartFile file){
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(" Upload a valid Excel");
            }
            ccConversionService.saveCCConversionFromExcel(file);
            return ResponseEntity.ok("Data Uploaded to CCConversion");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getallcc_conversion")
    ResponseEntity<?> getAllCCConversion (){
        try {
            List<CCConversion> listAllCCConversion = ccConversionService.getAllCCConversion();
            if (listAllCCConversion.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listAllCCConversion);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getcc_conversion")
    ResponseEntity<?> getCCConversionByMonth (@RequestParam(required = false) List<String> months){
        try {
            List<CCConversion> listCCConversionByMonth = ccConversionService.getCCConversionByMonth(months);
            if (listCCConversionByMonth.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listCCConversionByMonth);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/cc_conversion_summary")
    ResponseEntity<?> getCCConversionSummary (
            @RequestParam (required = false)List<String> months,
            @RequestParam (required = false) List<String> branches,
            @RequestParam (required = false) List<String> cceNames){
        try {
            List<CCConversionDTO> listCCConversion = ccConversionService.getCCConversionSummary(months, branches, cceNames);
            if (listCCConversion.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listCCConversion);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    ResponseEntity<?> deleteCCConversionAll (){
        try {
            ccConversionService.deleteCCConversionAll();
            return ResponseEntity.ok("CC Conversion All Data deleted ");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }
}
