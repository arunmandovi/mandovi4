package com.mandovi.DTO;

public class MGAProfitSummaryDTO {

    private String city;
    private String branch;
    private Double serviceNetRetailDDL;
    private Double serviceNetRetailSelling;
    private Double serviceProfit;
    private Double servicePercentageProfit;
    private Double bodyShopNetRetailDDL;
    private Double bodyShopNetRetailSelling;
    private Double bodyShopProfit;
    private Double bodyShopPercentageProfit;
    private Double serviceBodyShopNetRetailDDL;
    private Double serviceBodyShopNetRetailSelling;
    private Double serviceBodyShopProfit;
    private Double serviceBodyShopPercentageProfit;

    public MGAProfitSummaryDTO() {
    }

    public MGAProfitSummaryDTO(String city, String branch, Double serviceNetRetailDDL, Double serviceNetRetailSelling, Double serviceProfit, Double servicePercentageProfit, Double bodyShopNetRetailDDL, Double bodyShopNetRetailSelling, Double bodyShopProfit, Double bodyShopPercentageProfit, Double serviceBodyShopNetRetailDDL, Double serviceBodyShopNetRetailSelling, Double serviceBodyShopProfit, Double serviceBodyShopPercentageProfit) {
        this.city = city;
        this.branch = branch;
        this.serviceNetRetailDDL = serviceNetRetailDDL;
        this.serviceNetRetailSelling = serviceNetRetailSelling;
        this.serviceProfit = serviceProfit;
        this.servicePercentageProfit = servicePercentageProfit;
        this.bodyShopNetRetailDDL = bodyShopNetRetailDDL;
        this.bodyShopNetRetailSelling = bodyShopNetRetailSelling;
        this.bodyShopProfit = bodyShopProfit;
        this.bodyShopPercentageProfit = bodyShopPercentageProfit;
        this.serviceBodyShopNetRetailDDL = serviceBodyShopNetRetailDDL;
        this.serviceBodyShopNetRetailSelling = serviceBodyShopNetRetailSelling;
        this.serviceBodyShopProfit = serviceBodyShopProfit;
        this.serviceBodyShopPercentageProfit = serviceBodyShopPercentageProfit;
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

    public Double getServiceNetRetailDDL() {
        return serviceNetRetailDDL;
    }

    public void setServiceNetRetailDDL(Double serviceNetRetailDDL) {
        this.serviceNetRetailDDL = serviceNetRetailDDL;
    }

    public Double getServiceNetRetailSelling() {
        return serviceNetRetailSelling;
    }

    public void setServiceNetRetailSelling(Double serviceNetRetailSelling) {
        this.serviceNetRetailSelling = serviceNetRetailSelling;
    }

    public Double getServiceProfit() {
        return serviceProfit;
    }

    public void setServiceProfit(Double serviceProfit) {
        this.serviceProfit = serviceProfit;
    }

    public Double getServicePercentageProfit() {
        return servicePercentageProfit;
    }

    public void setServicePercentageProfit(Double servicePercentageProfit) {
        this.servicePercentageProfit = servicePercentageProfit;
    }

    public Double getBodyShopNetRetailDDL() {
        return bodyShopNetRetailDDL;
    }

    public void setBodyShopNetRetailDDL(Double bodyShopNetRetailDDL) {
        this.bodyShopNetRetailDDL = bodyShopNetRetailDDL;
    }

    public Double getBodyShopNetRetailSelling() {
        return bodyShopNetRetailSelling;
    }

    public void setBodyShopNetRetailSelling(Double bodyShopNetRetailSelling) {
        this.bodyShopNetRetailSelling = bodyShopNetRetailSelling;
    }

    public Double getBodyShopProfit() {
        return bodyShopProfit;
    }

    public void setBodyShopProfit(Double bodyShopProfit) {
        this.bodyShopProfit = bodyShopProfit;
    }

    public Double getBodyShopPercentageProfit() {
        return bodyShopPercentageProfit;
    }

    public void setBodyShopPercentageProfit(Double bodyShopPercentageProfit) {
        this.bodyShopPercentageProfit = bodyShopPercentageProfit;
    }

    public Double getServiceBodyShopNetRetailDDL() {
        return serviceBodyShopNetRetailDDL;
    }

    public void setServiceBodyShopNetRetailDDL(Double serviceBodyShopNetRetailDDL) {
        this.serviceBodyShopNetRetailDDL = serviceBodyShopNetRetailDDL;
    }

    public Double getServiceBodyShopNetRetailSelling() {
        return serviceBodyShopNetRetailSelling;
    }

    public void setServiceBodyShopNetRetailSelling(Double serviceBodyShopNetRetailSelling) {
        this.serviceBodyShopNetRetailSelling = serviceBodyShopNetRetailSelling;
    }

    public Double getServiceBodyShopProfit() {
        return serviceBodyShopProfit;
    }

    public void setServiceBodyShopProfit(Double serviceBodyShopProfit) {
        this.serviceBodyShopProfit = serviceBodyShopProfit;
    }

    public Double getServiceBodyShopPercentageProfit() {
        return serviceBodyShopPercentageProfit;
    }

    public void setServiceBodyShopPercentageProfit(Double serviceBodyShopPercentageProfit) {
        this.serviceBodyShopPercentageProfit = serviceBodyShopPercentageProfit;
    }

    @Override
    public String toString() {
        return "MGAProfitSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", serviceNetRetailDDL=" + serviceNetRetailDDL +
                ", serviceNetRetailSelling=" + serviceNetRetailSelling +
                ", serviceProfit=" + serviceProfit +
                ", servicePercentageProfit=" + servicePercentageProfit +
                ", bodyShopNetRetailDDL=" + bodyShopNetRetailDDL +
                ", bodyShopNetRetailSelling=" + bodyShopNetRetailSelling +
                ", bodyShopProfit=" + bodyShopProfit +
                ", bodyShopPercentageProfit=" + bodyShopPercentageProfit +
                ", serviceBodyShopNetRetailDDL=" + serviceBodyShopNetRetailDDL +
                ", serviceBodyShopNetRetailSelling=" + serviceBodyShopNetRetailSelling +
                ", serviceBodyShopProfit=" + serviceBodyShopProfit +
                ", serviceBodyShopPercentageProfit=" + serviceBodyShopPercentageProfit +
                '}';
    }
}
