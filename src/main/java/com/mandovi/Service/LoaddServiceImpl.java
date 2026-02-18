package com.mandovi.Service;

import com.mandovi.DTO.LoaddSummaryDTO;
import com.mandovi.Entity.Loadd;
import com.mandovi.Repository.LoaddRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LoaddServiceImpl implements LoaddService {

    private final LoaddRepository loaddRepository;

    JdbcTemplate jdbcTemplate;

 ;   public LoaddServiceImpl(LoaddRepository loaddRepository) {
        this.loaddRepository = loaddRepository;
    }

    @Override
    @Transactional
    public void saveLoadDataFromExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Location groups
            Set<String> bangaloreLocations = new HashSet<>(Arrays.asList(
                    "BKH","BNG","BSN","CDE","CMJ","GRB","HNR","JPN","KDH","MAF","MLU","NXS",
                    "RJN","VDR","VJN","WGR","YLH","YPR"
            ));
            Set<String> mysoreLocations = new HashSet<>(Arrays.asList(
                    "BNR","CMR","HSR","JVR","KIV","KKE","KRS","KSH","KSN","MSE","NGL","SOM","TNR","KLG", "MNY"
            ));
            Set<String> mangaloreLocations = new HashSet<>(Arrays.asList(
                    "BMR","BTL","VLA","KDB","UPA","SKL","SLL","AYR","YEY","MNL","SJH","SYG"
            ));

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);

            Row firstDataRow = sheet.getRow(1);
            if (firstDataRow == null)
                throw new RuntimeException("No data found in Excel");

            String uploadMonth = firstDataRow.getCell(3).getStringCellValue().trim();
            loaddRepository.deleteByMonth(uploadMonth);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Loadd loadd = new Loadd();

                loadd.setLocation(row.getCell(0).getStringCellValue());
                loadd.setServiceTypeCode(row.getCell(1).getStringCellValue());
                loadd.setYear(row.getCell(2).getStringCellValue());
                loadd.setMonth(row.getCell(3).getStringCellValue());
                loadd.setModelChannel(row.getCell(4).getStringCellValue());

                double numericValue = row.getCell(5).getNumericCellValue();
                loadd.setServiceLoad((int) numericValue);

                // Auto-update jcBillDate
                try {
                    int year = Integer.parseInt(loadd.getYear().trim());
                    String month = loadd.getMonth().trim();

                    Month m = Month.from(dateTimeFormatter.parse(month));
                    LocalDate jcBillDate = LocalDate.of(year, m.getValue(), 1);
                    loadd.setJcBillDate(jcBillDate);

                } catch (Exception e) {
                    System.out.println("Invalid year/month in row " + i);
                }

                loadd.setChannel(loadd.getModelChannel());

                // Branch mapping
                if (loadd.getLocation() != null) {
                    switch (loadd.getLocation().trim().toUpperCase()) {
                        case "BMR": loadd.setBranch("Balmatta"); break;
                        case "BTL": loadd.setBranch("Bantwal"); break;
                        case "VLA": loadd.setBranch("Vittla"); break;
                        case "KDB": loadd.setBranch("Kadaba"); break;
                        case "UPA": loadd.setBranch("Uppinangady"); break;
                        case "SKL": loadd.setBranch("Surathkal"); break;
                        case "SLL": loadd.setBranch("Sullia"); break;
                        case "AYR": loadd.setBranch("Adyar"); break;
                        case "YEY": loadd.setBranch("Yeyyadi BR"); break;
                        case "MNL": loadd.setBranch("Nexa Service"); break;
                        case "SJH": loadd.setBranch("Sujith Bagh Lane"); break;
                        case "SYG": loadd.setBranch("Naravi"); break;
                        case "BKH": loadd.setBranch("NS Palya"); break;
                        case "BNG": loadd.setBranch("Sarjapura"); break;
                        case "BNR": loadd.setBranch("Bannur"); break;
                        case "BSN": loadd.setBranch("Basaveshwarnagar"); break;
                        case "CDE": loadd.setBranch("Kolar Nexa"); break;
                        case "CMJ": loadd.setBranch("Basavangudi"); break;
                        case "CMR": loadd.setBranch("ChamrajNagar"); break;
                        case "GRB": loadd.setBranch("Gowribidanur"); break;
                        case "HNR": loadd.setBranch("Hennur"); break;
                        case "HSR": loadd.setBranch("Hunsur Road"); break;
                        case "JPN": loadd.setBranch("JP Nagar"); break;
                        case "JVR": loadd.setBranch("Maddur"); break;
                        case "KDH": loadd.setBranch("Kolar"); break;
                        case "KIV": loadd.setBranch("Gonikoppa"); break;
                        case "KKE": loadd.setBranch("Mandya"); break;
                        case "KRS": loadd.setBranch("KRS Road"); break;
                        case "KSH": loadd.setBranch("Kushalnagar"); break;
                        case "KSN": loadd.setBranch("Krishnarajapet"); break;
                        case "MAF": loadd.setBranch("Basavanagudi-SOW"); break;
                        case "MLU": loadd.setBranch("Malur SOW"); break;
                        case "MSE": loadd.setBranch("Mysore Nexa"); break;
                        case "NGL": loadd.setBranch("Nagamangala"); break;
                        case "NXS": loadd.setBranch("Maluru WS"); break;
                        case "RJN": loadd.setBranch("Uttarahali Kengeri"); break;
                        case "SOM": loadd.setBranch("Somvarpet"); break;
                        case "TNR": loadd.setBranch("Narasipura"); break;
                        case "VDR": loadd.setBranch("Vidyarannapura"); break;
                        case "VJN": loadd.setBranch("Vijayanagar"); break;
                        case "WGR": loadd.setBranch("Wilson Garden"); break;
                        case "YLH": loadd.setBranch("Yelahanka"); break;
                        case "YPR": loadd.setBranch("Yeshwanthpur WS"); break;
                        case "KLG": loadd.setBranch("Kollegal"); break;
                        case "MNY": loadd.setBranch("Mandya Nexa"); break;
                        default: loadd.setBranch("Unknown"); break;
                    }
                }

                // City mapping
                String locationCode = loadd.getLocation();
                if (bangaloreLocations.contains(locationCode)) loadd.setCity("Bangalore");
                else if (mysoreLocations.contains(locationCode)) loadd.setCity("Mysore");
                else if (mangaloreLocations.contains(locationCode)) loadd.setCity("Mangalore");
                else loadd.setCity("Unknown");

                // Financial Year
                try {
                    int year = Integer.parseInt(loadd.getYear());
                    String charMonth = loadd.getMonth();

                    Month m = Month.from(dateTimeFormatter.parse(charMonth));
                    int monthNum = m.getValue();

                    if (monthNum >= 4)
                        loadd.setFinancialYear(year + "-" + (year + 1));
                    else
                        loadd.setFinancialYear((year - 1) + "-" + year);

                } catch (Exception e) {
                    System.out.println("Invalid year/month for financial year in row " + i);
                }

                // Load Type Mapping
                String serviceTypeCode = loadd.getServiceTypeCode();
                switch (serviceTypeCode) {
                    case "ACC","BDW","CDS","CVMS","REFF","TV1","TV2","TV3","WASH","WMOS","FR4",
                         "PMSTV","TRN","FR","RJ","IFC" -> loadd.setLoadType("OTHERS");
                    case "FR1","FR2","FR3" -> loadd.setLoadType("FREE SERVICE");
                    case "SC","CCP" -> loadd.setLoadType("NO");
                    case "BANDP" -> loadd.setLoadType("BODYSHOP");
                    case "RR" -> loadd.setLoadType("RR");
                    case "PMS" -> loadd.setLoadType("PMS");
                    default -> loadd.setLoadType("UNKNOWN");
                }

                // Quarter / Half-year mapping
                String month = loadd.getMonth().trim().toUpperCase();
                switch (month) {
                    case "APR","MAY","JUN" -> { loadd.setQtrWise("Qtr1"); loadd.setHalfYear("H1"); }
                    case "JUL","AUG","SEP" -> { loadd.setQtrWise("Qtr2"); loadd.setHalfYear("H1"); }
                    case "OCT","NOV","DEC" -> { loadd.setQtrWise("Qtr3"); loadd.setHalfYear("H2"); }
                    case "JAN","FEB","MAR" -> { loadd.setQtrWise("Qtr4"); loadd.setHalfYear("H2"); }
                }

                // Save row (NO DUPLICATE CHECK NOW)
                loaddRepository.save(loadd);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<Loadd> getAllLoadData() {
        return  loaddRepository.findAll();
    }

    @Override
    public List<Loadd> getLoadByMonthYear(List<String> months, List<String> years) {
        return loaddRepository.getLoadByMonthYear(months, years);
    }

    @Override
    public List<LoaddSummaryDTO> getLoaddSummary(List<String> months, List<String> channels, List<String> qtrWise, List<String> halfYear) {
     return loaddRepository.getLoaddSummaryByCity(months, channels, qtrWise, halfYear);
    }

    @Override
    public List<LoaddSummaryDTO> getLoaddSummaryBranchWise(List<String> months, List<String> cities, List<String> branches, List<String> channels, List<String> qtrWise, List<String> halfYear) {
        return loaddRepository.getLoaddSummaryBranchWise(months, cities, branches, channels, qtrWise, halfYear);
    }

    @Override
    public void deleteLoaddAll() {
        loaddRepository.deleteLoaddAll();
    }

}
