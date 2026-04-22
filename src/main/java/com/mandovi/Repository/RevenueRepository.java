package com.mandovi.Repository;

import com.mandovi.DTO.RevenueSummaryDTO;
import com.mandovi.Entity.Revenue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    @Transactional
    @Query("""
    SELECT r FROM Revenue r
    WHERE (:months IS NULL OR r.month IN (:months))
    AND (:years IS NULL OR r.year IN (:years)) AND (:financialYears IS NULL OR r.financialYear IN (:financialYears))
    """)
    public List<Revenue> getRevenueByMonthYear(@Param("months") List<String> months, @Param("years") List<String> years,
                                               @Param("financialYears") List<String> financialYears );

    @Transactional
    @Modifying
    @Query("DELETE FROM Revenue r WHERE r.month = :month AND r.year = :year ")
    void deleteByMonthYear(@Param("month") String month, @Param("year") String year );

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
            WHERE (:months IS NULL OR r.month IN (:months))
             AND (:qtrWise IS NULL OR r.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR r.halfYear IN (:halfYear))
             AND (:financialYears IS NULL OR r.financialYear IN (:financialYears))
            GROUP By r.city
            """)
    List<RevenueSummaryDTO> getRevenueSummaryByCity (
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("financialYears") List<String> financialYears );

    //Group by branch
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
            WHERE (:months IS NULL OR r.month IN (:months))
             AND (:cities IS NULL OR r.city IN (:cities))
             AND (:qtrWise IS NULL OR r.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR r.halfYear IN (:halfYear))
             AND (:financialYears IS NULL OR r.financialYear IN (:financialYears))
            GROUP By r.city, r.branch
            """)
    List<RevenueSummaryDTO> getRevenueSummaryBranchWise (
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("financialYears") List<String> financialYears );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.revenue;", nativeQuery = true)
    void deleteRevenueAll ();
}
