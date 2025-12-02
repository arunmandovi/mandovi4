package com.mandovi.Repository;

import com.mandovi.DTO.HoldUpDTO;
import com.mandovi.Entity.HoldUp;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HoldUpRepository extends JpaRepository<HoldUp, Integer> {

    boolean existsByHoldUpDate (LocalDate holdUpDate);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.hold_up;", nativeQuery = true)
    void deleteHoldUpAll ();

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO hold_up_summary (city, branch, service, hold_up_summary_date, month, day, year, count) " +
                    "SELECT city, branch, 'Bodyshop', hold_up_date, month, day, year, SUM(count) " +
                    "FROM hold_up " +
                    "WHERE service = 'Bodyshop' " +
                    "GROUP BY city, branch, hold_up_date, month, day, year,",
            nativeQuery = true)
    void insertBodyShopSummary();

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO hold_up_summary (city, branch, service, hold_up_summary_date, month, day, year, count) " +
                    "SELECT city, branch, 'Service', hold_up_date, month, day, year, SUM(count) " +
                    "FROM hold_up " +
                    "WHERE service = 'Service' " +
                    "GROUP BY city, branch, hold_up_date, month, day, year,",
            nativeQuery = true)
    void insertServiceSummary();

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO hold_up_summary (city, branch, service, hold_up_summary_date, month, day, year, count) " +
                    "SELECT city, branch, 'PMS', hold_up_date, month, day, year, SUM(count) " +
                    "FROM hold_up " +
                    "WHERE service_type = 'PMS' " +
                    "GROUP BY city, branch, hold_up_date, month, day, year,",
            nativeQuery = true)
    void insertPMSSummary();

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.HoldUpDTO(
            h.city,
            null,
            SUM(CASE WHEN h.serviceType = 'PMS' THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "1" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "1-2" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "3-5" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "6-7" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "8-15" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "16-30" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = ">30" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "1" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "1-2" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "3-5" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "6-7" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "8-15" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "16-30" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = ">30" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.count ELSE 0 END),
            SUM(h.count)
            )
            FROM HoldUp h
            GROUP BY h.city
            """)
    List<HoldUpDTO> getHoldUpDTOCityWise ();

    //Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.HoldUpDTO(
            h.city,
            h.branch,
            SUM(CASE WHEN h.serviceType = 'PMS' THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "1" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "1-2" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "3-5" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "6-7" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "8-15" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = "16-30" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' AND days = ">30" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "1" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "1-2" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "3-5" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "6-7" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "8-15" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = "16-30" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' AND days = ">30" THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.count ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.count ELSE 0 END),
            SUM(h.count)
            )
            FROM HoldUp h
            WHERE (:cities IS NULL OR h.city IN (:cities))
            GROUP BY h.city, h.branch
            """)
    List<HoldUpDTO> getHoldUpDTOBranchWise (@Param("cities") List<String> cities );
}
