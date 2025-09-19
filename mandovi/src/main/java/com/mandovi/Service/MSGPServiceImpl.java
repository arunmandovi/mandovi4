package com.mandovi.Service;

import com.mandovi.Entity.MSGP;
import com.mandovi.Repository.MSGPRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class MSGPServiceImpl implements MSGPService {
    private final MSGPRepository msGPRepository;

    public MSGPServiceImpl(MSGPRepository msGPRepository) {
        this.msGPRepository = msGPRepository;
    }

    @Override
    public void saveMSGPFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;

                MSGP msgp = new MSGP();

                msgp.setCity(row.getCell(0).getStringCellValue());
                msgp.setLocation_code(row.getCell(1).getStringCellValue());
                msgp.setYear(row.getCell(2).getStringCellValue());
                msgp.setMonth(row.getCell(3).getStringCellValue());
                msgp.setService_description(row.getCell(4).getStringCellValue());
                msgp.setNet_retail_ddl(row.getCell(5).getNumericCellValue());

                //Updating sum_of_net_retail_ddl column by dividing net reatail_ddl_column's value by 100000
                msgp.setSum_of_net_retail_ddl(msgp.getNet_retail_ddl()/100000);

                //Updating branch column by checking the value from column loaction_code
                if (msgp.getLocation_code() != null){
                    switch (msgp.getLocation_code().trim().toUpperCase()){
                        case "BMR": msgp.setBranch("Balmatta"); break;
                        case "BTL": msgp.setBranch("Bantwal"); break;
                        case "VLA": msgp.setBranch("Vittla"); break;
                        case "KDB": msgp.setBranch("Kadaba"); break;
                        case "UPA": msgp.setBranch("Uppinangady"); break;
                        case "SKL": msgp.setBranch("Surathkal"); break;
                        case "SLL": msgp.setBranch("Sullia"); break;
                        case "AYR": msgp.setBranch("Adyar"); break;
                        case "YEY": msgp.setBranch("Yeyyadi BR"); break;
                        case "MNL": msgp.setBranch("Nexa Service"); break;
                        case "SJH": msgp.setBranch("Sujith Bagh Lane"); break;
                        case "SYG": msgp.setBranch("Naravi"); break;
                        case "BKH": msgp.setBranch("NS Palya"); break;
                        case "BNG": msgp.setBranch("Sarjapura"); break;
                        case "BNR": msgp.setBranch("Bannur"); break;
                        case "BSN": msgp.setBranch("Basaveshwarnagar"); break;
                        case "CDE": msgp.setBranch("Kolar Nexa"); break;
                        case "CMJ": msgp.setBranch("Basavangudi"); break;
                        case "CMR": msgp.setBranch("ChamrajNagar"); break;
                        case "GRB": msgp.setBranch("Gowribidanur"); break;
                        case "HNR": msgp.setBranch("Hennur"); break;
                        case "HSR": msgp.setBranch("Hunsur Road"); break;
                        case "JPN": msgp.setBranch("JP Nagar"); break;
                        case "JVR": msgp.setBranch("Maddur"); break;
                        case "KDH": msgp.setBranch("Kolar"); break;
                        case "KIV": msgp.setBranch("Gonikoppa"); break;
                        case "KKE": msgp.setBranch("Mandya"); break;
                        case "KRS": msgp.setBranch("KRS Road"); break;
                        case "KSH": msgp.setBranch("Kushalnagar"); break;
                        case "KSN": msgp.setBranch("Krishnarajapet"); break;
                        case "MAF": msgp.setBranch("Basavanagudi-SOW"); break;
                        case "MLU": msgp.setBranch("Malur SOW"); break;
                        case "MSE": msgp.setBranch("Mysore Nexa"); break;
                        case "NGL": msgp.setBranch("Nagamangala"); break;
                        case "NXS": msgp.setBranch("Maluru WS"); break;
                        case "RJN": msgp.setBranch("Uttarahali Kengeri"); break;
                        case "SOM": msgp.setBranch("Somvarpet"); break;
                        case "TNR": msgp.setBranch("Narasipura"); break;
                        case "VDR": msgp.setBranch("Vidyarannapura"); break;
                        case "VJN": msgp.setBranch("Vijayanagar"); break;
                        case "WGR": msgp.setBranch("Wilson Garden"); break;
                        case "YLH": msgp.setBranch("Yelahanka"); break;
                        case "YPR": msgp.setBranch("Yeshwanthpur WS"); break;
                        case "KLG": msgp.setBranch("Kollegal"); break;
                        default: msgp.setBranch("Unknown"); break;
                    }
                }

                //Updating the column period by concating columns month & year
                msgp.setPeriod(msgp.getMonth()+"-"+msgp.getYear());

                //Updating qtr_wise & half_year columns by checking month column's values
                String month = msgp.getMonth();
                switch (month){
                    case "Apr", "May", "Jun", "APR", "MAY", "JUN" ->{ msgp.setQtr_wise("Q1"); msgp.setHalf_year("H1");}
                    case "Jul", "Aug", "Sep", "JUL", "AUG", "SEP" ->{ msgp.setQtr_wise("Q2"); msgp.setHalf_year("H1");}
                    case "Oct", "Nov", "Dec", "OCT", "NOV", "DEC" ->{ msgp.setQtr_wise("Q3"); msgp.setHalf_year("H2");}
                    case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR" ->{ msgp.setQtr_wise("Q4"); msgp.setHalf_year("H2");}
                }

                //Updating the financial_year column by checking the values from month & year
                int year = Integer.parseInt(msgp.getYear());
                switch (month.trim().toUpperCase()){
                    case "APR", "MAY", "JUN", "JUL", "AUG",
                         "SEP", "OCT", "NOV", "DEC" -> msgp.setFinancial_year(year+"-"+(year+1));
                    case "JAN", "FEB", "MAR" -> msgp.setFinancial_year((year-1)+"-"+year);
                }
                msGPRepository.save(msgp);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
