package com.mandovi.Service;

import com.mandovi.Entity.ServiceLoad;
import com.mandovi.Repository.ServiceLoadRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class ServiceLoadServiceImpl implements ServiceLoadService{
    private final ServiceLoadRepository serviceLoadRepository;

    public ServiceLoadServiceImpl(ServiceLoadRepository serviceLoadRepository) {
        this.serviceLoadRepository = serviceLoadRepository;
    }


    @Override
    public void saveServiceLoad(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DateTimeFormatter date = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);

            for (int i = 1; i<= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if (row == null) continue;

                ServiceLoad serviceLoad = new ServiceLoad();
                serviceLoad.setCity(row.getCell(0).getStringCellValue());
                serviceLoad.setBranch(row.getCell(1).getStringCellValue());
                serviceLoad.setServiceType(row.getCell(2).getStringCellValue());
                switch (serviceLoad.getServiceType().toUpperCase()){
                    case "ACC","CDS","FR","FR4","REFF","RJ","TV1","TV2","TV3",
                         "WASH","WMOS","CVMS","PMSTV","BDW","TRN","IFC","IPC": serviceLoad.setServiceMainType("OTHERS");break;
                    case "BANDP": serviceLoad.setServiceMainType("BODYSHOP");break;
                    case "FR1","FR2","FR3": serviceLoad.setServiceMainType("FREE SERVICE");break;
                    case "CCP","SC": serviceLoad.setServiceMainType("NO");break;
                    case "PMS": serviceLoad.setServiceMainType("PMS");break;
                    case "RR" : serviceLoad.setServiceMainType("RR");break;
                    default:serviceLoad.setServiceMainType("UNKNOWN");break;
                }
                serviceLoad.setServiceSubType(row.getCell(3).getStringCellValue());
                serviceLoad.setMonth(row.getCell(4).getStringCellValue());
                serviceLoad.setYear(row.getCell(5).getStringCellValue());
                try {
                    int year = Integer.parseInt(serviceLoad.getYear());
                    String month = serviceLoad.getMonth();
                    Month m = Month.from(date.parse(month));
                    int monthNum = m.getValue();
                    if (monthNum >= 4)
                        serviceLoad.setFinancialYear(year+"-"+year+1);
                    else
                        serviceLoad.setFinancialYear(year-1+"-"+year);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                serviceLoad.setChannel(row.getCell(6).getStringCellValue());
                serviceLoad.setServiceLoad((int)row.getCell(7).getNumericCellValue());
                serviceLoadRepository.save(serviceLoad);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
