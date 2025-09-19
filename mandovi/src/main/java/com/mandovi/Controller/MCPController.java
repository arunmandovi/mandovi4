package com.mandovi.Controller;


import com.mandovi.Service.MCPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
