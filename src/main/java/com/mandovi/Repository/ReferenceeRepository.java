package com.mandovi.Repository;

import com.mandovi.Entity.Referencee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceeRepository extends JpaRepository<Referencee, Integer> {

    @Transactional
    @Query("SELECT r FROM Referencee r WHERE r.month = :month AND r.year = :year")
    public List<Referencee> getReferenceeByMonthYear(@Param("month") String month,@Param("year") String year);
}
