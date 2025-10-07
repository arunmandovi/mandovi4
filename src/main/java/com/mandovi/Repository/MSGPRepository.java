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
    @Query("SELECT m FROM MSGP m WHERE m.month = :month AND m.year = :year")
    public List<MSGP> getMSGPByMonthYear(@Param("month") String month,@Param("year") String year);

    //Service and BodyShop Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPServiceBodyShopSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Service and BodyShop Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            null,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MSGPSummaryDTO> getMSGPServiceBodyShopSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Service and BodyShop Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPServiceBodyShopSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Service Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType NOT IN ('BODYSHOP')
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPServiceSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Service Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            null,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType NOT IN ('BODYSHOP')
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MSGPSummaryDTO> getMSGPServiceSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Service Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType NOT IN ('BODYSHOP')
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPServiceSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //BodyShop Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'BODYSHOP'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPBodyShopSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //BodyShop Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            null,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'BODYSHOP'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MSGPSummaryDTO> getMSGPBodyShopSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //BodyShop Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'BODYSHOP'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPBodysShopSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //FREE SERVICE Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'FREE SERVICE'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPFreeServiceSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //FREE SERVICE Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            null,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'FREE SERVICE'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MSGPSummaryDTO> getMSGPFreeServiceSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //FREE SERVICE Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'FREE SERVICE'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPFreeServiceSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //PMS Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'PMS'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPPMSSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //PMS Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            null,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'PMS'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MSGPSummaryDTO> getMSGPPMSSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //PMS Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'PMS'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPPMSSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Running Repair Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'RR'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPRunningRepairSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Running Repair Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            null,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'RR'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MSGPSummaryDTO> getMSGPRunningRepairSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Running Repair Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'RR'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPRunningRepairSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Others Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'OTHERS'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MSGPSummaryDTO> getMSGPOthersSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Others Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            null,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'OTHERS'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MSGPSummaryDTO> getMSGPOthersSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Others Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END),
            ((SUM(CASE WHEN m.financialYear = '2025-2026' THEN m.netRetailDDL ELSE 0 END ) -
            SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN m.financialYear = '2024-2025' THEN m.netRetailDDL ELSE 0 END ), 0)
        )
        FROM MSGP m
        WHERE m.loadType = 'OTHERS'
          AND (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPSummaryDTO> getMSGPOthersSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );
}
