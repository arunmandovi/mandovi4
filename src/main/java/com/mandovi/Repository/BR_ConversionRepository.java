package com.mandovi.Repository;

import com.mandovi.Entity.BR_Conversion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BR_ConversionRepository extends JpaRepository<BR_Conversion, Integer> {

    @Transactional
    @Query("SELECT b FROM BR_Conversion b WHERE b.month = :month AND b.year = :year")
    List<BR_Conversion> getBR_ConversionByMonthYear(@Param("month") String month, @Param("year") String year);
}
