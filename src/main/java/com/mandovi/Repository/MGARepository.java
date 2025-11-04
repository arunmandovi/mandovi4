package com.mandovi.Repository;

import com.mandovi.DTO.MCPSummaryDTO;
import com.mandovi.DTO.MGASummaryDTO;
import com.mandovi.Entity.MGA;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MGARepository extends JpaRepository<MGA, Integer> {

    @Transactional
    @Query("""
    SELECT m FROM MGA m
    WHERE (:months IS NULL OR m.month IN (:months))
    AND (:years IS NULL OR m.year IN (:years))
    """)
    public List<MGA> getMGAMonth(
            @Param("months") List<String> months, @Param("years") List<String> years );

    //Group by city
    @Query("""
        SELECT new com.mandovi.DTO.MGASummaryDTO(
            m.city,
            null,
            SUM(m.loadd),
            SUM(m.consumption),
            SUM(m.consumption) / SUM(m.loadd),
            (SUM(m.loadd) * 455) - SUM(m.consumption),
            ((SUM(m.loadd) * 455) - SUM(m.consumption)) / SUM(m.loadd)
        )
        FROM MGA m
        WHERE (:months IS NULL OR m.month IN (:months))
        AND (:channels IS NULL OR m.channel IN (:channels))
        AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
        AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
        GROUP BY m.city
    """)
    List<MGASummaryDTO> getMGASummaryByCity(
            @Param("months") List<String> months,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear
    );

    //Group by branch
    @Query("""
        SELECT new com.mandovi.DTO.MGASummaryDTO(
            m.city,
            m.branch,
            SUM(m.loadd),
            SUM(m.consumption),
            SUM(m.consumption) / SUM(m.loadd),
            (SUM(m.loadd) * 455) - SUM(m.consumption),
            ((SUM(m.loadd) * 455) - SUM(m.consumption)) / SUM(m.loadd)
        )
        FROM MGA m
        WHERE (:months IS NULL OR m.month IN (:months))
        AND (:cities IS NULL OR m.city IN (:cities))
        AND (:channels IS NULL OR m.channel IN (:channels))
        AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
        AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
        GROUP BY m.city, m.branch
    """)
    List<MGASummaryDTO> getMGASummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear);

}
