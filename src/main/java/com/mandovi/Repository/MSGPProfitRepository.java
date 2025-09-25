package com.mandovi.Repository;

import com.mandovi.Entity.MSGPProfit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MSGPProfitRepository extends JpaRepository<MSGPProfit, Integer> {

    @Transactional
    @Query("SELECT m FROM MSGPProfit m WHERE m.month = :month AND m.year = :year")
    public List<MSGPProfit> getMSGPProfitByMonthYear (@Param("month") String month,@Param("year") String year);
}
