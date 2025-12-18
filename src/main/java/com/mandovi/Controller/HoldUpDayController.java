package com.mandovi.Controller;

import com.mandovi.DTO.HoldUpDayDTO;
import com.mandovi.Service.HoldUpDayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hold_up")
public class HoldUpDayController {
    private final HoldUpDayService holdUpDayService;

    public HoldUpDayController(HoldUpDayService holdUpDayService) {
        this.holdUpDayService = holdUpDayService;
    }

    @GetMapping("gel_hold_up_day_list")
    ResponseEntity<?> getHoldUpDayList (){
        List<String> list = holdUpDayService.getHoldUpDayList();
        try {
            if (list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/hold_up_day_summary")
    ResponseEntity<?> getHoldUpDayCityWise (){
        try {
            List<HoldUpDayDTO> listHoldUpDayCityWise = holdUpDayService.getHoldUpDayByCity();
            if (listHoldUpDayCityWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listHoldUpDayCityWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/hold_up_day_branch_summary")
    ResponseEntity<?> getHoldUpDayBranchWise (@RequestParam (required = false) List<String> cities){
        try {
            List<HoldUpDayDTO> listHoldUpDayBranchWise = holdUpDayService.getHoldUpByBranch(cities);
            if (listHoldUpDayBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listHoldUpDayBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
