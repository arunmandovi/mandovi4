package com.mandovi.Service;

import com.mandovi.Entity.MGA;
import com.mandovi.Repository.MGARepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MGAServiceImpl implements MGAService {
    private final MGARepository mgaRepository;

    public MGAServiceImpl(MGARepository mgaRepository) {
        this.mgaRepository = mgaRepository;
    }
    private Double round2Decimal (Double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    private String getStringCell(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) return null;
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue()); // for numeric codes
        }
        return null;
    }
    Set<String> arenaChannel = new HashSet<String>(Arrays.asList(
            "WILSON GARDEN","VIJAYANAGAR","JP NAGAR","YESHWANTHPUR WS","BASAVESHWARNAGAR","HENNUR","SARJAPURA","KOLAR","GAURIBIDNUR",
            "UTTARAHALI KENGERI ROAD","VIDYARANYAPURA-2S","MALUR-SOW","BASAVANGUDI","BASAVANAGUDI-SOW","CHOKKANDAHALLI","KRS ROAD",
            "HUNSUR ROAD","BANNUR","MANDYA","GONIKOPPA","KUSHAL NAGAR","CHAMRAJNAGAR","KRISHNARAJAPET-RO(2S)","SOMVARPET-3S(RO)","MADDUR",
            "NAGAMANGALA-3S(RO)","T NARSAIPURA-3S(RO)","BALMATTA W/S","BANTWAL","UPPINANGADY","KADABA","VITTLA","SURATHKAL","SULLIA","ADYAR",
            "YEYYADI BR","SUJITH BAGH LANE","NARAVI-3S(RO)","CHOKKANDAHALLI-SRV","UTTARAHALI KENGERI ROAD-SRV","NEAR SANJAY THEATRE-2S(RO)"

    ));
    Set<String> nexaChannel = new HashSet<>(Arrays.asList(
            "YELAHANKA","TIRUPATHI ROAD-2S(NEXA)","MYSORE-2S(NEXA)","NEXA SERVICE"
    ));

    @Override
    @Transactional
    public void saveMGAFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                MGA mga = new MGA();

                //Converting Date to LocalDate to store in DB
                Cell cell = row.getCell(0);
                LocalDate localDate = null;

                if (cell != null) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        // Numeric cell (date)
                        Date date = cell.getDateCellValue();
                        localDate = date.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    } else if (cell.getCellType() == CellType.STRING) {
                        // String cell (like "2025-09-19")
                        String dateStr = cell.getStringCellValue();
                        localDate = LocalDate.parse(dateStr); // Use DateTimeFormatter if custom format
                    }
                }

                mga.setMgaDate(localDate);

//                mga.setMgaDate((LocalDate) row.getCell(0).getDateCellValue());
                mga.setDealerCode(row.getCell(1).getStringCellValue());
                mga.setForCode(row.getCell(2).getStringCellValue());
                mga.setOutletCode(row.getCell(3).getStringCellValue());
                mga.setDealerForOutletCode(row.getCell(4).getStringCellValue());
                mga.setCity(row.getCell(5).getStringCellValue());
                mga.setLocation(row.getCell(6).getStringCellValue());
                mga.setDealerName(row.getCell(7).getStringCellValue());
                mga.setServiceAdvisor(row.getCell(8).getStringCellValue());
                mga.setConsumption(round2Decimal(row.getCell(9).getNumericCellValue()));
                mga.setLoadd((int)row.getCell(10).getNumericCellValue());
                mga.setMgaLoad(round2Decimal(row.getCell(11).getNumericCellValue()));

                //Updating branch based on location
                if (mga.getLocation() != null) {
                    switch (mga.getLocation().trim().toUpperCase()) {
                        case "WILSON GARDEN" -> mga.setBranch("Wilson Garden");
                        case "VIJAYANAGAR" -> mga.setBranch("Vijayanagar");
                        case "BANTWAL" -> mga.setBranch("Bantwal");
                        case "VITTLA" -> mga.setBranch("Vittla");
                        case "NEXA SERVICE" -> mga.setBranch("Nexa Service");
                        case "JP NAGAR" -> mga.setBranch("Jp Nagar");
                        case "YESHWANTHPUR WS" -> mga.setBranch("YeshwanthPur WS");
                        case "BASAVESHWARNAGAR" -> mga.setBranch("BasaveShwarnagar");
                        case "HENNUR" -> mga.setBranch("Hennur");
                        case "SARJAPURA" -> mga.setBranch("Sarjapura");
                        case "KOLAR" -> mga.setBranch("Kolar");
                        case "YELAHANKA" -> mga.setBranch("Yelahanka");
                        case "BASAVANGUDI" -> mga.setBranch("Basavangudi");
                        case "BASAVANAGUDI-SOW" -> mga.setBranch("Basavangudi-SOW");
                        case "KADABA" -> mga.setBranch("Kadaba");
                        case "UPPINANGADY" -> mga.setBranch("Uppinangady");
                        case "SURATHKAL" -> mga.setBranch("Surathkal");
                        case "SULLIA" -> mga.setBranch("Sullia");
                        case "ADYAR" -> mga.setBranch("Adyar");
                        case "BALMATTA W/S" -> mga.setBranch("Balmatta");
                        case "BANNUR" -> mga.setBranch("Bannur");
                        case "CHAMRAJNAGAR" -> mga.setBranch("ChamrajNagar");
                        case "CHOKKANDAHALLI-SRV" -> mga.setBranch("Maluru WS");
                        case "GAURIBIDNUR" -> mga.setBranch("Gowribidanur");
                        case "GONIKOPPA" -> mga.setBranch("Gonikoppa");
                        case "HUNSUR ROAD" -> mga.setBranch("Hunsur Road");
                        case "KRISHNARAJAPET-RO(2S)" -> mga.setBranch("Krishnarajapet");
                        case "KRS ROAD" -> mga.setBranch("KRS Road");
                        case "KUSHAL NAGAR" -> mga.setBranch("Kushalnagar");
                        case "MALUR-SOW" -> mga.setBranch("Malur SOW");
                        case "MANDYA" -> mga.setBranch("Mandya");
                        case "MYSORE-2S(NEXA)" -> mga.setBranch("Mysore Nexa");
                        case "NAGAMANGALA-3S(RO)" -> mga.setBranch("Nagamangala");
                        case "NARAVI-3S(RO)" -> mga.setBranch("Naravi");
                        case "NEAR SANJAY THEATRE-2S(RO)" -> mga.setBranch("Maddur");
                        case "SOMVARPET-3S(RO)" -> mga.setBranch("Somvarpet");
                        case "SUJITH BAGH LANE" -> mga.setBranch("Sujith Bagh Lane");
                        case "T NARSAIPURA-3S(RO)" -> mga.setBranch("Narsaipura");
                        case "TIRUPATHI ROAD-2S(NEXA)" -> mga.setBranch("Kolar Nexa");
                        case "UTTARAHALI KENGERI ROAD-SRV" -> mga.setBranch("Uttarahali Kengeri");
                        case "VIDYARANYAPURA-2S" -> mga.setBranch("Vidyaranyapura");
                        case "YEYYADI BR" -> mga.setBranch("Yeyadi BR");
                        case "KOLLEGAL-3S(RO)" -> mga.setBranch("Kollegal");
                        default -> mga.setBranch("UNKNOWN");
                    }
                }

                //Updating month based on mga_date
                if (row.getCell(0)!= null){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
                    mga.setMonth(formatter.format(mga.getMgaDate()));
                }else {
                    mga.setMonth("UNKNOWN");
                }

                String location = mga.getLocation().trim().toUpperCase();
                if (arenaChannel.contains(location)) {
                    mga.setChannel("ARENA");
                } else if (nexaChannel.contains(location)) {
                    mga.setChannel("NEXA");
                }else {
                    mga.setChannel("UNKNOWN");
                }

                //Updating qtr_wise & half_year based on month\
                switch (mga.getMonth().trim().toUpperCase()){
                    case "APR", "MAY", "JUN" ->{ mga.setQtrWise("Q1"); mga.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" ->{ mga.setQtrWise("Q2"); mga.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" ->{ mga.setQtrWise("Q3"); mga.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" ->{ mga.setQtrWise("Q4"); mga.setHalfYear("H2");}
                }

                mgaRepository.save(mga);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MGA> getAllMGA() {
        return mgaRepository.findAll();
    }


}
