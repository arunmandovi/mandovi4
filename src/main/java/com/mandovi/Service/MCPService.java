package com.mandovi.Service;

import com.mandovi.Entity.MCP;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MCPService {
    void saveMCPGFromExcel(MultipartFile file);

    public List<MCP> getAllMCP();

    List<MCP> getMCPByMonthYear(String month, String year);
}
