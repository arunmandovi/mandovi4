package com.mandovi.Controller;

import com.mandovi.Entity.MGAProfit;
import com.mandovi.Service.MGAProfitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mga_profit")
public class MGAProfitController {
    private final MGAProfitService mgaProfitService;

    public MGAProfitController(MGAProfitService mgaProfitService) {
        this.mgaProfitService = mgaProfitService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadMGAProfitExcel (@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Upload a valid file");
        } try {
            mgaProfitService.saveMGAProfitFromExcel(file);
            return ResponseEntity.ok().body("MGA Profit file Uploaded Successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }

    @GetMapping("/getallmga_profit")
    public ResponseEntity<?> getAllMGAProfit (){
        List<MGAProfit> listMGAProfit = mgaProfitService.getALLMGAProfit();
        if (listMGAProfit.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listMGAProfit);
    }
}
