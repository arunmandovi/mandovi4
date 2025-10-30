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

    @GetMapping("/getmsgp/{month}/{year}")
    public ResponseEntity<List<MSGP>> getMSGPByMonthYear(@PathVariable String month, @PathVariable String year){
        List<MSGP> msgpRecords = msgpService.getMSGPByMonthYear(month, year);
        if (msgpRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(msgpRecords);
    }

    @GetMapping("/msgp_summary")
    public ResponseEntity<?> getMSGPServiceBodyShopSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MSGPSummaryDTO> listMSGPServiceBodyShopSummary = msgpService.getMSGPSummary(months, qtrWise, halfYear);
            if (listMSGPServiceBodyShopSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPServiceBodyShopSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("ERROR :" + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internal Server ERROR : "+ e.getMessage());
        }
    }

    @GetMapping("msgp_branch_summary")
    public ResponseEntity<?> getMSGPSummaryBranchWise(
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> months ){
        try {
            List<MSGPSummaryDTO> listMSGPSummaryBranchWise = msgpService.getMSGPSummaryBranchWise(cities, months);
            if (listMSGPSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPSummaryBranchWise);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Internale Server ERROR : "+e.getMessage());
        }
    }

}
