package com.mandovi.Repository;

import com.mandovi.DTO.MGASummaryDTO;
import com.mandovi.DTO.MSGPSummaryDTO;
import com.mandovi.Entity.MSGP;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MSGPRepository extends JpaRepository<MSGP, Integer> {

    @Transactional
    @Query("""
    SELECT m FROM MSGP m
    WHERE (:months IS NULL OR m.month IN  (:months))
    AND (:years IS NULL OR m.year IN  (:years))
    """)
    public List<MSGP> getMSGPByMonthYear(@Param("months") List<String> months,@Param("years") List<String> years);

    //Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE (:months IS NULL OR m.month IN (:months))
          AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    //Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND m.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'BODYSHOP' THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'FREE SERVICE' THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'PMS' THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'RR' THEN m.netRetailDDL ELSE 0 END ), 0),
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' AND loadType = 'OTHERS' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE (:months IS NULL OR m.month IN (:months))
         AND (:cities IS NULL OR m.city IN (:cities))
         AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
         AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

}
