package com.mandovi.Repository;

import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.Entity.Loadd;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoaddRepository extends JpaRepository<Loadd, Integer> {

    @Transactional
    @Query("""
    SELECT l FROM Loadd l
    WHERE (:months IS NULL OR l.month IN (:months))
     AND (:years IS NULL OR l.year IN (:years))
    """)
    List<Loadd> getLoadByMonthYear(
            @Param("months") List<String> months, @Param("years") List<String> years);

    @Transactional
    @Modifying
    @Query("DELETE FROM Loadd l WHERE l.month = :month AND l.year = :year")
    void deleteByMonthYear(@Param("month") String month,
                           @Param("year") String year);



    // Group by city
    @Query("""
SELECT new com.mandovi.DTO.LoaddSummaryDTO(
    ls.city,
    NULL,

    ls.total2024,
    ls.total2025,
    (ls.total2025 - ls.total2024) * 100.0 / NULLIF(ls.total2024,0),

    ls.bodyshop2024,
    ls.bodyshop2025,
    (ls.bodyshop2025 - ls.bodyshop2024) * 100.0 / NULLIF(ls.bodyshop2024,0),

    ls.freeService2024,
    ls.freeService2025,
    (ls.freeService2025 - ls.freeService2024) * 100.0 / NULLIF(ls.freeService2024,0),

    ls.pms2024,
    ls.pms2025,
    (ls.pms2025 - ls.pms2024) * 100.0 / NULLIF(ls.pms2024,0),

    ls.general2024,
    ls.general2025,
    (ls.general2025 - ls.general2024) * 100.0 / NULLIF(ls.general2024,0),

    ls.rr2024,
    ls.rr2025,
    (ls.rr2025 - ls.rr2024) * 100.0 / NULLIF(ls.rr2024,0),

    ls.others2024,
    ls.others2025,
    (ls.others2025 - ls.others2024) * 100.0 / NULLIF(ls.others2024,0),

    ls.bodyshop2024 * 100.0 / NULLIF(ls.general2024,0),
    ls.bodyshop2025 * 100.0 / NULLIF(ls.general2025,0),

    (ls.bodyshop2025 * 100.0 / NULLIF(ls.general2025,0)) -
    (ls.bodyshop2024 * 100.0 / NULLIF(ls.general2024,0))
)

FROM (
    SELECT l.city AS city,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS total2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS total2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='BODYSHOP'
            THEN l.serviceLoad ELSE 0 END) AS bodyshop2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='BODYSHOP'
            THEN l.serviceLoad ELSE 0 END) AS bodyshop2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='FREE SERVICE'
            THEN l.serviceLoad ELSE 0 END) AS freeService2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='FREE SERVICE'
            THEN l.serviceLoad ELSE 0 END) AS freeService2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='PMS'
            THEN l.serviceLoad ELSE 0 END) AS pms2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='PMS'
            THEN l.serviceLoad ELSE 0 END) AS pms2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType IN ('FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS general2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType IN ('FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS general2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='RR'
            THEN l.serviceLoad ELSE 0 END) AS rr2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='RR'
            THEN l.serviceLoad ELSE 0 END) AS rr2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='OTHERS'
            THEN l.serviceLoad ELSE 0 END) AS others2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='OTHERS'
            THEN l.serviceLoad ELSE 0 END) AS others2025

    FROM Loadd l
    WHERE (:months IS NULL OR l.month IN :months)
    AND (:channels IS NULL OR l.channel IN :channels)
    AND (:qtrWise IS NULL OR l.qtrWise IN :qtrWise)
    AND (:halfYear IS NULL OR l.halfYear IN :halfYear)

    GROUP BY l.city
) ls
""")
    List<LoaddSummaryDTO> getLoaddSummaryByCity(
            @Param("months") List<String> months,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear);

    //Group by branch
    @Query("""
SELECT new com.mandovi.DTO.LoaddSummaryDTO(
    ls.city,
    ls.branch,

    ls.total2024,
    ls.total2025,
    (ls.total2025 - ls.total2024) * 100.0 / NULLIF(ls.total2024,0),

    ls.bodyshop2024,
    ls.bodyshop2025,
    (ls.bodyshop2025 - ls.bodyshop2024) * 100.0 / NULLIF(ls.bodyshop2024,0),

    ls.freeService2024,
    ls.freeService2025,
    (ls.freeService2025 - ls.freeService2024) * 100.0 / NULLIF(ls.freeService2024,0),

    ls.pms2024,
    ls.pms2025,
    (ls.pms2025 - ls.pms2024) * 100.0 / NULLIF(ls.pms2024,0),

    ls.general2024,
    ls.general2025,
    (ls.general2025 - ls.general2024) * 100.0 / NULLIF(ls.general2024,0),

    ls.rr2024,
    ls.rr2025,
    (ls.rr2025 - ls.rr2024) * 100.0 / NULLIF(ls.rr2024,0),

    ls.others2024,
    ls.others2025,
    (ls.others2025 - ls.others2024) * 100.0 / NULLIF(ls.others2024,0),

    ls.bodyshop2024 * 100.0 / NULLIF(ls.general2024,0),
    ls.bodyshop2025 * 100.0 / NULLIF(ls.general2025,0),

    (ls.bodyshop2025 * 100.0 / NULLIF(ls.general2025,0)) -
    (ls.bodyshop2024 * 100.0 / NULLIF(ls.general2024,0))
)

FROM (
    SELECT
           l.city AS city,
           l.branch AS branch,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS total2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS total2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='BODYSHOP'
            THEN l.serviceLoad ELSE 0 END) AS bodyshop2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='BODYSHOP'
            THEN l.serviceLoad ELSE 0 END) AS bodyshop2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='FREE SERVICE'
            THEN l.serviceLoad ELSE 0 END) AS freeService2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='FREE SERVICE'
            THEN l.serviceLoad ELSE 0 END) AS freeService2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='PMS'
            THEN l.serviceLoad ELSE 0 END) AS pms2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='PMS'
            THEN l.serviceLoad ELSE 0 END) AS pms2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType IN ('FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS general2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType IN ('FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS general2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='RR'
            THEN l.serviceLoad ELSE 0 END) AS rr2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='RR'
            THEN l.serviceLoad ELSE 0 END) AS rr2025,

        SUM(CASE WHEN l.financialYear='2024-2025'
            AND l.loadType='OTHERS'
            THEN l.serviceLoad ELSE 0 END) AS others2024,

        SUM(CASE WHEN l.financialYear='2025-2026'
            AND l.loadType='OTHERS'
            THEN l.serviceLoad ELSE 0 END) AS others2025

    FROM Loadd l
    WHERE (:months IS NULL OR l.month IN :months)
    AND (:cities IS NULL OR l.city IN :cities)
    AND (:branches IS NULL OR l.branch IN (:branches))
    AND (:channels IS NULL OR l.channel IN :channels)
    AND (:qtrWise IS NULL OR l.qtrWise IN :qtrWise)
    AND (:halfYear IS NULL OR l.halfYear IN :halfYear)

    GROUP BY l.city, l.branch
) ls
""")
    List<LoaddSummaryDTO> getLoaddSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("branches") List<String> branches,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.loadd", nativeQuery = true)
    void deleteLoaddAll();
}