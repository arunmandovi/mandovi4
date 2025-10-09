package com.mandovi.DTO;

public class BatteryTyreSummaryDTO {

    private String city;
    private String branch;
    private  Long batteryQty;
    private  Double batteryNetRetailDDL;
    private  Double batteryNetRetailSelling;
    private Double batteryProfit;
    private Double batteryPercentageProfit;
    private Long tyreQty;
    private Double tyreNetRetailDDL;
    private Double tyreNetRetailSelling;
    private Double tyreProfit;
    private Double tyrePercentageProfit;
    private Double batteryTyreProfit;
    private Double batteryTyrePercentageProfit;

    public BatteryTyreSummaryDTO() {
    }

    public BatteryTyreSummaryDTO(String city, String branch, Long batteryQty, Double batteryNetRetailDDL, Double batteryNetRetailSelling, Double batteryProfit, Double batteryPercentageProfit, Long tyreQty, Double tyreNetRetailDDL, Double tyreNetRetailSelling, Double tyreProfit, Double tyrePercentageProfit, Double batteryTyreProfit, Double batteryTyrePercentageProfit) {
        this.city = city;
        this.branch = branch;
        this.batteryQty = batteryQty;
        this.batteryNetRetailDDL = batteryNetRetailDDL;
        this.batteryNetRetailSelling = batteryNetRetailSelling;
        this.batteryProfit = batteryProfit;
        this.batteryPercentageProfit = batteryPercentageProfit;
        this.tyreQty = tyreQty;
        this.tyreNetRetailDDL = tyreNetRetailDDL;
        this.tyreNetRetailSelling = tyreNetRetailSelling;
        this.tyreProfit = tyreProfit;
        this.tyrePercentageProfit = tyrePercentageProfit;
        this.batteryTyreProfit = batteryTyreProfit;
        this.batteryTyrePercentageProfit = batteryTyrePercentageProfit;
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

    public Long getBatteryQty() {
        return batteryQty;
    }

    public void setBatteryQty(Long batteryQty) {
        this.batteryQty = batteryQty;
    }

    public Double getBatteryNetRetailDDL() {
        return batteryNetRetailDDL;
    }

    public void setBatteryNetRetailDDL(Double batteryNetRetailDDL) {
        this.batteryNetRetailDDL = batteryNetRetailDDL;
    }

    public Double getBatteryNetRetailSelling() {
        return batteryNetRetailSelling;
    }

    public void setBatteryNetRetailSelling(Double batteryNetRetailSelling) {
        this.batteryNetRetailSelling = batteryNetRetailSelling;
    }

    public Double getBatteryProfit() {
        return batteryProfit;
    }

    public void setBatteryProfit(Double batteryProfit) {
        this.batteryProfit = batteryProfit;
    }

    public Double getBatteryPercentageProfit() {
        return batteryPercentageProfit;
    }

    public void setBatteryPercentageProfit(Double batteryPercentageProfit) {
        this.batteryPercentageProfit = batteryPercentageProfit;
    }

    public Long getTyreQty() {
        return tyreQty;
    }

    public void setTyreQty(Long tyreQty) {
        this.tyreQty = tyreQty;
    }

    public Double getTyreNetRetailDDL() {
        return tyreNetRetailDDL;
    }

    public void setTyreNetRetailDDL(Double tyreNetRetailDDL) {
        this.tyreNetRetailDDL = tyreNetRetailDDL;
    }

    public Double getTyreNetRetailSelling() {
        return tyreNetRetailSelling;
    }

    public void setTyreNetRetailSelling(Double tyreNetRetailSelling) {
        this.tyreNetRetailSelling = tyreNetRetailSelling;
    }

    public Double getTyreProfit() {
        return tyreProfit;
    }

    public void setTyreProfit(Double tyreProfit) {
        this.tyreProfit = tyreProfit;
    }

    public Double getTyrePercentageProfit() {
        return tyrePercentageProfit;
    }

    public void setTyrePercentageProfit(Double tyrePercentageProfit) {
        this.tyrePercentageProfit = tyrePercentageProfit;
    }

    public Double getBatteryTyreProfit() {
        return batteryTyreProfit;
    }

    public void setBatteryTyreProfit(Double batteryTyreProfit) {
        this.batteryTyreProfit = batteryTyreProfit;
    }

    public Double getBatteryTyrePercentageProfit() {
        return batteryTyrePercentageProfit;
    }

    public void setBatteryTyrePercentageProfit(Double batteryTyrePercentageProfit) {
        this.batteryTyrePercentageProfit = batteryTyrePercentageProfit;
    }

    @Override
    public String toString() {
        return "BatteryTyreSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", batteryQty=" + batteryQty +
                ", batteryNetRetailDDL=" + batteryNetRetailDDL +
                ", batteryNetRetailSelling=" + batteryNetRetailSelling +
                ", batteryProfit=" + batteryProfit +
                ", batteryPercentageProfit=" + batteryPercentageProfit +
                ", tyreQty=" + tyreQty +
                ", tyreNetRetailDDL=" + tyreNetRetailDDL +
                ", tyreNetRetailSelling=" + tyreNetRetailSelling +
                ", tyreProfit=" + tyreProfit +
                ", tyrePercentageProfit=" + tyrePercentageProfit +
                ", batteryTyreProfit=" + batteryTyreProfit +
                ", batteryTyrePercentageProfit=" + batteryTyrePercentageProfit +
                '}';
    }
}
