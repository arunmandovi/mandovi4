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
    @Query("SELECT new com.mandovi.DTO.MCPSummaryDTO( " +
            "m.city, " +
            "null, " +
            "SUM(m.mcpQuantity ), " +
            "SUM(m.amountCollected )) " +
            "FROM MCP m " +
            "WHERE (:month IS NULL OR m.month = :month) " +
            "  AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise) " +
            "  AND (:halfYear IS NULL OR m.halfYear = :halfYear) " +
            "GROUP BY m.city")
    List<MCPSummaryDTO> getMCPSummaryByCity(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by branch
    @Query("SELECT new com.mandovi.DTO.MCPSummaryDTO( " +
            "null, " +
            "m.branch, " +
            "SUM(m.mcpQuantity ), " +
            "SUM(m.amountCollected )) " +
            "FROM MCP m " +
            "WHERE (:month IS NULL OR m.month = :month) " +
            "  AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise) " +
            "  AND (:halfYear IS NULL OR m.halfYear = :halfYear) " +
            "GROUP BY m.branch")
    List<MCPSummaryDTO> getMCPSummaryByBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);

    //Group by city and branch
    @Query("SELECT new com.mandovi.DTO.MCPSummaryDTO( " +
            "m.city, " +
            "m.branch, " +
            "SUM(m.mcpQuantity ), " +
            "SUM(m.amountCollected)) " +
            "FROM MCP m " +
            "WHERE (:month IS NULL OR m.month = :month) " +
            "  AND (:qtrWise IS NULL OR m.qtrWise = :qtrWise) " +
            "  AND (:halfYear IS NULL OR m.halfYear = :halfYear) " +
            "GROUP BY m.city, m.branch")
    List<MCPSummaryDTO> getMCPSummaryByCityAndBranch(
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear);
}
