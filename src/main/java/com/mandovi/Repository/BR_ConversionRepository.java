package com.mandovi.Repository;

import com.mandovi.DTO.BR_ConversionRevenueSummaryDTO;
import com.mandovi.DTO.BR_ConversionSummaryDTO;
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
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 100.0 / SUM(b.no)) END " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city")
    List<BR_ConversionSummaryDTO> getBR_ConversionArenaSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //Group by branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionSummaryDTO( " +
            "null, " +
            "b.branch, " +
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 100.0 / SUM(b.no)) END " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.branch")
    List<BR_ConversionSummaryDTO> getBR_ConversionArenaSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //Group by city_branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionSummaryDTO( " +
            "b.city, " +
            "b.branch, " +
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 100.0 / SUM(b.no)) END " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city, b.branch")
    List<BR_ConversionSummaryDTO> getBR_ConversionArenaSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //Group by city for Nexa
    @Query("SELECT new com.mandovi.DTO.BR_ConversionSummaryDTO( " +
            "b.city, " +
            "null, " +
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 100.0 / SUM(b.no)) END " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'NEXA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city")
    List<BR_ConversionSummaryDTO> getBR_ConversionNexaSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //Group by branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionSummaryDTO( " +
            "null, " +
            "b.branch, " +
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 100.0 / SUM(b.no)) END " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'NEXA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.branch")
    List<BR_ConversionSummaryDTO> getBR_ConversionNexaSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //Group by city_branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionSummaryDTO( " +
            "b.city, " +
            "b.branch, " +
            "SUM(b.no), " +
            "SUM(b.br_conversion), " +
            "CASE WHEN SUM(b.no) = 0 THEN 0 ELSE (SUM(b.br_conversion) * 100.0 / SUM(b.no)) END " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'NEXA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city, b.branch")
    List<BR_ConversionSummaryDTO> getBR_ConversionNexaSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //RevenueGroup by city for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionRevenueSummaryDTO( " +
            "b.city, " +
            "null, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "SUM(b.labour_amt) + SUM(b.part_amount) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city")
    List<BR_ConversionRevenueSummaryDTO> getBR_ConversionRevenueArenaSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //RevenueGroup by branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionRevenueSummaryDTO( " +
            "null, " +
            "b.branch, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "SUM(b.labour_amt) + SUM(b.part_amount) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.branch")
    List<BR_ConversionRevenueSummaryDTO> getBR_ConversionRevenueArenaSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //RevenueGroup by city and branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionRevenueSummaryDTO( " +
            "b.city, " +
            "b.branch, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "SUM(b.labour_amt) + SUM(b.part_amount) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'ARENA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city, b.branch")
    List<BR_ConversionRevenueSummaryDTO> getBR_ConversionRevenueArenaSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //RevenueGroup by city for Nexa
    @Query("SELECT new com.mandovi.DTO.BR_ConversionRevenueSummaryDTO( " +
            "b.city, " +
            "null, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "SUM(b.labour_amt) + SUM(b.part_amount) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'NEXA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city, b.branch")
    List<BR_ConversionRevenueSummaryDTO> getBR_ConversionRevenueNexaSummaryByCity(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //RevenueGroup by branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionRevenueSummaryDTO( " +
            "null, " +
            "b.branch, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "SUM(b.labour_amt) + SUM(b.part_amount) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'NEXA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.branch")
    List<BR_ConversionRevenueSummaryDTO> getBR_ConversionRevenueNexaSummaryByBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);

    //RevenueGroup by city and branch for Arena
    @Query("SELECT new com.mandovi.DTO.BR_ConversionRevenueSummaryDTO( " +
            "b.city, " +
            "b.branch, " +
            "SUM(b.labour_amt), " +
            "SUM(b.part_amount), " +
            "SUM(b.labour_amt) + SUM(b.part_amount) " +
            ") " +
            "FROM BR_Conversion b " +
            "WHERE b.channel = 'NEXA' " +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "AND (:qtr_wise IS NULL OR b.qtr_wise = :qtr_wise) " +
            "AND (:half_year IS NULL OR b.half_year = :half_year) " +
            "GROUP BY b.city, b.branch")
    List<BR_ConversionRevenueSummaryDTO> getBR_ConversionRevenueNexaSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("year") String year,
            @Param("qtr_wise") String qtrWise,
            @Param("half_year") String halfYear);
}
