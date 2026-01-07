package com.mandovi.Controller;

import com.mandovi.DTO.SAConversionDTO;
import com.mandovi.Entity.SAConversion;
import com.mandovi.Service.SAConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/sa")
public class SAConversionController {
    private final SAConversionService saConversionService;

    public SAConversionController(SAConversionService saConversionService) {
        this.saConversionService = saConversionService;
    }

    @PostMapping("/upload")
    ResponseEntity<?> saveSAFromExcel (@RequestParam MultipartFile file){
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(" Upload Valid Excel ");
            }
            saConversionService.saveSAConversionFromExcel(file);
            return ResponseEntity.ok("SA Conversion File Uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getallsa_conversion")
    ResponseEntity<?> getALlSAConversion (){
        try {
            List<SAConversion> listAllSAConversion = saConversionService.getSAConversionALl();
            if (listAllSAConversion.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listAllSAConversion);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getsa_conversion")
    ResponseEntity<?> getSAConversionBYMonth ( @RequestParam (required = false) List<String> months ){
        try {
            List<SAConversion> listSAConversionByMonth = saConversionService.getSAConversionByMonth(months);
            if (listSAConversionByMonth.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listSAConversionByMonth);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/sa_conversion_summary")
    ResponseEntity<?> getSAConversionSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> branches,
            @RequestParam (required = false) List<String> saNames ){
        try {
            List<SAConversionDTO> listSAConversionSummary = saConversionService.getSAConversionSummary(months, branches, saNames);
            if (listSAConversionSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listSAConversionSummary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    ResponseEntity<?> deleteSAConversionALL (){
        try {
            saConversionService.deleteSAConversionALL();
            return ResponseEntity.ok("ALL DATA from SAConversion deleted");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }
}
