package com.mandovi.DTO;

public class OilSummaryDTO {
    private String city;
    private String branch;
    private Double fullSyntheticQTY;
    private Double semiSyntheticQTY;
    private Double fullAndSemiSyntheticQty;
    private Double mineralQTY;
    private Double grandTotal;
    private Double percentageFullSyntheticQTY;
    private Double percentageSemiSyntheticQTY;
    private Double percentageFullSemiSyntheticQTY;
    private Double fullSyntheticProfit;
    private Double semiSyntheticProfit;
    private Double fullSemiSyntheticProfit;
    private Double mineralProfit;
    private Double profitTotal;

    public OilSummaryDTO() {
    }

    public OilSummaryDTO(String city, String branch, Double fullSyntheticQTY, Double semiSyntheticQTY, Double fullAndSemiSyntheticQty, Double mineralQTY, Double grandTotal, Double percentageFullSyntheticQTY, Double percentageSemiSyntheticQTY, Double percentageFullSemiSyntheticQTY, Double fullSyntheticProfit, Double semiSyntheticProfit, Double fullSemiSyntheticProfit, Double mineralProfit, Double profitTotal) {
        this.city = city;
        this.branch = branch;
        this.fullSyntheticQTY = fullSyntheticQTY;
        this.semiSyntheticQTY = semiSyntheticQTY;
        this.fullAndSemiSyntheticQty = fullAndSemiSyntheticQty;
        this.mineralQTY = mineralQTY;
        this.grandTotal = grandTotal;
        this.percentageFullSyntheticQTY = percentageFullSyntheticQTY;
        this.percentageSemiSyntheticQTY = percentageSemiSyntheticQTY;
        this.percentageFullSemiSyntheticQTY = percentageFullSemiSyntheticQTY;
        this.fullSyntheticProfit = fullSyntheticProfit;
        this.semiSyntheticProfit = semiSyntheticProfit;
        this.fullSemiSyntheticProfit = fullSemiSyntheticProfit;
        this.mineralProfit = mineralProfit;
        this.profitTotal = profitTotal;
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

    public Double getFullSyntheticQTY() {
        return fullSyntheticQTY;
    }

    public void setFullSyntheticQTY(Double fullSyntheticQTY) {
        this.fullSyntheticQTY = fullSyntheticQTY;
    }

    public Double getSemiSyntheticQTY() {
        return semiSyntheticQTY;
    }

    public void setSemiSyntheticQTY(Double semiSyntheticQTY) {
        this.semiSyntheticQTY = semiSyntheticQTY;
    }

    public Double getFullAndSemiSyntheticQty() {
        return fullAndSemiSyntheticQty;
    }

    public void setFullAndSemiSyntheticQty(Double fullAndSemiSyntheticQty) {
        this.fullAndSemiSyntheticQty = fullAndSemiSyntheticQty;
    }

    public Double getMineralQTY() {
        return mineralQTY;
    }

    public void setMineralQTY(Double mineralQTY) {
        this.mineralQTY = mineralQTY;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getPercentageFullSyntheticQTY() {
        return percentageFullSyntheticQTY;
    }

    public void setPercentageFullSyntheticQTY(Double percentageFullSyntheticQTY) {
        this.percentageFullSyntheticQTY = percentageFullSyntheticQTY;
    }

    public Double getPercentageSemiSyntheticQTY() {
        return percentageSemiSyntheticQTY;
    }

    public void setPercentageSemiSyntheticQTY(Double percentageSemiSyntheticQTY) {
        this.percentageSemiSyntheticQTY = percentageSemiSyntheticQTY;
    }

    public Double getPercentageFullSemiSyntheticQTY() {
        return percentageFullSemiSyntheticQTY;
    }

    public void setPercentageFullSemiSyntheticQTY(Double percentageFullSemiSyntheticQTY) {
        this.percentageFullSemiSyntheticQTY = percentageFullSemiSyntheticQTY;
    }

    public Double getFullSyntheticProfit() {
        return fullSyntheticProfit;
    }

    public void setFullSyntheticProfit(Double fullSyntheticProfit) {
        this.fullSyntheticProfit = fullSyntheticProfit;
    }

    public Double getSemiSyntheticProfit() {
        return semiSyntheticProfit;
    }

    public void setSemiSyntheticProfit(Double semiSyntheticProfit) {
        this.semiSyntheticProfit = semiSyntheticProfit;
    }

    public Double getFullSemiSyntheticProfit() {
        return fullSemiSyntheticProfit;
    }

    public void setFullSemiSyntheticProfit(Double fullSemiSyntheticProfit) {
        this.fullSemiSyntheticProfit = fullSemiSyntheticProfit;
    }

    public Double getMineralProfit() {
        return mineralProfit;
    }

    public void setMineralProfit(Double mineralProfit) {
        this.mineralProfit = mineralProfit;
    }

    public Double getProfitTotal() {
        return profitTotal;
    }

    public void setProfitTotal(Double profitTotal) {
        this.profitTotal = profitTotal;
    }

    @Override
    public String toString() {
        return "OilSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", fullSyntheticQTY=" + fullSyntheticQTY +
                ", semiSyntheticQTY=" + semiSyntheticQTY +
                ", fullAndSemiSyntheticQty=" + fullAndSemiSyntheticQty +
                ", mineralQTY=" + mineralQTY +
                ", grandTotal=" + grandTotal +
                ", percentageFullSyntheticQTY=" + percentageFullSyntheticQTY +
                ", percentageSemiSyntheticQTY=" + percentageSemiSyntheticQTY +
                ", percentageFullSemiSyntheticQTY=" + percentageFullSemiSyntheticQTY +
                ", fullSyntheticProfit=" + fullSyntheticProfit +
                ", semiSyntheticProfit=" + semiSyntheticProfit +
                ", fullSemiSyntheticProfit=" + fullSemiSyntheticProfit +
                ", mineralProfit=" + mineralProfit +
                ", profitTotal=" + profitTotal +
                '}';
    }
}
