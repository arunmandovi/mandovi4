package com.mandovi.Service;

import com.mandovi.Entity.MSGPProfit;
import com.mandovi.Repository.MSGPProfitRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class MSGPProfitServiceImpl implements MSGPProfitService {
    private final MSGPProfitRepository msgpProfitRepository;

    public MSGPProfitServiceImpl(MSGPProfitRepository msGPProfitRepository, MSGPProfitRepository msgpProfitRepository) {
        this.msgpProfitRepository = msgpProfitRepository;
    }

    private Double round2Decimal(Double d) {
        return Math.round(d * 100.0) / 100.0;
    }
    Set<String> bangaloreBranches = new HashSet<>(Arrays.asList(
            "BKH","BNG","BSN","CDE","CMJ","GRB","HNR","JPN","KDH","MAF",
            "MLU","NXS","RJN","VDR","VJN","WGR","YLH","YPR"
    ));
    Set<String> mysoreBranches = new HashSet<>(Arrays.asList(
            "BNR","CMR","HSR","JVR","KIV","KKE","KRS","KSH","KSN","MSE","NGL","SOM","TNR","KLG"
    ));
    Set<String> mangaloreBranches = new HashSet<>(Arrays.asList(
            "BMR","BTL","VLA","KDB","UPA","SKL","SLL","AYR","YEY","MNL","SJH","SYG"
    ));

    @Override
    public void saveMSGPProfitFromExcel(MultipartFile file)  {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                MSGPProfit msgpProfit = new MSGPProfit();

                msgpProfit.setService_description(row.getCell(0).getStringCellValue());
                msgpProfit.setLocation_code(row.getCell(1).getStringCellValue());
                msgpProfit.setMonth(row.getCell(2).getStringCellValue());

                //Convert Numeric Cell's year column's value to String
                int num_year = (int) row.getCell(3).getNumericCellValue();
                String year = String.valueOf(num_year);
                msgpProfit.setYear(year);
                msgpProfit.setNet_retail_ddl(round2Decimal(row.getCell(4).getNumericCellValue()));
                msgpProfit.setNet_retail_selling(round2Decimal(row.getCell(5).getNumericCellValue()));

                //Updating Sum of net retails value by dividing the net retails values from 100000
                msgpProfit.setSum_of_net_retail_ddl(round2Decimal(msgpProfit.getNet_retail_ddl()/100000));
                msgpProfit.setSum_of_net_retail_selling(round2Decimal(msgpProfit.getNet_retail_selling()/100000));

                //Updating date column by as the first day of respective month mentioned in month column
                msgpProfit.setDate("1-"+msgpProfit.getMonth());

                //Updating city column by checking location code
                String location_code = msgpProfit.getLocation_code();
                if (bangaloreBranches.contains(location_code)) {
                    msgpProfit.setCity("Bangalore");
                } else if (mysoreBranches.contains(location_code)) {
                    msgpProfit.setCity("Mysore");
                } else if (mangaloreBranches.contains(location_code)) {
                    msgpProfit.setCity("Mangalore");
                }else {
                    msgpProfit.setCity("UNKNOWN");
                }

                //Updating branch based on location_code
                if(location_code!= null){
                    switch (location_code) {
                        case "BMR": msgpProfit.setBranch("Balmatta"); break;
                        case "BTL": msgpProfit.setBranch("Bantwal"); break;
                        case "VLA": msgpProfit.setBranch("Vittla"); break;
                        case "KDB": msgpProfit.setBranch("Kadaba"); break;
                        case "UPA": msgpProfit.setBranch("Uppinangady"); break;
                        case "SKL": msgpProfit.setBranch("Surathkal"); break;
                        case "SLL": msgpProfit.setBranch("Sullia"); break;
                        case "AYR": msgpProfit.setBranch("Adyar"); break;
                        case "YEY": msgpProfit.setBranch("Yeyyadi BR"); break;
                        case "MNL": msgpProfit.setBranch("Nexa Service"); break;
                        case "SJH": msgpProfit.setBranch("Sujith Bagh Lane"); break;
                        case "SYG": msgpProfit.setBranch("Naravi"); break;
                        case "BKH": msgpProfit.setBranch("NS Palya"); break;
                        case "BNG": msgpProfit.setBranch("Sarjapura"); break;
                        case "BNR": msgpProfit.setBranch("Bannur"); break;
                        case "BSN": msgpProfit.setBranch("Basaveshwarnagar"); break;
                        case "CDE": msgpProfit.setBranch("Kolar Nexa"); break;
                        case "CMJ": msgpProfit.setBranch("Basavangudi"); break;
                        case "CMR": msgpProfit.setBranch("ChamrajNagar"); break;
                        case "GRB": msgpProfit.setBranch("Gowribidanur"); break;
                        case "HNR": msgpProfit.setBranch("Hennur"); break;
                        case "HSR": msgpProfit.setBranch("Hunsur Road"); break;
                        case "JPN": msgpProfit.setBranch("JP Nagar"); break;
                        case "JVR": msgpProfit.setBranch("Maddur"); break;
                        case "KDH": msgpProfit.setBranch("Kolar"); break;
                        case "KIV": msgpProfit.setBranch("Gonikoppa"); break;
                        case "KKE": msgpProfit.setBranch("Mandya"); break;
                        case "KRS": msgpProfit.setBranch("KRS Road"); break;
                        case "KSH": msgpProfit.setBranch("Kushalnagar"); break;
                        case "KSN": msgpProfit.setBranch("Krishnarajapet"); break;
                        case "MAF": msgpProfit.setBranch("Basavanagudi-SOW"); break;
                        case "MLU": msgpProfit.setBranch("Malur SOW"); break;
                        case "MSE": msgpProfit.setBranch("Mysore Nexa"); break;
                        case "NGL": msgpProfit.setBranch("Nagamangala"); break;
                        case "NXS": msgpProfit.setBranch("Maluru WS"); break;
                        case "RJN": msgpProfit.setBranch("Uttarahali Kengeri"); break;
                        case "SOM": msgpProfit.setBranch("Somvarpet"); break;
                        case "TNR": msgpProfit.setBranch("Narasipura"); break;
                        case "VDR": msgpProfit.setBranch("Vidyarannapura"); break;
                        case "VJN": msgpProfit.setBranch("Vijayanagar"); break;
                        case "WGR": msgpProfit.setBranch("Wilson Garden"); break;
                        case "YLH": msgpProfit.setBranch("Yelahanka"); break;
                        case "YPR": msgpProfit.setBranch("Yeshwanthpur WS"); break;
                        case "KLG": msgpProfit.setBranch("Kollegal"); break;
                        default: msgpProfit.setBranch("Unknown"); break;
                    }
                }

                //Updating qtr_wise & half_year column based on month
                String month = msgpProfit.getMonth().trim().toUpperCase();
                switch (month) {
                    case "APR", "MAY", "JUN" ->{ msgpProfit.setQtr_wise("Q1"); msgpProfit.setHalf_year("H1");}
                    case "JUL", "AUG", "SEP" ->{ msgpProfit.setQtr_wise("Q2"); msgpProfit.setHalf_year("H1");}
                    case "OCT", "NOV", "DEC" ->{ msgpProfit.setQtr_wise("Q3"); msgpProfit.setHalf_year("H2");}
                    case "JAN", "FEB", "MAR" ->{ msgpProfit.setQtr_wise("Q4"); msgpProfit.setHalf_year("H2");}
                }

                msgpProfitRepository.save(msgpProfit);


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
