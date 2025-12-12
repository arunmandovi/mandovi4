package com.mandovi.Repository;

import com.mandovi.DTO.ProductivitySummaryDTO;
import com.mandovi.Entity.Productivity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductivityRepository extends JpaRepository<Productivity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE mandovi.productivity SET service_utilized_bay = :newServiceUtilizedBay WHERE branch = :branch", nativeQuery = true)
    public int updateServiceUtilizedBay(
            @Param("branch") String branch,
            @Param("newServiceUtilizedBay") Integer newServiceUtilizedBay);

    @Modifying
    @Transactional
    @Query(value = "UPDATE mandovi.productivity SET bodyshop_utilized_bay = :newBodyShopUtilizedBay WHERE branch = :branch", nativeQuery = true)
    public int updateBodyShopUtilizedBay(
            @Param("branch") String branch,
            @Param("newBodyShopUtilizedBay") Integer newBodyShopUtilizedBay);

    //Group By City
    @Query("""
           SELECT NEW com.mandovi.DTO.ProductivitySummaryDTO(
           l.city,
           CAST(NULL AS string),
           (SELECT SUM(p.serviceUtilizedBay)
            FROM Productivity p
            WHERE p.city = l.city),
           SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','RR','OTHERS') THEN l.serviceLoad ELSE 0 END),
           0.0,
           SUM(CASE WHEN l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END),
           0.0,
           SUM(CASE WHEN l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END),
           0.0,
           SUM(CASE WHEN l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END),
           0.0,
           SUM(CASE WHEN l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END),
           0.0,
           (SELECT SUM(p.bodyShopUtilizedBay)
            FROM Productivity p
            WHERE p.city = l.city),
           SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END),
           0.0,
           1
           )
           FROM Loadd l
           WHERE (:months IS NULL OR l.month IN :months)
             AND (:years IS NULL OR l.year IN :years)
           GROUP BY l.city
           """)
    List<ProductivitySummaryDTO> getProductSummaryCityWise(
            @Param("months") List<String> months,
            @Param("years") List<String> years);




    //Group By Branch
    @Query("""
           SELECT NEW com.mandovi.DTO.ProductivitySummaryDTO(
           l.city,
           l.branch,
           (SELECT SUM(p.serviceUtilizedBay)
            FROM Productivity p
            WHERE p.branch = l.branch ),
           SUM(CASE WHEN l.loadType IN ('FREE SERVICE','PMS','RR','OTHERS') THEN l.serviceLoad ELSE 0 END),
           0.0,
           SUM(CASE WHEN l.loadType = 'FREE SERVICE' THEN l.serviceLoad ELSE 0 END),
           0.0,
           SUM(CASE WHEN l.loadType = 'PMS' THEN l.serviceLoad ELSE 0 END),
           0.0,
           SUM(CASE WHEN l.loadType = 'RR' THEN l.serviceLoad ELSE 0 END),
           0.0,
           SUM(CASE WHEN l.loadType = 'OTHERS' THEN l.serviceLoad ELSE 0 END),
           0.0,
           (SELECT SUM(p.bodyShopUtilizedBay)
            FROM Productivity p
            WHERE p.branch = l.branch ),
           SUM(CASE WHEN l.loadType = 'BODYSHOP' THEN l.serviceLoad ELSE 0 END),
           0.0,
           1
           )
           FROM Loadd l
           WHERE (:months IS NULL OR l.month IN :months)
             AND (:years IS NULL OR l.year IN :years)
             AND (:cities IS NULL OR l.city IN :cities)
           GROUP BY l.city, l.branch
           """)
    List<ProductivitySummaryDTO> getProductSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("years") List<String> years,
            @Param("cities") List<String> cities );

    @Modifying
    @Transactional
    @Query(value = "UPDATE mandovi.productivity SET worked_days = :workingDays WHERE month = :month", nativeQuery = true)
    public int updateWorkingDays(
            @Param("month") String month,
            @Param("workingDays") Integer workingDays);


}
