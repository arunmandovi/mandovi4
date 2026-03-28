package com.mandovi.Service;

import com.mandovi.DTO.*;
import com.mandovi.Entity.InsuranceDifference;
import com.mandovi.Entity.Outstanding;
import com.mandovi.Repository.InsuranceDifferenceRepository;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OutstandingServiceImpl implements OutstandingService {

    private static final Logger log =
            LoggerFactory.getLogger(OutstandingServiceImpl.class);

    private final OutstandingRepository outstandingRepository;
    private final InsuranceDifferenceRepository insuranceDifferenceRepository;

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
            "sundry debtors - w.g.spares"
    );

    private static final Map<String, Set<Double>> INVALID_PARTY_BALANCE_MAP =
            Map.ofEntries(
                    Map.entry("hariprasad k.s - i105164277 - ka21m9721", Set.of(1970.0)),
                    Map.entry("danial john tauro - 1621454166", Set.of(354.0)),
                    Map.entry("director - i118277515 - ka19mc4335", Set.of(797.0)),
                    Map.entry("fathimath safika - 1831587144 - ka21p9362", Set.of(90.0)),
                    Map.entry("fisher  paykel healthcare india pvt ltd - 1621907044 - ka50p6247", Set.of(600.0)),
                    Map.entry("johnson antony raj - 1518032731 - ka19mf9391", Set.of(30.0)),
                    Map.entry("k mohan kamath - 1829105820 - ka19mj3161", Set.of(1327.0)),
                    Map.entry("k p bojanna - 1621292104 - ka19mg3440", Set.of(164.0)),
                    Map.entry("lijukumar - 1724588196 - kl24n8080", Set.of(772.0)),
                    Map.entry("m prakash hegde - 2249418425 - ka19mn0577", Set.of(1217.0)),
                    Map.entry("mandovi true value - 2140997773", Set.of(82.0)),
                    Map.entry("merlin mascarenhas - 1726373034 - ka19p1982", Set.of(29.0)),
                    Map.entry("mohammed chayabba abdul majee - 2456826699", Set.of(826.0)),
                    Map.entry("national insurance company limited - 01-295 - ka19mh4249", Set.of(312.0)),
                    Map.entry("national insurance company limited - 01-295 - ka21z3306", Set.of(1000.0)),
                    Map.entry("neethashree - 2355129051 - ka19mp3154", Set.of(442.0)),
                    Map.entry("royal sundaram general insurance co.  limited - 03-11 - ka21n8996", Set.of(3778.0)),
                    Map.entry("sanjiven - 1312108549 - tn74m8739", Set.of(100.0)),
                    Map.entry("sbi general insurance co ltd - 25-74 - ka19mm4251", Set.of(1941.93)),
                    Map.entry("shashi a amin - 1829321489 - ka19mj9555", Set.of(123.0)),
                    Map.entry("sudheesh - 2565208978", Set.of(590.0)),
                    Map.entry("the new india assurance co ltd - 04-115 - kl60h321", Set.of(0.3)),
                    Map.entry("universal sompo gic ltd - 22-35 - ka19mc9116", Set.of(1000.0)),
                    Map.entry("vijaya k - 2039090839 - ka21z3306", Set.of(999.0)),
                    Map.entry("neelappa gowda (ka21p9296)", Set.of(665.0)),
                    Map.entry("shahid hussain(ka03my7368)", Set.of(611.0)),
                    Map.entry("ranjith n m ka19me3761", Set.of(100.0)),
                    Map.entry("abdul rahman rasheed", Set.of(1000.0)),
                    Map.entry("appanna m s (ka12z4118)", Set.of(1655.0)),
                    Map.entry("joseph k f ka21z4650", Set.of(1000.0)),
                    Map.entry("reliance general insurance company limited - 11-09 - ka21ma2202", Set.of(497.0)),
                    Map.entry("sbi general insurance co ltd - 25-74 - ka12mc1350", Set.of(-327.47)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka21p2495", Set.of(924.0)),
                    Map.entry("universal sompo gic ltd - 22-35 - ka19mp2249", Set.of(-1426.0)),
                    Map.entry("sourabh y jain - 1934183546 - ka19mk3678", Set.of(-715.0)),
                    Map.entry("bajaj general insurance limited - 02-02 - ka21ma6193", Set.of(-934.0)),
                    Map.entry("kushalraj - 2353756490 - ka27n8942", Set.of(204.0)),
                    Map.entry("universal sompo gic ltd - 22-35 - ka21p4026", Set.of(610.0))
            );

    public OutstandingServiceImpl(OutstandingRepository outstandingRepository, InsuranceDifferenceRepository insuranceDifferenceRepository) {
        this.outstandingRepository = outstandingRepository;
        this.insuranceDifferenceRepository = insuranceDifferenceRepository;
    }

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

            insuranceDifferenceRepository.deleteInsuranceDifferenceAll();
            insuranceDifferenceRepository.insertInsuranceDifferenceFromOutstanding();

        } catch (Exception e) {
            throw new RuntimeException("Excel upload failed", e);
        }
    }

    @Override
    public List<Outstanding> getOutstandingAll() {
        return outstandingRepository.findAll();
    }

    @Override
    public List<Outstanding> getDifferentOutstanding(List<String> types) {

        if (types == null || types.isEmpty()) {
            return outstandingRepository.findAll();
        }

        List<Outstanding> result = new ArrayList<>();

        for (String type : types) {
            switch (type.toUpperCase().trim()) {
                case "CASH" -> result.addAll(outstandingRepository.getCashOutstandingSAWise());
                case "INVOICE" -> result.addAll(outstandingRepository.getInvoiceOutstanding());
                case "INSURANCE" -> result.addAll(outstandingRepository.getInsuranceOutstanding());
                case "OTHERS" -> result.addAll(outstandingRepository.getOthersOutstandingSAWise());
                default -> throw new RuntimeException("Invalid type: " + type);
            }
        }

        return result;
    }

    @Override
    public void deleteOutstandingAll() {
        outstandingRepository.deleteOutstandingAll();
    }

    @Override
    public List<TotalOutstandingDTO> getTotalOutstandingSAWise(List<String> segments, List<String> salesMans) {
        return outstandingRepository.getTotalOutstandingSAWise(segments, salesMans);
    }

    @Override
    public List<TotalOutstandingDTO> getTotalOutstandingPartyWise(List<String> segments, List<String> salesMans, String party) {
        return outstandingRepository.getTotalOutstandingPartyNameWise(segments, salesMans, party );
    }

    @Override
    public List<TotalOutstandingDTO> getCashOutstandingBranchWise(List<String> segments) {
        return outstandingRepository.getCashOutstandingBranchWise(segments);
    }

    @Override
    public List<TotalOutstandingDTO> getTotalOutstandingBranchWise(List<String> segments) {
        return outstandingRepository.getTotalOutstandingBranchWise(segments);
    }

    @Override
    public void deleteInsuranceDifferenceAll() {
        insuranceDifferenceRepository.deleteInsuranceDifferenceAll();
    }

    @Override
    public List<TotalOutstandingDTO> getCashOutstandingSAWise(List<String> segments, List<String> salesMans) {
        return outstandingRepository.getCashOutstandingSAWise(segments, salesMans);
    }

    @Override
    public List<TotalOutstandingDTO> getCashOutstandingPartyWise(List<String> segments, List<String> salesMans, String party) {
        return outstandingRepository.getCashOutstandingPartyWise(segments, salesMans,party );
    }

    @Override
    public List<TotalOutstandingDTO> getInvoiceOutstandingBranchWise(List<String> segments) {
        return outstandingRepository.getInvoiceOutstandingBranchWise(segments);
    }

    @Override
    public List<TotalOutstandingDTO> getInvoiceOutstandingSAWise(List<String> segments, List<String> salesMans) {
        return outstandingRepository.getInvoiceOutstandingSAWise(segments, salesMans);
    }

    @Override
    public List<TotalOutstandingDTO> getInvoiceOutstandingPartyWise(List<String> segments, List<String> salesMans, String party) {
        return outstandingRepository.getInvoiceOutstandingPartyWise(segments, salesMans, party);
    }

    @Override
    public List<TotalOutstandingDTO> getOthersOutstandingBranchWise(List<String> segments) {
        return outstandingRepository.getOthersOutstandingBranchWise(segments);
    }

    @Override
    public List<TotalOutstandingDTO> getOthersOutstandingSAWise(List<String> segments, List<String> salesMans) {
        return outstandingRepository.getOthersOutstandingSAWise(segments, salesMans);
    }

    @Override
    public List<TotalOutstandingDTO> getOthersOutstandingPartyWise(List<String> segments, List<String> salesMans, String party) {
        return outstandingRepository.getOthersOutstandingPartyWise(segments, salesMans, party);
    }

    @Override
    public List<InsuranceDifference> getAllInsuranceDifference() {
        return insuranceDifferenceRepository.findAll();
    }

    @Override
    public List<IDOutstandingDTO> getIDOutstandingBranchWise(List<String> segments) {
        return outstandingRepository.getIDOutstandingBranchWise(segments);
    }

    @Override
    public List<IDOutstandingDTO> getIDOutstandingSAWise(List<String> segments, List<String> insuranceParties) {
        return outstandingRepository.getIDOutstandingSAWise(segments, insuranceParties);
    }

    @Override
    public List<IDOutstandingDTO> getIDOutstandingPartyWise(List<String> segments, List<String> insuranceParties, String party) {
        return outstandingRepository.getIDOutstandingPartyWise(segments, insuranceParties, party);
    }

    @Override
    public List<TotalOutstandingDTO> getCustomerCollectOutstandingBranchWise(List<String> segments) {
        return outstandingRepository.getCustomerCollectOutstandingBranchWise(segments);
    }

    @Override
    public List<TotalOutstandingDTO> getCustomerCollectOutstandingSAWise(List<String> segments, List<String> salesMans) {
        return outstandingRepository.getCustomerCollectOutstandingSAWise(segments, salesMans);
    }

    @Override
    public List<TotalOutstandingDTO> getCustomerCollectOutstandingPartyWise(List<String> segments, List<String> salesMans, String party) {
        return outstandingRepository.getCustomerCollectOutstandingPartyWise(segments, salesMans, party);
    }

    private boolean passesBusinessFilters(Outstanding o) {

        String ledger = normalize(o.getLedgerGroupName());
        String party = normalize(o.getPartyName());
        String billNo = normalize(o.getBillNo());
        String segment = normalize(o.getSegment());

        if (INVALID_LEDGER_GROUPS.contains(ledger)) return false;

        Double balance = o.getBalanceAmt();

        if (party != null
                && balance != null
                && INVALID_PARTY_BALANCE_MAP.containsKey(party)
                && INVALID_PARTY_BALANCE_MAP.get(party).contains(balance)) {

            log.debug("Filtered party with matching balance: {}, balance={}",
                    o.getPartyName(), balance);
            return false;
        }

        if (party.contains("msil - (extended warranty claim recoverable)")) return false;

        if ( billNo.contains("op") || billNo.contains("tv")) return false;

        if (billNo.contains("mcp")) {

            if (o.getOutstandingDate() == null) return false;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(o.getOutstandingDate(), formatter);

                if (date.getYear() != 2026) return false;

            } catch (Exception e) {
                return false;
            }
        }

        if ((billNo.contains("ew") || billNo.contains("ad")) &&
                (segment.contains("naravi") || segment.contains("kadaba"))) {
            if (billNo.contains("ew")) {
                return false;
            }
        }

        if (billNo.contains("ad") &&
                (segment.contains("kadaba") || segment.contains("naravi")) &&
                o.getBalanceAmt() != null && o.getBalanceAmt() > 1000) {
            return false;
        }

        return true;
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }

    private Optional<Outstanding> parseRow(Row row, int rowNumber) {

        try {
            if (row == null) return Optional.empty();

            Outstanding o = new Outstanding();

            String segmentBranch = getString(row, 0);
            String segment = segmentBranch.split("\\s+")[0];
            o.setSegment(segment);

            o.setLedgerGroupName(getString(row, 1));

            String party = getString(row, 2);
            LocalDate outstandingDate = getDate(row, 3);
            String billNo = getString(row, 4);

            if (billNo != null) {
                String normalizedBill = billNo.trim();
                if (normalizedBill.matches("(?i).*/(rs|csi)/.*")) {
                    o.setSegment("Spares");
                }
            }

            if (billNo != null && billNo.trim().toUpperCase().contains("BI")) {

                Map<String, String> companyMap = Map.of(
                        "GO DIGIT", "GO DIGIT",
                        "INDUSIND", "INDUSIND",
                        "TATA AIG", "TATA AIG",
                        "THE NEW INDIA", "NEW INDIA",
                        "THE ORIENTAL", "ORIENTAL",
                        "GENERALI CENTRAL", "GENERALI CENTRAL"
                );

                String partyUpper = party == null ? "" : party.toUpperCase();

                party = companyMap.entrySet().stream()
                        .filter(e -> partyUpper.contains(e.getKey()))
                        .map(Map.Entry::getValue)
                        .findFirst()
                        .orElse(partyUpper.split("\\s+")[0].trim());
            }

            o.setPartyName(getString(row,2));


            String formattedDate = outstandingDate == null
                    ? null
                    : outstandingDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            o.setOutstandingDate(formattedDate);
            o.setBillNo(billNo);

            o.setBillAmt(getDouble(row, 5));
            o.setPaidAmt(getDouble(row, 6));
            o.setBalanceAmt(getDouble(row, 7));

            o.setDueSince(getInt(row, 8));
            o.setUpToSeven(getDouble(row, 9));
            o.setEightToThirty(getDouble(row, 10));
            o.setThirtyOneToNinty(getDouble(row, 11));
            o.setMoreThanNinty(getDouble(row, 12));

            String salesMan = row.getCell(13).getStringCellValue();

            if (salesMan != null) {

                if (salesMan.contains("_1")) {
                    salesMan = salesMan.substring(0, salesMan.indexOf("_1"));
                }

                if (salesMan.contains("-")) {
                    salesMan = salesMan.substring(salesMan.indexOf("-") + 1).trim();
                }

                String[] words = salesMan.split("\\s+");

                if (words.length > 0) {

                    if (words[0].startsWith("MM")) {
                        words[0] = words[0].substring(2);
                    }

                    int startIndex = 0;
                    if (words[0].matches("\\d+")) {
                        startIndex = 1;
                    }

                    StringBuilder sb = new StringBuilder();
                    for (int i = startIndex; i < words.length; i++) {
                        sb.append(words[i]).append(" ");
                    }

                    salesMan = sb.toString().trim();
                }
            }

            o.setSalesMan(salesMan);

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

    @Transactional
    public void consolidateOutstandingData() {

        List<Outstanding> allRows = outstandingRepository.findAll();

        Map<String, List<Outstanding>> groupedByParty =
                allRows.stream()
                        .filter(o -> o.getPartyName() != null)
                        .collect(Collectors.groupingBy(Outstanding::getPartyName));

//        for (List<Outstanding> partyRows : groupedByParty.values()) {
//
//            partyRows.sort(Comparator.comparing(
//                    Outstanding::getOutstandingDate,
//                    Comparator.nullsLast(Comparator.naturalOrder())
//            ));
//
//            boolean[] processed = new boolean[partyRows.size()];
//
//            for (int i = 0; i < partyRows.size(); i++) {
//
//                if (processed[i]) continue;
//
//                Outstanding base = partyRows.get(i);
//                List<Outstanding> group = new ArrayList<>();
//                group.add(base);
//                processed[i] = true;
//
//                for (int j = i + 1; j < partyRows.size(); j++) {
//
//                    if (processed[j]) continue;
//                    Outstanding next = partyRows.get(j);
//
//                    if (!Objects.equals(base.getSegment(), next.getSegment())) continue;
//                    if (base.getOutstandingDate() == null || next.getOutstandingDate() == null)
//                        continue;
//
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//                    if (base.getOutstandingDate() == null || next.getOutstandingDate() == null)
//                        continue;
//
//                    try {
//                        LocalDate baseDate = LocalDate.parse(base.getOutstandingDate(), formatter);
//                        LocalDate nextDate = LocalDate.parse(next.getOutstandingDate(), formatter);
//
//                        long daysDiff = Math.abs(ChronoUnit.DAYS.between(baseDate, nextDate));
//
//                        if (daysDiff <= 62) {
//                            group.add(next);
//                            processed[j] = true;
//                        }
//
//                    } catch (Exception e) {
//                    }
//                }
//
//                if (group.size() > 1) {
//                    consolidateAndReplace(group);
//                }
//            }
//        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (List<Outstanding> partyRows : groupedByParty.values()) {

            // 🔹 Step 1: Calculate net balance (including negatives)
            double netBalance = partyRows.stream()
                    .mapToDouble(o -> o.getBalanceAmt() == null ? 0.0 : o.getBalanceAmt())
                    .sum();

            // 🔹 Step 2: Delete all rows if net = 0
            List<Integer> ids = partyRows.stream()
                    .map(Outstanding::getOutstandingSINo)
                    .filter(Objects::nonNull)
                    .toList();

            if (netBalance == 0.0) {
                outstandingRepository.deleteAllById(ids);
                continue;
            }

            Outstanding latest = partyRows.get(0);

            // 🔹 Step 4: Create consolidated row
            Outstanding consolidated = new Outstanding();

            consolidated.setPartyName(latest.getPartyName());
            consolidated.setSegment(latest.getSegment());
            consolidated.setLedgerGroupName(latest.getLedgerGroupName());
            consolidated.setOutstandingDate(latest.getOutstandingDate());
            consolidated.setBillNo(latest.getBillNo()); // latest bill
            consolidated.setBalanceAmt(netBalance);

            // Optional: reset ageing buckets
            consolidated.setUpToSeven(0.0);
            consolidated.setEightToThirty(0.0);
            consolidated.setThirtyOneToNinty(0.0);
            consolidated.setMoreThanNinty(0.0);

            consolidated.setSalesMan(latest.getSalesMan());

            // 🔹 Step 5: Delete old rows
            outstandingRepository.deleteAllById(ids);

            // 🔹 Step 6: Insert single row
            outstandingRepository.save(consolidated);
        }
    }

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

        double finalBalance = 0.0;
        double finalUpToSeven = 0.0;
        double finalEightToThirty = 0.0;
        double finalThirtyOneToNinty = 0.0;
        double finalMoreThanNinty = 0.0;

        for (Outstanding r : rows) {

            double bal = r.getBalanceAmt() == null ? 0.0 : r.getBalanceAmt();
            finalBalance += bal;

            if (r.getUpToSeven() != null && r.getUpToSeven() != 0.0) {
                finalUpToSeven += bal;
            } else if (r.getEightToThirty() != null && r.getEightToThirty() != 0.0) {
                finalEightToThirty += bal;
            } else if (r.getThirtyOneToNinty() != null && r.getThirtyOneToNinty() != 0.0) {
                finalThirtyOneToNinty += bal;
            } else if (r.getMoreThanNinty() != null && r.getMoreThanNinty() != 0.0) {
                finalMoreThanNinty += bal;
            }
        }

        if (finalBalance == 0.0) {
            List<Integer> ids = rows.stream()
                    .map(Outstanding::getOutstandingSINo)
                    .filter(Objects::nonNull)
                    .toList();

            outstandingRepository.deleteAllById(ids);
            return;
        }

        Outstanding latest = rows.stream()
                .max(Comparator.comparing(
                        r -> r.getBalanceAmt() == null ? 0.0 : r.getBalanceAmt()
                ))
                .orElseThrow();

        Outstanding consolidated = new Outstanding();

        consolidated.setPartyName(latest.getPartyName());
        consolidated.setSegment(latest.getSegment());



        consolidated.setLedgerGroupName(latest.getLedgerGroupName());
        consolidated.setOutstandingDate(latest.getOutstandingDate());
        consolidated.setBillNo(latest.getBillNo());
        consolidated.setBalanceAmt(finalBalance);
        consolidated.setUpToSeven(finalUpToSeven);
        consolidated.setEightToThirty(finalEightToThirty);
        consolidated.setThirtyOneToNinty(finalThirtyOneToNinty);
        consolidated.setMoreThanNinty(finalMoreThanNinty);

        String salesMann = latest.getSalesMan();
        if (salesMann != null && salesMann.contains("_")) {
            salesMann = salesMann.substring(0, salesMann.indexOf("_"));
        }
        consolidated.setSalesMan(salesMann);

        List<Integer> ids = rows.stream()
                .map(Outstanding::getOutstandingSINo)
                .filter(Objects::nonNull)
                .toList();

        outstandingRepository.deleteAllById(ids);

        outstandingRepository.save(consolidated);

    }

}