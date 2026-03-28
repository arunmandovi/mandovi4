package com.mandovi.Service;

import com.mandovi.DTO.MGASummaryDTO;
import com.mandovi.Entity.MGA;
import com.mandovi.Repository.MGARepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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

    Set<String> arenaChannel = new HashSet<String>(Arrays.asList(
            "WILSON GARDEN","VIJAYANAGAR","JP NAGAR","YESHWANTHPUR WS","BASAVESHWARNAGAR","HENNUR","SARJAPURA","KOLAR","GAURIBIDNUR",
            "UTTARAHALI KENGERI ROAD","VIDYARANYAPURA-2S","MALUR-SOW","BASAVANGUDI","BASAVANAGUDI-SOW","CHOKKANDAHALLI","KRS ROAD",
            "HUNSUR ROAD","BANNUR","MANDYA","GONIKOPPA","KUSHAL NAGAR","CHAMRAJNAGAR","KRISHNARAJAPET-RO(2S)","SOMVARPET-3S(RO)","MADDUR",
            "NAGAMANGALA-3S(RO)","T NARSAIPURA-3S(RO)","BALMATTA W/S","BANTWAL","UPPINANGADY","KADABA","VITTLA","SURATHKAL","SULLIA","ADYAR",
            "YEYYADI BR","SUJITH BAGH LANE","NARAVI-3S(RO)","CHOKKANDAHALLI-SRV","UTTARAHALI KENGERI ROAD-SRV","NEAR SANJAY THEATRE-2S(RO)",
            "KOLLEGAL-3S(RO)","MANDYA-2S(STUDIO)"
    ));
    Set<String> nexaChannel = new HashSet<>(Arrays.asList(
            "YELAHANKA","TIRUPATHI ROAD-2S(NEXA)","MYSORE-2S(NEXA)","NEXA SERVICE"
    ));

    private String getStingFromCell (Row row, int cellIndex){
        if (row.getCell(cellIndex) == null)
            return null;

        return switch (row.getCell(cellIndex).getCellType()){
            case NUMERIC -> String.valueOf((int) row.getCell(cellIndex).getNumericCellValue());
            case STRING -> row.getCell(cellIndex).getStringCellValue();
            default -> null;
        };
    }

    @Override
    @Transactional
    public void saveMGAFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            LocalDate uploadDate = firstRow.getCell(0).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            mgaRepository.deleteByDate(uploadDate);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                MGA mga = new MGA();

                Cell cell = row.getCell(0);
                LocalDate localDate = null;

                if (cell != null) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        Date date = cell.getDateCellValue();
                        localDate = date.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    } else if (cell.getCellType() == CellType.STRING) {
                        String dateStr = cell.getStringCellValue();
                        localDate = LocalDate.parse(dateStr);
                    }
                }

                mga.setMgaDate(localDate);

                mga.setCity(row.getCell(5).getStringCellValue());
                mga.setServiceAdvisor(row.getCell(8).getStringCellValue());
                mga.setConsumption(row.getCell(9).getNumericCellValue());

                switch (row.getCell(10).getCellType()){
                    case NUMERIC -> mga.setLoadd((int)row.getCell(10).getNumericCellValue());
                    default -> mga.setLoadd(0);
                }
                if (mga.getLoadd() == 0){
                    mga.setMgaLoad(0.0);
                } else {
                    mga.setMgaLoad(mga.getConsumption()/mga.getLoadd());
                }

                if (row.getCell(6).getStringCellValue() != null) {
                    switch (row.getCell(6).getStringCellValue().trim().toUpperCase()) {
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
                        case "MANDYA-2S(STUDIO)" -> mga.setBranch("Mandya Nexa");
                        case "GONIKOPPAL-2S STUDIO" -> mga.setBranch("Gonikoppa Nexa");
                        default -> mga.setBranch("UNKNOWN");
                    }
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
                mga.setMonth(formatter.format(mga.getMgaDate()));
                DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("YYYY");
                mga.setYear(yearFormatter.format(mga.getMgaDate()));

                if (arenaChannel.contains(row.getCell(6).getStringCellValue().toUpperCase())) {
                    mga.setChannel("ARENA");
                } else if (nexaChannel.contains(row.getCell(6).getStringCellValue().toUpperCase())) {
                    mga.setChannel("NEXA");
                }else {
                    mga.setChannel("UNKNOWN");
                }

                switch (mga.getMonth().trim().toUpperCase()){
                    case "APR", "MAY", "JUN" ->{ mga.setQtrWise("Qtr1"); mga.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" ->{ mga.setQtrWise("Qtr2"); mga.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" ->{ mga.setQtrWise("Qtr3"); mga.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" ->{ mga.setQtrWise("Qtr4"); mga.setHalfYear("H2");}
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

    @Override
    public List<MGA> getMGAMonthYear(List<String> months, List<String> years) {
        return mgaRepository.getMGAMonth(months, years);
    }

    @Override
    public List<MGASummaryDTO> getMGASummary(List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear) {
        return mgaRepository.getMGASummaryByCity(months, channels, qtrWise, halfYear);
    }

    @Override
    public List<MGASummaryDTO> getMGASummaryBranchWise(List<String> months, List<String> cities, List<String> channels, List<String> qtrWise, List<String> halfYear) {
        return mgaRepository.getMGASummaryBranchWise(months, cities, channels, qtrWise, halfYear);
    }

    @Override
    public void deleteMGAAll() {
        mgaRepository.deleteMGAAll();
    }


}
