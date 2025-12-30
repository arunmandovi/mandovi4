package com.mandovi.Repository;

import com.mandovi.DTO.CCConversionDTO;
import com.mandovi.Entity.CCConversion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CCConversionRepository extends JpaRepository<CCConversion, Integer> {
    @Query("""
            SELECT new com.mandovi.DTO.CCConversionDTO(
            c.branch,
            c.cceName,
            MIN(c.dateOfJoin),
            null,
            SUM(c.pmsAppt),
            SUM(c.pmsConversion),
            CASE
                WHEN SUM(c.pmsAppt) = 0 THEN 0.0
                ELSE (SUM(c.pmsConversion) * 100.0 / SUM(c.pmsAppt))
            END
            )
            FROM CCConversion c
            WHERE (:months IS NULL OR c.month IN (:months))
             AND (:branches IS NULL OR c.branch IN (:branches))
             AND (:cceNames IS NULL OR c.cceName IN (:cceNames))
            GROUP BY c.branch,c.cceName
            """)
    public List<CCConversionDTO> getCCConversionSummary (
            @Param("months") List<String> months,
            @Param("branches") List<String> branches,
            @Param("cceNames") List<String> cceNames);

    @Transactional
    @Query("""
            SELECT c FROM CCConversion c
            WHERE (:months IS NULL OR c.month IN (:months))
            """)
    public List<CCConversion> getCCConversionByMonth (List<String> months);

    @Modifying
    @Transactional
    @Query (value = "TRUNCATE TABLE mandovi.cc_conversion;", nativeQuery = true)
    void deleteCCConversionAll ();

}
