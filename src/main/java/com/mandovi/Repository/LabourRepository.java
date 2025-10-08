package com.mandovi.Repository;

import com.mandovi.DTO.LabourSummaryDTO;
import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.Entity.Labour;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabourRepository extends JpaRepository<Labour, Integer> {

    @Transactional
    @Query("SELECT l FROM Labour l WHERE l.month = :month AND l.year = :year")
    List<Labour> getLabourByMonthYear(@Param("month") String month, @Param("year") String year);

    //Service Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourServiceSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Service Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourServiceSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Service Group by city & branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourServiceSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BODYSHOP Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'BODYSHOP'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourBodyShopSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BODYSHOP Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'BODYSHOP'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourBodyShopSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BODYSHOP Group by city & branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'BODYSHOP'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourBodyShopSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //SRBR Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            NULL,
            SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END),
            SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2025-2026' THEN l.labour ELSE 0 END),
            ( (SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2025-2026' THEN l.labour ELSE 0 END) -
               SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END)) /
               NULLIF(SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END), 0)
            ) )
            FROM Labour l
            WHERE (:month IS NULL OR l.month = :month)
              AND (:year IS NULL OR l.year = :year)
              AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourSrBrSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //SRBR Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END),
            SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2025-2026' THEN l.labour ELSE 0 END),
            ( (SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2025-2026' THEN l.labour ELSE 0 END) -
               SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END)) /
               NULLIF(SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END), 0)
            ) )
            FROM Labour l
            WHERE (:month IS NULL OR l.month = :month)
              AND (:year IS NULL OR l.year = :year)
              AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourSrBrSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //SRBR Group by city and branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END),
            SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2025-2026' THEN l.labour ELSE 0 END),
            ( (SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2025-2026' THEN l.labour ELSE 0 END) -
               SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END)) /
               NULLIF(SUM(CASE WHEN l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR', 'BODYSHOP') AND l.financialYear = '2024-2025' THEN l.labour ELSE 0 END), 0)
            ) )
            FROM Labour l
            WHERE (:month IS NULL OR l.month = :month)
              AND (:year IS NULL OR l.year = :year)
              AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourSrBrSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FREE SERVICE Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'FREE SERVICE'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourFreeServiceSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FREE SERVICE Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'FREE SERVICE'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourFreeServiceSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FREE SERVICE Group by city & branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'FREE SERVICE'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourFreeServiceSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //PMS Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'PMS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourPMSSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //PMS Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'PMS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourPMSSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //PMS Group by city & branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'PMS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourPMSSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FPR Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourFPRSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FPR Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourFPRSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FPR Group by city & branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourFPRSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Running Repair Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'RR'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourRunningRepairSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Running Repair Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'RR'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourRunningRepairSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Running Repair Group by city & branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'RR'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourRunningRepairSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Others Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'OTHERS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourOthersSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Others Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'OTHERS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourOthersSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Others Group by city & branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE l.loadType = 'OTHERS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourOthersSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);
}
