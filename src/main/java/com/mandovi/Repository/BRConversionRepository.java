package com.mandovi.Repository;

import com.mandovi.DTO.BRConversionSummaryDTO;
import com.mandovi.Entity.BRConversion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BRConversionRepository extends JpaRepository<BRConversion, Integer> {

    @Transactional
    @Query("""
    SELECT b FROM BRConversion b
    WHERE (:months IS NULL OR b.month IN (:months))
    AND (:years IS NULL OR b.year IN (:years))
    """)
    List<BRConversion> getBR_ConversionByMonthYear(
            @Param("months") List<String> months,
            @Param("years") List<String> years );

    // Group by city
    @Query("""
            SELECT new com.mandovi.DTO.BRConversionSummaryDTO(
                b.city,
                null,
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.grandTotal ELSE 0 END),
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.brConversion ELSE 0 END),
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.brConversion ELSE 0 END) * 100.0 /
                NULLIF(SUM(CASE WHEN b.channel = 'ARENA' THEN b.grandTotal ELSE 0 END), 0),
            
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.grandTotal ELSE 0 END),
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.brConversion ELSE 0 END),
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.brConversion ELSE 0 END) * 100.0 /
                NULLIF(SUM(CASE WHEN b.channel = 'NEXA' THEN b.grandTotal ELSE 0 END), 0),
            
                SUM(b.grandTotal),
                SUM(b.brConversion),
                SUM(b.brConversion) * 100.0 / NULLIF(SUM(b.grandTotal), 0),
            
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.labourAmt ELSE 0 END),
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.partAmount ELSE 0 END),
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.labourAmt ELSE 0 END) +
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.partAmount ELSE 0 END),
            
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.labourAmt ELSE 0 END),
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.partAmount ELSE 0 END),
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.labourAmt ELSE 0 END) +
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.partAmount ELSE 0 END),
            
                SUM(b.labourAmt),
                SUM(b.partAmount),
                SUM(b.labourAmt) + SUM(b.partAmount)
            )
            FROM BRConversion b
            WHERE (:months IS NULL OR b.month IN (:months))
              AND (:qtrWise IS NULL OR b.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR b.halfYear IN (:halfYear))
            GROUP BY b.city
            """)
    List<BRConversionSummaryDTO> getBRConversionSummaryByCity(
            @Param("months") List<String> month,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear
    );

    // Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.BRConversionSummaryDTO(
                b.city,
                b.branch,
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.grandTotal ELSE 0 END),
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.brConversion ELSE 0 END),
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.brConversion ELSE 0 END) * 100.0 /
                NULLIF(SUM(CASE WHEN b.channel = 'ARENA' THEN b.grandTotal ELSE 0 END), 0),
            
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.grandTotal ELSE 0 END),
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.brConversion ELSE 0 END),
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.brConversion ELSE 0 END) * 100.0 /
                NULLIF(SUM(CASE WHEN b.channel = 'NEXA' THEN b.grandTotal ELSE 0 END), 0),
            
                SUM(b.grandTotal),
                SUM(b.brConversion),
                SUM(b.brConversion) * 100.0 / NULLIF(SUM(b.grandTotal), 0),
            
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.labourAmt ELSE 0 END),
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.partAmount ELSE 0 END),
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.labourAmt ELSE 0 END) +
                SUM(CASE WHEN b.channel = 'ARENA' THEN b.partAmount ELSE 0 END),
            
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.labourAmt ELSE 0 END),
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.partAmount ELSE 0 END),
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.labourAmt ELSE 0 END) +
                SUM(CASE WHEN b.channel = 'NEXA' THEN b.partAmount ELSE 0 END),
            
                SUM(b.labourAmt),
                SUM(b.partAmount),
                SUM(b.labourAmt) + SUM(b.partAmount)
            )
            FROM BRConversion b
            WHERE (:months IS NULL OR b.month IN (:months))
             AND (:cities IS NULL OR b.city IN (:cities))
             AND (:qtrWise IS NULL OR b.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR b.halfYear IN (:halfYear))
            GROUP BY b.city, b.branch
            """)
    List<BRConversionSummaryDTO> getBRConversionSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );
}
