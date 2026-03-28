package com.mandovi.Service;

import com.mandovi.DTO.MCPSummaryDTO;
import com.mandovi.Entity.MCP;
import com.mandovi.Repository.MCPRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MCPServiceImpl implements MCPService {
    private final MCPRepository mcpRepository;

    public MCPServiceImpl(MCPRepository mcpRepository) {
        this.mcpRepository = mcpRepository;
    }
    Set<String> arenaModels = new HashSet<>(Arrays.asList(
            "ALTO","A-STAR","VITARA BREZZA","VICTORIS","CELERIO","DZIRE","EECO","SWIFT","ESTEEM","GYPSY","KIZASHI","M 800",
            "OMNI","RITZ","S-PRESSO","SUPER CARRY","ERTIGA","SX4","TOUR S (CNG)","VERSA","WAGON R","ZEN","BREZZA","GRAND VITARA CNG K15C"
    ));
    Set<String> nexaModels = new HashSet<>(Arrays.asList(
            "INVICTO","BALENO","XL6","CIAZ","FRONX","IGNIS","JIMNY","GRAND VITARA","MARUTI S-CRO","S-CROSS (D13)"
    ));

    @Override
    public void saveMCPGFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel ");

            String uploadMonth = firstRow.getCell(3).getStringCellValue().trim();
            Cell yearCell = firstRow.getCell(4);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);
            mcpRepository.deleteByMonthYear(uploadMonth, uploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                MCP mcp = new MCP();

                mcp.setCity(row.getCell(0).getStringCellValue());
                mcp.setMonth(row.getCell(3).getStringCellValue());

                Cell cell = row.getCell(4);
                int num_year = (cell.getCellType() == CellType.NUMERIC)
                        ? (int) cell.getNumericCellValue() : Integer.parseInt(cell.getStringCellValue());
                mcp.setYear(String.valueOf(num_year));

                mcp.setMcpQuantity((int) row.getCell(5).getNumericCellValue());
                mcp.setAmountCollected(row.getCell(6).getNumericCellValue());

                switch (row.getCell(1).getStringCellValue().toUpperCase()) {
                    case "BALMATTA WORKSHOP" -> mcp.setBranch("Balmatta");
                    case "BANGALORE EAST TALUK-SRV" ->mcp.setBranch("Sarjapura");
                    case "BANNUR ROAD-SRV" -> mcp.setBranch("Bannur");
                    case "BANTWAL-SRV" -> mcp.setBranch("Bantwal");
                    case "BASAVESHWAR NAGAR-SRV" -> mcp.setBranch("Basaveshwarnagar");
                    case "CHAMARAJANAGAR-SRV" -> mcp.setBranch("ChamrajNagar");
                    case "CHAMARAJPET-2S" -> mcp.setBranch("Basavangudi");
                    case "HENNUR-SRV" -> mcp.setBranch("Hennur");
                    case "HUNSUR ROAD" ->  mcp.setBranch("Hunsur Road");
                    case "JP NAGAR" ->  mcp.setBranch("JP Nagar");
                    case "KADABA-R(3S)" -> mcp.setBranch("Kadaba");
                    case "KALLAHALLI-SRV" -> mcp.setBranch("Mandya");
                    case "KRS ROAD" ->   mcp.setBranch("KRS Road");
                    case "KUSHAL NAGAR-SRV" ->  mcp.setBranch("Kushalnagar");
                    case "MYSORE-2S(NEXA)" ->   mcp.setBranch("Mysore Nexa");
                    case "NARAVI-3S(RO)" ->   mcp.setBranch("Naravi");
                    case "SUJITH BAGH LANE-SRV" ->  mcp.setBranch("Sujith Bagh Lane");
                    case "SULLIA-SRV" ->  mcp.setBranch("Sullia");
                    case "T NARSAIPURA-3S(RO)" -> mcp.setBranch("Narasipura");
                    case "TIRUPATHI ROAD-2S(NEXA)" ->  mcp.setBranch("Kolar Nexa");
                    case "UPPINANGADY-SRV" ->  mcp.setBranch("Uppinangady");
                    case "UTTARAHALI KENGERI ROAD-SRV" ->  mcp.setBranch("Uttarahali Kengeri");
                    case "VIDYARANYAPURA-2S" ->  mcp.setBranch("Vidyarannapura");
                    case "VIJAYANAGAR" ->  mcp.setBranch("Vijayanagar");
                    case "VITTLA-RO(2S)" ->  mcp.setBranch("Vittla");
                    case "WILSON GARDEN" ->  mcp.setBranch("Wilson Garden");
                    case "YELAHANKA MAIN ROAD-2S" ->   mcp.setBranch("Yelahanka");
                    case "YESHWANTPUR - SRV" ->   mcp.setBranch("Yeshwanthpur WS");
                    case "NEXA SERVICE KOLAR" ->   mcp.setBranch("Kolar Nexa");
                    case "KOLLEGAL-3S(RO)" ->   mcp.setBranch("Kollegal");
                    case "MANDYA-2S(STUDIO)" -> mcp.setBranch("Mandya Nexa");
                    case "GONIKOPPAL-2S STUDIO" -> mcp.setBranch("Gonikoppa Nexa");
                    case "SURATHKAL-SRV" -> mcp.setBranch("Surathkal");
                    case "MANGALORE-2S(NEXA)" -> mcp.setBranch("Nexa Service");
                    case "BASAVANAGUDI-SOW" -> mcp.setBranch("Basavanagudi-SOW");
                    case "B.H ROAD-R(3S)" -> mcp.setBranch("Gowribidanur");
                }

                String model = row.getCell(2).getStringCellValue().toUpperCase();
                if (arenaModels.stream().anyMatch(model::contains)) {
                    mcp.setChannel("ARENA");
                } else if (nexaModels.stream().anyMatch(model::contains)) {
                    mcp.setChannel("NEXA");
                }else {
                    mcp.setChannel("UNKNOWN");
                }

                String month = mcp.getMonth().trim().toUpperCase();
                switch (month) {
                    case "APR", "MAY", "JUN" ->{ mcp.setQtrWise("Qtr1"); mcp.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" ->{ mcp.setQtrWise("Qtr2"); mcp.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" ->{ mcp.setQtrWise("Qtr3"); mcp.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" ->{ mcp.setQtrWise("Qtr4"); mcp.setHalfYear("H2");}
                }
                mcpRepository.save(mcp);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<MCP> getAllMCP() {
        return mcpRepository.findAll();
    }

    @Override
    public List<MCP> getMCPByMonthYear(List<String> months, List<String> years) {
        return mcpRepository.getMCPByMonthYear(months, years);
    }

    @Override
    public List<MCPSummaryDTO> getMCPSummary(List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear) {
        return mcpRepository.getMCPSummaryByCity(months, channels, qtrWise, halfYear);
    }

    @Override
    public List<MCPSummaryDTO> getMCPSummaryBranchWise(List<String> months, List<String> cities, List<String> channels, List<String> qtrWise, List<String> halfYear) {
        return mcpRepository.getMCPSummaryBranchWise(months, cities, channels, qtrWise, halfYear);
    }

    @Override
    public void deleteMCPALl() {
        mcpRepository.deleteMCPAll();
    }
}
