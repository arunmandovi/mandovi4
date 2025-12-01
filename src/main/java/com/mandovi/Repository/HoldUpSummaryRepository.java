package com.mandovi.Repository;

import com.mandovi.DTO.HoldUpSummaryDTO;
import com.mandovi.Entity.HoldUpSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HoldUpSummaryRepository extends JpaRepository<HoldUpSummary, Integer> {
    //Group By city
    @Query("""
            SELECT new com.mandovi.DTO.HoldUpSummaryDTO(
            h.city,
            null,
            SUM(CASE WHEN h.service = 'Service' THEN h.count ElSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.count ElSE 0 END),
            SUM(CASE WHEN h.service = 'PMS' THEN h.count ElSE 0 END),
            SUM(CASE WHEN h.service IN ('Service', 'Bodyshop') THEN h.count ElSE 0 END)
            )
            FROM HoldUpSummary h
            WHERE h.month = :month AND h.day = :day
            GROUP BY h.city
            """)
    List<HoldUpSummaryDTO> getHoldUpSummaryCityWise (
            @Param("month") String month,
            @Param("day") String day );

    //Group By branch
    @Query("""
            SELECT new com.mandovi.DTO.HoldUpSummaryDTO(
            h.city,
            h.branch,
            SUM(CASE WHEN h.service = 'Service' THEN h.count ElSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.count ElSE 0 END),
            SUM(CASE WHEN h.service = 'PMS' THEN h.count ElSE 0 END),
            SUM(CASE WHEN h.service IN ('Service', 'Bodyshop') THEN h.count ElSE 0 END)
            )
            FROM HoldUpSummary h
            WHERE h.month = :month AND h.day = :day
            AND (:cities IS NULL OR h.city IN (:cities))
            GROUP BY h.city,h.branch
            """)
    List<HoldUpSummaryDTO> getHoldUpSummaryBranchWise (
            @Param("month") String month,
            @Param( "day") String day,
            @Param("cities") List<String> cities );
}
