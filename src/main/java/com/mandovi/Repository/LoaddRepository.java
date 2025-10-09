package com.mandovi.Repository;

import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.Entity.Loadd;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoaddRepository extends JpaRepository<Loadd, Integer> {

    @Transactional
    @Query("SELECT l FROM Loadd l WHERE l.month = :month AND l.year = :year ")
    List<Loadd> getLoadByMonthYear(@Param("month") String month, @Param("year") String year);

    //Service Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddServiceSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Service Group By Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddServiceSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Service Group by city and Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddServiceSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BodyShop Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'BODYSHOP'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddBodyShopSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BodyShop Group By Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'BODYSHOP'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddBodyShopSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BodyShop Group by city and Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'BODYSHOP'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddBodyShopSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Free Service Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'FREE SERVICE'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddFreeServiceSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FREE SERVICE Group By Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'FREE SERVICE'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddFreeServiceSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FREE SERVICE Group by city and Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'FREE SERVICE'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddFreeServiceSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //PMS Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'PMS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddPMSSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //PMS Group By Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'PMS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddPMSSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //PMS Group by city and Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'PMS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddPMSSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FPR Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddFPRSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FPR Group By Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddFPRSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //FPR Group by city and Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddFPRSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Running Repair Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'RR'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddRunningRepairSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Running Repair Group By Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'RR'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddRunningRepairSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Running Repair Group by city and Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'RR'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddRunningRepairSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //OTHERS Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'OTHERS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddOthersSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //OTHERS Group By Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            null,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'OTHERS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddOthersSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //OTHERS Group by city and Branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE l.loadType = 'OTHERS'
            AND (:month IS NULL OR l.month = :month)
            AND (:year IS NULL OR l.year = :year)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddOthersSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BS Loadd on FPS Loadd by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            CAST((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END), 0) AS long),
            CAST((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END), 0) AS long),
            (((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END), 0)) -
             ((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END), 0)))
            )
            FROM Loadd l
            WHERE (:month IS NULL OR l.month = :month)
              AND (:year IS NULL OR l.year = :year)
              AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddBSAndFPRSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BS Loadd on FPS Loadd by branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            null,
            l.branch,
            CAST((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END), 0) AS long),
            CAST((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END), 0) AS long),
            (((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END), 0)) -
             ((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END), 0)))
            )
            FROM Loadd l
            WHERE (:month IS NULL OR l.month = :month)
              AND (:year IS NULL OR l.year = :year)
              AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddBSAndFPRSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //BS Loadd on FPS Loadd by city and branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            CAST((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END), 0) AS long),
            CAST((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END), 0) AS long),
            (((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2025-2026' THEN l.serviceLoad ELSE 0 END), 0)) -
             ((SUM(CASE WHEN l.loadType = 'BODYSHOP' AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END) * 100.0) /
                 NULLIF(SUM(CASE WHEN l.loadType IN ('FREE SERVICE', 'PMS', 'RR') AND l.financialYear = '2024-2025' THEN l.serviceLoad ELSE 0 END), 0)))
            )
            FROM Loadd l
            WHERE (:month IS NULL OR l.month = :month)
              AND (:year IS NULL OR l.year = :year)
              AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddBSAndFPRSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);
}
