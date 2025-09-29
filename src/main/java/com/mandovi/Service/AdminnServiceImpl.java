package com.mandovi.Service;

import com.mandovi.Entity.Adminn;
import com.mandovi.Entity.Employee;
import com.mandovi.Repository.AdminnRepository;
import com.mandovi.Repository.EmployeeRepository;
import org.apache.poi.sl.draw.geom.GuideIf;
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
        newAdminn.setBranch(adminn.getBranch());
        newAdminn.setAdminnPassword(adminn.getAdminnPassword());
        adminnRepository.save(newAdminn);
        return newAdminn;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Adminn> getAllAdminn() {
        return adminnRepository.findAll();
    }



    @Override
    public String enableAdminn(String adminnId) {
        Optional<Adminn> optionalAdminn = adminnRepository.findByAdminnId(adminnId);
        if (optionalAdminn.isEmpty()) {
            throw new RuntimeException("Admin not exist with Id : "+ adminnId);
        }

        Adminn adminn = optionalAdminn.get();
        if (adminn.getAdminnStatus() == Adminn.Status.APPROVED){
            return adminn.getAdminnName() +" is already APPROVED as Admin";
        } else if (adminn.getAdminnStatus() == Adminn.Status.PENDING) {
            adminn.setAdminnStatus(Adminn.Status.APPROVED);
            adminnRepository.save(adminn);
            return adminn.getAdminnName()+" is APPROVED as Admin";
        }
        else {
            return "Invalid Status for Admin "+ adminn.getAdminnName();
        }
    }

    @Override
    public String disableAdminn(String adminnId) {
        Optional<Adminn> optionalAdminn = adminnRepository.findByAdminnId(adminnId);
        if (optionalAdminn.isEmpty()) {
            throw new RuntimeException("Admin does not exist with AdminId: "+adminnId);
        }

        Adminn adminn = optionalAdminn.get();
        if (adminn.getAdminnStatus() == Adminn.Status.APPROVED) {
            adminn.setAdminnStatus(Adminn.Status.PENDING);
            adminnRepository.save(adminn);
            return adminn.getAdminnName()+ " is REJECTED as Admin";
        } else if (adminn.getAdminnStatus() == Adminn.Status.PENDING) {
            return adminn.getAdminnName()+" is already REJECTED as Admin";
        }else {
            return "Invalid Status for Admin "+ adminn.getAdminnName();
        }
    }

    @Override
    public String enableEmployee(String employeeId) {
        Optional<Employee> optionalEmployee = adminnRepository.findByEmployeeId(employeeId);
        if (optionalEmployee.isEmpty()){
            throw new RuntimeException("Employee not Exist with EmployeeId: "+employeeId);
        }
        Employee employee =optionalEmployee.get();
        if (employee.getEmployeeStatus() == Employee.Status.PENDING) {
            employee.setEmployeeStatus(Employee.Status.APPROVED);
            employeeRepository.save(employee);
            return employee.getEmployeeName()+ " is APPROVED for the Employee Login";
        } else if (employee.getEmployeeStatus() == Employee.Status.APPROVED) {
            return employee.getEmployeeName()+" is already Approved for the Employee login";
        }else {
            return "Invalid Status for Employee "+employee.getEmployeeName();
        }
    }

    @Override
    public String disableEmployee(String employeeId) {
        Optional<Employee> optionalEmployee = adminnRepository.findByEmployeeId(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw  new RuntimeException("Employee not Exist with EmployeeID "+employeeId);
        }
        Employee employee = optionalEmployee.get();
        if (employee.getEmployeeStatus() == Employee.Status.APPROVED){
            employee.setEmployeeStatus(Employee.Status.PENDING);
            employeeRepository.save(employee);
            return employee.getEmployeeName()+" is Disabled from the Employee Login";
        } else if (employee.getEmployeeStatus() == Employee.Status.PENDING) {
            return employee.getEmployeeName()+" is already Rejected from Employee Login";
        }else {
            return "Invalid Status for Employee "+ employee.getEmployeeName();
        }
    }


}
