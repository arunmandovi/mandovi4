package com.mandovi.Repository;

import com.mandovi.DTO.SparesSummaryDTO;
import com.mandovi.Entity.Spares;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SparesRepository extends JpaRepository<Spares, Integer> {

    @Transactional
    @Query("SELECT s FROM Spares s WHERE s.month = :month AND s.year = :year")
    public List<Spares> getSparedByMonthYear(@Param("month") String month, @Param("year") String year);

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.SparesSummaryDTO(
            s.city,
            null,
            SUM(s.srSparesLastYear),
            SUM(s.srSparesCurrentYear),
            (SUM(s.srSparesCurrentYear) - SUM(s.srSparesLastYear)) * 100 / NULLIF(SUM(s.srSparesLastYear), 0),
            SUM(s.brSparesLastYear),
            SUM(s.brSparesCurrentYear),
            (SUM(s.brSparesCurrentYear) - SUM(s.brSparesLastYear)) * 100 / NULLIF(SUM(s.brSparesLastYear), 0),
            SUM(s.srBrSparesLastYear),
            SUM(s.srBrSparesCurrentYear),
            (SUM(s.srBrSparesCurrentYear) - SUM(s.srBrSparesLastYear)) * 100 / NULLIF(SUM(s.srBrSparesLastYear), 0),
            SUM(s.batteryLastYear),
            SUM(s.batteryCurrentYear),
            (SUM(s.batteryCurrentYear) - SUM(s.batteryLastYear)) * 100 / NULLIF(SUM(s.batteryLastYear), 0),
            SUM(s.tyreLastYear),
            SUM(s.tyreCurrentYear),
            (SUM(s.tyreCurrentYear) - SUM(s.tyreLastYear)) * 100 / NULLIF(SUM(s.tyreLastYear), 0)
            )
            FROM Spares s
            WHERE (:month IS NULL OR s.month = :month)
             AND (:qtrWise IS NULL OR s.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR s.halfYear = :halfYear)
            GROUP BY s.city
            """)
    List<SparesSummaryDTO> getSparesSummaryDTOByCity (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );

    //Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.SparesSummaryDTO(
            MAX(s.city),
            s.branch,
            SUM(s.srSparesLastYear),
            SUM(s.srSparesCurrentYear),
            (SUM(s.srSparesCurrentYear) - SUM(s.srSparesLastYear)) * 100 / NULLIF(SUM(s.srSparesLastYear), 0),
            SUM(s.brSparesLastYear),
            SUM(s.brSparesCurrentYear),
            (SUM(s.brSparesCurrentYear) - SUM(s.brSparesLastYear)) * 100 / NULLIF(SUM(s.brSparesLastYear), 0),
            SUM(s.srBrSparesLastYear),
            SUM(s.srBrSparesCurrentYear),
            (SUM(s.srBrSparesCurrentYear) - SUM(s.srBrSparesLastYear)) * 100 / NULLIF(SUM(s.srBrSparesLastYear), 0),
            SUM(s.batteryLastYear),
            SUM(s.batteryCurrentYear),
            (SUM(s.batteryCurrentYear) - SUM(s.batteryLastYear)) * 100 / NULLIF(SUM(s.batteryLastYear), 0),
            SUM(s.tyreLastYear),
            SUM(s.tyreCurrentYear),
            (SUM(s.tyreCurrentYear) - SUM(s.tyreLastYear)) * 100 / NULLIF(SUM(s.tyreLastYear), 0)
            )
            FROM Spares s
            WHERE (:month IS NULL OR s.month = :month)
             AND (:qtrWise IS NULL OR s.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR s.halfYear = :halfYear)
            GROUP BY s.branch
            """)
    List<SparesSummaryDTO> getSparesSummaryDTOByBranch (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );

    //Group by city and branch
    @Query("""
            SELECT new com.mandovi.DTO.SparesSummaryDTO(
            s.city,
            s.branch,
            SUM(s.srSparesLastYear),
            SUM(s.srSparesCurrentYear),
            (SUM(s.srSparesCurrentYear) - SUM(s.srSparesLastYear)) * 100 / NULLIF(SUM(s.srSparesLastYear), 0),
            SUM(s.brSparesLastYear),
            SUM(s.brSparesCurrentYear),
            (SUM(s.brSparesCurrentYear) - SUM(s.brSparesLastYear)) * 100 / NULLIF(SUM(s.brSparesLastYear), 0),
            SUM(s.srBrSparesLastYear),
            SUM(s.srBrSparesCurrentYear),
            (SUM(s.srBrSparesCurrentYear) - SUM(s.srBrSparesLastYear)) * 100 / NULLIF(SUM(s.srBrSparesLastYear), 0),
            SUM(s.batteryLastYear),
            SUM(s.batteryCurrentYear),
            (SUM(s.batteryCurrentYear) - SUM(s.batteryLastYear)) * 100 / NULLIF(SUM(s.batteryLastYear), 0),
            SUM(s.tyreLastYear),
            SUM(s.tyreCurrentYear),
            (SUM(s.tyreCurrentYear) - SUM(s.tyreLastYear)) * 100 / NULLIF(SUM(s.tyreLastYear), 0)
            )
            FROM Spares s
            WHERE (:month IS NULL OR s.month = :month)
             AND (:qtrWise IS NULL OR s.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR s.halfYear = :halfYear)
            GROUP BY s.city, s.branch
            """)
    List<SparesSummaryDTO> getSparesSummaryDTOByCityAndBranch (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );
}
