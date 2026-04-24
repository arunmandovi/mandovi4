package com.mandovi.Repository;

import com.mandovi.DTO.PerVehicleReportSummaryDTO;
import com.mandovi.Entity.Loadd;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerVehicleRepository extends JpaRepository<Loadd, Integer> {
    @Transactional

    //Group BY City
    @Query("""
SELECT NEW com.mandovi.DTO.PerVehicleReportSummaryDTO(
    l.city,
    NULL,

    SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','OTHERS','RR')
        THEN l.serviceLoad ELSE 0 END),

    COALESCE(lb.labourGeneral,0),

    COALESCE(sp.srSpares,0),

    COALESCE(lb.labourGeneral,0) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','OTHERS','RR')
        THEN l.serviceLoad ELSE 0 END),0),

    COALESCE(sp.srSpares,0) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','OTHERS','RR')
        THEN l.serviceLoad ELSE 0 END),0),

    (COALESCE(lb.labourGeneral,0) + COALESCE(sp.srSpares,0)) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','OTHERS','RR')
        THEN l.serviceLoad ELSE 0 END),0),

    SUM(CASE WHEN l.loadType='BODYSHOP'
        THEN l.serviceLoad ELSE 0 END),

    COALESCE(lb.bodyShopLabour,0),

    COALESCE(sp.brSpares,0),

    COALESCE(lb.bodyShopLabour,0) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType='BODYSHOP'
        THEN l.serviceLoad ELSE 0 END),0),

    COALESCE(sp.brSpares,0) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType='BODYSHOP'
        THEN l.serviceLoad ELSE 0 END),0),

    (COALESCE(lb.bodyShopLabour,0) + COALESCE(sp.brSpares,0)) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType='BODYSHOP'
        THEN l.serviceLoad ELSE 0 END),0)
)

FROM Loadd l

LEFT JOIN (
    SELECT la.city AS city,
           SUM(CASE WHEN la.loadType IN ('FREE SERVICE','PMS','OTHERS','RR','NO')
                THEN la.labour ELSE 0 END) AS labourGeneral,
           SUM(CASE WHEN la.loadType='BODYSHOP'
                THEN la.labour ELSE 0 END) AS bodyShopLabour
    FROM Labour la
    WHERE (:months IS NULL OR la.month IN :months)
     AND (:years IS NULL OR la.year IN :years)
     AND (:qtrWise IS NULL OR la.qtrWise IN :qtrWise)
     AND (:halfYear IS NULL OR la.halfYear IN :halfYear)
     AND (
             :financialYears IS NULL OR
             la.financialYear IN (:financialYears) AND
             la.deleteYear IN (:financialYears)
         )
    GROUP BY la.city
) lb ON lb.city = l.city

LEFT JOIN (
    SELECT s.city AS city,
           SUM(s.srSparesCurrentYear) AS srSpares,
           SUM(s.brSparesCurrentYear) AS brSpares
    FROM Spares s
    WHERE (:months IS NULL OR s.month IN :months)
     AND (:years IS NULL OR s.year IN :years)
     AND (:qtrWise IS NULL OR s.qtrWise IN :qtrWise)
     AND (:halfYear IS NULL OR s.halfYear IN :halfYear)
     AND (:financialYears IS NULL OR s.financialYear IN (:financialYears))
    GROUP BY s.city
) sp ON sp.city = l.city

WHERE (:months IS NULL OR l.month IN :months)
AND (:years IS NULL OR l.year IN :years)
AND (:qtrWise IS NULL OR l.qtrWise IN :qtrWise)
AND (:halfYear IS NULL OR l.halfYear IN :halfYear)
AND (
             :financialYears IS NULL OR
             l.financialYear IN (:financialYears) AND
             l.deleteYear IN (:financialYears)
         )
GROUP BY l.city, lb.labourGeneral, lb.bodyShopLabour, sp.srSpares, sp.brSpares
""")
    List<PerVehicleReportSummaryDTO> getPerVehicleSummary(
            @Param("months") List<String> months,
            @Param("years") List<String> years,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("financialYears") List<String> financialYears );

    //Group BY Branch
    @Query("""
SELECT NEW com.mandovi.DTO.PerVehicleReportSummaryDTO(
    l.city,
    l.branch,

    SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','OTHERS','RR')
        THEN l.serviceLoad ELSE 0 END),

    COALESCE(lb.labourGeneral,0),

    COALESCE(sp.srSpares,0),

    COALESCE(lb.labourGeneral,0) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','OTHERS','RR')
        THEN l.serviceLoad ELSE 0 END),0),

    COALESCE(sp.srSpares,0) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','OTHERS','RR')
        THEN l.serviceLoad ELSE 0 END),0),

    (COALESCE(lb.labourGeneral,0) + COALESCE(sp.srSpares,0)) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','OTHERS','RR')
        THEN l.serviceLoad ELSE 0 END),0),

    SUM(CASE WHEN l.loadType='BODYSHOP'
        THEN l.serviceLoad ELSE 0 END),

    COALESCE(lb.bodyShopLabour,0),

    COALESCE(sp.brSpares,0),

    COALESCE(lb.bodyShopLabour,0) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType='BODYSHOP'
        THEN l.serviceLoad ELSE 0 END),0),

    COALESCE(sp.brSpares,0) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType='BODYSHOP'
        THEN l.serviceLoad ELSE 0 END),0),

    (COALESCE(lb.bodyShopLabour,0) + COALESCE(sp.brSpares,0)) * 100000 /
        NULLIF(SUM(CASE WHEN l.loadType='BODYSHOP'
        THEN l.serviceLoad ELSE 0 END),0)
)

FROM Loadd l

LEFT JOIN (
    SELECT la.city AS city,
           la.branch AS branch,
           SUM(CASE WHEN la.loadType IN ('FREE SERVICE','PMS','OTHERS','RR','NO')
                THEN la.labour ELSE 0 END) AS labourGeneral,
           SUM(CASE WHEN la.loadType='BODYSHOP'
                THEN la.labour ELSE 0 END) AS bodyShopLabour
    FROM Labour la
    WHERE (:months IS NULL OR la.month IN :months)
     AND (:years IS NULL OR la.year IN :years)
     AND (:cities IS NULL OR la.city IN :cities)
     AND (:qtrWise IS NULL OR la.qtrWise IN :qtrWise)
     AND (:halfYear IS NULL OR la.halfYear IN :halfYear)
     AND (
             :financialYears IS NULL OR
             la.financialYear IN (:financialYears) AND
             la.deleteYear IN (:financialYears)
         )
    GROUP BY la.city, la.branch
) lb ON lb.city = l.city AND lb.branch = l.branch

LEFT JOIN (
    SELECT s.city AS city,
           s.branch AS branch,
           SUM(s.srSparesCurrentYear) AS srSpares,
           SUM(s.brSparesCurrentYear) AS brSpares
    FROM Spares s
    WHERE (:months IS NULL OR s.month IN :months)
     AND (:years IS NULL OR s.year IN :years)
     AND (:cities IS NULL OR s.city IN :cities)
     AND (:qtrWise IS NULL OR s.qtrWise IN :qtrWise)
     AND (:halfYear IS NULL OR s.halfYear IN :halfYear)
     AND (:financialYears IS NULL OR s.financialYear IN (:financialYears))
    GROUP BY s.city, s.branch
) sp ON sp.city = l.city AND sp.branch = l.branch

WHERE (:months IS NULL OR l.month IN :months)
AND (:years IS NULL OR l.year IN :years)
AND (:cities IS NULL OR l.city IN :cities)
AND (:qtrWise IS NULL OR l.qtrWise IN :qtrWise)
AND (:halfYear IS NULL OR l.halfYear IN :halfYear)
AND (
             :financialYears IS NULL OR
             l.financialYear IN (:financialYears) AND
             l.deleteYear IN (:financialYears)
         )
GROUP BY
    l.city,
    l.branch,
    lb.labourGeneral,
    lb.bodyShopLabour,
    sp.srSpares,
    sp.brSpares
""")
    List<PerVehicleReportSummaryDTO> getPerVehicleSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("years") List<String> years,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("financialYears") List<String> financialYears );

}
