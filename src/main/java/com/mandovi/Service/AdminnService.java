package com.mandovi.Service;

import com.mandovi.Entity.Adminn;
import com.mandovi.Entity.Employee;

import java.util.List;

public interface AdminnService {

    public Adminn adminnLogin (String adminnId, String adminnPassword);

    public Adminn registerAdminn (Adminn adminn);

    public List<Employee> getAllEmployee ();

    public Employee approveEmployee(String employeeId);

    public Employee disableEmployee(String employeeId);

}

