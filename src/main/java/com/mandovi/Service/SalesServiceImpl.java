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

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Sales sales = new Sales();

                sales.setBranch(row.getCell(0).getStringCellValue());
                sales.setCity(row.getCell(1).getStringCellValue());
                sales.setMonth(row.getCell(2).getStringCellValue());
                Cell year_cell = row.getCell(3);
                int num_year = year_cell.getCellType() == CellType.NUMERIC
                        ? (int) year_cell.getNumericCellValue() : Integer.parseInt(year_cell.getStringCellValue());
                sales.setYear(String.valueOf(num_year));
                sales.setChannel(row.getCell(4).getStringCellValue());
                sales.setVin((int)row.getCell(5).getNumericCellValue());
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
