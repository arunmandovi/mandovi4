package com.mandovi.Repository;

import com.mandovi.Entity.TAT;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TATRepository extends JpaRepository<TAT,Integer> {

    @Transactional
    @Query("SELECT t FROM TAT t WHERE t.month = :month AND t.year = :year")
    public List<TAT> getTATByMonthYear (@Param("month") String month, @Param("year") String year);
}
