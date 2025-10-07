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

    @GetMapping("/getmsgp_profit/{month}/{year}")
    public ResponseEntity<List<MSGPProfit>> getMSGPProfitByMonthYear(@PathVariable String month, @PathVariable String year){
        List<MSGPProfit> msgpProfitRecords = msgpProfitService.getMSGPProfitByMonthYear(month, year);
        if (msgpProfitRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(msgpProfitRecords);
    }

    @GetMapping("/msgp_profit_summary")
    public ResponseEntity<List<MSGPProfitSummaryDTO>> getMSGPProfitSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MSGPProfitSummaryDTO> listMSGPProfitSummary = msgpProfitService.getMSGPProfitSummary(groupBy, month, qtrWise, halfYear);
            if (listMSGPProfitSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPProfitSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
