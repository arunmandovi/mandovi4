package com.mandovi.Repository;

import com.mandovi.DTO.MGAProfitSummaryDTO;
import com.mandovi.Entity.MGAProfit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MGAProfitRepository extends JpaRepository<MGAProfit, Integer> {

    @Transactional
    @Query("""
            SELECT m from MGAProfit m
            WHERE (:months IS NULL OR m.month IN (:months))
            AND (:years IS NULL OR m.year IN (:years))
            """)
    public List<MGAProfit> getMGAProfitMonthYear (
            @Param("months") List<String> months,
            @Param("years") List<String> years );

    @Transactional
    @Modifying
    @Query("DELETE FROM MGAProfit m WHERE m.month = :month")
    void deleteByMonth(@Param("month") String month);

    @Query("""
            SELECT new com.mandovi.DTO.MGAProfitSummaryDTO(
            m.city,
            null,
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailDDL ELSE 0 END),
            (SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailDDL ELSE 0 END)) * 100 /
            NULLIF(SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailDDL ELSE 0 END), 0),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailDDL ELSE 0 END),
            (SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailDDL ELSE 0 END)) * 100 /
            NULLIF(SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailDDL ELSE 0 END), 0),
            SUM(m.netRetailDDL),
            SUM(m.netRetailSelling),
            SUM(m.netRetailSelling) - SUM(m.netRetailDDL),
            (SUM(m.netRetailSelling) - SUM(m.netRetailDDL)) * 100 / NULLIF(SUM(m.netRetailDDL), 0)
            )
            FROM MGAProfit m
            WHERE (:months IS NULL OR m.month IN (:months))
            AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
            AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
            GROUP BY m.city
            """)
    public List<MGAProfitSummaryDTO> getMGAProfitSummary (
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    @Query("""
            SELECT new com.mandovi.DTO.MGAProfitSummaryDTO(
            m.city,
            m.branch,
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailDDL ELSE 0 END),
            (SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailDDL ELSE 0 END)) * 100 /
            NULLIF(SUM(CASE WHEN m.serviceDescription = 'Service' THEN m.netRetailDDL ELSE 0 END), 0),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailDDL ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailSelling ELSE 0 END),
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailDDL ELSE 0 END),
            (SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailSelling ELSE 0 END) -
            SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailDDL ELSE 0 END)) * 100 /
            NULLIF(SUM(CASE WHEN m.serviceDescription = 'Bodyshop' THEN m.netRetailDDL ELSE 0 END), 0),
            SUM(m.netRetailDDL),
            SUM(m.netRetailSelling),
            SUM(m.netRetailSelling) - SUM(m.netRetailDDL),
            (SUM(m.netRetailSelling) - SUM(m.netRetailDDL)) * 100 / NULLIF(SUM(m.netRetailDDL), 0)
            )
            FROM MGAProfit m
            WHERE (:months IS NULL OR m.month IN (:months))
            AND (:cities IS NULL OR m.city IN (:cities))
            AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
            AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
            GROUP BY m.city, m.branch
            """)
    public List<MGAProfitSummaryDTO> getMGAProfitSummaryBranchWise (
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.mga_profit;", nativeQuery = true)
    void deleteMGAProfitAll ();
}
