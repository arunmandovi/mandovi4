package com.mandovi.Service;

import com.mandovi.Entity.Employee;

public interface EmployeeService {

    public Employee newEmployeeRegistration(Employee emp);

    public Employee loginEmployee(String employeeId, String employeePassword);

}
