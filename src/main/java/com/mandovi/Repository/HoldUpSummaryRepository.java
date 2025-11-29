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
            SUM(CASE WHEN h.service = 'PMS' THEN h.count ElSE 0 END)
            )
            FROM HoldUpSummary h
            WHERE (:holdUpSummaryDate IS NULL OR h.holdUpSummaryDate IN (:holdUpSummaryDate))
            GROUP BY h.city
            """)
    List<HoldUpSummaryDTO> getHoldUpSummaryCityWise (@Param("holdUpSummaryDate")List<LocalDate> holdUpSummaryDate);

    //Group By branch
    @Query("""
            SELECT new com.mandovi.DTO.HoldUpSummaryDTO(
            h.city,
            h.branch,
            SUM(CASE WHEN h.service = 'Service' THEN h.count ElSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.count ElSE 0 END),
            SUM(CASE WHEN h.service = 'PMS' THEN h.count ElSE 0 END)
            )
            FROM HoldUpSummary h
            WHERE (:holdUpSummaryDate IS NULL OR h.holdUpSummaryDate IN (:holdUpSummaryDate))
            GROUP BY h.city,h.branch
            """)
    List<HoldUpSummaryDTO> getHoldUpSummaryBranchWise (@Param("holdUpSummaryDate")List<LocalDate> holdUpSummaryDate);
}
