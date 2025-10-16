package com.mandovi.Repository;

import com.mandovi.DTO.VASSummaryDTO;
import com.mandovi.Entity.VAS;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VASRepository extends JpaRepository<VAS, Integer> {

    @Transactional
    @Query("SELECT v FROM VAS v WHERE v.month = :month AND v.year = :year")
    public List<VAS> getVASByMonthYear (@Param("month") String month, @Param("year") String year);

    //Group by city
    @Query("""
    SELECT new com.mandovi.DTO.VASSummaryDTO(
        v.city,
        null,
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.loadType = 'PMS' AND l.city = v.city) AS double),
        SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType = 'PMS' AND l.city = v.city) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.serviceTypeCode IN ('PMS','FR3') AND l.city = v.city) AS double),
        SUM(CASE WHEN v.vas = 'WHEEL ALIGNMENT' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'WHEEL ALIGNMENT' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'WHEEL ALIGNMENT' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('PMS','FR3') AND l.city = v.city) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.serviceTypeCode IN ('PMS','FR3') AND l.city = v.city) AS double),
        SUM(CASE WHEN v.vas = 'WHEEL BALANCING' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'WHEEL BALANCING' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'WHEEL BALANCING' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('PMS','FR3') AND l.city = v.city) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.loadType IN ('FREE SERVICE','PMS','RR','BODYSHOP') AND l.city = v.city) AS double),
        SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType IN ('FREE SERVICE','PMS','RR','BODYSHOP') AND l.city = v.city) AS double), 0),
        SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType IN ('FREE SERVICE','PMS','RR','BODYSHOP') AND l.city = v.city) AS double), 0)
    )
    FROM VAS v
    WHERE (:month IS NULL OR v.month = :month)
    GROUP BY v.city
    """)
    List<VASSummaryDTO> getVASSummaryByCity(@Param("month") String month);

    //Group by branch
    @Query("""
    SELECT new com.mandovi.DTO.VASSummaryDTO(
        MAX(v.city),
        v.branch,
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.loadType = 'PMS' AND l.branch = v.branch) AS double),
        SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType = 'PMS' AND l.branch = v.branch) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.serviceTypeCode IN ('PMS','FR3') AND l.branch = v.branch) AS double),
        SUM(CASE WHEN v.vas = 'WHEEL ALIGNMENT' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'WHEEL ALIGNMENT' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'WHEEL ALIGNMENT' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('PMS','FR3') AND l.branch = v.branch) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.serviceTypeCode IN ('PMS','FR3') AND l.branch = v.branch) AS double),
        SUM(CASE WHEN v.vas = 'WHEEL BALANCING' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'WHEEL BALANCING' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'WHEEL BALANCING' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.serviceTypeCode IN ('PMS','FR3') AND l.branch = v.branch) AS double), 0),
        CAST((SELECT SUM(l.serviceLoad)
              FROM Loadd l
              WHERE l.loadType IN ('FREE SERVICE','PMS','RR','BODYSHOP') AND l.branch = v.branch) AS double),
        SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType IN ('FREE SERVICE','PMS','RR','BODYSHOP') AND l.branch = v.branch) AS double), 0),
        SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.jobCardNo ELSE 0 END),
        CAST(SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.basicAmt ELSE 0 END) AS double),
        (SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.jobCardNo ELSE 0 END) * 100.0) /
        NULLIF(CAST((SELECT SUM(l.serviceLoad)
                     FROM Loadd l
                     WHERE l.loadType IN ('FREE SERVICE','PMS','RR','BODYSHOP') AND l.branch = v.branch) AS double), 0)
    )
    FROM VAS v
    WHERE (:month IS NULL OR v.month = :month)
    GROUP BY v.branch
    """)
    List<VASSummaryDTO> getVASummaryByBranch(@Param("month") String month);
}
