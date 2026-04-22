package com.mandovi.Controller;

import com.mandovi.DTO.MSGPSummaryDTO;
import com.mandovi.Entity.MSGP;
import com.mandovi.Service.MSGPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/msgp")
public class MSGPController {
    private final MSGPService msgpService;

    public MSGPController(MSGPService msgpService) {
        this.msgpService = msgpService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMSGPExcel(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }
        try {
            msgpService.saveMSGPFromExcel(file);
            return ResponseEntity.ok().body("MSGP File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallmsgp")
    public List<MSGP> getALLMSGP(){
        return msgpService.getAllMSGP();
    }

    @GetMapping("/getmsgp")
    public ResponseEntity<?> getMSGPByMonthYear(
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> financialYears){
        try {
            List<MSGP> msgpRecords = msgpService.getMSGPByMonthYear(months, years, financialYears);
            if (msgpRecords.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(msgpRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/msgp_summary")
    public ResponseEntity<?> getMSGPServiceBodyShopSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam String selectedFinancialYear ){
        try {
            List<MSGPSummaryDTO> listMSGPServiceBodyShopSummary = msgpService.getMSGPSummary(months,
                    qtrWise,halfYear, selectedFinancialYear );
            if (listMSGPServiceBodyShopSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPServiceBodyShopSummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("ERROR : "+ e.getMessage());
        }
    }

    @GetMapping("/msgp_branch_summary")
    public ResponseEntity<?> getMSGPSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear,
            @RequestParam String selectedFinancialYear){
        try {
            List<MSGPSummaryDTO> listMSGPSummaryBranchWise = msgpService.getMSGPSummaryBranchWise(months,
                    cities, qtrWise, halfYear, selectedFinancialYear);
            if (listMSGPSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteMSGPAll (){
        try {
            msgpService.deleteMSGPAll();
            return ResponseEntity.ok().body(" ALL DATA deleted from MSGP");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

}
