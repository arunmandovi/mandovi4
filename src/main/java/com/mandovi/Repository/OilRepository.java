package com.mandovi.Repository;

import com.mandovi.DTO.OilSummaryDTO;
import com.mandovi.Entity.Oil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OilRepository extends JpaRepository<Oil, Integer> {

    @Transactional
    @Query("SELECT o FROM Oil o WHERE o.month = :month AND o.year = :year")
    public List<Oil> getOilByMonthYear(@Param("month") String month,@Param("year") String year);

    //Group by city
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            o.city,
            null,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailSelling ELSE 0 END)
        )
        FROM Oil o
        WHERE (:months IS NULL OR o.month IN (:months))
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.city
    """)
    List<OilSummaryDTO> getOilSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            MAX(o.city),
            o.branch,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailSelling ELSE 0 END)
        )
        FROM Oil o
        WHERE (:months IS NULL OR o.month IN (:months))
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.branch
    """)
    List<OilSummaryDTO> getOilSummaryByBranch(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            o.city,
            o.branch,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailSelling ELSE 0 END)
        )
        FROM Oil o
        WHERE (:months IS NULL OR o.month IN (:months))
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.city,o.branch
    """)
    List<OilSummaryDTO> getOilSummaryByCityAndBranch(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);
}
