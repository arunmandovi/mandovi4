package com.mandovi.Repository;

import com.mandovi.DTO.MSGPProfitSummaryDTO;
import com.mandovi.DTO.MSGPSummaryDTO;
import com.mandovi.Entity.MSGPProfit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MSGPProfitRepository extends JpaRepository<MSGPProfit, Integer> {

    @Transactional
    @Query("SELECT m FROM MSGPProfit m WHERE m.month = :month AND m.year = :year")
    public List<MSGPProfit> getMSGPProfitByMonthYear (@Param("month") String month,@Param("year") String year);

    //Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPProfitSummaryDTO(
            m.city,
            null,
            SUM(m.sumOfNetRetailDDL),
            SUM(m.sumOfNetRetailSelling),
            SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL),
            ((SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL)) / (SUM(m.sumOfNetRetailDDL))) * 100.00
        )
        FROM MSGPProfit m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city
    """)
    List<MSGPProfitSummaryDTO> getMSGPProfitSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPProfitSummaryDTO(
            null,
            m.branch,
            SUM(m.sumOfNetRetailDDL),
            SUM(m.sumOfNetRetailSelling),
            SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL),
            ((SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL)) / (SUM(m.sumOfNetRetailDDL))) * 100.00
        )
        FROM MSGPProfit m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.branch
    """)
    List<MSGPProfitSummaryDTO> getMSGPProfitSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );

    //Group by city and branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPProfitSummaryDTO(
            m.city,
            m.branch,
            SUM(m.sumOfNetRetailDDL),
            SUM(m.sumOfNetRetailSelling),
            SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL),
            ((SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL)) / (SUM(m.sumOfNetRetailDDL))) * 100.00
        )
        FROM MSGPProfit m
        WHERE (:month IS NULL OR m.month = :month)
          AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise)
          AND (:halfYear IS NULL OR m.halfYear = :halfYear)
        GROUP BY m.city, m.branch
    """)
    List<MSGPProfitSummaryDTO> getMSGPProfitSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear
    );
}
