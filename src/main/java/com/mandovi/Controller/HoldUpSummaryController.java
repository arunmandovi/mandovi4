package com.mandovi.Controller;

import com.mandovi.DTO.HoldUpSummaryDTO;
import com.mandovi.Entity.HoldUpSummary;
import com.mandovi.Service.HoldUpSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hold_up")
public class HoldUpSummaryController {
    private final HoldUpSummaryService holdUpSummaryService;

    public HoldUpSummaryController(HoldUpSummaryService holdUpSummaryService) {
        this.holdUpSummaryService = holdUpSummaryService;
    }

    @GetMapping("/getallhold_up")
    public ResponseEntity<?> getAllHoldUp (){
        try {
            List<HoldUpSummary> holdUpSummaryList = holdUpSummaryService.getAllHoldUp();
            if (holdUpSummaryList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(holdUpSummaryList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/gethold_up")
    public ResponseEntity<?> getHoldUpByMonthYear (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<HoldUpSummary> holdUpSummaryListByMonthYear = holdUpSummaryService.getHoldUpByMonthYear(months, years);
            if (holdUpSummaryListByMonthYear.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(holdUpSummaryListByMonthYear);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/hold_up_summary")
    ResponseEntity<?> getHoldUpSummaryCityWise (
            @RequestParam String month,
            @RequestParam String day ){
        try {
            List<HoldUpSummaryDTO> listHoldUpSummaryCityWise = holdUpSummaryService.getHoldUpSummaryCityWise(month, day);
            if (listHoldUpSummaryCityWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listHoldUpSummaryCityWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/hold_up_branch_summary")
    ResponseEntity<?> getHoldUpSummaryBranchWise (
            @RequestParam String month,
            @RequestParam String day,
            @RequestParam (required = false) List<String> cities){
        try {
            List<HoldUpSummaryDTO> listHoldUpSummaryBranchWise = holdUpSummaryService.getHoldUpSummaryBranchWise( month, day, cities );
            if (listHoldUpSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listHoldUpSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    ResponseEntity<?> deleteAllHoldUp (){
        try {
            holdUpSummaryService.deleteHodUpAll();
            return ResponseEntity.ok("Deleted Hold Up Summary");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error : "+e.getMessage());
        }
    }

}
