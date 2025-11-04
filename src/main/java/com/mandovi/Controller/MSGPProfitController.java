package com.mandovi.Controller;

import com.mandovi.DTO.MSGPProfitSummaryDTO;
import com.mandovi.Entity.MSGPProfit;
import com.mandovi.Service.MSGPProfitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/msgp_profit")
public class MSGPProfitController {
    private final MSGPProfitService msgpProfitService;

    public MSGPProfitController(MSGPProfitService msgpProfitService) {
        this.msgpProfitService = msgpProfitService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMSGPProfitExcel(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            msgpProfitService.saveMSGPProfitFromExcel(file);
            return ResponseEntity.ok().body("MSGPProfit File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallmsgp_profit")
    public List<MSGPProfit> getALLMSGP_Profit(){
        return msgpProfitService.getAllMSGP_Profit();
    }

    @GetMapping("/getmsgp_profit")
    public ResponseEntity<List<MSGPProfit>> getMSGPProfitByMonthYear(
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years){
        List<MSGPProfit> msgpProfitRecords = msgpProfitService.getMSGPProfitByMonthYear(months, years);
        if (msgpProfitRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(msgpProfitRecords);
    }

    @GetMapping("/msgp_profit_summary")
    public ResponseEntity<?> getMSGPProfitSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MSGPProfitSummaryDTO> listMSGPProfitSummary = msgpProfitService.getMSGPProfitSummary(months, qtrWise, halfYear);
            if (listMSGPProfitSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPProfitSummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }

    @GetMapping("msgp_profit_branch_summary")
    public ResponseEntity<?> getMSGPSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MSGPProfitSummaryDTO> listMSGPProfitSummaryBranchWise = msgpProfitService.getMSGPProfitSummaryBranchWise(months, cities, qtrWise, halfYear);
            if (listMSGPProfitSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPProfitSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }
}
