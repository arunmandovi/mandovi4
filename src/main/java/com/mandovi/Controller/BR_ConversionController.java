package com.mandovi.Controller;

import com.mandovi.Entity.BR_Conversion;
import com.mandovi.Service.BR_ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/br_conversion")
public class BR_ConversionController {
    private final BR_ConversionService br_conversionService;

    public BR_ConversionController(BR_ConversionService brConversionService) {
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
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallbr_conversion")
    public List<BR_Conversion> getAllBR_Conversion(){
        return br_conversionService.getAllBR_Conversion();
    }

    @GetMapping("/getbr_conversion/{month}/{year}")
    public ResponseEntity<List<BR_Conversion>> getBR_ConversionByMonthYear(@PathVariable String month, @PathVariable String year){
        List<BR_Conversion> brConversionRecords = br_conversionService.getBR_ConversionByMonthYear(month, year);
        if (brConversionRecords.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(brConversionRecords);
    }
}