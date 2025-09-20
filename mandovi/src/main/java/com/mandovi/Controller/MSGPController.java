package com.mandovi.Controller;

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
}
