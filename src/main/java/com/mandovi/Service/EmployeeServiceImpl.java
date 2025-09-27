package com.mandovi.Service;

import com.mandovi.Entity.Employee;
import com.mandovi.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee newEmployeeRegistration(Employee emp) {
        //  Validate required fields
        if (emp.getEmployeeName() == null || emp.getEmployeeName().trim().isEmpty()) {
            throw new RuntimeException("Employee Name is required");
        }
        if (emp.getEmployeeId() == null || emp.getEmployeeId().trim().isEmpty()) {
            throw new RuntimeException("Employee ID is required");
        }
        if (emp.getDesignation() == null || emp.getDesignation().trim().isEmpty()) {
            throw new RuntimeException("Designation is required");
        }
        if (emp.getEmployeePassword() == null || emp.getEmployeePassword().trim().isEmpty()) {
            throw new RuntimeException("Employee Password is required");
        }

        // ✅ Check if employeeId already exists
        Optional<Employee> existingEmployee = employeeRepository.findByEmployeeId(emp.getEmployeeId());
        if (existingEmployee.isPresent()) {
            throw new RuntimeException("Employee already exists with ID: " + emp.getEmployeeId());
        }

        Employee newEmp = new Employee();
        newEmp.setEmployeeName(emp.getEmployeeName());
        newEmp.setEmployeeId(emp.getEmployeeId());
        newEmp.setDesignation(emp.getDesignation());
        newEmp.setEmployeePassword(emp.getEmployeePassword());
        return employeeRepository.save(newEmp);
    }

    @Override
    public Employee loginEmployee(String employeeId, String employeePassword) {
        Employee employeeOptional = employeeRepository.getEmployeeByEmployeeId(employeeId);
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new RuntimeException("EmployeeId is required ");
        }
        if (employeeOptional.getEmployeeId() == null || employeeOptional.getEmployeeId().trim().isEmpty()){
            throw new RuntimeException("Employee does not exist with Employee ID : "+employeeId);
        }
        if (!employeeOptional.getEmployeePassword().equals(employeePassword)){
            throw new RuntimeException("Invalid Password");
        }
        if(employeeOptional.getEmployeeStatus() == Employee.Status.PENDING){
            throw new RuntimeException("Admin has not approved");
        }
        return employeeOptional;
    }
}
