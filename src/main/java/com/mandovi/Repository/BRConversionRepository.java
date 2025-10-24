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
    @Query("SELECT b FROM BRConversion b WHERE b.month = :month AND b.year = :year")
    List<BRConversion> getBR_ConversionByMonthYear(@Param("month") String month, @Param("year") String year);

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
              AND (:qtrWise IS NULL OR b.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR b.halfYear = :halfYear)
            GROUP BY b.city
            """)
    List<BRConversionSummaryDTO> getBRConversionSummaryByCity(
            @Param("months") List<String> month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    // Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.BRConversionSummaryDTO(
                MAX(b.city),
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
              AND (:qtrWise IS NULL OR b.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR b.halfYear = :halfYear)
            GROUP BY b.branch
            """)
    List<BRConversionSummaryDTO> getBRConversionSummaryByBranch(
            @Param("months") List<String> month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    // Group by city and branch
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
              AND (:qtrWise IS NULL OR b.qtrWise = :qtrWise)
              AND (:halfYear IS NULL OR b.halfYear = :halfYear)
            GROUP BY b.city, b.branch
            """)
    List<BRConversionSummaryDTO> getBRConversionSummaryByCityAndBranch(
            @Param("months") List<String> month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );
}
