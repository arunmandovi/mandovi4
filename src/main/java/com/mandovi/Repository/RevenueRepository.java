package com.mandovi.Repository;

import com.mandovi.DTO.RevenueSummaryDTO;
import com.mandovi.Entity.Revenue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    @Transactional
    @Query("SELECT r FROM Revenue r WHERE r.month = :month AND r.year = :year")
    public List<Revenue> getRevenueByMonthYear(@Param("month") String month, @Param("year") String year);

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.RevenueSummaryDTO(
            r.city,
            null,
            SUM(r.srLabourLastYear),
            SUM(r.srLabourCurrentYear),
            (SUM(r.srLabourCurrentYear) - SUM(r.srLabourLastYear)) * 100.00 / NULLIF(SUM(r.srLabourLastYear), 0),
            SUM(r.brLabourLastYear),
            SUM(r.brLabourCurrentYear),
            (SUM(r.brLabourCurrentYear) - SUM(r.brLabourLastYear)) * 100.00 / NULLIF(SUM(r.brLabourLastYear), 0),
            SUM(r.srAndBrLabourLastYear),
            SUM(r.srAndBrLabourCurrentYear),
            (SUM(r.srAndBrLabourCurrentYear) - SUM(r.srAndBrLabourLastYear)) * 100.00 / NULLIF(SUM(r.srAndBrLabourLastYear), 0),
            SUM(r.srSparesLastYear),
            SUM(r.srSparesCurrentYear),
            (SUM(r.srSparesCurrentYear) - SUM(r.srSparesLastYear)) * 100.00 / NULLIF(SUM(r.srSparesLastYear), 0),
            SUM(r.brSparesLastYear),
            SUM(r.brSparesCurrentYear),
            (SUM(r.brSparesCurrentYear) - SUM(r.brSparesLastYear)) * 100.00 / NULLIF(SUM(r.brSparesLastYear), 0),
            SUM(r.srAndBrSparesLastYear),
            SUM(r.srAndBrSparesCurrentYear),
            (SUM(r.srAndBrSparesCurrentYear) - SUM(r.srAndBrSparesLastYear)) * 100.00 / NULLIF(SUM(r.srAndBrSparesLastYear), 0),
            SUM(r.srAndBrTotalLastYear),
            SUM(r.srAndBrTotalCurrentYear),
            (SUM(r.srAndBrTotalCurrentYear) - SUM(r.srAndBrTotalLastYear)) * 100.0 / NULLIF(SUM(r.srAndBrTotalLastYear), 0)
            )
            FROM Revenue r
            WHERE (:month IS NULL OR r.month = :month)
             AND (:qtrWise IS NULL OR r.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR r.halfYear = :halfYear)
            GROUP By r.city
            """)
    List<RevenueSummaryDTO> getRevenueSummaryByCity (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.RevenueSummaryDTO(
            MAX(r.city),
            r.branch,
            SUM(r.srLabourLastYear),
            SUM(r.srLabourCurrentYear),
            (SUM(r.srLabourCurrentYear) - SUM(r.srLabourLastYear)) * 100.00 / NULLIF(SUM(r.srLabourLastYear), 0),
            SUM(r.brLabourLastYear),
            SUM(r.brLabourCurrentYear),
            (SUM(r.brLabourCurrentYear) - SUM(r.brLabourLastYear)) * 100.00 / NULLIF(SUM(r.brLabourLastYear), 0),
            SUM(r.srAndBrLabourLastYear),
            SUM(r.srAndBrLabourCurrentYear),
            (SUM(r.srAndBrLabourCurrentYear) - SUM(r.srAndBrLabourLastYear)) * 100.00 / NULLIF(SUM(r.srAndBrLabourLastYear), 0),
            SUM(r.srSparesLastYear),
            SUM(r.srSparesCurrentYear),
            (SUM(r.srSparesCurrentYear) - SUM(r.srSparesLastYear)) * 100.00 / NULLIF(SUM(r.srSparesLastYear), 0),
            SUM(r.brSparesLastYear),
            SUM(r.brSparesCurrentYear),
            (SUM(r.brSparesCurrentYear) - SUM(r.brSparesLastYear)) * 100.00 / NULLIF(SUM(r.brSparesLastYear), 0),
            SUM(r.srAndBrSparesLastYear),
            SUM(r.srAndBrSparesCurrentYear),
            (SUM(r.srAndBrSparesCurrentYear) - SUM(r.srAndBrSparesLastYear)) * 100.00 / NULLIF(SUM(r.srAndBrSparesLastYear), 0),
            SUM(r.srAndBrTotalLastYear),
            SUM(r.srAndBrTotalCurrentYear),
            (SUM(r.srAndBrTotalCurrentYear) - SUM(r.srAndBrTotalLastYear)) * 100.0 / NULLIF(SUM(r.srAndBrTotalLastYear), 0)
            )
            FROM Revenue r
            WHERE (:month IS NULL OR r.month = :month)
             AND (:qtrWise IS NULL OR r.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR r.halfYear = :halfYear)
            GROUP By r.branch
            """)
    List<RevenueSummaryDTO> getRevenueSummaryByBranch (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.RevenueSummaryDTO(
            r.city,
            r.branch,
            SUM(r.srLabourLastYear),
            SUM(r.srLabourCurrentYear),
            (SUM(r.srLabourCurrentYear) - SUM(r.srLabourLastYear)) * 100.00 / NULLIF(SUM(r.srLabourLastYear), 0),
            SUM(r.brLabourLastYear),
            SUM(r.brLabourCurrentYear),
            (SUM(r.brLabourCurrentYear) - SUM(r.brLabourLastYear)) * 100.00 / NULLIF(SUM(r.brLabourLastYear), 0),
            SUM(r.srAndBrLabourLastYear),
            SUM(r.srAndBrLabourCurrentYear),
            (SUM(r.srAndBrLabourCurrentYear) - SUM(r.srAndBrLabourLastYear)) * 100.00 / NULLIF(SUM(r.srAndBrLabourLastYear), 0),
            SUM(r.srSparesLastYear),
            SUM(r.srSparesCurrentYear),
            (SUM(r.srSparesCurrentYear) - SUM(r.srSparesLastYear)) * 100.00 / NULLIF(SUM(r.srSparesLastYear), 0),
            SUM(r.brSparesLastYear),
            SUM(r.brSparesCurrentYear),
            (SUM(r.brSparesCurrentYear) - SUM(r.brSparesLastYear)) * 100.00 / NULLIF(SUM(r.brSparesLastYear), 0),
            SUM(r.srAndBrSparesLastYear),
            SUM(r.srAndBrSparesCurrentYear),
            (SUM(r.srAndBrSparesCurrentYear) - SUM(r.srAndBrSparesLastYear)) * 100.00 / NULLIF(SUM(r.srAndBrSparesLastYear), 0),
            SUM(r.srAndBrTotalLastYear),
            SUM(r.srAndBrTotalCurrentYear),
            (SUM(r.srAndBrTotalCurrentYear) - SUM(r.srAndBrTotalLastYear)) * 100.0 / NULLIF(SUM(r.srAndBrTotalLastYear), 0)
            )
            FROM Revenue r
            WHERE (:month IS NULL OR r.month = :month)
             AND (:qtrWise IS NULL OR r.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR r.halfYear = :halfYear)
            GROUP By r.city, r.branch
            """)
    List<RevenueSummaryDTO> getRevenueSummaryByCityAndBranch (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );
}
