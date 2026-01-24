package com.mandovi.Controller;

import com.mandovi.DTO.ServiceeSummaryDTO;
import com.mandovi.Entity.Servicee;
import com.mandovi.Service.ServiceeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping ("/api/servicee")
public class ServiceeController {
    private final ServiceeService serviceeService;

    public ServiceeController(ServiceeService serviceeService) {
        this.serviceeService = serviceeService;
    }

    @PostMapping ("/upload")
    public ResponseEntity<?> saveServicee (MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Upload a valid Excel");
        }
        try {
            serviceeService.saveServiceExcel(file);
            return ResponseEntity.ok("Servicee FIle uploaded");
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getallservicee")
    public ResponseEntity<?> getAllServicee (){
        try {
            List<Servicee> listServiceeAll = serviceeService.getServiceeAll();
            if (listServiceeAll.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listServiceeAll);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getservicee")
    public ResponseEntity<?> getService (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<Servicee> listServicee = serviceeService.getService(months, years);
            if (listServicee.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listServicee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/servicee_branch_summary")
    public ResponseEntity<?> getServiceSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years,
            @RequestParam (required = false) List<String> branches,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> serviceCodes ){
        try {
            List<ServiceeSummaryDTO> listServiceSummaryDTO = serviceeService.getServiceeSummaryBranchWise(months, years, branches, channels, serviceCodes);
            if (listServiceSummaryDTO.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listServiceSummaryDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
