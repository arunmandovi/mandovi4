package com.mandovi.Controller;

import com.mandovi.DTO.ServiceLoadSummaryDTO;
import com.mandovi.Entity.ServiceLoad;
import com.mandovi.Service.ServiceLoadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/service_load")
public class ServiceLoadController {
    private final ServiceLoadService serviceLoadService;

    public ServiceLoadController(ServiceLoadService serviceLoadService) {
        this.serviceLoadService = serviceLoadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> saveServiceLoad (MultipartFile file){
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Upload a valid Excel");
            }
            serviceLoadService.saveServiceLoad(file);
            return ResponseEntity.ok("ServiceLoad File uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getallservice_load")
    public ResponseEntity<?> getServiceLoadAll (){
        try {
            List<ServiceLoad> listAllServiceLoad = serviceLoadService.getServiceLoadAll();
            if (listAllServiceLoad.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listAllServiceLoad);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getservice_load")
    public ResponseEntity<?> getServiceLoadByMonthYear(
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<ServiceLoad> listServiceLoad = serviceLoadService.getServiceLoadByMonthYear(months, years);
            if (listServiceLoad.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listServiceLoad);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteServiceLoadAll (){
        try {
            serviceLoadService.deleteServiceLoadAll();
            return ResponseEntity.ok("ServiceLoadd ALL deleted");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/service_load_summary")
    public ResponseEntity<?> getServiceLoadSummaryCityWise (
            @RequestParam(required = false) List<String> cities,
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> financialYears,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> serviceTypes ){
        try {
            List<ServiceLoadSummaryDTO> serviceLoadSummaryDTOList = serviceLoadService.getServiceLoadSummaryCityWise(cities, months, financialYears, channels, serviceTypes);
            if (serviceLoadSummaryDTOList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(serviceLoadSummaryDTOList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR :"+e.getMessage());
        }
    }
}
