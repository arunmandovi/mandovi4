package com.mandovi.Service;

import com.mandovi.DTO.SalesSummaryDTO;
import com.mandovi.Entity.Sales;
import com.mandovi.Repository.SalesRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;

    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    private static String normalize(String model) {
        return model == null ? ""
                : model.toUpperCase()
                .replaceAll("[^A-Z0-9 ]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private static final Set<String> ARENA_KEYWORDS = Set.of(
            "ALTO", "SWIFT", "DZIRE", "ERTIGA", "WAGON", "CELERIO", "EECO", "S PRESSO","VERSA", "SX4",
            "SUPER CARRY", "OMNI", "RITZ", "ZEN", "ESTEEM", "GYPSY", "A STAR", "M 800", "VITARA BREZZA", "NEW BREZZA"
    );

    private static final Set<String> NEXA_KEYWORDS = Set.of(
            "BALENO",  "CIAZ",  "XL6",  "FRONX",  "IGNIS",  "S CROSS",
            "SCROSS", "JIMNY", "INVICTO", "GRAND VITARA", "KIZASHI"
    );

    private static boolean containsAny(String model, Set<String> keywords) {
        return keywords.stream().anyMatch(model::contains);
    }

    private static LocalDate getLocalDateFromCell(Cell cell) {
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getDateCellValue()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().isBlank()) {
            return LocalDate.parse(cell.getStringCellValue().trim());
        }

        return null;
    }

    private static String getStringFromCell(Cell cell) {
        if (cell == null) return null;

        return switch (cell.getCellType()) {
            case STRING  -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default      -> null;
        };
    }

    private static Integer getIntegerFromCell(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.NUMERIC) return null;
        return (int) cell.getNumericCellValue();
    }

    @Override
    public void saveSalesFromExcel(MultipartFile file) {

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {
             Sheet sheet = workbook.getSheetAt(0);

            // Define location Codes for respective Cities
            Set<String> bangaloreLocations = new HashSet<>(Arrays.asList(
                    "BKH","BNG","BSN","CDE","CMJ","GRB","HNR","JPN",
                    "KDH","MAF","MLU","NXS","RJN","VDR","VJN","WGR","YLH","YPR" ));
            Set<String> mysoreLocations = new HashSet<>(Arrays.asList(
                    "BNR","CMR","HSR","JVR","KIV","KKE","KRS","KSH",
                    "KSN","MSE","NGL","SOM","TNR","KLG" ));
            Set<String> mangaloreLocations = new HashSet<>(Arrays.asList(
                    "BMR","BTL","BTW", "OLD","VLA","VI1","KDB","UPA","UPP","SKL","STK","SLL","AYR","YEY",
                    "MNL","MGA","SJH","SYG" ));

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Sales sales = new Sales();

                sales.setLocationCode(getStringFromCell(row.getCell(0)));
                if (sales.getLocationCode() != null) {
                    switch (sales.getLocationCode().trim().toUpperCase()) {
                        case "BMR": sales.setBranch("Balmatta"); break;
                        case "BTL":
                        case "BTW": sales.setBranch("Bantwal"); break;
                        case "VLA":
                        case "VI1": sales.setBranch("Vittla"); break;
                        case "KDB": sales.setBranch("Kadaba"); break;
                        case "MNL":
                        case "MGA": sales.setBranch("Nexa"); break;
                        case "UPA":
                        case "UPP": sales.setBranch("Uppinangady"); break;
                        case "STK":
                        case "SKL": sales.setBranch("Surathkal"); break;
                        case "OLD":
                        case "EW":
                        case "SLL": sales.setBranch("Sullia"); break;
                        case "AYR": sales.setBranch("Adyar"); break;
                        case "YEY": sales.setBranch("Yeyyadi BR"); break;
                        case "SJH": sales.setBranch("Sujith Bagh Lane"); break;
                        case "SYG": sales.setBranch("Naravi"); break;
                        case "BKH": sales.setBranch("NS Palya"); break;
                        case "BNG": sales.setBranch("Sarjapura"); break;
                        case "BNR": sales.setBranch("Bannur"); break;
                        case "BSN": sales.setBranch("Basaveshwarnagar"); break;
                        case "CDE": sales.setBranch("Kolar Nexa"); break;
                        case "CMJ": sales.setBranch("Basavangudi"); break;
                        case "CMR": sales.setBranch("ChamrajNagar"); break;
                        case "GRB": sales.setBranch("Gowribidanur"); break;
                        case "HNR": sales.setBranch("Hennur"); break;
                        case "HSR": sales.setBranch("Hunsur Road"); break;
                        case "JPN": sales.setBranch("JP Nagar"); break;
                        case "JVR": sales.setBranch("Maddur"); break;
                        case "KDH": sales.setBranch("Kolar"); break;
                        case "KIV": sales.setBranch("Gonikoppa"); break;
                        case "KKE": sales.setBranch("Mandya"); break;
                        case "KRS": sales.setBranch("KRS Road"); break;
                        case "KSH": sales.setBranch("Kushalnagar"); break;
                        case "KSN": sales.setBranch("Krishnarajapet"); break;
                        case "MAF": sales.setBranch("Basavanagudi-SOW"); break;
                        case "MLU": sales.setBranch("Malur SOW"); break;
                        case "MSE": sales.setBranch("Mysore Nexa"); break;
                        case "NGL": sales.setBranch("Nagamangala"); break;
                        case "NXS": sales.setBranch("Maluru WS"); break;
                        case "RJN": sales.setBranch("Uttarahali Kengeri"); break;
                        case "SOM": sales.setBranch("Somvarpet"); break;
                        case "TNR": sales.setBranch("Narasipura"); break;
                        case "VDR": sales.setBranch("Vidyarannapura"); break;
                        case "VJN": sales.setBranch("Vijayanagar"); break;
                        case "WGR": sales.setBranch("Wilson Garden"); break;
                        case "YLH": sales.setBranch("Yelahanka"); break;
                        case "YPR": sales.setBranch("Yeshwanthpur WS"); break;
                        case "KLG": sales.setBranch("Kollegal"); break;
                        case "MNY": sales.setBranch("Mandya Nexa"); break;
                        default: sales.setBranch("Unknown"); break;
                    }
                }

                String locationCode = sales.getLocationCode();
                if (bangaloreLocations.contains(locationCode)){
                    sales.setCity("Bangalore");
                } else if (mysoreLocations.contains(locationCode)){
                    sales.setCity("Mysore");
                } else if (mangaloreLocations.contains(locationCode)) {
                    sales.setCity("Mangalore");
                } else {
                    sales.setCity("UNKNOWN");
                }

                sales.setInvDate(getLocalDateFromCell(row.getCell(1)));

                DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
                sales.setDay(dayFormatter.format(sales.getInvDate()));
                DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");
                sales.setMonth(monthFormatter.format(sales.getInvDate()));
                DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
                sales.setYear(yearFormatter.format(sales.getInvDate()));

                sales.setModel(getStringFromCell(row.getCell(2)));
                String rawModel = sales.getModel();
                String model = normalize(rawModel);
                if (containsAny(model, NEXA_KEYWORDS)){
                    sales.setChannel("NEXA");
                }else if (containsAny(model, ARENA_KEYWORDS)){
                    sales.setChannel("ARENA");
                } else {
                    sales.setChannel("UNKNOWN");
                }

                sales.setVariantDesc(getStringFromCell(row.getCell(3)));

                sales.setFuelType(getStringFromCell(row.getCell(4)));
                sales.setRegNo(getStringFromCell(row.getCell(5)));

                Cell cell = row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                if (cell == null) {
                    sales.setPinCode(null);
                } else {
                    switch (cell.getCellType()) {
                        case NUMERIC -> {
                            Integer pinNumber = getIntegerFromCell(cell);
                            sales.setPinCode(pinNumber != null ? String.valueOf(pinNumber) : null);
                        }
                        case STRING -> sales.setPinCode(getStringFromCell(cell));
                        default -> sales.setPinCode(null);
                    }
                }

                sales.setPinDesc(getStringFromCell(row.getCell(7)));
                 sales.setVin(getStringFromCell(row.getCell(8)));

                salesRepository.save(sales);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to save sales data from Excel", e);
        }
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Override
    public List<Sales> getAllSalesByMonthYear(List<String> months, List<String> years) {
        return salesRepository.getAllSalesByMonthYear(months, years);
    }

    @Override
    public List<SalesSummaryDTO> getSalesSummaryCityWise(List<String> years, List<String> months, List<String> channels) {
        return salesRepository.getSalesSummaryCityWise(years, months, channels);
    }

    @Override
    public List<SalesSummaryDTO> getSalesSummaryBranchWise(List<String> years, List<String> months, List<String> cities, List<String> channels) {
        return salesRepository.getSalesSummaryBranchWise(years, months, cities, channels);
    }
}
