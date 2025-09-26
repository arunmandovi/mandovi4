package com.mandovi.Controller;

import com.mandovi.Entity.Adminn;
import com.mandovi.Service.AdminnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminn")
public class AdminnController {
    private final AdminnService adminnService;

    public AdminnController(AdminnService adminnService) {
        this.adminnService = adminnService;
    }

    @PostMapping("/login_adminn")
    public ResponseEntity<?> loginAdminn(@RequestParam String adminnId, @RequestParam String adminnPassword){
        try {
            Adminn newAdminn = adminnService.adminnLogin(adminnId, adminnPassword);
            return ResponseEntity.ok(newAdminn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error : "+e.getMessage());
        }
    }

    @PostMapping("/new_adminn_registration")
    public ResponseEntity<?> registerAdminn(@RequestBody Adminn adminn){
        try {
            Adminn newAdminn = adminnService.registerAdminn(adminn);
            return ResponseEntity.ok(newAdminn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error : " + e.getMessage());
        }
    }
}
