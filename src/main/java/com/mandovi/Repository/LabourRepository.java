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

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR','BODYSHOP') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE (:month IS NULL OR l.month = :month)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            MAX(l.city),
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR','BODYSHOP') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE (:month IS NULL OR l.month = :month)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.branch
            """)
    List<LabourSummaryDTO> getLabourSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by city and branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR','BODYSHOP') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE (:month IS NULL OR l.month = :month)
            AND (:qtrWise IS NULL OR l.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR l.halfYear = :halfYear)
            GROUP BY l.city, l.branch
            """)
    List<LabourSummaryDTO> getLabourSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);
}
