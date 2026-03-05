package com.mandovi.Repository;

import com.mandovi.DTO.MSGPProfitSummaryDTO;
import com.mandovi.Entity.MSGPProfit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MSGPProfitRepository extends JpaRepository<MSGPProfit, Integer> {

    @Transactional
    @Query("""
    SELECT m FROM MSGPProfit m
    WHERE (:months IS NULL OR m.month IN (:months))
    AND (:years IS NULL OR m.year IN (:years))
    """)
    public List<MSGPProfit> getMSGPProfitByMonthYear (@Param("months") List<String> months,@Param("years") List<String> years);

    @Transactional
    @Modifying
    @Query("DELETE FROM MSGPProfit m WHERE m.month = :month and m.year = :year")
    void deleteByMonthYear(@Param("month") String month, @Param("year") String year );

    //Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MSGPProfitSummaryDTO(
            m.city,
            null,
            SUM(m.sumOfNetRetailDDL),
            SUM(m.sumOfNetRetailSelling),
            SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL),
            (SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL)) * 100.00 /
            NULLIF(SUM(m.sumOfNetRetailDDL), 0),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailDDL ELSE 0 END), 0),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailDDL ELSE 0 END), 0)
        )
        FROM MSGPProfit m
        WHERE (:months IS NULL OR m.month IN (:months))
          AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
          AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
        GROUP BY m.city
    """)
    List<MSGPProfitSummaryDTO> getMSGPProfitSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear
    );

    //Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MSGPProfitSummaryDTO(
            m.city,
            m.branch,
            SUM(m.sumOfNetRetailDDL),
            SUM(m.sumOfNetRetailSelling),
            SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL),
            (SUM(m.sumOfNetRetailSelling) - SUM(m.sumOfNetRetailDDL)) * 100.00 /
            NULLIF(SUM(m.sumOfNetRetailDDL), 0),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.sumOfNetRetailDDL ELSE 0 END), 0),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailSelling ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailDDL ELSE 0 END),
            (SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailDDL ELSE 0 END)) * 100.00 /
            NULLIF(SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.sumOfNetRetailDDL ELSE 0 END), 0)
        )
        FROM MSGPProfit m
        WHERE (:months IS NULL OR m.month IN (:months))
         AND (:cities IS NULL OR m.city IN (:cities))
         AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
         AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
        GROUP BY m.city, m.branch
    """)
    List<MSGPProfitSummaryDTO> getMSGPProfitSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.msgp_profit;", nativeQuery = true)
    void deleteMSGPProfitAll ();
}
