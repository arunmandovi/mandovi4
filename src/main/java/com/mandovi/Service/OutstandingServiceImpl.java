package com.mandovi.Service;

import com.mandovi.Entity.Outstanding;
import com.mandovi.Repository.OutstandingRepository;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OutstandingServiceImpl implements OutstandingService {

    private static final Logger log =
            LoggerFactory.getLogger(OutstandingServiceImpl.class);

    private final OutstandingRepository outstandingRepository;

    // ============================
    // INVALID MASTER DATA
    // ============================

    private static final Set<String> INVALID_LEDGER_GROUPS = Set.of(
            "company s/r & b/r bill",
            "debtors control account",
            "free service customer",
            "receivable from msil",
            "receivable from mul control a/c",
            "showrom",
            "sundry debtors - fsc",
            "sundry debtors - insurance",
            "sundry debtors - legacy",
            "sundry debtors - spares",
            "sundry debtors - w.g.spares"
    );

    private static final Set<String> INVALID_PARTY_NAMES =
            Stream.of(
                            "ABDUL RAHMAN RASHEED",
                            "APPANNA M S (KA12Z4118)",
                            "HARIPRASAD K.S - I105164277 - KA21M9721",
                            "RANJITH N M KA19ME3761",
                            "M PRAKASH HEGDE - 2249418425 - KA19MN0577",
                            "UNIVERSAL SOMPO GIC LTD - 22-35 - KA19MC9116",
                            "VIJAYA K - 2039090839 - KA21Z3306",
                            "NEELAPPA GOWDA (KA21P9296)",
                            "SHEETHAL SHETTY S KA19ML0270",
                            "NATIONAL INSURANCE COMPANY LIMITED - 01-295 - KA21Z3306",
                            "SHASHI A AMIN - 1829321489 - KA19MJ9555",
                            "FATHIMATH SAFIKA - 1831587144 - KA21P9362",
                            "JOHNSON ANTONY RAJ - 1518032731 - KA19MF9391",
                            "K MOHAN KAMATH - 1829105820 - KA19MJ3161",
                            "K P BOJANNA - 1621292104 - KA19MG3440",
                            "LIJUKUMAR - 1724588196 - KL24N8080",
                            "MERLIN MASCARENHAS - 1726373034 - KA19P1982",
                            "NEETHASHREE - 2355129051 - KA19MP3154",
                            "ROYAL SUNDARAM GENERAL INSURANCE CO.   LIMITED - 03-11 - KA21N8996",
                            "SBI GENERAL INSURANCE CO LTD - 25-74 - KA19MM4251",
                            "SHAHID HUSSAIN(KA03MY7368)",
                            "JOSEPH K F KA21Z4650",
                            "AMITHA DHANANJAYA - 2249862266 - KA19MN2566"
                    )
                    .map(s -> s.trim().toLowerCase())
                    .collect(Collectors.toUnmodifiableSet());

    public OutstandingServiceImpl(OutstandingRepository outstandingRepository) {
        this.outstandingRepository = outstandingRepository;
    }

    // ============================
    // EXCEL UPLOAD
    // ============================
    @Override
    @Transactional
    public void saveOutstandingFromExcel(MultipartFile file) {

        outstandingRepository.deleteOutstandingAll();

        try (InputStream inputStream = file.getInputStream()) {

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                final int rowNumber = i + 1;
                Row row = sheet.getRow(i);

                parseRow(row, rowNumber)
                        .filter(this::passesBusinessFilters)
                        .ifPresent(outstandingRepository::save);
            }

            consolidateOutstandingData();

            // Remove negative and zero balances
            outstandingRepository.deleteByBalanceAmtLessThanEqual(0.0);

        } catch (Exception e) {
            throw new RuntimeException("Excel upload failed", e);
        }
    }

    // ============================
    // FILTER RULES
    // ============================
    private boolean passesBusinessFilters(Outstanding o) {

        String ledger = normalize(o.getLedgerGroupName());
        String party = normalize(o.getPartyName());
        String billNo = normalize(o.getBillNo());
        String segment = normalize(o.getSegment());

        if (INVALID_LEDGER_GROUPS.contains(ledger)) return false;

        if (INVALID_PARTY_NAMES.contains(party)) {
            log.debug("Filtered invalid party: {}", o.getPartyName());
            return false;
        }

        if (party.contains("msil - (extended warranty claim recoverable)")) return false;

        if (billNo.contains("rs") || billNo.contains("op")
                || billNo.contains("csi") || billNo.contains("tv")) return false;

        if (billNo.contains("mcp")) {
            if (o.getOutstandingDate() == null ||
                    o.getOutstandingDate().getYear() != 2025) return false;
        }

        if ((billNo.contains("ad") || billNo.contains("ew")) &&
                (segment.contains("naravi") || segment.contains("kadaba"))) return false;

        return true;
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }

    // ============================
    // ROW PARSER
    // ============================
    private Optional<Outstanding> parseRow(Row row, int rowNumber) {

        try {
            if (row == null) return Optional.empty();

            Outstanding o = new Outstanding();
            o.setSegment(getString(row, 0));
            o.setLedgerGroupName(getString(row, 1));
            o.setPartyName(getString(row, 2));
            o.setOutstandingDate(getDate(row, 3));
            o.setBillNo(getString(row, 4));

            // ONLY THESE 3 ARE DOUBLE
            o.setBillAmt(getDouble(row, 5));
            o.setPaidAmt(getDouble(row, 6));
            o.setBalanceAmt(getDouble(row, 7));

            // REMAIN INTEGER
            o.setDueSince(getInt(row, 8));
            o.setUpToSeven(getInt(row, 9));
            o.setEightToThirty(getInt(row, 10));
            o.setThirtyOneToNinty(getInt(row, 11));
            o.setMoreThanNinty(getInt(row, 12));

            o.setSalesMan(getString(row, 13));

            return Optional.of(o);

        } catch (Exception e) {
            log.warn("Row {} skipped due to parsing error", rowNumber, e);
            return Optional.empty();
        }
    }

    private String getString(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) return null;

        return cell.getCellType() == CellType.STRING
                ? cell.getStringCellValue().trim()
                : String.valueOf((long) cell.getNumericCellValue());
    }

    private int getInt(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) return 0;

        try {
            return cell.getCellType() == CellType.NUMERIC
                    ? (int) cell.getNumericCellValue()
                    : Integer.parseInt(cell.getStringCellValue().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private Double getDouble(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) return 0.0;

        try {
            return cell.getCellType() == CellType.NUMERIC
                    ? cell.getNumericCellValue()
                    : Double.parseDouble(cell.getStringCellValue().trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    private LocalDate getDate(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getDateCellValue()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        try {
            return LocalDate.parse(cell.getStringCellValue().trim());
        } catch (Exception e) {
            return null;
        }
    }

    // ============================
    // CONSOLIDATION LOGIC
    // ============================
    @Transactional
    public void consolidateOutstandingData() {

        List<Outstanding> allRows = outstandingRepository.findAll();

        Map<String, List<Outstanding>> groupedByParty =
                allRows.stream()
                        .filter(o -> o.getPartyName() != null)
                        .collect(Collectors.groupingBy(Outstanding::getPartyName));

        for (List<Outstanding> partyRows : groupedByParty.values()) {

            partyRows.sort(Comparator.comparing(
                    Outstanding::getOutstandingDate,
                    Comparator.nullsLast(Comparator.naturalOrder())
            ));

            boolean[] processed = new boolean[partyRows.size()];

            for (int i = 0; i < partyRows.size(); i++) {

                if (processed[i]) continue;

                Outstanding base = partyRows.get(i);
                List<Outstanding> group = new ArrayList<>();
                group.add(base);
                processed[i] = true;

                for (int j = i + 1; j < partyRows.size(); j++) {

                    if (processed[j]) continue;
                    Outstanding next = partyRows.get(j);

                    if (!Objects.equals(base.getSegment(), next.getSegment())) continue;
                    if (base.getOutstandingDate() == null || next.getOutstandingDate() == null)
                        continue;

                    long daysDiff = Math.abs(
                            ChronoUnit.DAYS.between(
                                    base.getOutstandingDate(),
                                    next.getOutstandingDate()
                            )
                    );

                    if (daysDiff <= 7) {
                        group.add(next);
                        processed[j] = true;
                    }
                }

                if (group.size() > 1) {
                    consolidateAndReplace(group);
                }
            }
        }
    }

    // ============================
    // FINAL CONSOLIDATION RULES
    // ============================
    private void consolidateAndReplace(List<Outstanding> rows) {

        List<Integer> zeroIds = rows.stream()
                .filter(r -> r.getBalanceAmt() != null && r.getBalanceAmt() == 0.0)
                .map(Outstanding::getOutstandingSINo)
                .filter(Objects::nonNull)
                .toList();

        if (!zeroIds.isEmpty()) {
            outstandingRepository.deleteAllById(zeroIds);
            rows.removeIf(r -> r.getBalanceAmt() != null && r.getBalanceAmt() == 0.0);
        }

        if (rows.isEmpty()) return;

        boolean allPositive =
                rows.stream().allMatch(r -> r.getBalanceAmt() != null && r.getBalanceAmt() > 0.0);

        if (allPositive) return;

        double finalBalance = rows.stream()
                .mapToDouble(r -> r.getBalanceAmt() == null ? 0.0 : r.getBalanceAmt())
                .sum();

        if (finalBalance == 0.0) {
            List<Integer> ids = rows.stream()
                    .map(Outstanding::getOutstandingSINo)
                    .filter(Objects::nonNull)
                    .toList();

            outstandingRepository.deleteAllById(ids);
            return;
        }

        Outstanding latest = rows.stream()
                .max(Comparator.comparing(Outstanding::getOutstandingDate))
                .orElseThrow();

        Outstanding consolidated = new Outstanding();
        consolidated.setPartyName(latest.getPartyName());
        consolidated.setSegment(latest.getSegment());
        consolidated.setLedgerGroupName(latest.getLedgerGroupName());
        consolidated.setOutstandingDate(latest.getOutstandingDate());
        consolidated.setBillNo(latest.getBillNo());
        consolidated.setBalanceAmt(finalBalance);
        consolidated.setSalesMan(latest.getSalesMan());

        List<Integer> ids = rows.stream()
                .map(Outstanding::getOutstandingSINo)
                .filter(Objects::nonNull)
                .toList();

        outstandingRepository.deleteAllById(ids);
        outstandingRepository.save(consolidated);
    }
}
