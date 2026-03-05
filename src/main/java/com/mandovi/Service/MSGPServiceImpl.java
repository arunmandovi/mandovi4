package com.mandovi.Service;

import com.mandovi.DTO.MSGPSummaryDTO;
import com.mandovi.Entity.MSGP;
import com.mandovi.Repository.MSGPRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class MSGPServiceImpl implements MSGPService {
    private final MSGPRepository msgpRepository;

    public MSGPServiceImpl(MSGPRepository msgpRepository) {
        this.msgpRepository = msgpRepository;
    }

    @Override
    public void saveMSGPFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            Row firstRow = sheet.getRow(1);
            String uploadMonth = firstRow.getCell(3).getStringCellValue().trim();
            Cell yearCell = firstRow.getCell(2);
            int numYear = (yearCell.getCellType() == CellType.NUMERIC)
                    ? (int) yearCell.getNumericCellValue()
                    : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);
            String anotherUploadYear = String.valueOf(numYear + 1);

            msgpRepository.deleteByMonthYear(uploadMonth, uploadYear);
            msgpRepository.deleteByMonthYear(uploadMonth, anotherUploadYear);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)continue;

                MSGP msgp = new MSGP();

                msgp.setCity(row.getCell(0).getStringCellValue());
                msgp.setLocationCode(row.getCell(1).getStringCellValue());
                msgp.setYear(row.getCell(2).getStringCellValue());
                msgp.setMonth(row.getCell(3).getStringCellValue());
                msgp.setServiceDescription(row.getCell(4).getStringCellValue());
                msgp.setNetRetailDDL(row.getCell(5).getNumericCellValue());

                msgp.setSumOfNetRetailDDL(msgp.getNetRetailDDL()/100000);

                if (msgp.getLocationCode() != null){
                    switch (msgp.getLocationCode().trim().toUpperCase()){
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
                        case "MNY": msgp.setBranch("Mandya Nexa"); break;
                        default: msgp.setBranch("Unknown"); break;
                    }
                }

                switch (msgp.getServiceDescription().trim().toUpperCase()){
                    case "1ST FREE SERVICE", "2ND FREE SERVICE", "3RD FREE SERVICE" -> msgp.setLoadType("FREE SERVICE");
                    case "PAID SERVICE" -> msgp.setLoadType("PMS");
                    case "RUNNING REPAIR" -> msgp.setLoadType("RR");
                    case "BODY  REPAIR" -> msgp.setLoadType("BODYSHOP");
                    default -> msgp.setLoadType("OTHERS");
                }

                String month = msgp.getMonth();
                switch (month){
                    case "Apr", "May", "Jun", "APR", "MAY", "JUN" ->{ msgp.setQtrWise("Qtr1"); msgp.setHalfYear("H1");}
                    case "Jul", "Aug", "Sep", "JUL", "AUG", "SEP" ->{ msgp.setQtrWise("Qtr2"); msgp.setHalfYear("H1");}
                    case "Oct", "Nov", "Dec", "OCT", "NOV", "DEC" ->{ msgp.setQtrWise("Qtr3"); msgp.setHalfYear("H2");}
                    case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR" ->{ msgp.setQtrWise("Qtr4"); msgp.setHalfYear("H2");}
                }

                int year = Integer.parseInt(msgp.getYear());
                switch (month.trim().toUpperCase()){
                    case "APR", "MAY", "JUN", "JUL", "AUG",
                         "SEP", "OCT", "NOV", "DEC" -> msgp.setFinancialYear(year+"-"+(year+1));
                    case "JAN", "FEB", "MAR" -> msgp.setFinancialYear((year-1)+"-"+year);
                }
                msgpRepository.save(msgp);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MSGP> getAllMSGP() {
        return msgpRepository.findAll();
    }

    @Override
    public List<MSGP> getMSGPByMonthYear(List<String> months, List<String> years) {
        return msgpRepository.getMSGPByMonthYear(months, years);
    }

    @Override
    public List<MSGPSummaryDTO> getMSGPSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return msgpRepository.getMSGPSummaryByCity(months, qtrWise, halfYear);
    }

    @Override
    public List<MSGPSummaryDTO> getMSGPSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return msgpRepository.getMSGPSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

    @Override
    public void deleteMSGPAll() {
        msgpRepository.deleteMSGPALL();
    }


}
