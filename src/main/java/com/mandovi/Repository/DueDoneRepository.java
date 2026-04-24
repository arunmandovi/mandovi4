package com.mandovi.Repository;

import com.mandovi.DTO.DueDoneSummaryDTO;
import com.mandovi.Entity.DueDone;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface DueDoneRepository extends JpaRepository<DueDone, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM DueDone d WHERE d.month = :month AND d.year = :year")
    void deleteByMonthYear(@Param("month") String month, @Param("year") String year );

    @Transactional
    @Query("""
            SELECT d from DueDone d
            WHERE (:months IS NULL OR d.month IN (:months))
            AND (:years IS NULL OR d.year IN (:years)) AND (:financialYears IS NULL OR d.financialYear IN (:financialYears))
            """)
    List<DueDone> getDueDoneByMonthYear (
            @Param("months") List<String> months, @Param("years") List<String> years,
            @Param("financialYears") List<String> financialYears );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.due_done", nativeQuery = true)
    void deleteDueDoneAll();

    //Group By city
    @Query("""
            SELECT new com.mandovi.DTO.DueDoneSummaryDTO(
            d.city,
            null,
            SUM(d.totalDue),
            SUM(d.totalDone),
            CASE
             WHEN SUM(d.totalDue) = 0 THEN 0.0
             ELSE SUM(d.totalDone) * 100.0 / SUM(d.totalDue)
             END
            )
            FROM DueDone d
            WHERE (:months IS NULL OR d.month IN (:months))
            AND (:channels IS NULL OR d.channel IN (:channels))
            AND (:qtrWise IS NULL OR d.qtrWise IN (:qtrWise))
            AND (:halfYear IS NULL OR d.halfYear IN (:halfYear))
            AND (:financialYears IS NULL OR d.financialYear IN (:financialYears))
            GROUP BY d.city
            """)
    List<DueDoneSummaryDTO> getDueDoneSummaryByCity (
            @Param("months") List<String> months,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @RequestParam ("financialYears") List<String> financialYears );

    //Group By branch
    @Query("""
            SELECT new com.mandovi.DTO.DueDoneSummaryDTO(
            d.city,
            d.branch,
            SUM(d.totalDue),
            SUM(d.totalDone),
            CASE
             WHEN SUM(d.totalDue) = 0 THEN 0.0
             ELSE SUM(d.totalDone) * 100.0 / SUM(d.totalDue)
             END
            )
            FROM DueDone d
            WHERE (:months IS NULL OR d.month IN (:months))
            AND (:cities IS NULL OR d.city IN (:cities))
            AND (:channels IS NULL OR d.channel IN (:channels))
            AND (:qtrWise IS NULL OR d.qtrWise IN (:qtrWise))
            AND (:halfYear IS NULL OR d.halfYear IN (:halfYear))
            AND (:financialYears IS NULL OR d.financialYear IN (:financialYears))
            GROUP BY d.city, d.branch
            """)
    List<DueDoneSummaryDTO> getDueDoneSummaryByBranch (
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear,
            @RequestParam("financialYears") List<String> financialYears);

}
