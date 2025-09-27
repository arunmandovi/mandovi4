package com.mandovi.Repository;

import com.mandovi.DTO.BatteryTyreSummaryDTO;
import com.mandovi.Entity.BatteryTyre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatteryTyreRepository extends JpaRepository<BatteryTyre, Integer>, JpaSpecificationExecutor<BatteryTyre> {

    @Transactional
    @Query("SELECT b FROM BatteryTyre b WHERE b.month = :month AND b.year = :year")
    List<BatteryTyre> getBatteryTyreByMonthYear(@Param("month") String month, @Param("year") String year);

    //Group by city for Battery
    @Query("SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(" +
            "b.city, null, 'BATTERY', " +
            "SUM(b.sum_of_net_retail_qty), " +
            "SUM(b.sum_of_net_retail_ddl), " +
            "SUM(b.sum_of_net_retail_selling)) " +
            "FROM BatteryTyre b " +
            "WHERE b.oilType = 'BATTERY'" +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "GROUP BY b.city")
    List<BatteryTyreSummaryDTO> getBatterySummaryByCity(@Param("month") String month, @Param("year") String year);

    //Group by branch for Battery
    @Query("SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(" +
            "null, b.branch, 'BATTERY', " +
            "SUM(b.sum_of_net_retail_qty), " +
            "SUM(b.sum_of_net_retail_ddl), " +
            "SUM(b.sum_of_net_retail_selling)) " +
            "FROM BatteryTyre b " +
            "WHERE b.oilType = 'BATTERY'" +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "GROUP BY b.branch")
    List<BatteryTyreSummaryDTO> getBatterySummaryByBranch(@Param("month") String month, @Param("year") String year);

    //Group by city+branch for Battery
    @Query("SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(" +
            "b.city, b.branch, 'BATTERY', " +
            "SUM(b.sum_of_net_retail_qty), " +
            "SUM(b.sum_of_net_retail_ddl), " +
            "SUM(b.sum_of_net_retail_selling)) " +
            "FROM BatteryTyre b " +
            "WHERE b.oilType = 'BATTERY'" +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "GROUP BY b.city, b.branch")
    List<BatteryTyreSummaryDTO> getBatterySummaryByCityAndBranch(@Param("month") String month, @Param("year") String year);

    //Group by city for Tyre
    @Query("SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(" +
            "b.city, null, 'TYRE', " +
            "SUM(b.sum_of_net_retail_qty), " +
            "SUM(b.sum_of_net_retail_ddl), " +
            "SUM(b.sum_of_net_retail_selling)) " +
            "FROM BatteryTyre b " +
            "WHERE b.oilType = 'TYRE'" +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "GROUP BY b.city")
    List<BatteryTyreSummaryDTO> getTyreSummaryByCity(@Param("month") String month, @Param("year") String year);

    //Group by branch for Tyre
    @Query("SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(" +
            "null, b.branch, 'TYRE', " +
            "SUM(b.sum_of_net_retail_qty), " +
            "SUM(b.sum_of_net_retail_ddl), " +
            "SUM(b.sum_of_net_retail_selling)) " +
            "FROM BatteryTyre b " +
            "WHERE b.oilType = 'TYRE'" +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "GROUP BY b.branch")
    List<BatteryTyreSummaryDTO> getTyreSummaryByBranch(@Param("month") String month, @Param("year") String year);

    //Group by city+branch for Tyre
    @Query("SELECT new com.mandovi.DTO.BatteryTyreSummaryDTO(" +
            "b.city, b.branch, 'TYRE', " +
            "SUM(b.sum_of_net_retail_qty), " +
            "SUM(b.sum_of_net_retail_ddl), " +
            "SUM(b.sum_of_net_retail_selling)) " +
            "FROM BatteryTyre b " +
            "WHERE b.oilType = 'TYRE'" +
            "AND (:month IS NULL OR b.month = :month) " +
            "AND (:year IS NULL OR b.year = :year) " +
            "GROUP BY b.city, b.branch")
    List<BatteryTyreSummaryDTO> getTyreSummaryByCityAndBranch(@Param("month") String month, @Param("year") String year);

}
