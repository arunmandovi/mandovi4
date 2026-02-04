package com.mandovi.Service;

import com.mandovi.DTO.PMSPartsSummaryDTO;
import com.mandovi.Entity.PMSParts;
import com.mandovi.Repository.PMSPartsRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class PMSPartsServiceImpl implements PMSPartsService {
    private final PMSPartsRepository pmsPartsRepository;

    public PMSPartsServiceImpl(PMSPartsRepository pmsPartsRepository) {
        this.pmsPartsRepository = pmsPartsRepository;
    }

    private static LocalDate getPeriodFromCell(Cell cell) {
        if (cell == null) return null;

        // Case 1: Excel numeric date
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .withDayOfMonth(1);
        }

        // Case 2: Month-Year stored as STRING (e.g. "Apr 2025")
        if (cell.getCellType() == CellType.STRING) {
            String value = cell.getStringCellValue().trim();

            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("MMM yyyy")
                    .toFormatter(Locale.ENGLISH);

            try {
                YearMonth ym = YearMonth.parse(value, formatter);
                return ym.atDay(1); // 01-MM-YYYY
            } catch (DateTimeParseException e) {
                return null;
            }
        }

        return null;
    }


    @Override
    public void savePMSPartsFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            DataFormatter dataFormatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);

            Row firstRow = sheet.getRow(1);
            if (firstRow ==  null)
                throw new RuntimeException("No Data found in Excel");

            Cell uploadPeriodCell =
                    firstRow.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            LocalDate uploadPeriod = getPeriodFromCell(uploadPeriodCell);

            if (uploadPeriod == null) {
                throw new RuntimeException("Invalid upload period in Excel");
            }
            pmsPartsRepository.deleteByMonth(uploadPeriod);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                PMSParts pmsParts = new PMSParts();

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

                pmsParts.setParent(row.getCell(0).getStringCellValue());
                pmsParts.setLocationCode(row.getCell(1).getStringCellValue());

                Cell periodCell = row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                pmsParts.setPeriod(getPeriodFromCell(periodCell));

                pmsParts.setPartGroup(row.getCell(3).getStringCellValue());
                pmsParts.setRequired((int)row.getCell(4).getNumericCellValue());
                pmsParts.setChanged((int)row.getCell(5).getNumericCellValue());

                //Updating Double data type pms value from Intger required & changed column's values
                Double required = Double.valueOf(pmsParts.getRequired());
                Double changed = Double.valueOf(pmsParts.getChanged());
                pmsParts.setPms(changed/required*100);

                //Updating branch column using values from column location code
                String locationCode = pmsParts.getLocationCode();
                switch (locationCode.trim().toUpperCase()){
                    case "BMR": pmsParts.setBranch("Balmatta"); break;
                    case "BTL": pmsParts.setBranch("Bantwal"); break;
                    case "VLA": pmsParts.setBranch("Vittla"); break;
                    case "KDB": pmsParts.setBranch("Kadaba"); break;
                    case "UPA": pmsParts.setBranch("Uppinangady"); break;
                    case "SKL": pmsParts.setBranch("Surathkal"); break;
                    case "SLL": pmsParts.setBranch("Sullia"); break;
                    case "AYR": pmsParts.setBranch("Adyar"); break;
                    case "YEY": pmsParts.setBranch("Yeyyadi BR"); break;
                    case "MNL": pmsParts.setBranch("Nexa Service"); break;
                    case "SJH": pmsParts.setBranch("Sujith Bagh Lane"); break;
                    case "SYG": pmsParts.setBranch("Naravi"); break;
                    case "BKH": pmsParts.setBranch("NS Palya"); break;
                    case "BNG": pmsParts.setBranch("Sarjapura"); break;
                    case "BNR": pmsParts.setBranch("Bannur"); break;
                    case "BSN": pmsParts.setBranch("Basaveshwarnagar"); break;
                    case "CDE": pmsParts.setBranch("Kolar Nexa"); break;
                    case "CMJ": pmsParts.setBranch("Basavangudi"); break;
                    case "CMR": pmsParts.setBranch("ChamrajNagar"); break;
                    case "GRB": pmsParts.setBranch("Gowribidanur"); break;
                    case "HNR": pmsParts.setBranch("Hennur"); break;
                    case "HSR": pmsParts.setBranch("Hunsur Road"); break;
                    case "JPN": pmsParts.setBranch("JP Nagar"); break;
                    case "JVR": pmsParts.setBranch("Maddur"); break;
                    case "KDH": pmsParts.setBranch("Kolar"); break;
                    case "KIV": pmsParts.setBranch("Gonikoppa"); break;
                    case "KKE": pmsParts.setBranch("Mandya"); break;
                    case "KRS": pmsParts.setBranch("KRS Road"); break;
                    case "KSH": pmsParts.setBranch("Kushalnagar"); break;
                    case "KSN": pmsParts.setBranch("Krishnarajapet"); break;
                    case "MAF": pmsParts.setBranch("Basavanagudi-SOW"); break;
                    case "MLU": pmsParts.setBranch("Malur SOW"); break;
                    case "MSE": pmsParts.setBranch("Mysore Nexa"); break;
                    case "NGL": pmsParts.setBranch("Nagamangala"); break;
                    case "NXS": pmsParts.setBranch("Maluru WS"); break;
                    case "RJN": pmsParts.setBranch("Uttarahali Kengeri"); break;
                    case "SOM": pmsParts.setBranch("Somvarpet"); break;
                    case "TNR": pmsParts.setBranch("Narasipura"); break;
                    case "VDR": pmsParts.setBranch("Vidyarannapura"); break;
                    case "VJN": pmsParts.setBranch("Vijayanagar"); break;
                    case "WGR": pmsParts.setBranch("Wilson Garden"); break;
                    case "YLH": pmsParts.setBranch("Yelahanka"); break;
                    case "YPR": pmsParts.setBranch("Yeshwanthpur WS"); break;
                    case "KLG": pmsParts.setBranch("Kollegal"); break;
                    case "MNY": pmsParts.setBranch("Mandya Nexa"); break;
                    default: pmsParts.setBranch("Unknown"); break;
                }

                // Auto update city based on location
                String location = pmsParts.getLocationCode();

                if (bangaloreBranches.contains(location)) {
                    pmsParts.setCity("Bangalore");
                } else if (mysoreBranches.contains(location)) {
                    pmsParts.setCity("Mysore");
                } else if (mangaloreLocations.contains(location)) {
                    pmsParts.setCity("Mangalore");
                } else {
                    pmsParts.setCity("Unknown");
                }

                //Updating month column using the value stored in date column
                LocalDate date = pmsParts.getPeriod();
                DateTimeFormatter sdf = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
                pmsParts.setMonth(sdf.format(pmsParts.getPeriod()));
                DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
                pmsParts.setYear(yearFormatter.format(pmsParts.getPeriod()));

                switch (pmsParts.getMonth().trim().toUpperCase()) {
                    case "APR", "MAY", "JUN" ->{ pmsParts.setQtrWise("Qtr1"); pmsParts.setHalfYear("H1");}
                    case "JUL", "AUG", "SEP" ->{ pmsParts.setQtrWise("Qtr2"); pmsParts.setHalfYear("H1");}
                    case "OCT", "NOV", "DEC" ->{ pmsParts.setQtrWise("Qtr3"); pmsParts.setHalfYear("H2");}
                    case "JAN", "FEB", "MAR" ->{ pmsParts.setQtrWise("Qtr4"); pmsParts.setHalfYear("H2");}

                }
                pmsPartsRepository.save(pmsParts);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PMSParts> getAllPMS_Parts() {
        return pmsPartsRepository.findAll();
    }

    @Override
    public List<PMSParts> getPMSPartsByMonthYear(List<String> months, List<String> years) {
        return pmsPartsRepository.getPMSPartsByMonthYear(months, years);
    }

    @Override
    public List<PMSPartsSummaryDTO> getPMSPartsSummary(List<String> months, List<String> qtrWise, List<String> halfYear) {
        return pmsPartsRepository.getPMSPartsSummaryByCity(months, qtrWise, halfYear);
    }

    @Override
    public List<PMSPartsSummaryDTO> getPMSPartsSummaryBranchWise(List<String> months, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return pmsPartsRepository.getPMSPartsSummaryBranchWise(months, cities, qtrWise, halfYear);
    }

    @Override
    public void deletePMSPartsAll() {
        pmsPartsRepository.deletePMSPartsALl();
    }
}
