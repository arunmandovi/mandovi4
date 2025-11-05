package com.mandovi.Repository;

import com.mandovi.DTO.VASSummaryDTO;
import com.mandovi.Entity.VAS;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VASRepository extends JpaRepository<VAS, Integer> {

    @Transactional
    @Query("""
    SELECT v FROM VAS v
    WHERE (:months IS NULL OR v.month IN (:months))
    AND (:years IS NULL OR v.year IN (:years))
    """)
    public List<VAS> getVASByMonthYear (@Param("months") List<String> months, @Param("years") List<String> years);

    //Group by city
    @Query("""
    SELECT new com.mandovi.DTO.VASSummaryDTO(
        v.city,
        null,
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.loadType = 'PMS' AND l.city = v.city AND l.financialYear = '2025-2026'
              AND (:months IS NULL OR l.month IN (:months))) AS double),
        SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType = 'PMS' AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
              AND (:months IS NULL OR l.month IN (:months))) AS double),
        SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
              AND (:months IS NULL OR l.month IN (:months))) AS double),
        SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.loadType IN ('FREE SERVICE','RR','BODYSHOP','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
              AND (:months IS NULL OR l.month IN (:months))) AS double),
        SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType IN ('FREE SERVICE','RR','BODYSHOP','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType IN ('FREE SERVICE','RR','BODYSHOP','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.city = v.city AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))) AS double), 0)
            )
    FROM VAS v
    WHERE (:months IS NULL OR v.month IN (:months))
    AND (:qtrWise IS NULL OR v.qtrWise IN (:qtrWise))
    AND (:halfYear IS NULL OR v.halfYear IN (:halfYear))
    GROUP BY v.city
    """)
    List<VASSummaryDTO> getVASSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    //Group by branch
    @Query("""
    SELECT new com.mandovi.DTO.VASSummaryDTO(
        v.city,
        v.branch,
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.loadType = 'PMS' AND l.branch = v.branch AND l.financialYear = '2025-2026'
              AND (:months IS NULL OR l.month IN (:months))
              AND (:cities IS NULL OR l.city IN (:cities))
              AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double),
        SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType = 'PMS' AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
              AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double),
        SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double),
        SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.basicAmt ELSE 0 END) AS double),
        ((SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0) / 4),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.loadType IN ('FREE SERVICE','RR','BODYSHOP','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
              AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double),
        SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType IN ('FREE SERVICE','RR','BODYSHOP','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType IN ('FREE SERVICE','RR','BODYSHOP','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0),
        SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('FR3','PMS') AND l.branch = v.branch AND l.financialYear = '2025-2026'
                     AND (:months IS NULL OR l.month IN (:months))
                     AND (:cities IS NULL OR l.city IN (:cities))
                     AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
                     AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))) AS double), 0)
            )
    FROM VAS v
    WHERE (:months IS NULL OR v.month IN (:months))
     AND (:cities IS NULL OR v.city IN (:cities))
     AND (:qtrWise IS NULL OR v.qtrWise IN (:qtrWise))
     AND (:halfYear IS NULL OR v.halfYear IN (:halfYear))
    GROUP BY v.city, v.branch
    """)
    List<VASSummaryDTO> getVASSummaryBranchWise(
           @Param("months") List<String> months,
           @Param("cities") List<String> cities,
           @Param("qtrWise") List<String> qtrWise,
           @Param("halfYear") List<String> halfYear );


}
