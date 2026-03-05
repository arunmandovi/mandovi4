package com.mandovi.Repository;

import com.mandovi.DTO.SalesSummaryDTO;
import com.mandovi.Entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query("""
            SELECT s FROM Sales s
            WHERE (:months IS NULL OR s.month IN (:months))
             AND (:years IS NULL OR s.year IN (:years))
            """)
    public List<Sales> getAllSalesByMonthYear (
            @Param("months") List<String> months,
            @Param("years") List<String> years );

    @Query("""
            SELECT new com.mandovi.DTO.SalesSummaryDTO(
            s.city,
            null,
            SUM(s.vin))
            FROM Sales s
            WHERE (:years IS NULL OR s.year IN (:years))
             AND (:months IS NULL OR s.month IN (:months))
             AND (:channels IS NULL OR s.channel IN (:channels))
            Group BY s.city
            """)
    public List<SalesSummaryDTO> getSalesSummaryCityWise (
            @Param("years") List<String> years,
            @Param("months") List<String> months,
            @Param("channels") List<String> channels );

    @Query("""
            SELECT new com.mandovi.DTO.SalesSummaryDTO(
            null,
            s.branch,
            SUM(s.vin))
            FROM Sales s
            WHERE (:years IS NULL OR s.year IN (:years))
             AND (:months IS NULL OR s.month IN (:months))
             AND (:cities IS NULL OR s.city IN (:cities))
             AND (:channels IS NULL OR s.channel IN (:channels))
            Group BY s.branch
            """)
    public List<SalesSummaryDTO> getSalesSummaryBranchWise (
            @Param("years") List<String> years,
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("channels") List<String> channels );
}
