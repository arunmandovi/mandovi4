package com.mandovi.Repository;

import com.mandovi.Entity.PMSParts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PMSPartsRepository extends JpaRepository<PMSParts, Integer> {

    @Transactional
    @Query("SELECT p FROM PMSParts p WHERE p.pmsDate = :pmsDate")
    public List<PMSParts> getPMSPartsByPMSDate(@Param("pmsDate")LocalDate pmsDate);
}
