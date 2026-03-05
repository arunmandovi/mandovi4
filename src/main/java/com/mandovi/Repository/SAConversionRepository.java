package com.mandovi.Repository;

import com.mandovi.DTO.SAConversionDTO;
import com.mandovi.Entity.SAConversion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SAConversionRepository extends JpaRepository<SAConversion, Integer> {

    @Transactional
    @Modifying
    @Query ("DELETE FROM SAConversion s WHERE s.month = :month AND s.year = :year")
    void deleteByMonthYear (@Param("month") String month, @Param("year") String year );

    @Query("""
            SELECT s FROM SAConversion s
            WHERE (:months IS NULL OR s.month IN (:months))
            """)
    public List<SAConversion> getSAConversionByMonth (@Param("months") List<String> months );

    @Query("""
            SELECT new com.mandovi.DTO.SAConversionDTO(
            s.branch,
            s.saName,
            SUM(s.pmsAppt),
            SUM(s.pmsConversion),
            CASE
                 WHEN SUM(pmsAppt) = 0 THEN 0.0
                 ELSE (SUM(s.pmsConversion * 100.0) / SUM(s.pmsAppt))
                 END
            )
            FROM SAConversion s
            WHERE (:months IS NULL OR s.month IN (:months))
             AND (:branches IS NULL OR s.branch IN (:branches))
             AND (:saNames IS NULL OR s.saName IN (:saNames))
            GROUP BY s.branch, s.saName
            """)
    public List<SAConversionDTO> getSAConversionSummary (
            @Param("months") List<String> months,
            @Param("branches") List<String> branches,
            @Param("saNames") List<String> saNames );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.sa_conversion;", nativeQuery = true)
    void deleteSAConversionALl ();
}
