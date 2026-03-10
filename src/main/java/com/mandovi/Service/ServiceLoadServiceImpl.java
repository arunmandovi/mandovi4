package com.mandovi.Service;

import com.mandovi.Entity.ServiceLoad;
import com.mandovi.Repository.ServiceLoadRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

            Row firstRow = sheet.getRow(1);
            String uploadMonth = firstRow.getCell(4).getStringCellValue();
            Cell yearCell = firstRow.getCell(5);
            int numYear =  yearCell.getCellType() == CellType.NUMERIC
                    ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
            String uploadYear = String.valueOf(numYear);

            serviceLoadRepository.deleteByMonthYear(uploadMonth, uploadYear);

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
                    int monthNum = Month.from(date.parse(serviceLoad.getMonth())).getValue();

                    int fyStart = (monthNum >= 4) ? year : year - 1;
                    serviceLoad.setFinancialYear(fyStart + "-" + (fyStart + 1));
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

    @Override
    public List<ServiceLoad> getServiceLoadAll() {
        return serviceLoadRepository.findAll();
    }

    @Override
    public List<ServiceLoad> getServiceLoadByMonthYear(List<String> months, List<String> years) {
        return serviceLoadRepository.getServiceLoadByMonthYear(months, years);
    }

    @Override
    public void deleteServiceLoadAll() {
        serviceLoadRepository.deleteServiceLoadAll();
    }
}
