package com.mandovi.Service;

import com.mandovi.DTO.PerVehicleReportSummaryDTO;
import com.mandovi.Repository.PerVehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerVehicleServiceImpl implements PerVehicleService{
    private final PerVehicleRepository perVehicleRepository;

    public PerVehicleServiceImpl(PerVehicleRepository perVehicleRepository) {
        this.perVehicleRepository = perVehicleRepository;
    }

    @Override
    public List<PerVehicleReportSummaryDTO> getPerVehicleSummary(List<String> months, List<String> years, List<String> qtrWise, List<String> halfYear) {
        return perVehicleRepository.getPerVehicleSummary(months, years, qtrWise, halfYear);
    }

    @Override
    public List<PerVehicleReportSummaryDTO> getPerVehicleSummaryBranchWise(List<String> months, List<String> years, List<String> cities, List<String> qtrWise, List<String> halfYear) {
        return perVehicleRepository.getPerVehicleSummaryBranchWise(months, years, cities, qtrWise, halfYear);
    }
}
