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

            // Get header row
            Row headerRow = sheet.getRow(0);
            List<String> columns = new ArrayList<>();
            for (Cell cell : headerRow) {
                String rawName = cell.getStringCellValue().trim().replace(" ", "_");
                // Convert date-style column names (like 01-05-2025 → May_25)
                String formattedName = formatColumnNameIfDate(rawName);
                columns.add(formattedName);
            }

            // Ensure city and branch exist
            if (!columns.contains("city") || !columns.contains("branch")) {
                throw new RuntimeException("Profit and Loss Excel file must have 'city' and 'branch' columns.");
            }

            // Detect column types
            Map<String, String> colTypes = detectColTypes(sheet, columns);

            // Create table if not exists
            createTableIfNotExists(columns);

            // Drop all existing columns except city and branch
            dropAllColumnsExceptCityBranch();

            // Add columns from new file
            addMissingColumns(columns, colTypes);

            // Insert or update data
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String city = Objects.toString(getCellValueAsObject(row.getCell(columns.indexOf("city"))), "");
                String branch = Objects.toString(getCellValueAsObject(row.getCell(columns.indexOf("branch"))), "");

                Map<String, Object> dataMap = new LinkedHashMap<>();
                for (int j = 0; j < columns.size(); j++) {
                    String colName = columns.get(j);
                    Object value = getCellValueAsObject(row.getCell(j));
                    dataMap.put(colName, value);
                }

                upsertRow(city, branch, dataMap);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> getAllProfit_Loss() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Converts strings like "01-05-2025" → "May_25"
     */
    private String formatColumnNameIfDate(String rawName) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = inputFormat.parse(rawName);
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM_yy", Locale.ENGLISH);
            return outputFormat.format(date);
        } catch (ParseException e) {
            // Not a date, return as-is
            return rawName;
        }
    }

    /**
     * Delete all columns from profit_loss table except 'city' and 'branch'
     */
    private void dropAllColumnsExceptCityBranch() {
        String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        List<String> existingCols = jdbcTemplate.queryForList(query, new Object[]{DATABASE, TABLE_NAME}, String.class);

        for (String col : existingCols) {
            if (!col.equalsIgnoreCase("city") && !col.equalsIgnoreCase("branch")
                    && !col.equalsIgnoreCase("profit_lossSINo")) {
                String dropSql = "ALTER TABLE `" + DATABASE + "`.`" + TABLE_NAME + "` DROP COLUMN `" + col + "`";
                jdbcTemplate.execute(dropSql);
            }
        }
    }

    private void upsertRow(String city, String branch, Map<String, Object> dataMap) {
        dataMap.put("city", city);
        dataMap.put("branch", branch);

        StringBuilder sql = new StringBuilder("INSERT INTO " + TABLE_NAME + " (");
        StringBuilder placeholder = new StringBuilder(" VALUES (");
        StringBuilder updateSql = new StringBuilder(" ON DUPLICATE KEY UPDATE ");

        List<Object> values = new ArrayList<>();

        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            sql.append("`").append(entry.getKey()).append("`,");
            placeholder.append("?,");
            updateSql.append("`").append(entry.getKey()).append("`=VALUES(`").append(entry.getKey()).append("`),");
            values.add(entry.getValue());
        }

        sql.setLength(sql.length() - 1);
        placeholder.setLength(placeholder.length() - 1);
        updateSql.setLength(updateSql.length() - 1);

        sql.append(")");
        sql.append(placeholder).append(")");
        sql.append(updateSql);

        jdbcTemplate.update(sql.toString(), values.toArray());
    }

    private Object getCellValueAsObject(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return cell.getNumericCellValue();
            default:
                return null;
        }
    }

    private void addMissingColumns(List<String> columns, Map<String, String> colTypes) {
        String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        List<String> existingCols = jdbcTemplate.queryForList(query, new Object[]{DATABASE, TABLE_NAME}, String.class);

        for (String col : columns) {
            if (!existingCols.contains(col)) {
                if (!col.equalsIgnoreCase("city") && !col.equalsIgnoreCase("branch")) {
                    String colType = colTypes.getOrDefault(col, "VARCHAR(255)");
                    String alterSql = "ALTER TABLE `" + DATABASE + "`.`" + TABLE_NAME + "` ADD COLUMN `" + col + "` " + colType;
                    jdbcTemplate.execute(alterSql);
                }
            }
        }
    }

    private Map<String, String> detectColTypes(Sheet sheet, List<String> columns) {
        Map<String, String> colTypes = new HashMap<>();
        Row sampleRow = sheet.getRow(1);

        for (int j = 0; j < columns.size(); j++) {
            String colName = columns.get(j);
            if (colName.equalsIgnoreCase("city") || colName.equalsIgnoreCase("branch")) {
                colTypes.put(colName, "VARCHAR(100)");
                continue;
            }
            Cell cell = (sampleRow != null) ? sampleRow.getCell(j) : null;
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                colTypes.put(colName, "DOUBLE");
            } else {
                colTypes.put(colName, "VARCHAR(100)");
            }
        }
        return colTypes;
    }

    private void createTableIfNotExists(List<String> columns) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "profit_lossSINo INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "city VARCHAR(450), branch VARCHAR(450), " +
                "UNIQUE KEY uq_city_branch (city, branch))";
        jdbcTemplate.execute(sql);
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
