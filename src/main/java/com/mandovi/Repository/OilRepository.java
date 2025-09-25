package com.mandovi.Repository;

import com.mandovi.Entity.Oil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OilRepository extends JpaRepository<Oil, Integer> {

    @Transactional
    @Query("SELECT o FROM Oil o WHERE o.month = :month AND o.year = :year")
    public List<Oil> getOilByMonthYear(@Param("month") String month,@Param("year") String year);
}
