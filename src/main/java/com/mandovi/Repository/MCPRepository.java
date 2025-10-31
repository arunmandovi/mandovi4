package com.mandovi.Repository;

import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.DTO.MCPSummaryDTO;
import com.mandovi.Entity.MCP;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MCPRepository extends JpaRepository<MCP,Long> {

    @Transactional
    @Query("SELECT m FROM MCP m WHERE m.month = :month AND m.year = :year")
    List<MCP> getMCPByMonthYear(@Param("month") String month,@Param("year") String year);

    //Group by city
    @Query("""
            SELECT new com.mandovi.DTO.MCPSummaryDTO(
            m.city,
            null,
            SUM(m.mcpQuantity ),
            SUM(m.amountCollected ))
            FROM MCP m
            WHERE (:months IS NULL OR m.month IN (:months))
              AND (:channels IS  NULL OR m.channel IN (:channels))
              AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
              AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
            GROUP BY m.city
            """)
    List<MCPSummaryDTO> getMCPSummaryByCity(
            @Param("months") List<String> months,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear);

    //Group by branch
    @Query("""
            SELECT new com.mandovi.DTO.MCPSummaryDTO(
            m.city,
            m.branch,
            SUM(m.mcpQuantity ),
            SUM(m.amountCollected))
            FROM MCP m
            WHERE (:months IS NULL OR m.month IN (:months))
            AND (:cities IS NULL OR m.city IN (:cities))
            AND (:channels IS NULL OR m.channel IN (:channels))
            AND (:qtrWise IS NULL OR m.qtrWise IN (:qtrWise))
            AND (:halfYear IS NULL OR m.halfYear IN (:halfYear))
            GROUP BY m.city, m.branch
            """)
    List<MCPSummaryDTO> getMCPSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear);
}
