package com.mandovi.Controller;

import com.mandovi.Entity.Outstanding;
import com.mandovi.Service.OutstandingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/outstanding")
public class OutstandingController {
    private final OutstandingService outstandingService;

    public OutstandingController(OutstandingService outstandingService) {
        this.outstandingService = outstandingService;
    }

    @PostMapping("/upload")
    ResponseEntity<?> saveOutstandingFromExcel(MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload valid Excel");
        }
        try {
            outstandingService.saveOutstandingFromExcel(file);
            return ResponseEntity.ok("Outstanding Excel uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getalloutstanding")
    ResponseEntity<?> getAllOutstanding (){
        try {
            List<Outstanding> listAllOutstanding = outstandingService.getOutstandingAll();
            if (listAllOutstanding.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listAllOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getoutstanding")
    ResponseEntity<?> getDifferentOutstanding (@RequestParam (required = false) List<String> types){
        try {
            List<Outstanding> listOutstanding = outstandingService.getDifferentOutstanding(types);
            if (listOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/delete_all")
    ResponseEntity<?> deleteOutstandingAll (){
        try {
            outstandingService.deleteOutstandingAll();
            return ResponseEntity.ok("Outstanding deleted completely");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
