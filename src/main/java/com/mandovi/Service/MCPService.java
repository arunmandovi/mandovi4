package com.mandovi.Service;

import com.mandovi.DTO.MCPSummaryDTO;
import com.mandovi.Entity.MCP;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MCPService {
    void saveMCPGFromExcel(MultipartFile file);

    public List<MCP> getAllMCP();

    List<MCP> getMCPByMonthYear(List<String> months, List<String> years);

    public List<MCPSummaryDTO> getMCPSummary (List<String> months,List<String> channels, List<String> qtrWise, List<String> halfYear);

    public List<MCPSummaryDTO> getMCPSummaryBranchWise (List<String> months, List<String> cities, List<String> channels, List<String> qtrWise, List<String> halfYear);
}
