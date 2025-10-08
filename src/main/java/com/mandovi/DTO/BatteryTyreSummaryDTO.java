package com.mandovi.DTO;

public class BatteryTyreSummaryDTO {

    private String city;
    private String branch;
    private  Long totalQty;
    private  Double totalDDL;
    private  Double totalSelling;
    private Double profit;
    private Double percentageProfit;

    public BatteryTyreSummaryDTO() {
    }

    public BatteryTyreSummaryDTO(String city, String branch, Long totalQty, Double totalDDL, Double totalSelling, Double profit, Double percentageProfit) {
        this.city = city;
        this.branch = branch;
        this.totalQty = totalQty;
        this.totalDDL = totalDDL;
        this.totalSelling = totalSelling;
        this.profit = profit;
        this.percentageProfit = percentageProfit;
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

    public Long getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Long totalQty) {
        this.totalQty = totalQty;
    }

    public Double getTotalDDL() {
        return totalDDL;
    }

    public void setTotalDDL(Double totalDDL) {
        this.totalDDL = totalDDL;
    }

    public Double getTotalSelling() {
        return totalSelling;
    }

    public void setTotalSelling(Double totalSelling) {
        this.totalSelling = totalSelling;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getPercentageProfit() {
        return percentageProfit;
    }

    public void setPercentageProfit(Double percentageProfit) {
        this.percentageProfit = percentageProfit;
    }

    @Override
    public String toString() {
        return "BatteryTyreSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", totalQty=" + totalQty +
                ", totalDDL=" + totalDDL +
                ", totalSelling=" + totalSelling +
                ", profit=" + profit +
                ", percentageProfit=" + percentageProfit +
                '}';
    }
}
