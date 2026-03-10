package com.mandovi.Repository;

import com.mandovi.Entity.ServiceLoad;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceLoadRepository extends JpaRepository<ServiceLoad, Integer> {

    @Transactional
    @Query("""
            SELECT s FROM ServiceLoad s
            WHERE (:months IS NULL OR s.month IN :months)
            AND (:years IS NULL OR s.year IN :years)
            """)
    List<ServiceLoad> getServiceLoadByMonthYear(@Param("months") List<String> months, @Param("years") List<String> years);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.service_load", nativeQuery = true)
    public void deleteServiceLoadAll ();

    @Transactional
    @Modifying
    @Query("DELETE FROM ServiceLoad s WHERE s.month = :month AND s.year = :year")
    void deleteByMonthYear (@Param("month") String month, @Param("year") String year);
}
