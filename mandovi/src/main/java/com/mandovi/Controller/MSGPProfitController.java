package com.mandovi.Controller;

import com.mandovi.Service.MSGPProfitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/msgpprofit")
public class MSGPProfitController {
    private final MSGPProfitService msgpProfitService;

    public MSGPProfitController(MSGPProfitService msgpProfitService) {
        this.msgpProfitService = msgpProfitService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMSGPProfitExcel(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            msgpProfitService.saveMSGPProfitFromExcel(file);
            return ResponseEntity.ok().body("MSGPProfit File has been uploaded successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }
}
