package com.mandovi.Repository;

import com.mandovi.Entity.MGAProfit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MGAProfitRepository extends JpaRepository<MGAProfit, Integer> {

    @Transactional
    @Query("""
            SELECT m from MGAProfit m
            WHERE (:months IS NULL OR m.month IN (:months))
            AND (:years IS NULL OR m.year IN (:years))
            """)
    public List<MGAProfit> getMGAProfitMonthYear (
            @Param("months") List<String> months,
            @Param("years") List<String> years );
}
