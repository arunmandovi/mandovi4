package com.mandovi.Repository;

import com.mandovi.Entity.HoldUp;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

public interface HoldUpRepository extends JpaRepository<HoldUp, Integer> {

    boolean existsByHoldUpDate (LocalDate holdUpDate);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.hold_up;", nativeQuery = true)
    void deleteHoldUpAll ();

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO hold_up_summary (city, branch, service, hold_up_summary_date, month, day, count) " +
                    "SELECT city, branch, 'Bodyshop', hold_up_date, month, day, SUM(count) " +
                    "FROM hold_up " +
                    "WHERE service = 'Bodyshop' " +
                    "GROUP BY city, branch, hold_up_date, month, day",
            nativeQuery = true)
    void insertBodyShopSummary();

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO hold_up_summary (city, branch, service, hold_up_summary_date, month, day, count) " +
                    "SELECT city, branch, 'Service', hold_up_date, month, day, SUM(count) " +
                    "FROM hold_up " +
                    "WHERE service = 'Service' " +
                    "GROUP BY city, branch, hold_up_date, month, day",
            nativeQuery = true)
    void insertServiceSummary();

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO hold_up_summary (city, branch, service, hold_up_summary_date, month, day, count) " +
                    "SELECT city, branch, 'PMS', hold_up_date, month, day, SUM(count) " +
                    "FROM hold_up " +
                    "WHERE service_type = 'PMS' " +
                    "GROUP BY city, branch, hold_up_date, month, day",
            nativeQuery = true)
    void insertPMSSummary();
}
