package com.mandovi.Repository;

import com.mandovi.DTO.HoldUpDayDTO;
import com.mandovi.Entity.HoldUpDay;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoldUpDayRepository extends JpaRepository<HoldUpDay, Integer> {

    @Query("""
            SELECT h.city, h.branch, h.service, h.regNo FROM HoldUpDay h
            """)
    List<String> getHoldUpDayList();

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.hold_up_day_summary;", nativeQuery = true)
    void deleteHoldUpDayAll ();

    @Query("""
        SELECT h FROM HoldUpDay h
    """)
    List<HoldUpDay> findAllHoldUpDays();

    @Query("""
            SELECT new com.mandovi.DTO.HoldUpDayDTO(
            h.city,
            null,
            SUM(CASE WHEN h.service = 'Service' THEN h.tillPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.clearedPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.tillPreviousDay ELSE 0 END) - SUM(CASE WHEN h.service = 'Service' THEN h.clearedPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.addedToday ELSE 0 END),
            (SUM(CASE WHEN h.service = 'Service' THEN h.tillPreviousDay ELSE 0 END) - SUM(CASE WHEN h.service = 'Service' THEN h.clearedPreviousDay ELSE 0 END)) +
            SUM(CASE WHEN h.service = 'Service' THEN h.addedToday ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.tillPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.clearedPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.tillPreviousDay ELSE 0 END) - SUM(CASE WHEN h.service = 'Bodyshop' THEN h.clearedPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.addedToday ELSE 0 END),
            (SUM(CASE WHEN h.service = 'Bodyshop' THEN h.tillPreviousDay ELSE 0 END) - SUM(CASE WHEN h.service = 'Bodyshop' THEN h.clearedPreviousDay ELSE 0 END)) +
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.addedToday ELSE 0 END)
            )
            FROM HoldUpDay h
            GROUP BY h.city
            """)
    List<HoldUpDayDTO> getHoldUpDayCityWise ();

    @Query("""
            SELECT new com.mandovi.DTO.HoldUpDayDTO(
            h.city,
            h.branch,
            SUM(CASE WHEN h.service = 'Service' THEN h.tillPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.clearedPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.tillPreviousDay ELSE 0 END) - SUM(CASE WHEN h.service = 'Service' THEN h.clearedPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Service' THEN h.addedToday ELSE 0 END),
            (SUM(CASE WHEN h.service = 'Service' THEN h.tillPreviousDay ELSE 0 END) - SUM(CASE WHEN h.service = 'Service' THEN h.clearedPreviousDay ELSE 0 END)) +
            SUM(CASE WHEN h.service = 'Service' THEN h.addedToday ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.tillPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.clearedPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.tillPreviousDay ELSE 0 END) - SUM(CASE WHEN h.service = 'Bodyshop' THEN h.clearedPreviousDay ELSE 0 END),
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.addedToday ELSE 0 END),
            (SUM(CASE WHEN h.service = 'Bodyshop' THEN h.tillPreviousDay ELSE 0 END) - SUM(CASE WHEN h.service = 'Bodyshop' THEN h.clearedPreviousDay ELSE 0 END)) +
            SUM(CASE WHEN h.service = 'Bodyshop' THEN h.addedToday ELSE 0 END)
            )
            FROM HoldUpDay h
            WHERE (:cities IS NULL OR h.city IN (:cities))
            GROUP BY h.city, h.branch
            """)
    List<HoldUpDayDTO> getHoldUpDayBranchWise (@Param("cities") List<String> cities);

}
