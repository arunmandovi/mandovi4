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
    private final BRConversionService br_conversionService;

    public BRConversionController(BRConversionService brConversionService) {
        br_conversionService = brConversionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBR_ConversionExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid BR Conversion Excel file.");
        }
        try {
            br_conversionService.saveBR_ConversionDataFromExcel(file);
            return ResponseEntity.ok(" BR Conversion File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }

    @GetMapping("/getallbr_conversion")
    public List<BRConversion> getAllBR_Conversion() {
        return br_conversionService.getAllBRConversion();
    }

    @GetMapping("/getbr_conversion/{month}/{year}")
    public ResponseEntity<List<BRConversion>> getBR_ConversionByMonthYear(@PathVariable String month, @PathVariable String year) {
        List<BRConversion> brConversionRecords = br_conversionService.getBRConversionByMonthYear(month, year);
        if (brConversionRecords.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(brConversionRecords);
    }

    @GetMapping("/br_conversion_summary")
    public ResponseEntity<?> getBRConversionSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam(required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<BRConversionSummaryDTO> listBRConversionSummary = br_conversionService.getBRConversionSummary(groupBy, month, qtrWise, halfYear);
            if (listBRConversionSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listBRConversionSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("ERROR : "+ e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server ERROR :"+ e.getMessage());
        }
    }
}
