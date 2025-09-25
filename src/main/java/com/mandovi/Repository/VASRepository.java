package com.mandovi.Repository;

import com.mandovi.Entity.VAS;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VASRepository extends JpaRepository<VAS, Integer> {

    @Transactional
    @Query("SELECT v FROM VAS v WHERE v.month = :month AND v.year = :year")
    public List<VAS> getVASByMonthYear (@Param("month") String month, @Param("year") String year);
}
