package com.mandovi.Repository;

import com.mandovi.DTO.VASSummaryDTO;
import com.mandovi.Entity.VAS;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VASRepository extends JpaRepository<VAS, Integer> {

    @Transactional
    @Query("""
    SELECT v FROM VAS v
    WHERE (:months IS NULL OR v.month IN (:months))
    AND (:years IS NULL OR v.year IN (:years)) AND (:financialYears IS NULL OR v.financialYear IN (:financialYears))
    """)
    public List<VAS> getVASByMonthYear (@Param("months") List<String> months, @Param("years") List<String> years,
                                        @Param("financialYears") List<String> financialYears);

    @Transactional
    @Modifying
    @Query("DELETE FROM VAS v WHERE v.month = :month AND v.year = :year")
    void deleteByMonthYear(@Param("month") String month, @Param("year") String year );

    //Group by city
    @Query("""
   SELECT new com.mandovi.DTO.VASSummaryDTO(
       v.city,
       null,
       l.pmsLoad,
       SUM(CASE WHEN v.vas='DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='DIAGNOSTIC CHARGES' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.pmsLoad,0),
       l.fr3PmsLoad,
       SUM(CASE WHEN v.vas='Wheel Alignment' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='Wheel Alignment' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='Wheel Alignment' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.fr3PmsLoad,0),
       l.fr3PmsLoad,
       SUM(CASE WHEN v.vas='Wheel Balancing' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='Wheel Balancing' THEN v.basicAmt ELSE 0 END) AS double),
       ((SUM(CASE WHEN v.vas='Wheel Balancing' THEN v.jobCardNo ELSE 0 END)/4)*100.0) /
           NULLIF(l.fr3PmsLoad,0),
       l.totalLoad,
       SUM(CASE WHEN v.vas='Exterior Cleaning' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='Exterior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='Exterior Cleaning' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.totalLoad,0),
       SUM(CASE WHEN v.vas='Interior Cleaning' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='Interior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='Interior Cleaning' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.totalLoad,0),
       SUM(CASE WHEN v.vas='Underbody Coating' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='Underbody Coating' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='Underbody Coating' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.fr3PmsLoad,0),
       SUM(CASE WHEN v.vas='Top Body Coating' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='Top Body Coating' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='Top Body Coating' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.fr3PmsLoad,0),
       SUM(CASE WHEN v.vas='RAT MESH' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='RAT MESH' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='RAT MESH' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.fr3PmsLoad,0),
       SUM(CASE WHEN v.vas='Evaporator Cleaning' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='Evaporator Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='Evaporator Cleaning' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.fr3PmsLoad,0),
       SUM(CASE WHEN v.vas='A/C Vent Cleaning' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='A/C Vent Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='A/C Vent Cleaning' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.fr3PmsLoad,0),
       SUM(CASE WHEN v.vas='Plastic Repair' THEN v.jobCardNo ELSE 0 END),
       CAST(SUM(CASE WHEN v.vas='Plastic Repair' THEN v.basicAmt ELSE 0 END) AS double),
       (SUM(CASE WHEN v.vas='Plastic Repair' THEN v.jobCardNo ELSE 0 END)*100.0) /
           NULLIF(l.fr3PmsLoad,0)
   )
   FROM VAS v
   LEFT JOIN (
       SELECT l.city as city,
              SUM(CASE WHEN l.loadType='PMS' THEN l.serviceLoad ELSE 0 END) as pmsLoad,
              SUM(CASE WHEN l.serviceTypeCode IN ('FR3','PMS') THEN l.serviceLoad ELSE 0 END) as fr3PmsLoad,
              SUM(CASE WHEN l.loadType IN ('FREE SERVICE','RR','BODYSHOP','PMS') THEN l.serviceLoad ELSE 0 END) as totalLoad
       FROM Loadd l
       WHERE l.financialYear = :financialYear
       AND (:months IS NULL OR l.month IN (:months))
       GROUP BY l.city
   ) l ON l.city=v.city

   WHERE (:months IS NULL OR v.month IN (:months))
   AND (:qtrWise IS NULL OR v.qtrWise IN (:qtrWise))
   AND (:halfYear IS NULL OR v.halfYear IN (:halfYear))
   AND v.financialYear = :financialYear
   GROUP BY v.city,l.pmsLoad,l.fr3PmsLoad,l.totalLoad
""")
    List<VASSummaryDTO> getVASSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("financialYear") String financialYear );

    //Group by branch
    @Query("""
    SELECT new com.mandovi.DTO.VASSummaryDTO(
        v.city,
        v.branch,
        l.pmsLoad,
        SUM(CASE WHEN v.vas='DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='DIAGNOSTIC CHARGES' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.pmsLoad,0),
        l.fr3PmsLoad,
        SUM(CASE WHEN v.vas='Wheel Alignment' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='Wheel Alignment' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='Wheel Alignment' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.fr3PmsLoad,0),
        l.fr3PmsLoad,
        SUM(CASE WHEN v.vas='Wheel Balancing' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='Wheel Balancing' THEN v.basicAmt ELSE 0 END) AS double),
        ((SUM(CASE WHEN v.vas='Wheel Balancing' THEN v.jobCardNo ELSE 0 END)/4)*100.0) /
            NULLIF(l.fr3PmsLoad,0),
        l.totalLoad,
        SUM(CASE WHEN v.vas='Exterior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='Exterior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='Exterior Cleaning' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.totalLoad,0),
        SUM(CASE WHEN v.vas='Interior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='Interior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='Interior Cleaning' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.totalLoad,0),
        SUM(CASE WHEN v.vas='Underbody Coating' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='Underbody Coating' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='Underbody Coating' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.fr3PmsLoad,0),
        SUM(CASE WHEN v.vas='Top Body Coating' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='Top Body Coating' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='Top Body Coating' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.fr3PmsLoad,0),
        SUM(CASE WHEN v.vas='RAT MESH' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='RAT MESH' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='RAT MESH' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.fr3PmsLoad,0),
        SUM(CASE WHEN v.vas='Evaporator Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='Evaporator Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='Evaporator Cleaning' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.fr3PmsLoad,0),
        SUM(CASE WHEN v.vas='A/C Vent Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='A/C Vent Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='A/C Vent Cleaning' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.fr3PmsLoad,0),
        SUM(CASE WHEN v.vas='Plastic Repair' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas='Plastic Repair' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas='Plastic Repair' THEN v.jobCardNo ELSE 0 END)*100.0) /
            NULLIF(l.fr3PmsLoad,0)
    )
    FROM VAS v
    LEFT JOIN (
        SELECT
            l.branch as branch,
            SUM(CASE WHEN l.loadType='PMS' THEN l.serviceLoad ELSE 0 END) as pmsLoad,
            SUM(CASE WHEN l.serviceTypeCode IN ('FR3','PMS') THEN l.serviceLoad ELSE 0 END) as fr3PmsLoad,
            SUM(CASE WHEN l.loadType IN ('FREE SERVICE','RR','BODYSHOP','PMS') THEN l.serviceLoad ELSE 0 END) as totalLoad
        FROM Loadd l
        WHERE (:financialYears IS NULL OR l.financialYear IN (:financialYears))
        AND (:months IS NULL OR l.month IN (:months))
        GROUP BY l.branch
    ) l ON l.branch = v.branch
    WHERE (:months IS NULL OR v.month IN (:months))
    AND (:cities IS NULL OR v.city IN (:cities))
    AND (:qtrWise IS NULL OR v.qtrWise IN (:qtrWise))
    AND (:halfYear IS NULL OR v.halfYear IN (:halfYear))
    AND (:financialYears IS NULL OR v.financialYear IN (:financialYears))
    GROUP BY v.city,v.branch,l.pmsLoad,l.fr3PmsLoad,l.totalLoad
    """)
    List<VASSummaryDTO> getVASSummaryBranchWise(
           @Param("months") List<String> months,
           @Param("cities") List<String> cities,
           @Param("qtrWise") List<String> qtrWise,
           @Param("halfYear") List<String> halfYear,
           @Param("financialYears") List<String> financialYears );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.vas;", nativeQuery = true)
    void deleteVASAll ();

}
