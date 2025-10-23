package com.mandovi.Repository;

import com.mandovi.DTO.BatteryTyreSummaryDTO;
import com.mandovi.Entity.BatteryTyre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatteryTyreRepository extends JpaRepository<BatteryTyre, Integer>, JpaSpecificationExecutor<BatteryTyre> {


    @Query("SELECT b FROM BatteryTyre b WHERE b.month = :month AND b.year = :year")
    List<BatteryTyre> getBatteryTyreByMonthYear(@Param("month") String month, @Param("year") String year);

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(
            b.city,
            null,
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),0),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),0),
            SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL),
            ((SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL)) * 100.00 )  / SUM(b.sumOfNetRetailDDL)
            )
            FROM BatteryTyre b
            WHERE (:months IS NULL OR b.month IN (:months))
             AND (:qtrWise IS NULL OR b.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR b.halfYear = :halfYear)
            GROUP BY b.city
            """)
    List<BatteryTyreSummaryDTO> getBatteryTyreSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(
            null,
            b.branch,
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),0),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),0),
            SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL),
            ((SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL)) * 100.00 )  / SUM(b.sumOfNetRetailDDL)
            )
            FROM BatteryTyre b
            WHERE (:months IS NULL OR b.month IN (:months))
             AND (:qtrWise IS NULL OR b.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR b.halfYear = :halfYear)
            GROUP BY b.branch
            """)
    List<BatteryTyreSummaryDTO> getBatteryTyreSummaryByBranch(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(
            b.city,
            b.branch,
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),0),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),0),
            SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL),
            ((SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL)) * 100.00 )  / SUM(b.sumOfNetRetailDDL)
            )
            FROM BatteryTyre b
            WHERE (:months IS NULL OR b.month IN (:months))
             AND (:qtrWise IS NULL OR b.qtrWise = :qtrWise)
             AND (:halfYear IS NULL OR b.halfYear = :halfYear)
            GROUP BY b.city, b.branch
            """)
    List<BatteryTyreSummaryDTO> getBatteryTyreSummaryByCityAnaBranch(
            @Param("months") List<String> months,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

}
