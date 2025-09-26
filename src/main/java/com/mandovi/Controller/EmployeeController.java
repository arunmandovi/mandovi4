package com.mandovi.Controller;

import com.mandovi.Entity.Employee;
import com.mandovi.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/new_emp_registration")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee emp) {
        try {
            Employee newEmployee = employeeService.newEmployeeRegistration(emp);
            return ResponseEntity.ok(newEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error : "+e.getMessage());
        }
    }

    @PostMapping("/login_employee")
    public ResponseEntity<?> loginEmployee(@RequestParam String employeeId,@RequestParam String employeePassword){
        try {
            Employee employee = employeeService.loginEmployee(employeeId, employeePassword);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error :"+e.getMessage());
        }
    }
}
