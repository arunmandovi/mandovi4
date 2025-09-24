package com.mandovi.Repository;

import com.mandovi.Entity.BatteryTyre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatteryTyreRepository extends JpaRepository<BatteryTyre, Integer> {

    @Transactional
    @Query("SELECT b FROM BatteryTyre b WHERE b.month = :month AND b.year = :year")
    List<BatteryTyre> getBatteryTyreByMonthYear(@Param("month") String month, @Param("year") String year);
}
