package com.mandovi.Repository;

import com.mandovi.Entity.Tat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TatRepository extends JpaRepository<Tat,Integer> {

    @Transactional
    @Query("SELECT t FROM Tat t WHERE t.month = :month AND t.year = :year")
    public List<Tat> getTATByMonthYear (@Param("month") String month, @Param("year") String year);
}
