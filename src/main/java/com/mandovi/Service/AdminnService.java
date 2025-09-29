package com.mandovi.Service;

import com.mandovi.Entity.Adminn;
import com.mandovi.Entity.Employee;

import java.util.List;

public interface AdminnService {

    public Adminn adminnLogin (String adminnId, String adminnPassword);

    public Adminn registerAdminn (Adminn adminn);

    public List<Employee> getAllEmployee ();

    public List<Adminn> getAllAdminn();

    public String enableAdminn(String adminnId);

    public String disableAdminn(String adminnId);

    public String enableEmployee(String employeeId);

    public String disableEmployee(String employeeId);

}

