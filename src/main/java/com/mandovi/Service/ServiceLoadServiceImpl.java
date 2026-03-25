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
                String serviceSubType = row.getCell(2).getStringCellValue();
                switch (serviceSubType.toUpperCase()){
                    case "ACC","CDS","FR","FR4","REFF","RJ","TV1","TV2","TV3","OTHERS",
                         "WASH","WMOS","CVMS","PMSTV","BDW","TRN","IFC","IPC": serviceLoad.setServiceType("OTHERS");break;
                    case "BANDP", "BODYSHOP": serviceLoad.setServiceType("BODYSHOP");break;
                    case "FR1","FR2","FR3","FREE SERVICE": serviceLoad.setServiceType("FREE SERVICE");break;
                    case "CCP","SC","NO": serviceLoad.setServiceType("NO");break;
                    case "PMS": serviceLoad.setServiceType("PMS");break;
                    case "RR" : serviceLoad.setServiceType("RR");break;
                    default:serviceLoad.setServiceType("UNKNOWN");break;
                }
                String servicePMSType = null;

                if (row.getCell(3) != null) {
                    servicePMSType = row.getCell(3).getStringCellValue();
                }

                if (servicePMSType != null) {
                    String value = servicePMSType.toUpperCase().trim();

                    switch (value) {
                        case "PMS2": serviceLoad.setServiceSubType("PMS2");break;
                        case "PMS3": serviceLoad.setServiceSubType("PMS3");break;
                        case "PMS4": serviceLoad.setServiceSubType("PMS4");break;
                        case "PMS5": serviceLoad.setServiceSubType("PMS5");break;
                        default:
                            if (value.contains("PMS")) {
                                serviceLoad.setServiceSubType("MORE THAN PMS5");
                            } else {
                                serviceLoad.setServiceSubType(null);
                            }
                            break;
                    }
                } else {
                    serviceLoad.setServiceSubType(null);
                }

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
