package com.mandovi.Service;

import com.mandovi.DTO.ProfitLossSummaryDTO;
import com.mandovi.Repository.ProfitLossRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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

    @Override
    public void saveProfitLossExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row headerRow = sheet.getRow(0);
            List<String> originalColumns = new ArrayList<>();

            DataFormatter formatter = new DataFormatter();

            for (Cell cell : headerRow) {
                String headerValue = formatter.formatCellValue(cell).trim();
                originalColumns.add(headerValue);
            }

            // 2️⃣ Build column index lookup
            Map<String, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < originalColumns.size(); i++) {
                indexMap.put(originalColumns.get(i).toLowerCase(), i);
            }

            if (!indexMap.containsKey("city") || !indexMap.containsKey("branch")) {
                throw new RuntimeException("❌ Excel must contain 'City' and 'Branch' columns.");
            }

            int cityIndex = indexMap.get("city");
            int branchIndex = indexMap.get("branch");

            // 3️⃣ Normalize dynamic columns (skip city & branch)
            Map<String, String> originalToNormalized = new HashMap<>();
            List<String> normalizedColumns = new ArrayList<>();

            for (String col : originalColumns) {
                if (col.equalsIgnoreCase("city") || col.equalsIgnoreCase("branch")) continue;

                String normalized = normalizeColumn(col);
                originalToNormalized.put(col.toLowerCase(), normalized);
                normalizedColumns.add(normalized);
            }

            // 4️⃣ Detect SQL column types using row 1
            Map<String, String> colTypes = detectColTypes(sheet, originalColumns, originalToNormalized);

            // 5️⃣ Prepare database
            createTableIfNotExists();
            dropAllColumnsExceptCityBranch();
            addMissingColumns(normalizedColumns, colTypes);

            profitlossRepository.deleteProfitLossAll();

            // 6️⃣ Process each row
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {

                Row row = sheet.getRow(r);
                if (row == null) continue;

                String city = Objects.toString(getCellValue(row.getCell(cityIndex)), "");
                String branch = Objects.toString(getCellValue(row.getCell(branchIndex)), "");

                Map<String, Object> rowMap = new LinkedHashMap<>();
                rowMap.put("city", city);
                rowMap.put("branch", branch);

                // Correct mapping: original → normalized → correct index
                for (String original : originalColumns) {

                    if (original.equalsIgnoreCase("city") || original.equalsIgnoreCase("branch"))
                        continue;

                    String normalized = originalToNormalized.get(original.toLowerCase());
                    int idx = indexMap.get(original.toLowerCase());

                    Object val = getCellValue(row.getCell(idx));

                    rowMap.put(normalized, val);
                }

                upsertRow(rowMap);
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Error processing Profit Loss Excel: " + e.getMessage(), e);
        }
    }

    // ===========================================================
    // NORMALIZE COLUMN NAME
    // ===========================================================
    private String normalizeColumn(String col) {
        col = col.trim().toLowerCase();
        col = col.replaceAll("[\\s-]+", "_");
        return col;
    }

    // ===========================================================
    // UPSERT ROW
    // ===========================================================
    private void upsertRow(Map<String, Object> dataMap) {

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

    // ===========================================================
    // READ CELL VALUE
    // ===========================================================
    private Object getCellValue(Cell cell) {
        if (cell == null) return null;

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> cell.getNumericCellValue();
            default -> null;
        };
    }

    // ===========================================================
    // DETECT SQL TYPES
    // ===========================================================
    private Map<String, String> detectColTypes(
            Sheet sheet,
            List<String> originalCols,
            Map<String, String> origToNorm
    ) {
        Map<String, String> types = new HashMap<>();
        Row row = sheet.getRow(1);

        for (String col : originalCols) {

            if (col.equalsIgnoreCase("city") || col.equalsIgnoreCase("branch"))
                continue;

            Cell cell = (row != null)
                    ? row.getCell(originalCols.indexOf(col))
                    : null;

            String normalized = origToNorm.get(col.toLowerCase());

            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                types.put(normalized, "DOUBLE");
            } else {
                types.put(normalized, "VARCHAR(255)");
            }
        }

        return types;
    }

    // ===========================================================
    // TABLE CREATION
    // ===========================================================
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

    // ===========================================================
    // DROP OLD DYNAMIC COLUMNS
    // ===========================================================
    private void dropAllColumnsExceptCityBranch() {

        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=? AND TABLE_NAME=?";
        List<String> cols = jdbcTemplate.queryForList(sql, new Object[]{DATABASE, TABLE_NAME}, String.class);

        for (String col : cols) {
            if (!col.equalsIgnoreCase("city")
                    && !col.equalsIgnoreCase("branch")
                    && !col.equalsIgnoreCase("profit_lossSINo")) {

                String drop = "ALTER TABLE `" + DATABASE + "`.`" + TABLE_NAME + "` DROP COLUMN `" + col + "`";
                jdbcTemplate.execute(drop);
            }
        }
    }

    // ===========================================================
    // ADD NEW COLUMNS
    // ===========================================================
    private void addMissingColumns(List<String> columns, Map<String, String> colTypes) {

        String sql =
                "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS " +
                        "WHERE TABLE_SCHEMA=? AND TABLE_NAME=?";

        List<String> existing =
                jdbcTemplate.queryForList(sql, new Object[]{DATABASE, TABLE_NAME}, String.class);

        for (String col : columns) {
            if (!existing.contains(col)) {

                String type = colTypes.getOrDefault(col, "VARCHAR(255)");

                String alter = "ALTER TABLE `" + DATABASE + "`.`" + TABLE_NAME +
                        "` ADD COLUMN `" + col + "` " + type;

                jdbcTemplate.execute(alter);
            }
        }
    }

    // ===========================================================
    // OTHER ENDPOINTS
    // ===========================================================
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
