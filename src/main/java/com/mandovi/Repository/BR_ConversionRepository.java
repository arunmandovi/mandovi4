package com.mandovi.Repository;

import com.mandovi.DTO.BR_ConversionSummaryDTO;
import com.mandovi.DTO.BatteryTyreSummaryDTO;
import com.mandovi.Entity.BR_Conversion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BR_ConversionRepository extends JpaRepository<BR_Conversion, Integer> {

    @Transactional
    @Query("SELECT b FROM BR_Conversion b WHERE b.month = :month AND b.year = :year")
    List<BR_Conversion> getBR_ConversionByMonthYear(@Param("month") String month, @Param("year") String year);

    //Group by city for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionSummaryDTO( " +
            "b.city, " +
            "null, " +
            ":qtr_wise, " +
            ":half_year, " +
            "'ARENA', " +
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 1.0 / SUM(b.no)) END, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "(SUM(b.labour_amt) + SUM(b.part_amount)) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city")
    List<BR_ConversionSummaryDTO> getBR_ConversionSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //Group by branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionSummaryDTO( " +
            "null, " +
            "b.branch, " +
            ":qtr_wise, " +
            ":half_year, " +
            "'ARENA', " +
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 1.0 / SUM(b.no)) END, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "(SUM(b.labour_amt) + SUM(b.part_amount)) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.branch")
    List<BR_ConversionSummaryDTO> getBR_ConversionSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //Group by city_branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionSummaryDTO( " +
            "b.city, " +
            "b.branch, " +
            ":qtr_wise, " +
            ":half_year, " +
            "'ARENA', " +
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 1.0 / SUM(b.no)) END, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "(SUM(b.labour_amt) + SUM(b.part_amount)) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city, b.branch")
    List<BR_ConversionSummaryDTO> getBR_ConversionSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);
}
