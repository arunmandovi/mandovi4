package com.mandovi.DTO;

public class OilSummaryDTO {
    private String city;
    private String branch;
    private Double fullSyntheticQTY;
    private Double semiSyntheticQTY;
    private Double mineralQTY;
    private Double grandTotal;
    private Double fullSyntheticPercentageQTY;
    private Double semiSyntheticPercentageQTY;
    private Double fullSemiSyntheticPercentageQTY;
    private Double fullSyntheticProfit;
    private Double semiSyntheticProfit;
    private Double fullSemiSyntheticProfit;
    private Double mineralProfit;
    private Double profitTotal;

    public OilSummaryDTO() {
    }

    public OilSummaryDTO(String city, String branch, Double fullSyntheticQTY, Double semiSyntheticQTY, Double mineralQTY, Double grandTotal, Double fullSyntheticPercentageQTY, Double semiSyntheticPercentageQTY, Double fullSemiSyntheticPercentageQTY, Double fullSyntheticProfit, Double semiSyntheticProfit, Double fullSemiSyntheticProfit, Double mineralProfit, Double profitTotal) {
        this.city = city;
        this.branch = branch;
        this.fullSyntheticQTY = fullSyntheticQTY;
        this.semiSyntheticQTY = semiSyntheticQTY;
        this.mineralQTY = mineralQTY;
        this.grandTotal = grandTotal;
        this.fullSyntheticPercentageQTY = fullSyntheticPercentageQTY;
        this.semiSyntheticPercentageQTY = semiSyntheticPercentageQTY;
        this.fullSemiSyntheticPercentageQTY = fullSemiSyntheticPercentageQTY;
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

    public Double getFullSyntheticPercentageQTY() {
        return fullSyntheticPercentageQTY;
    }

    public void setFullSyntheticPercentageQTY(Double fullSyntheticPercentageQTY) {
        this.fullSyntheticPercentageQTY = fullSyntheticPercentageQTY;
    }

    public Double getSemiSyntheticPercentageQTY() {
        return semiSyntheticPercentageQTY;
    }

    public void setSemiSyntheticPercentageQTY(Double semiSyntheticPercentageQTY) {
        this.semiSyntheticPercentageQTY = semiSyntheticPercentageQTY;
    }

    public Double getFullSemiSyntheticPercentageQTY() {
        return fullSemiSyntheticPercentageQTY;
    }

    public void setFullSemiSyntheticPercentageQTY(Double fullSemiSyntheticPercentageQTY) {
        this.fullSemiSyntheticPercentageQTY = fullSemiSyntheticPercentageQTY;
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
                ", mineralQTY=" + mineralQTY +
                ", grandTotal=" + grandTotal +
                ", fullSyntheticPercentageQTY=" + fullSyntheticPercentageQTY +
                ", semiSyntheticPercentageQTY=" + semiSyntheticPercentageQTY +
                ", fullSemiSyntheticPercentageQTY=" + fullSemiSyntheticPercentageQTY +
                ", fullSyntheticProfit=" + fullSyntheticProfit +
                ", semiSyntheticProfit=" + semiSyntheticProfit +
                ", fullSemiSyntheticProfit=" + fullSemiSyntheticProfit +
                ", mineralProfit=" + mineralProfit +
                ", profitTotal=" + profitTotal +
                '}';
    }
}
