package com.mandovi.Controller;


import com.mandovi.DTO.MCPSummaryDTO;
import com.mandovi.Entity.MCP;
import com.mandovi.Service.MCPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mcp")
public class MCPController {
    private final MCPService mcpService;

    public MCPController(MCPService mcpService) {
        this.mcpService = mcpService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMCPFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Please upload a valid Excel file.");
        }try {
            mcpService.saveMCPGFromExcel(file);
            return ResponseEntity.ok().body("MCP File has been uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: "+e.getMessage());
        }
    }

    @GetMapping("/getallmcp")
    public List<MCP> getAllMCP(){
        return mcpService.getAllMCP();
    }

    @GetMapping("/getmcp")
    public ResponseEntity<?> getMCPByMonthYear(
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> years ){
        try {
            List<MCP> mcpRecords = mcpService.getMCPByMonthYear(months, years);
            if (mcpRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(mcpRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/mcp_summary")
    public ResponseEntity<List<MCPSummaryDTO>> getMCPSummary (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MCPSummaryDTO> listMCPSummary = mcpService.getMCPSummary(months, channels, qtrWise, halfYear);
            if (listMCPSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMCPSummary);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/mcp_branch_summary")
    public ResponseEntity<?> getMCPSummaryBranchWise (
            @RequestParam (required = false) List<String> months,
            @RequestParam (required = false) List<String> cities,
            @RequestParam (required = false) List<String> channels,
            @RequestParam (required = false) List<String> qtrWise,
            @RequestParam (required = false) List<String> halfYear ){
        try {
            List<MCPSummaryDTO> listMCPSummaryBranchWise = mcpService.getMCPSummaryBranchWise(months, cities, channels, qtrWise, halfYear);
            if (listMCPSummaryBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMCPSummaryBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}
