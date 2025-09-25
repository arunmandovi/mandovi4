package com.mandovi.Repository;

import com.mandovi.Entity.Revenue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    @Transactional
    @Query("SELECT r FROM Revenue r WHERE r.month = :month AND r.year = :year")
    public List<Revenue> getRevenueByMonthYear(@Param("month") String month, @Param("year") String year);
}
