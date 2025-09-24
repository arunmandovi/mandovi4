package com.mandovi.Repository;

import com.mandovi.Entity.Loadd;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoaddRepository extends JpaRepository<Loadd, Integer> {

    @Transactional
    @Query("SELECT l FROM Loadd l WHERE l.month = :month AND l.year = :year ")
    List<Loadd> getLoadByMonthYear(@Param("month") String month, @Param("year") String year);
}
