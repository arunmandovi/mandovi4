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

    //Oil Qty Group by city
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            o.city,
            null,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END)
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.city
    """)
    List<OilSummaryDTO> getOilQtySummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Oil Qty Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            null,
            o.branch,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END)
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.branch
    """)
    List<OilSummaryDTO> getOilQtySummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Oil Qty Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            o.city,
            o.branch,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END)
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.city, o.branch
    """)
    List<OilSummaryDTO> getOilQtySummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Oil Percentage Qty Group by city
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            o.city,
            null,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            100.0
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.city
    """)
    List<OilSummaryDTO> getOilPercentageQtySummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Oil Percentage Qty Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            null,
            o.branch,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            100.0
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.branch
    """)
    List<OilSummaryDTO> getOilPercentageQtySummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Oil Percentage Qty Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            o.city,
            o.branch,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC') THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailQty ELSE 0 END) * 100 /
            NULLIF(SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailQty ELSE 0 END), 0),
            100.0
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.city, o.branch
    """)
    List<OilSummaryDTO> getOilPercentageQtySummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Oil Profit Qty Group by city
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            o.city,
            null,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC' , 'SEMI SYNTHETIC') THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC' , 'SEMI SYNTHETIC') THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailDDL ELSE 0 END)
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.city
    """)
    List<OilSummaryDTO> getOilProfitSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Oil Profit Qty Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            null,
            o.branch,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC' , 'SEMI SYNTHETIC') THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC' , 'SEMI SYNTHETIC') THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailDDL ELSE 0 END)
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.branch
    """)
    List<OilSummaryDTO> getOilProfitSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Oil Profit Qty Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.OilSummaryDTO(
            o.city,
            o.branch,
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'FULL SYNTHETIC' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'SEMI SYNTHETIC' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC' , 'SEMI SYNTHETIC') THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC' , 'SEMI SYNTHETIC') THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType = 'MINERAL' THEN o.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN o.oilType IN ('FULL SYNTHETIC','SEMI SYNTHETIC','MINERAL') THEN o.netRetailDDL ELSE 0 END)
        )
        FROM Oil o
        WHERE (:month IS NULL OR o.month = :month)
          AND (:qtrWise IS NULL OR o.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR o.halfYear = :halfYear)
        GROUP BY o.city, o.branch
    """)
    List<OilSummaryDTO> getOilProfitSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );
}
