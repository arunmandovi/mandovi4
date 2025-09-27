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

    @PutMapping("/approve_employee/{employeeId}")
    public ResponseEntity<?> approveEmployee(@PathVariable String employeeId){
        try {
            Employee employee = adminnService.approveEmployee(employeeId);
            return ResponseEntity.ok("Employee "+employee.getEmployeeName()+" Approved Successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error :"+e.getMessage());
        }
    }

    @PutMapping("/disable_emplyee/{employeeId}")
    public ResponseEntity<?> disableEmployee(@PathVariable String employeeId){
        try {
            Employee employee = adminnService.disableEmployee(employeeId);
            return ResponseEntity.ok("Employee "+employee.getEmployeeName()+" Disabled Successfuly");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : "+e.getMessage());
        }
    }
}
