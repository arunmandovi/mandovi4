package com.mandovi.Repository;

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
}
