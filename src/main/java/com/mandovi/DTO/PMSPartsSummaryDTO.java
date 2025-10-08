package com.mandovi.DTO;

public class PMSPartsSummaryDTO {
    private String city;
    private String branch;
    private Double airFilter;
    private Double beltWaterPump;
    private Double brakeFluid;
    private Double coolant;
    private Double fuelFilter;
    private Double oilFilter;
    private Double sparkPlug;
    private Double sevenPartsPMS;
    private Double drainPlugGasket;
    private Double isgBeltGenerator;
    private Double cngFilter;
    private Double threePartsPMS;
    private Double grandTotal;

    public PMSPartsSummaryDTO() {
    }

    public PMSPartsSummaryDTO(String city, String branch, Double airFilter, Double beltWaterPump, Double brakeFluid, Double coolant, Double fuelFilter, Double oilFilter, Double sparkPlug, Double sevenPartsPMS, Double drainPlugGasket, Double isgBeltGenerator, Double cngFilter, Double threePartsPMS, Double grandTotal) {
        this.city = city;
        this.branch = branch;
        this.airFilter = airFilter;
        this.beltWaterPump = beltWaterPump;
        this.brakeFluid = brakeFluid;
        this.coolant = coolant;
        this.fuelFilter = fuelFilter;
        this.oilFilter = oilFilter;
        this.sparkPlug = sparkPlug;
        this.sevenPartsPMS = sevenPartsPMS;
        this.drainPlugGasket = drainPlugGasket;
        this.isgBeltGenerator = isgBeltGenerator;
        this.cngFilter = cngFilter;
        this.threePartsPMS = threePartsPMS;
        this.grandTotal = grandTotal;
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

    public Double getAirFilter() {
        return airFilter;
    }

    public void setAirFilter(Double airFilter) {
        this.airFilter = airFilter;
    }

    public Double getBeltWaterPump() {
        return beltWaterPump;
    }

    public void setBeltWaterPump(Double beltWaterPump) {
        this.beltWaterPump = beltWaterPump;
    }

    public Double getBrakeFluid() {
        return brakeFluid;
    }

    public void setBrakeFluid(Double brakeFluid) {
        this.brakeFluid = brakeFluid;
    }

    public Double getCoolant() {
        return coolant;
    }

    public void setCoolant(Double coolant) {
        this.coolant = coolant;
    }

    public Double getFuelFilter() {
        return fuelFilter;
    }

    public void setFuelFilter(Double fuelFilter) {
        this.fuelFilter = fuelFilter;
    }

    public Double getOilFilter() {
        return oilFilter;
    }

    public void setOilFilter(Double oilFilter) {
        this.oilFilter = oilFilter;
    }

    public Double getSparkPlug() {
        return sparkPlug;
    }

    public void setSparkPlug(Double sparkPlug) {
        this.sparkPlug = sparkPlug;
    }

    public Double getSevenPartsPMS() {
        return sevenPartsPMS;
    }

    public void setSevenPartsPMS(Double sevenPartsPMS) {
        this.sevenPartsPMS = sevenPartsPMS;
    }

    public Double getDrainPlugGasket() {
        return drainPlugGasket;
    }

    public void setDrainPlugGasket(Double drainPlugGasket) {
        this.drainPlugGasket = drainPlugGasket;
    }

    public Double getIsgBeltGenerator() {
        return isgBeltGenerator;
    }

    public void setIsgBeltGenerator(Double isgBeltGenerator) {
        this.isgBeltGenerator = isgBeltGenerator;
    }

    public Double getCngFilter() {
        return cngFilter;
    }

    public void setCngFilter(Double cngFilter) {
        this.cngFilter = cngFilter;
    }

    public Double getThreePartsPMS() {
        return threePartsPMS;
    }

    public void setThreePartsPMS(Double threePartsPMS) {
        this.threePartsPMS = threePartsPMS;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() {
        return "PMSPartsSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", airFilter=" + airFilter +
                ", beltWaterPump=" + beltWaterPump +
                ", brakeFluid=" + brakeFluid +
                ", coolant=" + coolant +
                ", fuelFilter=" + fuelFilter +
                ", oilFilter=" + oilFilter +
                ", sparkPlug=" + sparkPlug +
                ", sevenPartsPMS=" + sevenPartsPMS +
                ", drainPlugGasket=" + drainPlugGasket +
                ", isgBeltGenerator=" + isgBeltGenerator +
                ", cngFilter=" + cngFilter +
                ", threePartsPMS=" + threePartsPMS +
                ", grandTotal=" + grandTotal +
                '}';
    }
}
