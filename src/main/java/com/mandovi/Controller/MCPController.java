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

    @GetMapping("/getmcp/{month}/{year}")
    public ResponseEntity<List<MCP>> getMCPByMonthYear(@PathVariable String month, @PathVariable String year){
        List<MCP> mcpRecords = mcpService.getMCPByMonthYear(month, year);
        if (mcpRecords.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mcpRecords);
    }

    @GetMapping("/mcp_summary")
    public ResponseEntity<List<MCPSummaryDTO>> getMCPSummary (
            @RequestParam String groupBy,
            @RequestParam (required = false) String month,
            @RequestParam (required = false) String qtrWise,
            @RequestParam (required = false) String halfYear ){
        try {
            List<MCPSummaryDTO> listMCPSummary = mcpService.getMCPSummary(groupBy, month, qtrWise, halfYear);
            if (listMCPSummary.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listMCPSummary);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
