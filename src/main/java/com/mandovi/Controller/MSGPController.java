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

    @GetMapping("/msgp_service_bodyshop")
    public ResponseEntity<List<MSGPSummaryDTO>> getMSGPServiceBodyShopSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MSGPSummaryDTO> listMSGPServiceBodyShopSummary = msgpService.getMSGPServiceBodyShopSummary(groupBy, month, qtrWise, halfYear);
            if (listMSGPServiceBodyShopSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPServiceBodyShopSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/msgp_service")
    public ResponseEntity<List<MSGPSummaryDTO>> getMSGPServiceSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MSGPSummaryDTO> listMSGPServiceSummary = msgpService.getMSGPServiceSummary(groupBy, month, qtrWise, halfYear);
            if (listMSGPServiceSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPServiceSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/msgp_bodyshop")
    public ResponseEntity<List<MSGPSummaryDTO>> getMSGPBodyShopSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MSGPSummaryDTO> listMSGPBodyShopSummary = msgpService.getMSGPBodyShopSummary(groupBy, month, qtrWise, halfYear);
            if (listMSGPBodyShopSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPBodyShopSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/msgp_freeservice")
    public ResponseEntity<List<MSGPSummaryDTO>> getMSGPFreeServiceSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MSGPSummaryDTO> listMSGPFreeServiceSummary = msgpService.getMSGPFreeServiceSummary(groupBy, month, qtrWise, halfYear);
            if (listMSGPFreeServiceSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPFreeServiceSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/msgp_pms")
    public ResponseEntity<List<MSGPSummaryDTO>> getMSGPPMSSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MSGPSummaryDTO> listMSGPPMSSummary = msgpService.getMSGPPMSSummary(groupBy, month, qtrWise, halfYear);
            if (listMSGPPMSSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPPMSSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("msgp_running_repair")
    public ResponseEntity<List<MSGPSummaryDTO>> getMSGPRunningRepairSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MSGPSummaryDTO> listMSGPRunningRepairSummary = msgpService.getMSGPRunningRepairSummary(groupBy, month, qtrWise, halfYear);
            if (listMSGPRunningRepairSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPRunningRepairSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("msgp_others")
    public ResponseEntity<List<MSGPSummaryDTO>> getMSGPOthersSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MSGPSummaryDTO> listMSGPOthersSummary = msgpService.getMSGPOthersSummary(groupBy, month, qtrWise, halfYear);
            if (listMSGPOthersSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMSGPOthersSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
