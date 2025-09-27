package com.mandovi.Service;

import com.mandovi.Entity.Adminn;
import com.mandovi.Entity.Employee;
import com.mandovi.Repository.AdminnRepository;
import com.mandovi.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminnServiceImpl implements AdminnService {
    private final AdminnRepository adminnRepository;
    private final EmployeeRepository employeeRepository;

    public AdminnServiceImpl(AdminnRepository adminnRepository, EmployeeRepository employeeRepository) {
        this.adminnRepository = adminnRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Adminn adminnLogin(String adminnId, String adminnPassword) {
        //Validate Required Fields
        if (adminnId == null || adminnId.trim().isEmpty()) {
            throw new RuntimeException("Admin ID is required");
        }
        Optional<Adminn> optionalAdminn = adminnRepository.findByAdminnId(adminnId);
        if (optionalAdminn.isEmpty()) {
            throw new RuntimeException("Admin not Exist with Admin Id : "+adminnId);
        }
        Adminn adminn = optionalAdminn.get();
        if (!adminn.getAdminnPassword().equals(adminnPassword)){
            throw new RuntimeException("Password is Incorrect");
        }
        return adminn;
    }

    @Override
    public Adminn registerAdminn(Adminn adminn) {
        //Validate Required Fields
        if (adminn.getAdminnName() == null || adminn.getAdminnName().trim().isEmpty()){
            throw new RuntimeException("Admin Name is Required");
        }
        if (adminn.getAdminnId() == null || adminn.getAdminnId().trim().isEmpty()) {
            throw new RuntimeException("Admin Id is required");
        }
        if (adminn.getAdminnPassword() ==null || adminn.getAdminnPassword().trim().isEmpty()){
            throw new RuntimeException("Admin Password is required");
        }
        Optional<Adminn> optionalAdminn = adminnRepository.findByAdminnId(adminn.getAdminnId());
        if (optionalAdminn.isPresent()) {
            throw new RuntimeException("Admin Already Exist with Id : "+adminn.getAdminnId());
        }
        //Creating new Admin
        Adminn newAdminn = new Adminn();
        newAdminn.setAdminnName(adminn.getAdminnName());
        newAdminn.setAdminnId(adminn.getAdminnId());
        newAdminn.setAdminnPassword(adminn.getAdminnPassword());
        adminnRepository.save(newAdminn);
        return newAdminn;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return adminnRepository.getAllEmployee();
    }

    @Override
    public Employee approveEmployee(String employeeId) {
        Employee employee = employeeRepository.getEmployeeByEmployeeId(employeeId);
        employee.setEmployeeStatus(Employee.Status.APPROVED);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee disableEmployee(String employeeId) {
        Employee employee = employeeRepository.getEmployeeByEmployeeId(employeeId);
        employee.setEmployeeStatus(Employee.Status.PENDING);
        return employeeRepository.save(employee);
    }


}
