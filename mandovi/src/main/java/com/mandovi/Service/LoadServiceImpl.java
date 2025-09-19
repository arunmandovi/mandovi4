package com.mandovi.Service;

import com.mandovi.Entity.Load;
import com.mandovi.Repository.LoadRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class LoadServiceImpl implements LoadService {

    private final LoadRepository loadRepository;

    public LoadServiceImpl(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    @Override
    @Transactional
    public void saveLoadDataFromExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            DataFormatter dataFormatter = new DataFormatter();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Define location Codes for respective Cities
            Set<String> bangaloreBranches = new HashSet<>(Arrays.asList(
                    "BKH","BNG","BSN","CDE","CMJ","GRB","HNR","JPN",
                    "KDH","MAF","MLU","NXS","RJN","VDR","VJN","WGR","YLH","YPR"
            ));
            Set<String> mysoreBranches = new HashSet<>(Arrays.asList(
                    "BNR","CMR","HSR","JVR","KIV","KKE","KRS","KSH",
                    "KSN","MSE","NGL","SOM","TNR","KLG"
            ));
            Set<String> mangaloreLocations = new HashSet<>(Arrays.asList(
                    "BMR","BTL","VLA","KDB","UPA","SKL","SLL","AYR","YEY","MNL","SJH","SYG"
            ));



            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Load load = new Load();

                load.setLocation(row.getCell(0).getStringCellValue());
                load.setServiceTypeCode(row.getCell(1).getStringCellValue());
                load.setYear(row.getCell(2).getStringCellValue());
                load.setMonth(row.getCell(3).getStringCellValue());
                load.setModelChannel(row.getCell(4).getStringCellValue());

                // Read numeric cell (6th column, index 5)
                double numericValue = row.getCell(5).getNumericCellValue();

                // Convert to int
                load.setServiceLoad((int) numericValue);


                // Auto update jcBillDate as 1st day of the given value from column month and year
                try {
                    int year = Integer.parseInt(load.getYear().trim());
                    String month = load.getMonth().trim();

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
                    Month parsedMonth = Month.from(dateTimeFormatter.parse(month));

                    LocalDate jcBillDate = LocalDate.of(year, parsedMonth.getValue(), 1);
                    load.setJcBillDate(jcBillDate);


                    System.out.println("jcBillDate set as: " + jcBillDate.format(dateTimeFormatter));
                } catch (Exception e) {
                    System.out.println("Invalid year/month in row " + i);
                }
                load.setChannel(load.getModelChannel());



                // Auto update branch based on location
                if (load.getLocation() != null) {
                    switch (load.getLocation().trim().toUpperCase()) {
                        case "BMR": load.setBranch("Balmatta"); break;
                        case "BTL": load.setBranch("Bantwal"); break;
                        case "VLA": load.setBranch("Vittla"); break;
                        case "KDB": load.setBranch("Kadaba"); break;
                        case "UPA": load.setBranch("Uppinangady"); break;
                        case "SKL": load.setBranch("Surathkal"); break;
                        case "SLL": load.setBranch("Sullia"); break;
                        case "AYR": load.setBranch("Adyar"); break;
                        case "YEY": load.setBranch("Yeyyadi BR"); break;
                        case "MNL": load.setBranch("Nexa Service"); break;
                        case "SJH": load.setBranch("Sujith Bagh Lane"); break;
                        case "SYG": load.setBranch("Naravi"); break;
                        case "BKH": load.setBranch("NS Palya"); break;
                        case "BNG": load.setBranch("Sarjapura"); break;
                        case "BNR": load.setBranch("Bannur"); break;
                        case "BSN": load.setBranch("Basaveshwarnagar"); break;
                        case "CDE": load.setBranch("Kolar Nexa"); break;
                        case "CMJ": load.setBranch("Basavangudi"); break;
                        case "CMR": load.setBranch("ChamrajNagar"); break;
                        case "GRB": load.setBranch("Gowribidanur"); break;
                        case "HNR": load.setBranch("Hennur"); break;
                        case "HSR": load.setBranch("Hunsur Road"); break;
                        case "JPN": load.setBranch("JP Nagar"); break;
                        case "JVR": load.setBranch("Maddur"); break;
                        case "KDH": load.setBranch("Kolar"); break;
                        case "KIV": load.setBranch("Gonikoppa"); break;
                        case "KKE": load.setBranch("Mandya"); break;
                        case "KRS": load.setBranch("KRS Road"); break;
                        case "KSH": load.setBranch("Kushalnagar"); break;
                        case "KSN": load.setBranch("Krishnarajapet"); break;
                        case "MAF": load.setBranch("Basavanagudi-SOW"); break;
                        case "MLU": load.setBranch("Malur SOW"); break;
                        case "MSE": load.setBranch("Mysore Nexa"); break;
                        case "NGL": load.setBranch("Nagamangala"); break;
                        case "NXS": load.setBranch("Maluru WS"); break;
                        case "RJN": load.setBranch("Uttarahali Kengeri"); break;
                        case "SOM": load.setBranch("Somvarpet"); break;
                        case "TNR": load.setBranch("Narasipura"); break;
                        case "VDR": load.setBranch("Vidyarannapura"); break;
                        case "VJN": load.setBranch("Vijayanagar"); break;
                        case "WGR": load.setBranch("Wilson Garden"); break;
                        case "YLH": load.setBranch("Yelahanka"); break;
                        case "YPR": load.setBranch("Yeshwanthpur WS"); break;
                        case "KLG": load.setBranch("Kollegal"); break;
                        default: load.setBranch("Unknown"); break;
                    }
                }

                // Auto update city based on location
                String locationCode = load.getLocation();

                if (bangaloreBranches.contains(locationCode)) {
                    load.setCity("Bangalore");
                } else if (mysoreBranches.contains(locationCode)) {
                    load.setCity("Mysore");
                } else if (mangaloreLocations.contains(locationCode)) {
                    load.setCity("Mangalore");
                } else {
                    load.setCity("Unknown");
                }
                // ✅ Auto update Financial Year
                try {
                    int year = Integer.parseInt(load.getYear());
                    String charMonth = load.getMonth();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
                    Month parsedMonth = Month.from(dateTimeFormatter.parse(charMonth));
                    int month = parsedMonth.getValue();

                    String financialYear;
                    if (month >= 4) { // Apr to Dec
                        financialYear = year + "-" + (year + 1);
                    } else { // Jan to Mar
                        financialYear = (year - 1) + "-" + year;
                    }
                    load.setFinancial_year(financialYear);

                } catch (Exception e) {
                    System.out.println("Invalid year/month for financial year in row " + i);
                }

                // Auto update loadType based on serviceTypeCode

                String serviceTypeCode = load.getServiceTypeCode();
                switch (serviceTypeCode) {
                    case "ACC","BDW","CDS","CVMS","REFF","TV1","TV2","TV3","WASH","WMOS","FR4","PMSTV","TRN","FR","RJ","IFC" -> load.setLoadType("OTHERS");
                    case "FR1","FR2","FR3" -> load.setLoadType("FREE SERVICE");
                    case "SC","CCP" -> load.setLoadType("NO");
                }



                // Auto update qtrWise and halfYear based on months
                String month =  load.getMonth().trim().toUpperCase();
                switch (month) {
                    case "APR", "MAY", "JUN" ->{ load.setQtrWise("Q1"); load.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" ->{ load.setQtrWise("Q2"); load.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" ->{ load.setQtrWise("Q3"); load.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" ->{ load.setQtrWise("Q4"); load.setHalfYear("H2");}
                }

                loadRepository.save(load);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
