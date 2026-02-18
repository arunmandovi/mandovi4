package com.mandovi.Service;

import com.mandovi.DTO.MGAProfitSummaryDTO;
import com.mandovi.Entity.MGAProfit;
import com.mandovi.Repository.MGAProfitRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MGAProfitServiceImpl implements MGAProfitService{
    private final MGAProfitRepository mgaProfitRepository;

    public MGAProfitServiceImpl(MGAProfitRepository mgaProfitRepository) {
        this.mgaProfitRepository = mgaProfitRepository;
    }

    @Override
    public void saveMGAProfitFromExcel(MultipartFile file) {
        try {
            InputStream inputStream =file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // Define location Codes for respective Cities
            Set<String> bangaloreLocations = new HashSet<>(Arrays.asList(
                    "BKH","BNG","BSN","CDE","CMJ","GRB","HNR","JPN",
                    "KDH","MAF","MLU","NXS","RJN","VDR","VJN","WGR","YLH","YPR" ));
            Set<String> mysoreLocations = new HashSet<>(Arrays.asList(
                    "BNR","CMR","HSR","JVR","KIV","KKE","KRS","KSH",
                    "KSN","MSE","NGL","SOM","TNR","KLG", "MNY" ));
            Set<String> mangaloreLocations = new HashSet<>(Arrays.asList(
                    "BMR","BTL","VLA","KDB","UPA","SKL","SLL","AYR","YEY","MNL","SJH","SYG" ));

            Row firstRow = sheet.getRow(1);
            if (firstRow == null)
                throw new RuntimeException("No Data found in Excel");

            String uploadMonth = firstRow.getCell(2).getStringCellValue().trim();
            mgaProfitRepository.deleteByMonth(uploadMonth);

            for (int i=1; i<= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if (row == null) continue;

                MGAProfit mgaProfit = new MGAProfit();

                mgaProfit.setServiceDescription(row.getCell(0).getStringCellValue());
                mgaProfit.setLocationCode(row.getCell(1).getStringCellValue());
                mgaProfit.setMonth(row.getCell(2).getStringCellValue());

                Cell cell = row.getCell(3);
                switch (cell.getCellType()){
                    case STRING -> mgaProfit.setYear(row.getCell(3).getStringCellValue());
                    case NUMERIC -> {
                        int numYear = (int) row.getCell(3).getNumericCellValue();
                        String year = String.valueOf((Integer.parseInt(String.valueOf(numYear))));
                        mgaProfit.setYear(year);
                    }
                }
                mgaProfit.setNetRetailDD(row.getCell(4).getNumericCellValue());
                mgaProfit.setNetRetailSell(row.getCell(5).getNumericCellValue());
                mgaProfit.setNetRetailDDL(mgaProfit.getNetRetailDD()/100000);
                mgaProfit.setNetRetailSelling(mgaProfit.getNetRetailSell()/100000);

                mgaProfit.setServiceType(mgaProfit.getServiceDescription());
                String locationCode = mgaProfit.getLocationCode();
                if (bangaloreLocations.contains(locationCode)){
                    mgaProfit.setCity("Bangalore");
                } else if (mysoreLocations.contains(locationCode)){
                    mgaProfit.setCity("Mysore");
                } else if (mangaloreLocations.contains(locationCode)) {
                    mgaProfit.setCity("Mangalore");
                } else {
                    mgaProfit.setCity("UNKNOWN");
                }

                if (mgaProfit.getLocationCode() != null) {
                    switch (mgaProfit.getLocationCode().trim().toUpperCase()) {
                        case "BMR": mgaProfit.setBranch("Balmatta"); break;
                        case "BTL": mgaProfit.setBranch("Bantwal"); break;
                        case "VLA": mgaProfit.setBranch("Vittla"); break;
                        case "KDB": mgaProfit.setBranch("Kadaba"); break;
                        case "UPA": mgaProfit.setBranch("Uppinangady"); break;
                        case "SKL": mgaProfit.setBranch("Surathkal"); break;
                        case "SLL": mgaProfit.setBranch("Sullia"); break;
                        case "AYR": mgaProfit.setBranch("Adyar"); break;
                        case "YEY": mgaProfit.setBranch("Yeyyadi BR"); break;
                        case "MNL": mgaProfit.setBranch("Nexa Service"); break;
                        case "SJH": mgaProfit.setBranch("Sujith Bagh Lane"); break;
                        case "SYG": mgaProfit.setBranch("Naravi"); break;
                        case "BKH": mgaProfit.setBranch("NS Palya"); break;
                        case "BNG": mgaProfit.setBranch("Sarjapura"); break;
                        case "BNR": mgaProfit.setBranch("Bannur"); break;
                        case "BSN": mgaProfit.setBranch("Basaveshwarnagar"); break;
                        case "CDE": mgaProfit.setBranch("Kolar Nexa"); break;
                        case "CMJ": mgaProfit.setBranch("Basavangudi"); break;
                        case "CMR": mgaProfit.setBranch("ChamrajNagar"); break;
                        case "GRB": mgaProfit.setBranch("Gowribidanur"); break;
                        case "HNR": mgaProfit.setBranch("Hennur"); break;
                        case "HSR": mgaProfit.setBranch("Hunsur Road"); break;
                        case "JPN": mgaProfit.setBranch("JP Nagar"); break;
                        case "JVR": mgaProfit.setBranch("Maddur"); break;
                        case "KDH": mgaProfit.setBranch("Kolar"); break;
                        case "KIV": mgaProfit.setBranch("Gonikoppa"); break;
                        case "KKE": mgaProfit.setBranch("Mandya"); break;
                        case "KRS": mgaProfit.setBranch("KRS Road"); break;
                        case "KSH": mgaProfit.setBranch("Kushalnagar"); break;
                        case "KSN": mgaProfit.setBranch("Krishnarajapet"); break;
                        case "MAF": mgaProfit.setBranch("Basavanagudi-SOW"); break;
                        case "MLU": mgaProfit.setBranch("Malur SOW"); break;
                        case "MSE": mgaProfit.setBranch("Mysore Nexa"); break;
                        case "NGL": mgaProfit.setBranch("Nagamangala"); break;
                        case "NXS": mgaProfit.setBranch("Maluru WS"); break;
                        case "RJN": mgaProfit.setBranch("Uttarahali Kengeri"); break;
                        case "SOM": mgaProfit.setBranch("Somvarpet"); break;
                        case "TNR": mgaProfit.setBranch("Narasipura"); break;
                        case "VDR": mgaProfit.setBranch("Vidyarannapura"); break;
                        case "VJN": mgaProfit.setBranch("Vijayanagar"); break;
                        case "WGR": mgaProfit.setBranch("Wilson Garden"); break;
                        case "YLH": mgaProfit.setBranch("Yelahanka"); break;
                        case "YPR": mgaProfit.setBranch("Yeshwanthpur WS"); break;
                        case "KLG": mgaProfit.setBranch("Kollegal"); break;
                        case "MNY": mgaProfit.setBranch("Mandya Nexa"); break;
                        default: mgaProfit.setBranch("Unknown"); break;
                    }
                }

                switch (mgaProfit.getMonth().trim().toUpperCase()){
                    case "APR", "MAY", "JUN" ->{ mgaProfit.setQtrWise("Qtr1"); mgaProfit.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" ->{ mgaProfit.setQtrWise("Qtr2"); mgaProfit.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" ->{ mgaProfit.setQtrWise("Qtr3"); mgaProfit.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" ->{ mgaProfit.setQtrWise("Qtr4"); mgaProfit.setHalfYear("H2");}
                }

                mgaProfitRepository.save(mgaProfit);
            }

        } catch (IOException e) {
            throw new RuntimeException("Uploading file ERROR"+e.getMessage());
        }
    }

    @Override
    public List<MGAProfit> getALLMGAProfit() {
        return mgaProfitRepository.findAll();
    }

    @Override
    public List<MGAProfit> getMGAProfitMonthYear(List<String> months, List<String> years) {
        return mgaProfitRepository.getMGAProfitMonthYear(months, years);
    }

    @Override
    public List<MGAProfitSummaryDTO> getMGAProfitSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return mgaProfitRepository.getMGAProfitSummary(months, qtrWise, halfYear);
    }

    @Override
    public List<MGAProfitSummaryDTO> getMGAProfitSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return mgaProfitRepository.getMGAProfitSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

    @Override
    public void deleteMGAAll() {
        mgaProfitRepository.deleteMGAProfitAll();
    }
}
