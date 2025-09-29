package com.mandovi.Controller;

import com.mandovi.Entity.Adminn;
import com.mandovi.Entity.Employee;
import com.mandovi.Service.AdminnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getallemployee")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeRecords = adminnService.getAllEmployee();
        if (employeeRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employeeRecords);
    }

    @GetMapping("/getalladminn")
    public ResponseEntity<List<Adminn>> getAllAdminn(){
            List<Adminn> adminnRecords = adminnService.getAllAdminn();
            if (adminnRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(adminnRecords);
    }

    @PutMapping("/enable_adminn/{adminnId}")
    public ResponseEntity<String> enableAdmin(@PathVariable String adminnId){
            String response = adminnService.enableAdminn(adminnId);
            return ResponseEntity.ok(response);
    }

    @PutMapping("/disable_adminn/{adminnId}")
    public ResponseEntity<String> disableAdmin(@PathVariable String adminnId){
        String response = adminnService.disableAdminn(adminnId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/enable_employee/{employeeId}")
    public ResponseEntity<String> enableEmployee(@PathVariable String employeeId){
        String response = adminnService.enableEmployee(employeeId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/disable_employee/{employeeId}")
    public ResponseEntity<String> disableEmployee(@PathVariable String employeeId){
        String response = adminnService.disableEmployee(employeeId);
        return ResponseEntity.ok(response);
    }
}
