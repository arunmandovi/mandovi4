package com.mandovi.Repository;

import com.mandovi.DTO.BatteryTyreSummaryDTO;
import com.mandovi.Entity.BatteryTyre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatteryTyreRepository extends JpaRepository<BatteryTyre, Integer>, JpaSpecificationExecutor<BatteryTyre> {


    @Query("""
          SELECT b
          FROM BatteryTyre b
          WHERE (:months IS NULL OR b.month IN :months)
          AND (:years IS NULL OR b.year IN :years)
    """)
    List<BatteryTyre> getBatteryTyreByMonthYear(
            @Param("months") List<String> months,
            @Param("years") List<String> years
    );

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(
            b.city,
            null,
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            CAST((SELECT (NULLIF(SUM(s.batteryCurrentYear), 0) - SUM(s.batteryLastYear)) * 100 / NULLIF(SUM(s.batteryLastYear), 0)
              FROM Spares s
              WHERE s.city = b.city
              AND (:months IS NULL OR s.month IN (:months))
             AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))) AS double),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            CAST((SELECT (NULLIF(SUM(s.tyreCurrentYear), 0) - SUM(s.tyreLastYear)) * 100 / NULLIF(SUM(s.tyreLastYear), 0)
              FROM Spares s
              WHERE s.city = b.city
              AND (:months IS NULL OR s.month IN (:months))
             AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))) AS double),
            SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL),
            ((SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL)) * 100.00 )  / SUM(b.sumOfNetRetailDDL)
            )
            FROM BatteryTyre b
            WHERE (:months IS NULL OR b.month IN (:months))
             AND (:qtrWise IS NULL OR b.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR b.halfYear IN (:halfYear))
            GROUP BY b.city
            """)
    List<BatteryTyreSummaryDTO> getBatteryTyreSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    //Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(
            b.city,
            b.branch,
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'BATTERY' THEN b.sumOfNetRetailDDL ELSE 0 END),
            CAST((SELECT (NULLIF(SUM(s.batteryCurrentYear), 0) - SUM(s.batteryLastYear)) * 100 / NULLIF(SUM(s.batteryLastYear), 0)
              FROM Spares s
              WHERE s.city = b.city AND s.branch = b.branch
              AND (:months IS NULL OR s.month IN (:months))
             AND (:cities IS NULL OR s.city IN (:cities))
             AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))) AS double),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailQTY ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailSelling ELSE 0 END) - SUM(CASE WHEN b.oilType = 'TYRE' THEN b.sumOfNetRetailDDL ELSE 0 END),
            CAST((SELECT (NULLIF(SUM(s.tyreCurrentYear), 0) - SUM(s.tyreLastYear)) * 100 / NULLIF(SUM(s.tyreLastYear), 0)
              FROM Spares s
              WHERE s.city = b.city AND s.branch = b.branch
              AND (:months IS NULL OR s.month IN (:months))
             AND (:cities IS NULL OR s.city IN (:cities))
             AND (:qtrWise IS NULL OR s.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR s.halfYear IN (:halfYear))) AS double),
            SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL),
            ((SUM(b.sumOfNetRetailSelling) - SUM(b.sumOfNetRetailDDL)) * 100.00 )  / SUM(b.sumOfNetRetailDDL)
            )
            FROM BatteryTyre b
            WHERE (:months IS NULL OR b.month IN (:months))
             AND (:cities IS NULL OR b.city IN (:cities))
             AND (:qtrWise IS NULL OR b.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR b.halfYear IN (:halfYear))
            GROUP BY b.city, b.branch
            """)
    List<BatteryTyreSummaryDTO> getBatteryTyreSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    @Modifying
    @Transactional
    @Query (value = " TRUNCATE TABLE mandovi.battery_tyre;", nativeQuery = true)
    void deleteBatteryTyreAll ();

}
