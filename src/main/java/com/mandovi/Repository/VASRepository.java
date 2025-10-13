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
    @Query(value = """
            SELECT
                v.city,
                null,
                l.total_pms,
                SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_pms,0),
            
                l.total_fr3_pms,
                SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0),
            
                l.total_fr3_pms,
                SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Wheel Balancing' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0),
            
                l.total_free,
                SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_free,0),
            
                SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Interior Cleaning' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_free,0),
            
                SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Underbody Coating' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0),
            
                SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Top Body Coating' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0),
            
                SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'RAT MESH' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0),
            
                SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Evaporator Cleaning' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0),
            
                SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'A/C Vent Cleaning' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0),
            
                SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.job_card_no ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.basic_amt ELSE 0 END),
                SUM(CASE WHEN v.vas = 'Plastic Repair' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0)
            
            FROM vas v
            LEFT JOIN (
                SELECT city,
                       SUM(CASE WHEN load_type = 'PMS' THEN service_load ELSE 0 END) AS total_pms,
                       SUM(CASE WHEN service_type_code IN ('FR3','PMS') THEN service_load ELSE 0 END) AS total_fr3_pms,
                       SUM(CASE WHEN load_type IN ('FREE SERVICE','PMS','RR','BODYSHOP') THEN service_load ELSE 0 END) AS total_free
                FROM loadd
                GROUP BY city
            ) l ON v.city = l.city
            WHERE (:month IS NULL OR v.month = :month)
            GROUP BY v.city, l.total_pms, l.total_fr3_pms, l.total_free;
            """, nativeQuery = true)
    List<Object[]> getVASSummaryByCity ( @Param("month") String month );

    //Group by branch
    @Query(value = """
            SELECT
                                                                                      v.city,
                                                                                      v.branch,
                                                                                      l.total_pms,
                                                                                      SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.job_card_no ELSE 0 END) AS diagnostic_jobs,
                                                                                      SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.basic_amt ELSE 0 END) AS diagnostic_amt,
                                                                                      SUM(CASE WHEN v.vas = 'DIAGNOSTIC CHARGES' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_pms,0) AS diagnostic_pct,
            
                                                                                      l.total_fr3_pms,
                                                                                      SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.job_card_no ELSE 0 END) AS wheel_align_jobs,
                                                                                      SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.basic_amt ELSE 0 END) AS wheel_align_amt,
                                                                                      SUM(CASE WHEN v.vas = 'Wheel Alignment' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_fr3_pms,0) AS wheel_align_pct,
            
                                                                                      l.total_free,
                                                                                      SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.job_card_no ELSE 0 END) AS exterior_jobs,
                                                                                      SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.basic_amt ELSE 0 END) AS exterior_amt,
                                                                                      SUM(CASE WHEN v.vas = 'Exterior Cleaning' THEN v.job_card_no ELSE 0 END) * 100 / NULLIF(l.total_free,0) AS exterior_pct
            
                                                                                      -- add remaining VAS fields similarly
            
                                                                                  FROM vas v
                                                                                  LEFT JOIN (
                                                                                      SELECT city,
                                                                                             branch,
                                                                                             SUM(CASE WHEN load_type = 'PMS' THEN service_load ELSE 0 END) AS total_pms,
                                                                                             SUM(CASE WHEN service_type_code IN ('FR3','PMS') THEN service_load ELSE 0 END) AS total_fr3_pms,
                                                                                             SUM(CASE WHEN load_type IN ('FREE SERVICE','PMS','RR','BODYSHOP') THEN service_load ELSE 0 END) AS total_free
                                                                                      FROM loadd
                                                                                      GROUP BY city, branch
                                                                                  ) l ON v.city = l.city AND v.branch = l.branch
                                                                                  WHERE (:month IS NULL OR v.month = :month)
                                                                                  GROUP BY v.city, v.branch, l.total_pms, l.total_fr3_pms, l.total_free
                                                                                  ORDER BY v.city, v.branch;
        """, nativeQuery = true)
    List<Object[]> getVASSummaryByBranch(@Param("month") String month);


}
