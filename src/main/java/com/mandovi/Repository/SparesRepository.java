package com.mandovi.Repository;

import com.mandovi.Entity.Spares;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SparesRepository extends JpaRepository<Spares, Integer> {

    @Transactional
    @Query("SELECT s FROM Spares s WHERE s.month = :month AND s.year = :year")
    public List<Spares> getSparedByMonthYear(@Param("month") String month, @Param("year") String year);
}
