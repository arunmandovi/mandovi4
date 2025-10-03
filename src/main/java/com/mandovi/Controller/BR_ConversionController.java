package com.mandovi.Controller;

import com.mandovi.DTO.BR_ConversionRevenueSummaryDTO;
import com.mandovi.DTO.BR_ConversionSummaryDTO;
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
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }

    @GetMapping("/getallbr_conversion")
    public List<BR_Conversion> getAllBR_Conversion() {
        return br_conversionService.getAllBR_Conversion();
    }

    @GetMapping("/getbr_conversion/{month}/{year}")
    public ResponseEntity<List<BR_Conversion>> getBR_ConversionByMonthYear(@PathVariable String month, @PathVariable String year) {
        List<BR_Conversion> brConversionRecords = br_conversionService.getBR_ConversionByMonthYear(month, year);
        if (brConversionRecords.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(brConversionRecords);
    }

    @GetMapping("/br_conversion_arena")
    public ResponseEntity<List<BR_ConversionSummaryDTO>> getBR_ConversionArenaSummary(
            @RequestParam String groupBy,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String qtr_wise,
            @RequestParam(required = false) String half_year) {
        try {
            List<BR_ConversionSummaryDTO> listBR_ConversionArena = br_conversionService.getBR_ConversionArenaSummary(groupBy, month, year, qtr_wise, half_year);
            if (listBR_ConversionArena.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listBR_ConversionArena);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("br_conversion_nexa")
    public ResponseEntity<List<BR_ConversionSummaryDTO>> getBR_ConversionNexaSummary(
            @RequestParam String groupBy,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String qtr_wise,
            @RequestParam(required = false) String half_year) {
        try {
            List<BR_ConversionSummaryDTO> listBR_ConversionNexa = br_conversionService.getBR_ConversionNexaSummary(groupBy, month, year, qtr_wise, half_year);
            if (listBR_ConversionNexa.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listBR_ConversionNexa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("br_conversion_revenue_arena")
    public ResponseEntity<List<BR_ConversionRevenueSummaryDTO>> getBR_ConversionRevenueArenaSummary(
            @RequestParam String groupBy,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String qtr_wise,
            @RequestParam(required = false) String half_year) {
        try {
            List<BR_ConversionRevenueSummaryDTO> listBR_ConversionRevenueArena = br_conversionService.getBR_ConversionRevenueArenaSummary(groupBy, month, year, qtr_wise, half_year);
            if (listBR_ConversionRevenueArena.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listBR_ConversionRevenueArena);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("br_conversion_revenue_nexa")
    public ResponseEntity<List<BR_ConversionRevenueSummaryDTO>> getBR_ConversionRevenueNexaSummary(
            @RequestParam String groupBy,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String qtr_wise,
            @RequestParam(required = false) String half_year) {
        try {
            List<BR_ConversionRevenueSummaryDTO> lisBR_ConversionRevenueNexa = br_conversionService.getBR_ConversionRevenueNexaSummary(groupBy, month, year, qtr_wise, half_year);
            if (lisBR_ConversionRevenueNexa.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(lisBR_ConversionRevenueNexa);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
