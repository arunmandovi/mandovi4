package com.mandovi.Repository;

import com.mandovi.Entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Transactional
    @Query("SELECT e FROM Employee e WHERE e.employeeId = :employeeId")
    Optional<Employee> findByEmployeeId(@Param("employeeId") String employeeId);

    @Query("SELECT e FROM Employee e WHERE e.employeeId = :employeeId")
    Employee getEmployeeByEmployeeId(@Param("employeeId")String employeeId);

}
