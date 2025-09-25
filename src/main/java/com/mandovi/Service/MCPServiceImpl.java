package com.mandovi.Service;

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
            "BALENO","ALTO","ALTO 800","ALTO 800 CNG","ALTO K10","Alto K10 CNG","ALTO K10C","A-STAR","BREZZA S-CNG",
            "CELERIO","CELERIO CNG","CELERIO DIESEL","DAZZLING NEW DZIRE","DAZZLING NEW DZIRE CNG","Dzire CNG",
            "DZIRE TOUR DIESEL","EECO","EECO CNG K12N 2022","EECO K12N 2022","EPIC NEW SWIFT","EPIC NEW SWIFT CNG",
            "ERTIGA CNG","ERTIGA DIESEL","ERTIGA DIESEL 1.5L","ERTIGA PETROL","ESTEEM","GRAND VITARA CNG K15C",
            "GYPSY","KIZASHI","M 800","MARUTI EECO CNG","MARUTI EECO PETROL","NEW BREZZA","NEW CELERIO 2021",
            "NEW CELERIO CNG 2021","New Dzire Diesel","New Dzire Petrol","NEW ERTIGA DIESEL","NEW ERTIGA PETROL",
            "NEW SWIFT CNG","NEW SWIFT DIESEL","NEW SWIFT DZIRE TOUR S PETROL","NEW SWIFT PE","NEW SWIFT PETROL",
            "NEW WAGON R 1.2L PETROL","NEW WAGON R 1L CNG","NEW WAGON R 1L PETROL","Next-Gen ERTIGA CNG","OMNI",
            "RITZ","RITZ DIESEL","S-PRESSO","S-PRESSO BIFUEL CNG","SUPER CARRY","SUPER CARRY CNG",
            "SUPER CARRY PETROL","SWIFT","SWIFT DIESEL","SWIFT DZIRE","SWIFT NEW / D","SWIFT NEW / DZIRE NEW PETROL",
            "SWIFT NEW/  D","SWIFT NEW/  DZIRE NEW DIESEL","SX4","SX4 DIESEL","TOUR S (CNG)","VERSA","VITARA",
            "VITARA BREZZA 1.3D","VITARA BREZZA K15B BS-VI","WAGON R","WAGON-R NEW","ZEN","ZEN ESTILO","NEW WAGON R",
            "NEW CELERIO","New Dzire Petro","EECO CNG K12"
    ));
    Set<String> nexaModels = new HashSet<>(Arrays.asList(
            "INVICTO","NEW BALENO","XL6","Baleno RS","CIAZ DIESEL","CIAZ PETROL","FRONX CNG","Fronx Domestic",
            "Fronx Domestic P74","Fronx Turbo Smart Hybrid Domestic P74","IGNIS-DIESEL","IGNIS-PETROL",
            "Jimny 5 Door Domestic P74","MARUTI BALENO","MARUTI BALENO DIESEL","MARUTI BALENO PETROL",
            "MARUTI GRAND VITARA Smart Hybrid","MARUTI GRAND VITARA Strong Hybrid","MARUTI S-CRO",
            "MARUTI S-CROSS SMART HYBRID 1.5L","NEW BALENO CNG","NEW CIAZ DIESEL","NEW CIAZ PETROL",
            "S-CROSS (D13)","S-CROSS (D16)","XL6 CNG","NEW CIAZ PETR"
    ));

    @Override
    public void saveMCPGFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                MCP mcp = new MCP();

                mcp.setCity(row.getCell(0).getStringCellValue());
                mcp.setLocation(row.getCell(1).getStringCellValue());
                mcp.setModel(row.getCell(2).getStringCellValue());
                mcp.setMonth(row.getCell(3).getStringCellValue());

                //Updating value for year column from Integer cell
                switch (row.getCell(4).getCellType()){
                    case NUMERIC : int num_year = (int) row.getCell(4).getNumericCellValue();
                    mcp.setYear(String.valueOf(num_year));
                    break;
                    case STRING: mcp.setYear(row.getCell(4).getStringCellValue());
                    default: mcp.setYear("");
                }

                mcp.setMcpQuantity((int) row.getCell(5).getNumericCellValue());
                mcp.setAmountCollected(row.getCell(6).getNumericCellValue());

                //Updating the branch column based on location
                String location = mcp.getLocation().trim().toUpperCase();
                switch (location) {
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

                }

                //Updating first day of month which mentioned in month column
                mcp.setDate("1-"+mcp.getMonth());

                //Updating the channel based on model
                String model = mcp.getModel();
                if (arenaModels.contains(model)) {
                    mcp.setChannel("ARENA");
                } else if (nexaModels.contains(model)) {
                    mcp.setChannel("NEXA");
                }else {
                    mcp.setChannel("UNKNOWN");
                }

                //Updating the qtr_wise & half_year column based on month
                String month = mcp.getMonth().trim().toUpperCase();
                switch (month) {
                    case "APR", "MAY", "JUN" ->{ mcp.setQtrWise("Q1"); mcp.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" ->{ mcp.setQtrWise("Q2"); mcp.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" ->{ mcp.setQtrWise("Q3"); mcp.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" ->{ mcp.setQtrWise("Q4"); mcp.setHalfYear("H2");}
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
    public List<MCP> getMCPByMonthYear(String month, String year) {
        String formattedMonth = month.substring(0,1).toUpperCase()+month.substring(1).toLowerCase();
        return mcpRepository.getMCPByMonthYear(formattedMonth, year);
    }
}
