package com.mandovi.Repository;

import com.mandovi.Entity.Adminn;
import com.mandovi.Entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminnRepository extends JpaRepository<Adminn, Integer> {

    @Transactional
    @Query("SELECT a FROM Adminn a WHERE a.adminnId = :adminnId")
    Optional<Adminn> findByAdminnId (@Param("adminnId") String adminnId);

    @Query("SELECT e FROM Employee e")
    public List<Employee> getAllEmployee();
}
