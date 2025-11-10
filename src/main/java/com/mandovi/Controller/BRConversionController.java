package com.mandovi.Controller;

import com.mandovi.DTO.BRConversionSummaryDTO;
import com.mandovi.Entity.BRConversion;
import com.mandovi.Service.BRConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/br_conversion")
public class BRConversionController {
    private final BRConversionService brConversionService;

    public BRConversionController(BRConversionService brConversionService) {
        this.brConversionService = brConversionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBR_ConversionExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid BR Conversion Excel file.");
        }
        try {
            brConversionService.saveBR_ConversionDataFromExcel(file);
            return ResponseEntity.ok(" BR Conversion File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }

    @GetMapping("/getallbr_conversion")
    public List<BRConversion> getAllBR_Conversion() {
        return brConversionService.getAllBRConversion();
    }

    @GetMapping ("/getbr_conversion")
    public ResponseEntity<?> getBRConversionMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<BRConversion> brConversionRecords = brConversionService.getBRConversionByMonthYear(months, years);
            if (brConversionRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(brConversionRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }

    @GetMapping("/br_conversion_summary")
    public ResponseEntity<?> getBRConversionSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam(required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<BRConversionSummaryDTO> listBRConversionSummary = brConversionService.getBRConversionSummary(months, qtrWise, halfYear);
            if (listBRConversionSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listBRConversionSummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR :"+ e.getMessage());
        }
    }

    @GetMapping("/br_conversion_branch_summary")
    public ResponseEntity<?> getBRConversionSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear){
        try {
            List<BRConversionSummaryDTO> listBRConversionSummaryBranchWise = brConversionService.getBRConversionSummaryBranchWise(months, cities, qtrWise, halfYear);
            if (listBRConversionSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listBRConversionSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteBRConversionAll (){
        try {
            brConversionService.deleteBRConversionAll();
            return ResponseEntity.ok().body("ALL DATA Deleted from BRConversion");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
