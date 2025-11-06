package com.mandovi.Controller;

import com.mandovi.DTO.PerVehicleReportSummaryDTO;
import com.mandovi.Service.PerVehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/per_vehicle")
public class PerVehicleController {
    private final PerVehicleService perVehicleService;

    public PerVehicleController(PerVehicleService perVehicleService) {
        this.perVehicleService = perVehicleService;
    }

    @GetMapping("/per_vehicle_summary")
    public ResponseEntity<?> getPerVehicleSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<PerVehicleReportSummaryDTO> listPerVehicleSummary = perVehicleService.getPerVehicleSummary(months, years, qtrWise, halfYear);
            if (listPerVehicleSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listPerVehicleSummary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/per_vehicle_branch_summary")
    public ResponseEntity<?> getPerVehicleSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<PerVehicleReportSummaryDTO> listPerVehicleSummaryBranchWise = perVehicleService.getPerVehicleSummaryBranchWise(months, years, cities, qtrWise, halfYear);
            if (listPerVehicleSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listPerVehicleSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
