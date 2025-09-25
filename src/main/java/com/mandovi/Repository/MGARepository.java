package com.mandovi.Repository;

import com.mandovi.Entity.MGA;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MGARepository extends JpaRepository<MGA, Integer> {

    @Transactional
    @Query("SELECT m FROM MGA m WHERE m.mgaDate = :mgaDate")
    public List<MGA> getMGAByMGADate(@Param("mgaDate") LocalDate mgaDate);

}
