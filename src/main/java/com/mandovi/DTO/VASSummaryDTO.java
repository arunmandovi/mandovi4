package com.mandovi.DTO;

public class VASSummaryDTO {
    private String city;
    private String branch;
    private Double pmsLoadDiagnosticCharges;
    private Double noOfVehiclesDiagnosticCharges;
    private Double labourEarningDiagnosticCharges;
    private Double percentageOfDiagnosticPMSLoad;
    private Double thirdFRSAndPMLLoadWheelAlignment;
    private Double noOfVehiclesWheelAlignment;
    private Double earningWheelAlignment;
    private Double percentageAgeWheelAlignment;
    private Double thirdFRSAndPMSLoadWheelBalancing;
    private Double noOfVehiclesWheelBalancing;
    private Double earningWheelBalancing;
    private Double percentageAgeWheelBalancing;
    private Double FPRAndBDRLoadExteriorCleaning;
    private Double noOfVehiclesExteriorCleaning;
    private Double amountExteriorCleaning;
    private Double percentageAgeExteriorCleaning;
    private Double noOfVehiclesInteriorCleaning;
    private Double amountInteriorCleaning;
    private Double percentageAgeInteriorCleaning;
    private Double noOfVehiclesUnderBodyCoating;
    private Double amountUnderBodyCoating;
    private Double percentageAgeUnderBodyCoating;
    private Double noOfVehiclesTopBodyCoating;
    private Double amountTopBodyCoating;
    private Double percentageAgeTopBodyCoating;
    private Double noOfVehiclesRatMesh;
    private Double amountRatMesh;
    private Double percentageAgeRatMesh;
    private Double noOfVehiclesACEvaporator;
    private Double amountACEvaporator;
    private Double percentageACEvaporator;
    private Double noOfVehiclesACVent;
    private Double amountACVent;
    private Double percentageAgeACVent;
    private Double noOfVehiclesPlasticRestorer;
    private Double amountPlasticRestorer;
    private Double percentageAgePlasticRestorer;

    public VASSummaryDTO() {
    }

    public VASSummaryDTO(String city, String branch, Double pmsLoadDiagnosticCharges, Double noOfVehiclesDiagnosticCharges, Double labourEarningDiagnosticCharges, Double percentageOfDiagnosticPMSLoad, Double thirdFRSAndPMLLoadWheelAlignment, Double noOfVehiclesWheelAlignment, Double earningWheelAlignment, Double percentageAgeWheelAlignment, Double thirdFRSAndPMSLoadWheelBalancing, Double noOfVehiclesWheelBalancing, Double earningWheelBalancing, Double percentageAgeWheelBalancing, Double FPRAndBDRLoadExteriorCleaning, Double noOfVehiclesExteriorCleaning, Double amountExteriorCleaning, Double percentageAgeExteriorCleaning, Double noOfVehiclesInteriorCleaning, Double amountInteriorCleaning, Double percentageAgeInteriorCleaning, Double noOfVehiclesUnderBodyCoating, Double amountUnderBodyCoating, Double percentageAgeUnderBodyCoating, Double noOfVehiclesTopBodyCoating, Double amountTopBodyCoating, Double percentageAgeTopBodyCoating, Double noOfVehiclesRatMesh, Double amountRatMesh, Double percentageAgeRatMesh, Double noOfVehiclesACEvaporator, Double amountACEvaporator, Double percentageACEvaporator, Double noOfVehiclesACVent, Double amountACVent, Double percentageAgeACVent, Double noOfVehiclesPlasticRestorer, Double amountPlasticRestorer, Double percentageAgePlasticRestorer) {
        this.city = city;
        this.branch = branch;
        this.pmsLoadDiagnosticCharges = pmsLoadDiagnosticCharges;
        this.noOfVehiclesDiagnosticCharges = noOfVehiclesDiagnosticCharges;
        this.labourEarningDiagnosticCharges = labourEarningDiagnosticCharges;
        this.percentageOfDiagnosticPMSLoad = percentageOfDiagnosticPMSLoad;
        this.thirdFRSAndPMLLoadWheelAlignment = thirdFRSAndPMLLoadWheelAlignment;
        this.noOfVehiclesWheelAlignment = noOfVehiclesWheelAlignment;
        this.earningWheelAlignment = earningWheelAlignment;
        this.percentageAgeWheelAlignment = percentageAgeWheelAlignment;
        this.thirdFRSAndPMSLoadWheelBalancing = thirdFRSAndPMSLoadWheelBalancing;
        this.noOfVehiclesWheelBalancing = noOfVehiclesWheelBalancing;
        this.earningWheelBalancing = earningWheelBalancing;
        this.percentageAgeWheelBalancing = percentageAgeWheelBalancing;
        this.FPRAndBDRLoadExteriorCleaning = FPRAndBDRLoadExteriorCleaning;
        this.noOfVehiclesExteriorCleaning = noOfVehiclesExteriorCleaning;
        this.amountExteriorCleaning = amountExteriorCleaning;
        this.percentageAgeExteriorCleaning = percentageAgeExteriorCleaning;
        this.noOfVehiclesInteriorCleaning = noOfVehiclesInteriorCleaning;
        this.amountInteriorCleaning = amountInteriorCleaning;
        this.percentageAgeInteriorCleaning = percentageAgeInteriorCleaning;
        this.noOfVehiclesUnderBodyCoating = noOfVehiclesUnderBodyCoating;
        this.amountUnderBodyCoating = amountUnderBodyCoating;
        this.percentageAgeUnderBodyCoating = percentageAgeUnderBodyCoating;
        this.noOfVehiclesTopBodyCoating = noOfVehiclesTopBodyCoating;
        this.amountTopBodyCoating = amountTopBodyCoating;
        this.percentageAgeTopBodyCoating = percentageAgeTopBodyCoating;
        this.noOfVehiclesRatMesh = noOfVehiclesRatMesh;
        this.amountRatMesh = amountRatMesh;
        this.percentageAgeRatMesh = percentageAgeRatMesh;
        this.noOfVehiclesACEvaporator = noOfVehiclesACEvaporator;
        this.amountACEvaporator = amountACEvaporator;
        this.percentageACEvaporator = percentageACEvaporator;
        this.noOfVehiclesACVent = noOfVehiclesACVent;
        this.amountACVent = amountACVent;
        this.percentageAgeACVent = percentageAgeACVent;
        this.noOfVehiclesPlasticRestorer = noOfVehiclesPlasticRestorer;
        this.amountPlasticRestorer = amountPlasticRestorer;
        this.percentageAgePlasticRestorer = percentageAgePlasticRestorer;
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

    public Double getPmsLoadDiagnosticCharges() {
        return pmsLoadDiagnosticCharges;
    }

    public void setPmsLoadDiagnosticCharges(Double pmsLoadDiagnosticCharges) {
        this.pmsLoadDiagnosticCharges = pmsLoadDiagnosticCharges;
    }

    public Double getNoOfVehiclesDiagnosticCharges() {
        return noOfVehiclesDiagnosticCharges;
    }

    public void setNoOfVehiclesDiagnosticCharges(Double noOfVehiclesDiagnosticCharges) {
        this.noOfVehiclesDiagnosticCharges = noOfVehiclesDiagnosticCharges;
    }

    public Double getLabourEarningDiagnosticCharges() {
        return labourEarningDiagnosticCharges;
    }

    public void setLabourEarningDiagnosticCharges(Double labourEarningDiagnosticCharges) {
        this.labourEarningDiagnosticCharges = labourEarningDiagnosticCharges;
    }

    public Double getPercentageOfDiagnosticPMSLoad() {
        return percentageOfDiagnosticPMSLoad;
    }

    public void setPercentageOfDiagnosticPMSLoad(Double percentageOfDiagnosticPMSLoad) {
        this.percentageOfDiagnosticPMSLoad = percentageOfDiagnosticPMSLoad;
    }

    public Double getThirdFRSAndPMLLoadWheelAlignment() {
        return thirdFRSAndPMLLoadWheelAlignment;
    }

    public void setThirdFRSAndPMLLoadWheelAlignment(Double thirdFRSAndPMLLoadWheelAlignment) {
        this.thirdFRSAndPMLLoadWheelAlignment = thirdFRSAndPMLLoadWheelAlignment;
    }

    public Double getNoOfVehiclesWheelAlignment() {
        return noOfVehiclesWheelAlignment;
    }

    public void setNoOfVehiclesWheelAlignment(Double noOfVehiclesWheelAlignment) {
        this.noOfVehiclesWheelAlignment = noOfVehiclesWheelAlignment;
    }

    public Double getEarningWheelAlignment() {
        return earningWheelAlignment;
    }

    public void setEarningWheelAlignment(Double earningWheelAlignment) {
        this.earningWheelAlignment = earningWheelAlignment;
    }

    public Double getPercentageAgeWheelAlignment() {
        return percentageAgeWheelAlignment;
    }

    public void setPercentageAgeWheelAlignment(Double percentageAgeWheelAlignment) {
        this.percentageAgeWheelAlignment = percentageAgeWheelAlignment;
    }

    public Double getThirdFRSAndPMSLoadWheelBalancing() {
        return thirdFRSAndPMSLoadWheelBalancing;
    }

    public void setThirdFRSAndPMSLoadWheelBalancing(Double thirdFRSAndPMSLoadWheelBalancing) {
        this.thirdFRSAndPMSLoadWheelBalancing = thirdFRSAndPMSLoadWheelBalancing;
    }

    public Double getNoOfVehiclesWheelBalancing() {
        return noOfVehiclesWheelBalancing;
    }

    public void setNoOfVehiclesWheelBalancing(Double noOfVehiclesWheelBalancing) {
        this.noOfVehiclesWheelBalancing = noOfVehiclesWheelBalancing;
    }

    public Double getEarningWheelBalancing() {
        return earningWheelBalancing;
    }

    public void setEarningWheelBalancing(Double earningWheelBalancing) {
        this.earningWheelBalancing = earningWheelBalancing;
    }

    public Double getPercentageAgeWheelBalancing() {
        return percentageAgeWheelBalancing;
    }

    public void setPercentageAgeWheelBalancing(Double percentageAgeWheelBalancing) {
        this.percentageAgeWheelBalancing = percentageAgeWheelBalancing;
    }

    public Double getFPRAndBDRLoadExteriorCleaning() {
        return FPRAndBDRLoadExteriorCleaning;
    }

    public void setFPRAndBDRLoadExteriorCleaning(Double FPRAndBDRLoadExteriorCleaning) {
        this.FPRAndBDRLoadExteriorCleaning = FPRAndBDRLoadExteriorCleaning;
    }

    public Double getNoOfVehiclesExteriorCleaning() {
        return noOfVehiclesExteriorCleaning;
    }

    public void setNoOfVehiclesExteriorCleaning(Double noOfVehiclesExteriorCleaning) {
        this.noOfVehiclesExteriorCleaning = noOfVehiclesExteriorCleaning;
    }

    public Double getAmountExteriorCleaning() {
        return amountExteriorCleaning;
    }

    public void setAmountExteriorCleaning(Double amountExteriorCleaning) {
        this.amountExteriorCleaning = amountExteriorCleaning;
    }

    public Double getPercentageAgeExteriorCleaning() {
        return percentageAgeExteriorCleaning;
    }

    public void setPercentageAgeExteriorCleaning(Double percentageAgeExteriorCleaning) {
        this.percentageAgeExteriorCleaning = percentageAgeExteriorCleaning;
    }

    public Double getNoOfVehiclesInteriorCleaning() {
        return noOfVehiclesInteriorCleaning;
    }

    public void setNoOfVehiclesInteriorCleaning(Double noOfVehiclesInteriorCleaning) {
        this.noOfVehiclesInteriorCleaning = noOfVehiclesInteriorCleaning;
    }

    public Double getAmountInteriorCleaning() {
        return amountInteriorCleaning;
    }

    public void setAmountInteriorCleaning(Double amountInteriorCleaning) {
        this.amountInteriorCleaning = amountInteriorCleaning;
    }

    public Double getPercentageAgeInteriorCleaning() {
        return percentageAgeInteriorCleaning;
    }

    public void setPercentageAgeInteriorCleaning(Double percentageAgeInteriorCleaning) {
        this.percentageAgeInteriorCleaning = percentageAgeInteriorCleaning;
    }

    public Double getNoOfVehiclesUnderBodyCoating() {
        return noOfVehiclesUnderBodyCoating;
    }

    public void setNoOfVehiclesUnderBodyCoating(Double noOfVehiclesUnderBodyCoating) {
        this.noOfVehiclesUnderBodyCoating = noOfVehiclesUnderBodyCoating;
    }

    public Double getAmountUnderBodyCoating() {
        return amountUnderBodyCoating;
    }

    public void setAmountUnderBodyCoating(Double amountUnderBodyCoating) {
        this.amountUnderBodyCoating = amountUnderBodyCoating;
    }

    public Double getPercentageAgeUnderBodyCoating() {
        return percentageAgeUnderBodyCoating;
    }

    public void setPercentageAgeUnderBodyCoating(Double percentageAgeUnderBodyCoating) {
        this.percentageAgeUnderBodyCoating = percentageAgeUnderBodyCoating;
    }

    public Double getNoOfVehiclesTopBodyCoating() {
        return noOfVehiclesTopBodyCoating;
    }

    public void setNoOfVehiclesTopBodyCoating(Double noOfVehiclesTopBodyCoating) {
        this.noOfVehiclesTopBodyCoating = noOfVehiclesTopBodyCoating;
    }

    public Double getAmountTopBodyCoating() {
        return amountTopBodyCoating;
    }

    public void setAmountTopBodyCoating(Double amountTopBodyCoating) {
        this.amountTopBodyCoating = amountTopBodyCoating;
    }

    public Double getPercentageAgeTopBodyCoating() {
        return percentageAgeTopBodyCoating;
    }

    public void setPercentageAgeTopBodyCoating(Double percentageAgeTopBodyCoating) {
        this.percentageAgeTopBodyCoating = percentageAgeTopBodyCoating;
    }

    public Double getNoOfVehiclesRatMesh() {
        return noOfVehiclesRatMesh;
    }

    public void setNoOfVehiclesRatMesh(Double noOfVehiclesRatMesh) {
        this.noOfVehiclesRatMesh = noOfVehiclesRatMesh;
    }

    public Double getAmountRatMesh() {
        return amountRatMesh;
    }

    public void setAmountRatMesh(Double amountRatMesh) {
        this.amountRatMesh = amountRatMesh;
    }

    public Double getPercentageAgeRatMesh() {
        return percentageAgeRatMesh;
    }

    public void setPercentageAgeRatMesh(Double percentageAgeRatMesh) {
        this.percentageAgeRatMesh = percentageAgeRatMesh;
    }

    public Double getNoOfVehiclesACEvaporator() {
        return noOfVehiclesACEvaporator;
    }

    public void setNoOfVehiclesACEvaporator(Double noOfVehiclesACEvaporator) {
        this.noOfVehiclesACEvaporator = noOfVehiclesACEvaporator;
    }

    public Double getAmountACEvaporator() {
        return amountACEvaporator;
    }

    public void setAmountACEvaporator(Double amountACEvaporator) {
        this.amountACEvaporator = amountACEvaporator;
    }

    public Double getPercentageACEvaporator() {
        return percentageACEvaporator;
    }

    public void setPercentageACEvaporator(Double percentageACEvaporator) {
        this.percentageACEvaporator = percentageACEvaporator;
    }

    public Double getNoOfVehiclesACVent() {
        return noOfVehiclesACVent;
    }

    public void setNoOfVehiclesACVent(Double noOfVehiclesACVent) {
        this.noOfVehiclesACVent = noOfVehiclesACVent;
    }

    public Double getAmountACVent() {
        return amountACVent;
    }

    public void setAmountACVent(Double amountACVent) {
        this.amountACVent = amountACVent;
    }

    public Double getPercentageAgeACVent() {
        return percentageAgeACVent;
    }

    public void setPercentageAgeACVent(Double percentageAgeACVent) {
        this.percentageAgeACVent = percentageAgeACVent;
    }

    public Double getNoOfVehiclesPlasticRestorer() {
        return noOfVehiclesPlasticRestorer;
    }

    public void setNoOfVehiclesPlasticRestorer(Double noOfVehiclesPlasticRestorer) {
        this.noOfVehiclesPlasticRestorer = noOfVehiclesPlasticRestorer;
    }

    public Double getAmountPlasticRestorer() {
        return amountPlasticRestorer;
    }

    public void setAmountPlasticRestorer(Double amountPlasticRestorer) {
        this.amountPlasticRestorer = amountPlasticRestorer;
    }

    public Double getPercentageAgePlasticRestorer() {
        return percentageAgePlasticRestorer;
    }

    public void setPercentageAgePlasticRestorer(Double percentageAgePlasticRestorer) {
        this.percentageAgePlasticRestorer = percentageAgePlasticRestorer;
    }

    @Override
    public String toString() {
        return "VASSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", pmsLoadDiagnosticCharges=" + pmsLoadDiagnosticCharges +
                ", noOfVehiclesDiagnosticCharges=" + noOfVehiclesDiagnosticCharges +
                ", labourEarningDiagnosticCharges=" + labourEarningDiagnosticCharges +
                ", percentageOfDiagnosticPMSLoad=" + percentageOfDiagnosticPMSLoad +
                ", thirdFRSAndPMLLoadWheelAlignment=" + thirdFRSAndPMLLoadWheelAlignment +
                ", noOfVehiclesWheelAlignment=" + noOfVehiclesWheelAlignment +
                ", earningWheelAlignment=" + earningWheelAlignment +
                ", percentageAgeWheelAlignment=" + percentageAgeWheelAlignment +
                ", thirdFRSAndPMSLoadWheelBalancing=" + thirdFRSAndPMSLoadWheelBalancing +
                ", noOfVehiclesWheelBalancing=" + noOfVehiclesWheelBalancing +
                ", earningWheelBalancing=" + earningWheelBalancing +
                ", percentageAgeWheelBalancing=" + percentageAgeWheelBalancing +
                ", FPRAndBDRLoadExteriorCleaning=" + FPRAndBDRLoadExteriorCleaning +
                ", noOfVehiclesExteriorCleaning=" + noOfVehiclesExteriorCleaning +
                ", amountExteriorCleaning=" + amountExteriorCleaning +
                ", percentageAgeExteriorCleaning=" + percentageAgeExteriorCleaning +
                ", noOfVehiclesInteriorCleaning=" + noOfVehiclesInteriorCleaning +
                ", amountInteriorCleaning=" + amountInteriorCleaning +
                ", percentageAgeInteriorCleaning=" + percentageAgeInteriorCleaning +
                ", noOfVehiclesUnderBodyCoating=" + noOfVehiclesUnderBodyCoating +
                ", amountUnderBodyCoating=" + amountUnderBodyCoating +
                ", percentageAgeUnderBodyCoating=" + percentageAgeUnderBodyCoating +
                ", noOfVehiclesTopBodyCoating=" + noOfVehiclesTopBodyCoating +
                ", amountTopBodyCoating=" + amountTopBodyCoating +
                ", percentageAgeTopBodyCoating=" + percentageAgeTopBodyCoating +
                ", noOfVehiclesRatMesh=" + noOfVehiclesRatMesh +
                ", amountRatMesh=" + amountRatMesh +
                ", percentageAgeRatMesh=" + percentageAgeRatMesh +
                ", noOfVehiclesACEvaporator=" + noOfVehiclesACEvaporator +
                ", amountACEvaporator=" + amountACEvaporator +
                ", percentageACEvaporator=" + percentageACEvaporator +
                ", noOfVehiclesACVent=" + noOfVehiclesACVent +
                ", amountACVent=" + amountACVent +
                ", percentageAgeACVent=" + percentageAgeACVent +
                ", noOfVehiclesPlasticRestorer=" + noOfVehiclesPlasticRestorer +
                ", amountPlasticRestorer=" + amountPlasticRestorer +
                ", percentageAgePlasticRestorer=" + percentageAgePlasticRestorer +
                '}';
    }
}
