package com.mandovi.Controller;

import com.mandovi.Service.VASService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/vas")
public class VASController {
    private final VASService vas;
    private final VASService vASService;

    public VASController(VASService vas, VASService vASService) {
        this.vas = vas;
        this.vASService = vASService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVASFromExcel(MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            vASService.saveVASFromExcel(file);
            return ResponseEntity.ok().body("VAS File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
