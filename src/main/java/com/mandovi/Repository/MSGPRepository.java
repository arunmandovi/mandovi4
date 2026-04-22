package com.mandovi.Repository;

import com.mandovi.DTO.MSGPSummaryDTO;
import com.mandovi.Entity.MSGP;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MSGPRepository extends JpaRepository<MSGP, Integer> {

    @Transactional
    @Query("""
    SELECT m FROM MSGP m
    WHERE (:months IS NULL OR m.month IN  (:months))
    AND (:years IS NULL OR m.year IN  (:years)) AND (:deleteYears IS NULL OR m.deleteYear IN (:deleteYears))
    """)
    public List<MSGP> getMSGPByMonthYear(@Param("months") List<String> months,@Param("years") List<String> years,
                                         @Param("deleteYears") List<String> deleteYears);

    @Transactional
    @Modifying
    @Query("DELETE FROM MSGP m WHERE m.month = :month AND m.year = :year AND m.deleteYear = :deleteYear")
    void deleteByMonthYear(@Param("month") String month,@Param("year") String year, @Param("deleteYear") String deleteYear );

    //Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE (:months IS NULL OR m.month IN (:months))
          AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
          AND m.deleteYear = :currYear
          AND m.financialYear IN (:prevYear, :currYear)
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("prevYear") String prevYear,
            @Param("currYear") String currYear );

    //Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'BODYSHOP' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'FREE SERVICE' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'PMS' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'RR' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = :currYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = :prevYear AND loadType = 'OTHERS' THEN m.sumOfNetRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE (:months IS NULL OR m.month IN (:months))
         AND (:cities IS NULL OR m.city IN (:cities))
         AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
         AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
         AND m.deleteYear = :currYear
         AND m.financialYear IN (:prevYear, :currYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("prevYear") String prevYear,
            @Param("currYear") String currYear );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.msgp;", nativeQuery = true)
    void deleteMSGPALL ();

}
