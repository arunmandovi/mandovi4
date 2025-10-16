package com.mandovi.DTO;

public class VASSummaryDTO {
    private String city;
    private String branch;
    private Double diagnosticChargesPMSLoad;
    private Long diagnosticChargesNoOfVehicles;
    private Double diagnosticChargesLabourEarning;
    private Double diagnosticChargesPercentagePMSLoad;
    private Double wheelAlignmentThirdFRSAndPMSLoad;
    private Long wheelAlignmentNoOfVehicles;
    private Double wheelAlignmentEarning;
    private Double wheelAlignmentPercentageAge;
    private Double wheelBalancingThirdFRSAndPMSLoad;
    private Long wheelBalancingNoOfVehicles;
    private Double wheelBalancingEarning;
    private Double wheelBalancingPercentageAge;
    private Double exteriorCleaningFPRAndBodyShopLoad;
    private Long exteriorCleaningNoOfVehicles;
    private Double exteriorCleaningAmount;
    private Double exteriorCleaningPercentageAge;
    private Long interiorCleaningNoOfVehicles;
    private Double interiorCleaningAmount;
    private Double interiorCleaningPercentageAge;

    public VASSummaryDTO() {
    }

    public VASSummaryDTO(String city, String branch, Double diagnosticChargesPMSLoad, Long diagnosticChargesNoOfVehicles, Double diagnosticChargesLabourEarning, Double diagnosticChargesPercentagePMSLoad, Double wheelAlignmentThirdFRSAndPMSLoad, Long wheelAlignmentNoOfVehicles, Double wheelAlignmentEarning, Double wheelAlignmentPercentageAge, Double wheelBalancingThirdFRSAndPMSLoad, Long wheelBalancingNoOfVehicles, Double wheelBalancingEarning, Double wheelBalancingPercentageAge, Double exteriorCleaningFPRAndBodyShopLoad, Long exteriorCleaningNoOfVehicles, Double exteriorCleaningAmount, Double exteriorCleaningPercentageAge, Long interiorCleaningNoOfVehicles, Double interiorCleaningAmount, Double interiorCleaningPercentageAge) {
        this.city = city;
        this.branch = branch;
        this.diagnosticChargesPMSLoad = diagnosticChargesPMSLoad;
        this.diagnosticChargesNoOfVehicles = diagnosticChargesNoOfVehicles;
        this.diagnosticChargesLabourEarning = diagnosticChargesLabourEarning;
        this.diagnosticChargesPercentagePMSLoad = diagnosticChargesPercentagePMSLoad;
        this.wheelAlignmentThirdFRSAndPMSLoad = wheelAlignmentThirdFRSAndPMSLoad;
        this.wheelAlignmentNoOfVehicles = wheelAlignmentNoOfVehicles;
        this.wheelAlignmentEarning = wheelAlignmentEarning;
        this.wheelAlignmentPercentageAge = wheelAlignmentPercentageAge;
        this.wheelBalancingThirdFRSAndPMSLoad = wheelBalancingThirdFRSAndPMSLoad;
        this.wheelBalancingNoOfVehicles = wheelBalancingNoOfVehicles;
        this.wheelBalancingEarning = wheelBalancingEarning;
        this.wheelBalancingPercentageAge = wheelBalancingPercentageAge;
        this.exteriorCleaningFPRAndBodyShopLoad = exteriorCleaningFPRAndBodyShopLoad;
        this.exteriorCleaningNoOfVehicles = exteriorCleaningNoOfVehicles;
        this.exteriorCleaningAmount = exteriorCleaningAmount;
        this.exteriorCleaningPercentageAge = exteriorCleaningPercentageAge;
        this.interiorCleaningNoOfVehicles = interiorCleaningNoOfVehicles;
        this.interiorCleaningAmount = interiorCleaningAmount;
        this.interiorCleaningPercentageAge = interiorCleaningPercentageAge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Double getDiagnosticChargesPMSLoad() {
        return diagnosticChargesPMSLoad;
    }

    public void setDiagnosticChargesPMSLoad(Double diagnosticChargesPMSLoad) {
        this.diagnosticChargesPMSLoad = diagnosticChargesPMSLoad;
    }

    public Long getDiagnosticChargesNoOfVehicles() {
        return diagnosticChargesNoOfVehicles;
    }

    public void setDiagnosticChargesNoOfVehicles(Long diagnosticChargesNoOfVehicles) {
        this.diagnosticChargesNoOfVehicles = diagnosticChargesNoOfVehicles;
    }

    public Double getDiagnosticChargesLabourEarning() {
        return diagnosticChargesLabourEarning;
    }

    public void setDiagnosticChargesLabourEarning(Double diagnosticChargesLabourEarning) {
        this.diagnosticChargesLabourEarning = diagnosticChargesLabourEarning;
    }

    public Double getDiagnosticChargesPercentagePMSLoad() {
        return diagnosticChargesPercentagePMSLoad;
    }

    public void setDiagnosticChargesPercentagePMSLoad(Double diagnosticChargesPercentagePMSLoad) {
        this.diagnosticChargesPercentagePMSLoad = diagnosticChargesPercentagePMSLoad;
    }

    public Double getWheelAlignmentThirdFRSAndPMSLoad() {
        return wheelAlignmentThirdFRSAndPMSLoad;
    }

    public void setWheelAlignmentThirdFRSAndPMSLoad(Double wheelAlignmentThirdFRSAndPMSLoad) {
        this.wheelAlignmentThirdFRSAndPMSLoad = wheelAlignmentThirdFRSAndPMSLoad;
    }

    public Long getWheelAlignmentNoOfVehicles() {
        return wheelAlignmentNoOfVehicles;
    }

    public void setWheelAlignmentNoOfVehicles(Long wheelAlignmentNoOfVehicles) {
        this.wheelAlignmentNoOfVehicles = wheelAlignmentNoOfVehicles;
    }

    public Double getWheelAlignmentEarning() {
        return wheelAlignmentEarning;
    }

    public void setWheelAlignmentEarning(Double wheelAlignmentEarning) {
        this.wheelAlignmentEarning = wheelAlignmentEarning;
    }

    public Double getWheelAlignmentPercentageAge() {
        return wheelAlignmentPercentageAge;
    }

    public void setWheelAlignmentPercentageAge(Double wheelAlignmentPercentageAge) {
        this.wheelAlignmentPercentageAge = wheelAlignmentPercentageAge;
    }

    public Double getWheelBalancingThirdFRSAndPMSLoad() {
        return wheelBalancingThirdFRSAndPMSLoad;
    }

    public void setWheelBalancingThirdFRSAndPMSLoad(Double wheelBalancingThirdFRSAndPMSLoad) {
        this.wheelBalancingThirdFRSAndPMSLoad = wheelBalancingThirdFRSAndPMSLoad;
    }

    public Long getWheelBalancingNoOfVehicles() {
        return wheelBalancingNoOfVehicles;
    }

    public void setWheelBalancingNoOfVehicles(Long wheelBalancingNoOfVehicles) {
        this.wheelBalancingNoOfVehicles = wheelBalancingNoOfVehicles;
    }

    public Double getWheelBalancingEarning() {
        return wheelBalancingEarning;
    }

    public void setWheelBalancingEarning(Double wheelBalancingEarning) {
        this.wheelBalancingEarning = wheelBalancingEarning;
    }

    public Double getWheelBalancingPercentageAge() {
        return wheelBalancingPercentageAge;
    }

    public void setWheelBalancingPercentageAge(Double wheelBalancingPercentageAge) {
        this.wheelBalancingPercentageAge = wheelBalancingPercentageAge;
    }

    public Double getExteriorCleaningFPRAndBodyShopLoad() {
        return exteriorCleaningFPRAndBodyShopLoad;
    }

    public void setExteriorCleaningFPRAndBodyShopLoad(Double exteriorCleaningFPRAndBodyShopLoad) {
        this.exteriorCleaningFPRAndBodyShopLoad = exteriorCleaningFPRAndBodyShopLoad;
    }

    public Long getExteriorCleaningNoOfVehicles() {
        return exteriorCleaningNoOfVehicles;
    }

    public void setExteriorCleaningNoOfVehicles(Long exteriorCleaningNoOfVehicles) {
        this.exteriorCleaningNoOfVehicles = exteriorCleaningNoOfVehicles;
    }

    public Double getExteriorCleaningAmount() {
        return exteriorCleaningAmount;
    }

    public void setExteriorCleaningAmount(Double exteriorCleaningAmount) {
        this.exteriorCleaningAmount = exteriorCleaningAmount;
    }

    public Double getExteriorCleaningPercentageAge() {
        return exteriorCleaningPercentageAge;
    }

    public void setExteriorCleaningPercentageAge(Double exteriorCleaningPercentageAge) {
        this.exteriorCleaningPercentageAge = exteriorCleaningPercentageAge;
    }

    public Long getInteriorCleaningNoOfVehicles() {
        return interiorCleaningNoOfVehicles;
    }

    public void setInteriorCleaningNoOfVehicles(Long interiorCleaningNoOfVehicles) {
        this.interiorCleaningNoOfVehicles = interiorCleaningNoOfVehicles;
    }

    public Double getInteriorCleaningAmount() {
        return interiorCleaningAmount;
    }

    public void setInteriorCleaningAmount(Double interiorCleaningAmount) {
        this.interiorCleaningAmount = interiorCleaningAmount;
    }

    public Double getInteriorCleaningPercentageAge() {
        return interiorCleaningPercentageAge;
    }

    public void setInteriorCleaningPercentageAge(Double interiorCleaningPercentageAge) {
        this.interiorCleaningPercentageAge = interiorCleaningPercentageAge;
    }

    @Override
    public String toString() {
        return "VASSummary{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", diagnosticChargesPMSLoad=" + diagnosticChargesPMSLoad +
                ", diagnosticChargesNoOfVehicles=" + diagnosticChargesNoOfVehicles +
                ", diagnosticChargesLabourEarning=" + diagnosticChargesLabourEarning +
                ", diagnosticChargesPercentagePMSLoad=" + diagnosticChargesPercentagePMSLoad +
                ", wheelAlignmentThirdFRSAndPMSLoad=" + wheelAlignmentThirdFRSAndPMSLoad +
                ", wheelAlignmentNoOfVehicles=" + wheelAlignmentNoOfVehicles +
                ", wheelAlignmentEarning=" + wheelAlignmentEarning +
                ", wheelAlignmentPercentageAge=" + wheelAlignmentPercentageAge +
                ", wheelBalancingThirdFRSAndPMSLoad=" + wheelBalancingThirdFRSAndPMSLoad +
                ", wheelBalancingNoOfVehicles=" + wheelBalancingNoOfVehicles +
                ", wheelBalancingEarning=" + wheelBalancingEarning +
                ", wheelBalancingPercentageAge=" + wheelBalancingPercentageAge +
                ", exteriorCleaningFPRAndBodyShopLoad=" + exteriorCleaningFPRAndBodyShopLoad +
                ", exteriorCleaningNoOfVehicles=" + exteriorCleaningNoOfVehicles +
                ", exteriorCleaningAmount=" + exteriorCleaningAmount +
                ", exteriorCleaningPercentageAge=" + exteriorCleaningPercentageAge +
                ", interiorCleaningNoOfVehicles=" + interiorCleaningNoOfVehicles +
                ", interiorCleaningAmount=" + interiorCleaningAmount +
                ", interiorCleaningPercentageAge=" + interiorCleaningPercentageAge +
                '}';
    }
}
