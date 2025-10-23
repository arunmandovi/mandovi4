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

    // Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE (:months IS NULL OR l.month IN (:months))
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LoaddSummaryDTO> getLoaddSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            MAX(l.city),
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE (:months IS NULL OR l.month IN (:months))
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LoaddSummaryDTO> getLoaddSummaryByBranch(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by city and branch
    @Query("""
            SELECT new com.mandovi.DTO.LoaddSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ),
            ((SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ))*100.00) /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END ) * 100.00 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.serviceLoad ELSE 0 END ), 0)
            )
            FROM Loadd l
            WHERE (:months IS NULL OR l.month IN (:months))
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LoaddSummaryDTO> getLoaddSummaryByCityAndBranch(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);
}