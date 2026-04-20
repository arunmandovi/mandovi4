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
     AND (:deleteYear IS NULL OR l.deleteYear IN (:deleteYear))
    """)
    List<Loadd> getLoadByMonthYear(
            @Param("months") List<String> months,
            @Param("years") List<String> years,
            @Param("deleteYear") List<String> deleteYear);

    @Transactional
    @Modifying
    @Query("DELETE FROM Loadd l WHERE l.month = :month AND l.year = :year AND l.deleteYear = :deleteYear")
    void deleteByMonthYear(@Param("month") String month,
                           @Param("year") String year,
                           @Param("deleteYear")String deleteYear );



    // Group by city
    @Query("""
SELECT new com.mandovi.DTO.LoaddSummaryDTO(
    ls.city,
    NULL,

    ls.totalPrev,
    ls.totalCurr,
    (ls.totalCurr - ls.totalPrev) * 100.0 / NULLIF(ls.totalPrev,0),

    ls.bodyshopPrev,
    ls.bodyshopCurr,
    (ls.bodyshopCurr - ls.bodyshopPrev) * 100.0 / NULLIF(ls.bodyshopPrev,0),

    ls.freeServicePrev,
    ls.freeServiceCurr,
    (ls.freeServiceCurr - ls.freeServicePrev) * 100.0 / NULLIF(ls.freeServicePrev,0),

    ls.pmsPrev,
    ls.pmsCurr,
    (ls.pmsCurr - ls.pmsPrev) * 100.0 / NULLIF(ls.pmsPrev,0),

    ls.generalPrev,
    ls.generalCurr,
    (ls.generalCurr - ls.generalPrev) * 100.0 / NULLIF(ls.generalPrev,0),

    ls.rrPrev,
    ls.rrCurr,
    (ls.rrCurr - ls.rrPrev) * 100.0 / NULLIF(ls.rrPrev,0),

    ls.othersPrev,
    ls.othersCurr,
    (ls.othersCurr - ls.othersPrev) * 100.0 / NULLIF(ls.othersPrev,0),

    ls.bodyshopPrev * 100.0 / NULLIF(ls.generalPrev,0),
    ls.bodyshopCurr * 100.0 / NULLIF(ls.generalCurr,0),

    (ls.bodyshopCurr * 100.0 / NULLIF(ls.generalCurr,0)) -
    (ls.bodyshopPrev * 100.0 / NULLIF(ls.generalPrev,0))
)
FROM (
    SELECT l.city AS city,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS totalPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS totalCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'BODYSHOP'
            THEN l.serviceLoad ELSE 0 END) AS bodyshopPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'BODYSHOP'
            THEN l.serviceLoad ELSE 0 END) AS bodyshopCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'FREE SERVICE'
            THEN l.serviceLoad ELSE 0 END) AS freeServicePrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'FREE SERVICE'
            THEN l.serviceLoad ELSE 0 END) AS freeServiceCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'PMS'
            THEN l.serviceLoad ELSE 0 END) AS pmsPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'PMS'
            THEN l.serviceLoad ELSE 0 END) AS pmsCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType IN ('FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS generalPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType IN ('FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS generalCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'RR'
            THEN l.serviceLoad ELSE 0 END) AS rrPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'RR'
            THEN l.serviceLoad ELSE 0 END) AS rrCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'OTHERS'
            THEN l.serviceLoad ELSE 0 END) AS othersPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'OTHERS'
            THEN l.serviceLoad ELSE 0 END) AS othersCurr

    FROM Loadd l
    WHERE (:months IS NULL OR l.month IN :months)
      AND (:channels IS NULL OR l.channel IN :channels)
      AND (:qtrWise IS NULL OR l.qtrWise IN :qtrWise)
      AND (:halfYear IS NULL OR l.halfYear IN :halfYear)
      AND l.deleteYear = :currYear
      AND l.financialYear IN (:prevYear, :currYear)

    GROUP BY l.city
) ls
""")
    List<LoaddSummaryDTO> getLoaddSummaryByCity(
            @Param("months") List<String> months,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("prevYear") String prevYear,
            @Param("currYear") String currYear
    );

    //Group by branch
    @Query("""
SELECT new com.mandovi.DTO.LoaddSummaryDTO(
    ls.city,
    ls.branch,

    ls.totalPrev,
    ls.totalCurr,
    (ls.totalCurr - ls.totalPrev) * 100.0 / NULLIF(ls.totalPrev,0),

    ls.bodyshopPrev,
    ls.bodyshopCurr,
    (ls.bodyshopCurr - ls.bodyshopPrev) * 100.0 / NULLIF(ls.bodyshopPrev,0),

    ls.freeServicePrev,
    ls.freeServiceCurr,
    (ls.freeServiceCurr - ls.freeServicePrev) * 100.0 / NULLIF(ls.freeServicePrev,0),

    ls.pmsPrev,
    ls.pmsCurr,
    (ls.pmsCurr - ls.pmsPrev) * 100.0 / NULLIF(ls.pmsPrev,0),

    ls.generalPrev,
    ls.generalCurr,
    (ls.generalCurr - ls.generalPrev) * 100.0 / NULLIF(ls.generalPrev,0),

    ls.rrPrev,
    ls.rrCurr,
    (ls.rrCurr - ls.rrPrev) * 100.0 / NULLIF(ls.rrPrev,0),

    ls.othersPrev,
    ls.othersCurr,
    (ls.othersCurr - ls.othersPrev) * 100.0 / NULLIF(ls.othersPrev,0),

    ls.bodyshopPrev * 100.0 / NULLIF(ls.generalPrev,0),
    ls.bodyshopCurr * 100.0 / NULLIF(ls.generalCurr,0),

    (ls.bodyshopCurr * 100.0 / NULLIF(ls.generalCurr,0)) -
    (ls.bodyshopPrev * 100.0 / NULLIF(ls.generalPrev,0))
)

FROM (
    SELECT
        l.city AS city,
        l.branch AS branch,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS totalPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS totalCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'BODYSHOP'
            THEN l.serviceLoad ELSE 0 END) AS bodyshopPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'BODYSHOP'
            THEN l.serviceLoad ELSE 0 END) AS bodyshopCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'FREE SERVICE'
            THEN l.serviceLoad ELSE 0 END) AS freeServicePrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'FREE SERVICE'
            THEN l.serviceLoad ELSE 0 END) AS freeServiceCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'PMS'
            THEN l.serviceLoad ELSE 0 END) AS pmsPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'PMS'
            THEN l.serviceLoad ELSE 0 END) AS pmsCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType IN ('FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS generalPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType IN ('FREE SERVICE','PMS','RR')
            THEN l.serviceLoad ELSE 0 END) AS generalCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'RR'
            THEN l.serviceLoad ELSE 0 END) AS rrPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'RR'
            THEN l.serviceLoad ELSE 0 END) AS rrCurr,

        SUM(CASE WHEN l.financialYear = :prevYear
            AND l.loadType = 'OTHERS'
            THEN l.serviceLoad ELSE 0 END) AS othersPrev,

        SUM(CASE WHEN l.financialYear = :currYear
            AND l.loadType = 'OTHERS'
            THEN l.serviceLoad ELSE 0 END) AS othersCurr

    FROM Loadd l
    WHERE (:months IS NULL OR l.month IN :months)
      AND (:cities IS NULL OR l.city IN :cities)
      AND (:branches IS NULL OR l.branch IN :branches)
      AND (:channels IS NULL OR l.channel IN :channels)
      AND (:qtrWise IS NULL OR l.qtrWise IN :qtrWise)
      AND (:halfYear IS NULL OR l.halfYear IN :halfYear)
      AND l.deleteYear = :currYear
      AND l.financialYear IN (:prevYear, :currYear)
    GROUP BY l.city, l.branch
) ls
""")
    List<LoaddSummaryDTO> getLoaddSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("branches") List<String> branches,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("prevYear") String prevYear,
            @Param("currYear") String currYear
    );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.loadd", nativeQuery = true)
    void deleteLoaddAll();
}