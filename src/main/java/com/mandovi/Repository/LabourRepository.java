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
     AND (:years IS NULL OR l.year IN (:years)) AND (:deleteYears IS NULL OR l.deleteYear IN (:deleteYears))
    """)
    List<Labour> getLabourByMonthYear(
            @Param("months") List<String> months,
            @Param("years") List<String> years,
            @Param("deleteYears") List<String> deleteYears );


    @Transactional
    @Modifying
    @Query("DELETE FROM Labour l WHERE l.month = :month AND l.year = :year AND l.deleteYear = :deleteYear")
    void deleteByMonthYear(@Param("month") String month,
                           @Param("year") String year,
                           @Param("deleteYear") String deleteYear );

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            null,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','NO','BODYSHOP') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','NO','BODYSHOP') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','NO','BODYSHOP') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','NO','BODYSHOP') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('OTHERS','FREE SERVICE', 'PMS', 'RR','NO','BODYSHOP') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'PMS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'RR' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'RR' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'RR' THEN l.labour ELSE 0 END ), 0),
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            (SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ) -
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END )) * 100 /
            NULLIF(SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ), 0)
            )
            FROM Labour l
            WHERE (:months IS NULL OR l.month IN (:months))
            AND (:channels IS NULL OR l.channel IN (:channels))
            AND (:qtrWise IS NULL OR l.qtrWise IN (:qtrWise))
            AND (:halfYear IS NULL OR l.halfYear IN (:halfYear))
            AND l.deleteYear = :currYear
            GROUP BY l.city
            """)
    List<LabourSummaryDTO> getLabourSummaryByCity(
            @Param("months") List<String> months,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("prevYear") String prevYear,
            @Param("currYear") String currYear );

    //Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.LabourSummaryDTO(
            l.city,
            l.branch,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO') THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = :currYear
                AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO')
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = :currYear
                         AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO')
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = :prevYear
                         AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO')
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = :prevYear
                            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR', 'NO')
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'BODYSHOP' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = :currYear
                AND l.loadType = 'BODYSHOP'
                THEN l.labour ELSE 0 END) = 0
                THEN NULL
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = :currYear
                         AND l.loadType = 'BODYSHOP'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = :prevYear
                         AND l.loadType = 'BODYSHOP'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = :prevYear
                            AND l.loadType = 'BODYSHOP'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP') THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = :currYear
                AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP')
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = :currYear
                         AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP')
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = :prevYear
                         AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP')
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = :prevYear
                            AND l.loadType IN ('OTHERS','FREE SERVICE','PMS','RR','BODYSHOP')
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'FREE SERVICE' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = :currYear
                AND l.loadType = 'FREE SERVICE'
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = :currYear
                         AND l.loadType = 'FREE SERVICE'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = :prevYear
                         AND l.loadType = 'FREE SERVICE'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = :prevYear
                            AND l.loadType = 'FREE SERVICE'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'PMS' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = :currYear
                AND l.loadType = 'PMS'
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = :currYear
                         AND l.loadType = 'PMS'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = :prevYear
                         AND l.loadType = 'PMS'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = :prevYear
                            AND l.loadType = 'PMS'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR') THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = :currYear
                AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = :currYear
                         AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = :prevYear
                         AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = :prevYear
                            AND l.loadType IN ('FREE SERVICE', 'PMS', 'RR')
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'RR' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = :currYear
                AND l.loadType = 'RR'
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = :currYear
                         AND l.loadType = 'RR'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = :prevYear
                         AND l.loadType = 'RR'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = :prevYear
                            AND l.loadType = 'RR'
                            THEN l.labour ELSE 0 END),
                        0)
                    )
            END,
            SUM(CASE WHEN l.financialYear = :prevYear AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            SUM(CASE WHEN l.financialYear = :currYear AND l.loadType = 'OTHERS' THEN l.labour ELSE 0 END ),
            CASE
                WHEN SUM(CASE WHEN l.financialYear = :currYear
                AND l.loadType = 'OTHERS'
                THEN l.labour ELSE 0 END) = 0
                THEN 0
                ELSE
                    (
                        (SUM(CASE WHEN l.financialYear = :currYear
                         AND l.loadType = 'OTHERS'
                         THEN l.labour ELSE 0 END)
                         -
                         SUM(CASE WHEN l.financialYear = :prevYear
                         AND l.loadType = 'OTHERS'
                         THEN l.labour ELSE 0 END)
                        ) * 100
                        /
                        NULLIF(
                            SUM(CASE WHEN l.financialYear = :prevYear
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
             AND l.deleteYear = :currYear
             AND l.financialYear IN (:prevYear, :currYear)
            GROUP BY l.city,l.branch
            """)
    List<LabourSummaryDTO> getLabourSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @Param("prevYear") String prevYear,
            @Param("currYear") String currYear );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.labour;", nativeQuery = true)
    void deleteLabourAll ();
}
