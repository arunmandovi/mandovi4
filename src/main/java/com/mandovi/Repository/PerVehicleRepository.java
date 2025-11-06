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

    @Query("""
SELECT new com.mandovi.DTO.PerVehicleReportSummaryDTO(
    l.city,
    NULL,
    SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') THEN l.serviceLoad ELSE 0 END),
    
    CAST((SELECT SUM(la.labour)
          FROM Labour la
          WHERE la.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') AND la.city = l.city
          AND (:months IS NULL OR la.month IN (:months))
          AND (:years IS NULL OR la.year IN (:years))
          AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
    ) AS double),

    CAST((SELECT SUM(s.srSparesCurrentYear)
          FROM Spares s
          WHERE (:months IS NULL OR s.month IN (:months)) AND s.city = l.city
          AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
    ) AS double),

    CAST((SELECT SUM(la.labour)
          FROM Labour la
          WHERE la.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') AND la.city = l.city
          AND (:months IS NULL OR la.month IN (:months))
          AND (:years IS NULL OR la.year IN (:years))
          AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
    ) AS double) *100000
    /
    NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') THEN l.serviceLoad ELSE 0 END), 0),

    CAST((SELECT SUM(s.srSparesCurrentYear)
          FROM Spares s
          WHERE (:months IS NULL OR s.month IN (:months)) AND s.city = l.city
          AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
    ) AS double) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') THEN l.serviceLoad ELSE 0 END), 0),

    (
        CAST((SELECT SUM(la.labour)
              FROM Labour la
              WHERE la.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') AND la.city = l.city
              AND (:months IS NULL OR la.month IN (:months))
              AND (:years IS NULL OR la.year IN (:years))
              AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
        ) AS double)
        +
        CAST((SELECT SUM(s.srSparesCurrentYear)
              FROM Spares s
              WHERE (:months IS NULL OR s.month IN (:months)) AND s.city = l.city
              AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
        ) AS double)
    ) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') THEN l.serviceLoad ELSE 0 END), 0),
    SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END),
    
    CAST((SELECT SUM(la.labour)
          FROM Labour la
          WHERE la.loadType = 'BODYSHOP' AND la.city = l.city
          AND (:months IS NULL OR la.month IN (:months))
          AND (:years IS NULL OR la.year IN (:years))
          AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
    ) AS double),

    CAST((SELECT SUM(s.brSparesCurrentYear)
          FROM Spares s
          WHERE (:months IS NULL OR s.month IN (:months)) AND s.city = l.city
          AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
    ) AS double),

    CAST((SELECT SUM(la.labour)
          FROM Labour la
          WHERE la.loadType = 'BODYSHOP' AND la.city = l.city
          AND (:months IS NULL OR la.month IN (:months))
          AND (:years IS NULL OR la.year IN (:years))
          AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
    ) AS double) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END), 0),

    CAST((SELECT SUM(s.brSparesCurrentYear)
          FROM Spares s
          WHERE (:months IS NULL OR s.month IN (:months)) AND s.city = l.city
          AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
    ) AS double) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END), 0),

    (
        CAST((SELECT SUM(la.labour)
              FROM Labour la
              WHERE la.loadType = 'BODYSHOP' AND la.city = l.city
              AND (:months IS NULL OR la.month IN (:months))
              AND (:years IS NULL OR la.year IN (:years))
              AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
        ) AS double)
        +
        CAST((SELECT SUM(s.brSparesCurrentYear)
              FROM Spares s
              WHERE (:months IS NULL OR s.month IN (:months)) AND s.city = l.city
              AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
        ) AS double)
    ) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END), 0)
)
FROM Loadd l
WHERE (:months IS NULL OR l.month IN (:months))
AND (:years IS NULL OR l.year IN (:years))
AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))
GROUP BY l.city
""")
    List<PerVehicleReportSummaryDTO> getPerVehicleSummary(
            @Param("months") List<String> months,
            @Param("years") List<String> years,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear
    );

    @Query("""
SELECT new com.mandovi.DTO.PerVehicleReportSummaryDTO(
    l.city,
    l.branch,
    SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') THEN l.serviceLoad ELSE 0 END),
    
    CAST((SELECT SUM(la.labour)
          FROM Labour la
          WHERE la.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') AND la.branch = l.branch
          AND (:months IS NULL OR la.month IN (:months))
          AND (:years IS NULL OR la.year IN (:years))
          AND (:cities IS NULL OR la.city IN (:cities))
          AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
    ) AS double),

    CAST((SELECT SUM(s.srSparesCurrentYear)
          FROM Spares s
          WHERE (:months IS NULL OR s.month IN (:months)) AND s.branch = l.branch
          AND (:cities IS NULL OR s.city IN (:cities))
          AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
    ) AS double),

    CAST((SELECT SUM(la.labour)
          FROM Labour la
          WHERE la.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') AND la.branch = l.branch
          AND (:months IS NULL OR la.month IN (:months))
          AND (:years IS NULL OR la.year IN (:years))
          AND (:cities IS NULL OR la.city IN (:cities))
          AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
    ) AS double) *100000
    /
    NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') THEN l.serviceLoad ELSE 0 END), 0),

    CAST((SELECT SUM(s.srSparesCurrentYear)
          FROM Spares s
          WHERE (:months IS NULL OR s.month IN (:months)) AND s.branch = l.branch
          AND (:cities IS NULL OR s.city IN (:cities))
          AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
    ) AS double) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') THEN l.serviceLoad ELSE 0 END), 0),

    (
        CAST((SELECT SUM(la.labour)
              FROM Labour la
              WHERE la.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') AND la.branch = l.branch
              AND (:months IS NULL OR la.month IN (:months))
              AND (:years IS NULL OR la.year IN (:years))
              AND (:cities IS NULL OR la.city IN (:cities))
              AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
        ) AS double)
        +
        CAST((SELECT SUM(s.srSparesCurrentYear)
              FROM Spares s
              WHERE (:months IS NULL OR s.month IN (:months)) AND s.branch = l.branch
              AND (:cities IS NULL OR s.city IN (:cities))
              AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
        ) AS double)
    ) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'OTHERS', 'RR') THEN l.serviceLoad ELSE 0 END), 0),
    SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END),
    
    CAST((SELECT SUM(la.labour)
          FROM Labour la
          WHERE la.loadType = 'BODYSHOP' AND la.branch = l.branch
          AND (:months IS NULL OR la.month IN (:months))
          AND (:years IS NULL OR la.year IN (:years))
          AND (:cities IS NULL OR la.city IN (:cities))
          AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
    ) AS double),

    CAST((SELECT SUM(s.brSparesCurrentYear)
          FROM Spares s
          WHERE (:months IS NULL OR s.month IN (:months)) AND s.branch = l.branch
          AND (:cities IS NULL OR s.city IN (:cities))
          AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
    ) AS double),

    CAST((SELECT SUM(la.labour)
          FROM Labour la
          WHERE la.loadType = 'BODYSHOP' AND la.branch = l.branch
          AND (:months IS NULL OR la.month IN (:months))
          AND (:years IS NULL OR la.year IN (:years))
          AND (:cities IS NULL OR la.city IN (:cities))
          AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
    ) AS double) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END), 0),

    CAST((SELECT SUM(s.brSparesCurrentYear)
          FROM Spares s
          WHERE (:months IS NULL OR s.month IN (:months)) AND s.branch = l.branch
          AND (:cities IS NULL OR s.city IN (:cities))
          AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
    ) AS double) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END), 0),

    (
        CAST((SELECT SUM(la.labour)
              FROM Labour la
              WHERE la.loadType = 'BODYSHOP' AND la.branch = l.branch
              AND (:months IS NULL OR la.month IN (:months))
              AND (:years IS NULL OR la.year IN (:years))
              AND (:cities IS NULL OR la.city IN (:cities))
              AND (:qtrWise IS NULL OR la.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR la.halfYear IN (:halfYear))
        ) AS double)
        +
        CAST((SELECT SUM(s.brSparesCurrentYear)
              FROM Spares s
              WHERE (:months IS NULL OR s.month IN (:months)) AND s.branch = l.branch
              AND (:cities IS NULL OR s.city IN (:cities))
              AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))
        ) AS double)
    ) * 100000
    /
    NULLIF(SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END), 0)
)
FROM Loadd l
WHERE (:months IS NULL OR l.month IN (:months))
AND (:years IS NULL OR l.year IN (:years))
AND (:cities IS NULL OR l.city IN (:cities))
AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))
GROUP BY l.city, l.branch
""")
    List<PerVehicleReportSummaryDTO> getPerVehicleSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("years") List<String> years,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear
    );

}
