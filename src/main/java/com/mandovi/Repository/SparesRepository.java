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
    @Query("""
    SELECT s FROM Spares s
    WHERE (:months IS NULL OR s.month IN (:months))
    AND (:years IS NULL OR s.year IN (:years))
    """)
    public List<Spares> getSparedByMonthYear(@Param("months") List<String> months, @Param("years") List<String> years);

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
            WHERE (:months IS NULL OR s.month IN (:months))
             AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
            GROUP BY s.city
            """)
    List<SparesSummaryDTO> getSparesSummaryDTOByCity (
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    //Group by branch
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
            WHERE (:months IS NULL OR s.month IN (:months))
             AND (:cities IS NULL OR s.city IN (:cities))
             AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
            GROUP BY s.city, s.branch
            """)
    List<SparesSummaryDTO> getSparesSummaryBranchWise (
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );
}
