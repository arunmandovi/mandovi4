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
                    Map.entry("bajaj general insurance limited - 02-02 - ka19mp6608", Set.of(-21012.0)),
                    Map.entry("sourabh y jain - 1934183546 - ka19mk3678", Set.of(-715.0)),
                    Map.entry("universal sompo gic ltd - 22-35 - ka21ma5982", Set.of(-377.0)),
                    Map.entry("divakara  k - 2142877106 - ka21z5134", Set.of(100.0)),
                    Map.entry("k k krishna - 2249921867 - ka21z6178", Set.of(2000.0)),
                    Map.entry("abdul arif - 2354798229 - ka21ma1339", Set.of(500.0)),
                    Map.entry("abdul jaleel k - 2456880500 - ka21ma2870", Set.of(6000.0)),
                    Map.entry("amitha dhananjaya - 2249862266 - ka19mn2566", Set.of(283.0)),
                    Map.entry("chethan  kumar - 2245907457 - ka19mm6794", Set.of(520.0)),
                    Map.entry("devipramod rao t - i129827124 - ka19md0460", Set.of(280.0)),
                    Map.entry("dheeraj kumar - 2355328641 - ka19mp2257", Set.of(105.0)),
                    Map.entry("hemanth shetty - 1725662453 - ka19mh7410", Set.of(74.0)),
                    Map.entry("hyder ali - 1622174635 - ka19mg5839", Set.of(11356.0,1144.0)),
                    Map.entry("icici lombard general insurance co ltd. - 06-22 - ka09mb0683", Set.of(190.0)),
                    Map.entry("iffco tokio general insurance co. ltd. - 05-19 - ka21z9974", Set.of(1282.78)),
                    Map.entry("jagannatha das - 1620439170 - ka21p2028", Set.of(2750.0)),
                    Map.entry("kamalaksha j - 1415091974 - ka19mf0319", Set.of(28.0)),
                    Map.entry("karvelu ilyas moosan - 1311139151 - ka21a9547", Set.of(480.0)),
                    Map.entry("m balakrishna rai - 2039677026 - ka21c1968", Set.of(4000.0)),
                    Map.entry("mahabala shetty - 1517447792 - ka19mg4698", Set.of(2.0)),
                    Map.entry("mohammed thanveer - i117810967 - ka19mf6410", Set.of(1280.0,2270.0)),
                    Map.entry("n r ganesh - 2352240621 - ka19mb9766", Set.of(610.0)),
                    Map.entry("national insurance company limited - 01-295 - ka19mf4438", Set.of(740.45)),
                    Map.entry("national insurance company limited - 01-295 - ka20ma1592", Set.of(1551.0)),
                    Map.entry("neo pack - 2144340526 - ka70m3477", Set.of(24620.0)),
                    Map.entry("nithin  kumar - 1623825039 - ka19mh0891", Set.of(874.0)),
                    Map.entry("rejeesh a k - 2457840152 - kl59n3031", Set.of(10000.0)),
                    Map.entry("rohith a - 1832252687 - ka19mk0571", Set.of(1838.0)),
                    Map.entry("sathish samanth k - 2459697063 - ka21c8408", Set.of(843.0)),
                    Map.entry("shresta shetty - 1210030175 - ka19md221", Set.of(177.0)),
                    Map.entry("shrinivas rao pailooru - 1516106212 - ka21n9728", Set.of(1620.0)),
                    Map.entry("sirajuddeen - 2461009197 - ka21n3577", Set.of(934.0)),
                    Map.entry("supreme auto dealers pvt ltd - 2038740711 - ka01mr1574", Set.of(10220.0)),
                    Map.entry("sushma divakar shetty - 2353735987 - ka19mn9882", Set.of(115.0)),
                    Map.entry("swasthik k s - 2144353586 - ka21z6052", Set.of(1467.0)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka19mg3040", Set.of(1892.0)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka20z5219", Set.of(548.0)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka21n6489", Set.of(421.25)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka21z3845", Set.of(1025.58)),
                    Map.entry("the oriental insurance company limited - 12-920 - mh02ee7891", Set.of(444.91)),
                    Map.entry("united india insurance company limited - 10-41 - ka21p9501", Set.of(25000.0)),
                    Map.entry("united india insurance company limited - 10-95 - kl14q9299", Set.of(5000.0)),
                    Map.entry("universal sompo gic ltd - 22-35 - ka19mk0197", Set.of(327.0)),
                    Map.entry("universal sompo gic ltd - 22-35 - ka21p5315", Set.of(134.0)),
                    Map.entry("bharath auto cars pvt ltd sales", Set.of(65301.99,35777.0,2888.0, 101078.99)),
                    Map.entry("bharath auto cars pvt. ltd. - 10785kjn", Set.of(1.0)),
                    Map.entry("essarr automotive sale", Set.of(1391.0,560.0)),
                    Map.entry("mahammad safwan - 2459513080 - ka19ml5014", Set.of(6500.0)),
                    Map.entry("m nagesh rao - i129117360 - ka21n2388", Set.of(2800.0)),
                    Map.entry("sbi general insurance co ltd - 25-74 - ka19mn0432", Set.of(167.54)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka19ma5924", Set.of(907.0)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka19md1496", Set.of(1520.81,4364.0)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka19mg0672", Set.of(-1256.82)),
                    Map.entry("bajaj general insurance limited - 02-02 - ka21ma6193", Set.of(-934.0)),
                    Map.entry("the new india assurance co ltd - 04-115 - ka19ac3995", Set.of(1373.0)),
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

        if (INVALID_LEDGER_GROUPS.contains(ledger)) {

            if (ledger.equals("debtors control account")) {

                Double balance = o.getBalanceAmt();

                if (billNo.contains("-ads") && balance != null && balance > 0) {

                    try {
                        if (o.getOutstandingDate() != null) {

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            LocalDate billDate = LocalDate.parse(o.getOutstandingDate(), formatter);

                            LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

                            if (!billDate.isBefore(oneMonthAgo)) {
                                return true;
                            }
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }
            }

            return false; // ❌ default behavior (delete)
        }

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

        if (party.contains("msil - (ew claim recoverable)")) return  false;

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

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    if (base.getOutstandingDate() == null || next.getOutstandingDate() == null)
                        continue;

                    try {
                        LocalDate baseDate = LocalDate.parse(base.getOutstandingDate(), formatter);
                        LocalDate nextDate = LocalDate.parse(next.getOutstandingDate(), formatter);

                        long daysDiff = Math.abs(ChronoUnit.DAYS.between(baseDate, nextDate));

                        if (daysDiff <= 62) {
                            group.add(next);
                            processed[j] = true;
                        }

                    } catch (Exception e) {
                    }
                }

                if (group.size() > 1) {
                    consolidateAndReplace(group);
                }
            }
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
        consolidated.setBillAmt(latest.getBillAmt());
        consolidated.setPaidAmt(latest.getPaidAmt());
        consolidated.setBalanceAmt(finalBalance);
        consolidated.setDueSince(latest.getDueSince());
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