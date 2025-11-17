package com.mandovi.Service;

import com.mandovi.DTO.ProfitLossSummaryDTO;
import com.mandovi.Repository.ProfitLossRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfitLossServiceImpl implements ProfitLossService {

    private final ProfitLossRepository profitlossRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "profit_loss";
    private static final String DATABASE = "mandovi";

    public ProfitLossServiceImpl(ProfitLossRepository profitlossRepository) {
        this.profitlossRepository = profitlossRepository;
    }

    // ============================================================
    // MAIN METHOD
    // ============================================================
    @Override
    public void saveProfitLossExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // 1️⃣ READ HEADER → ORIGINAL COLUMN NAMES
            Row headerRow = sheet.getRow(0);
            List<String> originalColumns = new ArrayList<>();

            for (Cell cell : headerRow) {
                originalColumns.add(cell.getStringCellValue().trim());
            }

            // 2️⃣ NORMALIZE → SQL SAFE COLUMN NAMES
            List<String> normalizedColumns = originalColumns.stream()
                    .map(this::normalizeColumn)
                    .filter(c -> !c.equalsIgnoreCase("city") &&
                            !c.equalsIgnoreCase("branch"))
                    .distinct()
                    .collect(Collectors.toList());

            // 3️⃣ INDEX MAP (CASE-INSENSITIVE)
            Map<String, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < originalColumns.size(); i++) {
                indexMap.put(originalColumns.get(i).toLowerCase(), i);
            }

            if (!indexMap.containsKey("city") || !indexMap.containsKey("branch")) {
                throw new RuntimeException("❌ Excel missing required columns: city or branch");
            }

            int cityIndex   = indexMap.get("city");
            int branchIndex = indexMap.get("branch");

            // 4️⃣ DETECT COLUMN TYPES
            Map<String, String> colTypes = detectColTypes(sheet, normalizedColumns);

            // 5️⃣ PREPARE DATABASE STRUCTURE
            createTableIfNotExists();
            dropAllColumnsExceptCityBranch();
            addMissingColumns(normalizedColumns, colTypes);

            // 6️⃣ INSERT OR UPDATE ROWS
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                String city   = Objects.toString(getCellValueAsObject(row.getCell(cityIndex)), "");
                String branch = Objects.toString(getCellValueAsObject(row.getCell(branchIndex)), "");

                Map<String, Object> rowMap = new LinkedHashMap<>();

                // For each normalized column, fetch original index
                for (int i = 0; i < normalizedColumns.size(); i++) {
                    String rawCol = originalColumns.get(i);
                    int excelIndex = indexMap.get(rawCol.toLowerCase());
                    Object value = getCellValueAsObject(row.getCell(excelIndex));
                    rowMap.put(normalizedColumns.get(i), value);
                }

                upsertRow(city, branch, rowMap);
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to process Excel: " + e.getMessage(), e);
        }
    }

    // ============================================================
    // SQL COLUMN NORMALIZER
    // ============================================================
    private String normalizeColumn(String col) {
        col = col.trim().toLowerCase();
        col = col.replaceAll("[\\s-]+", "_");

        if (!Character.isLetter(col.charAt(0))) {
            col = "col_" + col;
        }
        return col;
    }

    // ============================================================
    // UPSERT (INSERT OR UPDATE)
    // ============================================================
    private void upsertRow(String city, String branch, Map<String, Object> dataMap) {
        dataMap.put("city", city);
        dataMap.put("branch", branch);

        StringBuilder sql = new StringBuilder("INSERT INTO " + TABLE_NAME + " (");
        StringBuilder values = new StringBuilder(" VALUES (");
        StringBuilder updates = new StringBuilder(" ON DUPLICATE KEY UPDATE ");

        List<Object> params = new ArrayList<>();

        for (String key : dataMap.keySet()) {
            sql.append("`").append(key).append("`,");
            values.append("?,");
            updates.append("`").append(key).append("`=VALUES(`").append(key).append("`),");

            params.add(dataMap.get(key));
        }

        sql.setLength(sql.length() - 1);
        values.setLength(values.length() - 1);
        updates.setLength(updates.length() - 1);

        String finalSql = sql + ")" + values + ")" + updates;

        jdbcTemplate.update(finalSql, params.toArray());
    }

    // ============================================================
    // READ CELL VALUE
    // ============================================================
    private Object getCellValueAsObject(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> cell.getNumericCellValue();
            default -> null;
        };
    }

    // ============================================================
    // DROP OLD DYNAMIC COLUMNS
    // ============================================================
    private void dropAllColumnsExceptCityBranch() {
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=? AND TABLE_NAME=?";
        List<String> cols = jdbcTemplate.queryForList(sql, new Object[]{DATABASE, TABLE_NAME}, String.class);

        for (String col : cols) {
            if (!col.equalsIgnoreCase("city") &&
                    !col.equalsIgnoreCase("branch") &&
                    !col.equalsIgnoreCase("profit_lossSINo")) {

                String drop = "ALTER TABLE `" + DATABASE + "`.`" + TABLE_NAME + "` DROP COLUMN `" + col + "`";
                jdbcTemplate.execute(drop);
            }
        }
    }

    // ============================================================
    // ADD NEW EXCEL COLUMNS
    // ============================================================
    private void addMissingColumns(List<String> columns, Map<String, String> colTypes) {
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=? AND TABLE_NAME=?";
        List<String> existing = jdbcTemplate.queryForList(sql, new Object[]{DATABASE, TABLE_NAME}, String.class);

        for (String col : columns) {
            if (!existing.contains(col)) {
                String type = colTypes.getOrDefault(col, "VARCHAR(255)");
                String alter = "ALTER TABLE `" + DATABASE + "`.`" + TABLE_NAME + "` ADD COLUMN `" + col + "` " + type;
                jdbcTemplate.execute(alter);
            }
        }
    }

    // ============================================================
    // DETECT COLUMN TYPES
    // ============================================================
    private Map<String, String> detectColTypes(Sheet sheet, List<String> columns) {
        Map<String, String> types = new HashMap<>();
        Row row = sheet.getRow(1);

        for (int j = 0; j < columns.size(); j++) {
            Cell cell = (row != null) ? row.getCell(j) : null;

            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                types.put(columns.get(j), "DOUBLE");
            } else {
                types.put(columns.get(j), "VARCHAR(255)");
            }
        }
        return types;
    }

    // ============================================================
    // CREATE TABLE
    // ============================================================
    private void createTableIfNotExists() {
        String sql =
                "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` (" +
                        "profit_lossSINo INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                        "city VARCHAR(450), " +
                        "branch VARCHAR(450), " +
                        "UNIQUE KEY uq_city_branch (city, branch)" +
                        ")";
        jdbcTemplate.execute(sql);
    }

    // ============================================================
    // OTHER ENDPOINTS
    // ============================================================
    @Override
    public List<Map<String, Object>> getAllProfit_Loss() {
        return jdbcTemplate.queryForList("SELECT * FROM " + TABLE_NAME);
    }

    @Override
    public List<ProfitLossSummaryDTO> getProfitLossSummary() {
        return profitlossRepository.getProfitLossSummary();
    }

    @Override
    public List<ProfitLossSummaryDTO> getProfitSummaryBranchWise(List<String> cities) {
        return profitlossRepository.getProfitLossSummaryByCityBranch(cities);
    }

    @Override
    public void deleteProfitLossAll() {
        profitlossRepository.deleteProfitLossAll();
    }
}
