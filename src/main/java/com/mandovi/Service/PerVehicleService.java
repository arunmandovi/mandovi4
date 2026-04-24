package com.mandovi.Service;

import com.mandovi.DTO.PerVehicleReportSummaryDTO;

import java.util.List;

public interface PerVehicleService {
    public List<PerVehicleReportSummaryDTO> getPerVehicleSummary (
            List<String> months, List<String> years, List<String> qtrWise, List<String> halfYear, List<String> financialYears);

    public List<PerVehicleReportSummaryDTO> getPerVehicleSummaryBranchWise (
            List<String> months, List<String> years, List<String> cities,
            List<String> qtrWise, List<String> halfYear, List<String> financialYears );
}
