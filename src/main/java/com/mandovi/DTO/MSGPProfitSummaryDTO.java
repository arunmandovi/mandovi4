package com.mandovi.DTO;

public class MSGPProfitSummaryDTO {
    private String city;
    private String branch;
    private Double netRetailDDL;
    private Double netRetailSelling;
    private Double profit;
    private Double percentageProfit;

    public MSGPProfitSummaryDTO() {
    }

    public MSGPProfitSummaryDTO(String city, String branch, Double netRetailDDL, Double netRetailSelling, Double profit, Double percentageProfit) {
        this.city = city;
        this.branch = branch;
        this.netRetailDDL = netRetailDDL;
        this.netRetailSelling = netRetailSelling;
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

    public Double getNetRetailDDL() {
        return netRetailDDL;
    }

    public void setNetRetailDDL(Double netRetailDDL) {
        this.netRetailDDL = netRetailDDL;
    }

    public Double getNetRetailSelling() {
        return netRetailSelling;
    }

    public void setNetRetailSelling(Double netRetailSelling) {
        this.netRetailSelling = netRetailSelling;
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
        return "MSGPProfitSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", netRetailDDL=" + netRetailDDL +
                ", netRetailSelling=" + netRetailSelling +
                ", profit=" + profit +
                ", percentageProfit=" + percentageProfit +
                '}';
    }
}
