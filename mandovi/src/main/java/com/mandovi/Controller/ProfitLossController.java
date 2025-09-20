package com.mandovi.Controller;

import com.mandovi.Entity.ProfitLoss;
import com.mandovi.Service.ProfitLossService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profit_loss")
public class ProfitLossController {
    private final ProfitLossService profitLossService;

    public ProfitLossController(ProfitLossService profitLossService) {
        this.profitLossService = profitLossService;
    }

    @PostMapping("/upload")
    ResponseEntity<String> uploadProfitLossExcel(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Profit & Loss Conversion Excel file.");
        }try {
            profitLossService.saveProfitLossExcel(file);
            return ResponseEntity.ok().body("Profit & Loss File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallprofit_loss")
    public List<Map<String, Object>> getALLProfit_Loss(){
        return profitLossService.getAllProfit_Loss();
    }
}
