package com.mandovi.Controller;

import com.mandovi.DTO.HoldUpSummaryDTO;
import com.mandovi.Service.HoldUpSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hold_up")
public class HoldUpSummaryController {
    private final HoldUpSummaryService holdUpSummaryService;

    public HoldUpSummaryController(HoldUpSummaryService holdUpSummaryService) {
        this.holdUpSummaryService = holdUpSummaryService;
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
}
