package com.mandovi.Repository;

import com.mandovi.DTO.MCPSummaryDTO;
import com.mandovi.DTO.MGASummaryDTO;
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

    //Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MGASummaryDTO(
            m.city,
            null,
            SUM(m.loadd),
            SUM(m.consumption),
            SUM(m.consumption) / SUM(m.loadd),
            (SUM(m.loadd) * 455) - SUM(m.consumption),
            ((SUM(m.loadd) * 455) - SUM(m.consumption)) / SUM(m.loadd)
        )
        FROM MGA m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MGASummaryDTO> getMGASummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MGASummaryDTO(
            null,
            m.branch,
            SUM(m.loadd),
            SUM(m.consumption),
            SUM(m.consumption) / SUM(m.loadd),
            (SUM(m.loadd) * 455) - SUM(m.consumption),
            ((SUM(m.loadd) * 455) - SUM(m.consumption)) / SUM(m.loadd)
        )
        FROM MGA m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MGASummaryDTO> getMGASummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MGASummaryDTO(
            m.city,
            m.branch,
            SUM(m.loadd),
            SUM(m.consumption),
            SUM(m.consumption) / SUM(m.loadd),
            (SUM(m.loadd) * 455) - SUM(m.consumption),
            ((SUM(m.loadd) * 455) - SUM(m.consumption)) / SUM(m.loadd)
        )
        FROM MGA m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MGASummaryDTO> getMGASummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

}
