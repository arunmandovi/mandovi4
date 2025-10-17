package com.mandovi.Service;

import com.mandovi.DTO.LabourSummaryDTO;
import com.mandovi.Entity.Labour;
import com.mandovi.Repository.LabourRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LabourServiceImpl implements LabourService {
    private final LabourRepository labourRepository;

    public LabourServiceImpl(LabourRepository labourRepository) {
        this.labourRepository = labourRepository;
    }

    private Double round2Decimal(Double value){
        return Math.round(value*100.0)/100.0;
    }

    @Override
    @Transactional
    public void saveLabourFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            Set<String> bangaloreBranches = new HashSet<>(Arrays.asList(
                    "Wilson Garden","Vijayanagar","JP Nagar","Yeshwanthpur WS","Basaveshwarnagar","Hennur","Sarjapura",
                    "NS Palya","Kolar","Gowribidanur","Uttarahali Kengeri","Vidyarannapura","Yelahanka","Basavangudi",
                    "Basavanagudi-SOW","Kolar Nexa","Malur SOW","Maluru WS"
            ));
            Set<String> mysoreBranches = new HashSet<>(Arrays.asList(
                    "Bannur","ChamrajNagar","Gonikoppa","Hunsur Road","Krishnarajapet","KRS Road","Kushalnagar","Maddur",
                    "Mandya","Mysore Nexa","Nagamangala","Narasipura","Somvarpet","Kollegal","Kollegala"

            ));
            Set<String> mangaloreBranches = new HashSet<>(Arrays.asList(
                    "Balmatta","Bantwal","Nexa Service","Vittla","Kadaba","Uppinangady","Surathkal","Sullia","Adyar","Naravi",
                    "Sujith Bagh Lane","Yeyyadi BR"
            ));
            Set<String> othersLoadType = new HashSet<>(Arrays.asList(
                    "ACC","BDW","CDS","CVMS","REFF","TV1","TV2","TV3","WASH","WMOS",
                    "FR4","PMSTV","TRN","FR","RJ","IFC"
            ));

            for(int i=1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Labour labour = new Labour();

                labour.setBranch(row.getCell(0).getStringCellValue());
                labour.setChannel(row.getCell(1).getStringCellValue());
                labour.setYear(row.getCell(2).getStringCellValue());
                labour.setMonth(row.getCell(3).getStringCellValue());
                labour.setServiceTypeCode(row.getCell(4).getStringCellValue());
                labour.setSumOfBasicAmt(round2Decimal(row.getCell(5).getNumericCellValue()));

                //Updating labour column by dividing the sumofbasicamt;s value from 100000
                labour.setLabour(round2Decimal(labour.getSumOfBasicAmt()/100000));

                //Updating column period by concating columns month & year
                labour.setPeriod(labour.getMonth()+"-"+labour.getYear());

                //Updating the city column by checking value stored in column branch
                String branch = labour.getBranch();
                if (bangaloreBranches.contains(branch)) {
                    labour.setCity("Bangalore");
                }else if (mysoreBranches.contains(branch)) {
                    labour.setCity("Mysore");
                }else if (mangaloreBranches.contains(branch)) {
                    labour.setCity("Mangalore");
                }else {
                    labour.setCity("Unknown");
                }

                //Updating Financialyear column by checking the columns month & year
                int year = Integer.parseInt(labour.getYear());
                String monthh = labour.getMonth();
                switch (monthh) {
                    case "Apr", "May", "Jun", "APR", "MAY", "JUN",
                         "Jul", "Aug", "Sep", "JUL", "AUG", "SEP",
                         "Oct", "Nov", "Dec", "OCT", "NOV", "DEC" -> labour.setFinancialYear(year+"-"+(year+1));
                    case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR" -> labour.setFinancialYear((year-1)+"-"+year);
                }

                //Updating the loadType column by checking the value stored in servicetypecode
                String serviceTypeCode = labour.getServiceTypeCode();
                if (othersLoadType.contains(serviceTypeCode)) {
                    labour.setLoadType("OTHERS");
                }
                switch (serviceTypeCode) {
                    case "BANDP" -> labour.setLoadType("BODYSHOP");
                    case "FR1", "FR2", "FR3" -> labour.setLoadType("FREE SERVICE");
                    case "SC", "CCP" -> labour.setLoadType("NO");
                    case "PMS" -> labour.setLoadType("PMS");
                    case "RR" -> labour.setLoadType("RR");
                }
                String month = labour.getMonth();
                switch (month) {
                    case "Apr", "May", "Jun", "APR", "MAY", "JUN" ->{ labour.setQtrWise("Qtr1"); labour.setHalfYear("H1");}
                    case "Jul", "Aug", "Sep", "JUL", "AUG", "SEP" ->{ labour.setQtrWise("Qtr2"); labour.setHalfYear("H1");}
                    case "Oct", "Nov", "Dec", "OCT", "NOV", "DEC" ->{ labour.setQtrWise("Qtr3"); labour.setHalfYear("H2");}
                    case "Jan", "Feb", "Mar", "JAN", "FEB", "MAR" ->{ labour.setQtrWise("Qtr4"); labour.setHalfYear("H2");}
                }

                labourRepository.save(labour);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Labour> getAllLabour() {
        return labourRepository.findAll();
    }

    @Override
    public List<Labour> getLabourByMonthYear(String month, String year) {
        String formattedMonth = month.substring(0, 1).toUpperCase()+ month.substring(1).toLowerCase();
        return labourRepository.getLabourByMonthYear(formattedMonth, year);
    }

    @Override
    public List<LabourSummaryDTO> getLabourSummary(String groupBy, String month, String qtrWise, String halfYear) {
        if (groupBy == null || groupBy.isEmpty()) {
            throw new IllegalArgumentException("groupBy Parameter is Required");
        }
        switch (groupBy.toLowerCase()){
            case "city" : return labourRepository.getLabourSummaryByCity(month, qtrWise, halfYear);
            case "branch" : return labourRepository.getLabourSummaryByBranch(month, qtrWise, halfYear);
            case "city_branch" : return labourRepository.getLabourSummaryByCityAndBranch(month, qtrWise, halfYear);
            default: throw new IllegalArgumentException("groupBy Parameter is Invalid");
        }
    }

}
