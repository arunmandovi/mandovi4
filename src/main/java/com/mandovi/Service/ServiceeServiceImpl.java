package com.mandovi.Service;

import com.mandovi.DTO.ServiceeSummaryDTO;
import com.mandovi.Entity.Servicee;
import com.mandovi.Repository.ServiceeRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ServiceeServiceImpl implements ServiceeService{
    private final ServiceeRepository serviceRepository;

    public ServiceeServiceImpl(ServiceeRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void saveServiceExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i<=sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if (row == null)continue;

                Row firstRow = sheet.getRow(1);
                String uploadMonth = firstRow.getCell(2).getStringCellValue();
                Cell yearCell = firstRow.getCell(3);
                int numYear = yearCell.getCellType() == CellType.NUMERIC
                        ? (int) yearCell.getNumericCellValue() : Integer.parseInt(yearCell.getStringCellValue());
                String uploadYear = String.valueOf(numYear);

                serviceRepository.deleteByMonthYear(uploadMonth, uploadYear);

                Servicee servicee = new Servicee();

                servicee.setCity(row.getCell(0).getStringCellValue());
                servicee.setBranch(row.getCell(1).getStringCellValue());

//                String locationCode = row.getCell(1).getStringCellValue();
//                switch (locationCode.toUpperCase().trim()){
//                    case "BNG" : servicee.setBranch("Sarjapura");break;
//                    case "BSN" : servicee.setBranch("Basaveshwarnagar");break;
//                    case "CDE" : servicee.setBranch("Kolar Nexa");break;
//                    case "CMJ" : servicee.setBranch("Basavangudi");break;
//                    case "GRB" : servicee.setBranch("Gowribidanur");break;
//                    case "HNR" : servicee.setBranch("Hennur");break;
//                    case "JPN" : servicee.setBranch("JP Nagar");break;
//                    case "KDH" : servicee.setBranch("Kolar");break;
//                    case "MAF" : servicee.setBranch("Basavanagudi-SOW");break;
//                    case "MLU" : servicee.setBranch("Malur SOW");break;
//                    case "NXS" : servicee.setBranch("Maluru WS");break;
//                    case "RJN" : servicee.setBranch("Uttarahali Kengeri");break;
//                    case "VDR" : servicee.setBranch("Vidyarannapura");break;
//                    case "VJN" : servicee.setBranch("Vijayanagar");break;
//                    case "WGR" : servicee.setBranch("Wilson Garden");break;
//                    case "YLH" : servicee.setBranch("Yelahanka");break;
//                    case "YPR" : servicee.setBranch("Yeshwanthpur WS");break;
//                    case "BNR" : servicee.setBranch("Bannur");break;
//                    case "CMR" : servicee.setBranch("ChamrajNagar");break;
//                    case "HSR" : servicee.setBranch("Hunsur Road");break;
//                    case "JVR" : servicee.setBranch("Maddur");break;
//                    case "KIV" : servicee.setBranch("Gonikoppa");break;
//                    case "KKE" : servicee.setBranch("Mandya");break;
//                    case "KLG" : servicee.setBranch("Kollegal");break;
//                    case "MNY" : servicee.setBranch("Mandya Nexa"); break;
//                    case "RKV" : servicee.setBranch("Gonikoppa Nexa");break;
//                    case "KRS" : servicee.setBranch("KRS Road");break;
//                    case "KSH" : servicee.setBranch("Kushalnagar");break;
//                    case "KSN" : servicee.setBranch("Krishnarajapet");break;
//                    case "MSE" : servicee.setBranch("Mysore Nexa");break;
//                    case "NGL" : servicee.setBranch("Nagamangala");break;
//                    case "SOM" : servicee.setBranch("Somvarpet");break;
//                    case "TNR" : servicee.setBranch("Narasipura");break;
//                    case "BMR" : servicee.setBranch("Balmatta");break;
//                    case "BTL" : servicee.setBranch("Bantwal");break;
//                    case "VLA" : servicee.setBranch("Vittla");break;
//                    case "KDB" : servicee.setBranch("Kadaba");break;
//                    case "UPA" : servicee.setBranch("Uppinangady");break;
//                    case "SKL" : servicee.setBranch("Surathkal");break;
//                    case "SLL" : servicee.setBranch("Sullia");break;
//                    case "AYR" : servicee.setBranch("Adyar");break;
//                    case "YEY" : servicee.setBranch("Yeyyadi BR");break;
//                    case "MNL" : servicee.setBranch("Nexa Service");break;
//                    case "SJH" : servicee.setBranch("Sujith Bagh Lane");break;
//                    case "SYG" : servicee.setBranch("Naravi");break;
//                    case "BAA" : servicee.setBranch("ShivajiNagar SRV");break;
//                    case "BRE" : servicee.setBranch("CV RamanNagar SRV");break;
//                    default: servicee.setBranch("UNKNOWN");
//                }

                servicee.setMonth(row.getCell(2).getStringCellValue());

                Cell year_cell = row.getCell(3);
                int num_year = year_cell.getCellType() == CellType.NUMERIC
                        ? (int) year_cell.getNumericCellValue() : Integer.parseInt(year_cell.getStringCellValue());
                servicee.setYear(String.valueOf(num_year));


                String serviceCodes = row.getCell(4).getStringCellValue();
                switch (serviceCodes.toUpperCase().trim()){
                    case "PMS2": servicee.setServiceCode("PMS20");break;
                    case "PMS3": servicee.setServiceCode("PMS30");break;
                    case "PMS4": servicee.setServiceCode("PMS40");break;
                    case "PMS5": servicee.setServiceCode("PMS50");break;
                    default:servicee.setServiceCode("MORE THAN PMS50");break;
                }
                servicee.setChannel(row.getCell(5).getStringCellValue());
                servicee.setServiceLoadd((int) row.getCell(6).getNumericCellValue());

                serviceRepository.save(servicee);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Servicee> getServiceeAll() {
        return serviceRepository.findAll();
    }

    @Override
    public List<Servicee> getService(List<String> months, List<String> years) {
        return serviceRepository.getServicee(months, years);
    }

    @Override
    public List<ServiceeSummaryDTO> getServiceeSummaryBranchWise(List<String> months, List<String> years, List<String> branches, List<String> channels, List<String> serviceCodes) {
        return serviceRepository.getServiceSummaryBranchWise(months, years, branches, channels, serviceCodes);
    }
}
