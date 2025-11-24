package com.mandovi.Repository;

import com.mandovi.DTO.LabourSummaryDTO;
import com.mandovi.Entity.Labour;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabourRepository extends JpaRepository<Labour, Integer> {

    @Transactional
    @Query("""
    SELECT l FROM Labour l
    WHERE (:months IS NULL OR l.month IN (:months))
     AND (:years IS NULL OR l.year IN (:years))
    """)
    List<Labour> getLabourByMonthYear(
            @Param("months") List<String> months,
            @Param("years") List<String> years);


    @Transactional
    @Modifying
    @Query("DELETE FROM Labour l WHERE l.month = :month")
    void deleteByMonth(@Param("month") String month);

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR','BODYSHOP') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE (:months IS NULL OR l.month IN (:months))
            AND (:channels IS NULL OR l.channel IN (:channels))
            AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
            AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourSummaryByCity(
            @Param("months") List<String> months,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear);

    //Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = '2025-2026'
                AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO')
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = '2025-2026'
                         AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO')
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = '2024-2025'
                         AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO')
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = '2024-2025'
                            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO')
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = '2025-2026'
                AND l.loadType = 'BODYSHOP'
                THEN l.labour ELSE 0 END) = 0
                THEN NULL
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = '2025-2026'
                         AND l.loadType = 'BODYSHOP'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = '2024-2025'
                         AND l.loadType = 'BODYSHOP'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = '2024-2025'
                            AND l.loadType = 'BODYSHOP'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = '2025-2026'
                AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP')
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = '2025-2026'
                         AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP')
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = '2024-2025'
                         AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP')
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = '2024-2025'
                            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP')
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = '2025-2026'
                AND l.loadType = 'FREE SERVICE'
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = '2025-2026'
                         AND l.loadType = 'FREE SERVICE'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = '2024-2025'
                         AND l.loadType = 'FREE SERVICE'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = '2024-2025'
                            AND l.loadType = 'FREE SERVICE'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = '2025-2026'
                AND l.loadType = 'PMS'
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = '2025-2026'
                         AND l.loadType = 'PMS'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = '2024-2025'
                         AND l.loadType = 'PMS'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = '2024-2025'
                            AND l.loadType = 'PMS'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = '2025-2026'
                AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = '2025-2026'
                         AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = '2024-2025'
                         AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = '2024-2025'
                            AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = '2025-2026'
                AND l.loadType = 'RR'
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = '2025-2026'
                         AND l.loadType = 'RR'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = '2024-2025'
                         AND l.loadType = 'RR'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = '2024-2025'
                            AND l.loadType = 'RR'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = '2024-2025' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = '2025-2026' AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = '2025-2026'
                AND l.loadType = 'OTHERS'
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = '2025-2026'
                         AND l.loadType = 'OTHERS'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = '2024-2025'
                         AND l.loadType = 'OTHERS'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = '2024-2025'
                            AND l.loadType = 'OTHERS'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END
            )
            FROM Labour l
            WHERE (:months IS NULL OR l.month IN (:months))
             AND (:cities IS NULL OR l.city IN (:cities))
             AND (:channels IS NULL OR l.channel IN (:channels))
             AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))
            GROUP BY l.city,l.branch
            """)
    List<LabourSummaryDTO> getLabourSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.labour;", nativeQuery = true)
    void deleteLabourAll ();
}
