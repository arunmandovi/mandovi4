package com.mandovi.DTO;

public class MSGPProfitSummaryDTO {
    private String city;
    private String branch;
    private Double netRetailDDLServiceBodyShop;
    private Double netRetailSellingServiceBodyShop;
    private Double profitServiceBodyShop;
    private Double percentageProfitServiceBodyShop;
    private Double netRetailDDLService;
    private Double netRetailSellingService;
    private Double profitService;
    private Double percentageProfitService;
    private Double netRetailDDLBodyShop;
    private Double netRetailSellingBodyShop;
    private Double profitBodyShop;
    private Double percentageProfitBodyShop;

    public MSGPProfitSummaryDTO() {
    }

    public MSGPProfitSummaryDTO(String city, String branch, Double netRetailDDLServiceBodyShop, Double netRetailSellingServiceBodyShop, Double profitServiceBodyShop, Double percentageProfitServiceBodyShop, Double netRetailDDLService, Double netRetailSellingService, Double profitService, Double percentageProfitService, Double netRetailDDLBodyShop, Double netRetailSellingBodyShop, Double profitBodyShop, Double percentageProfitBodyShop) {
        this.city = city;
        this.branch = branch;
        this.netRetailDDLServiceBodyShop = netRetailDDLServiceBodyShop;
        this.netRetailSellingServiceBodyShop = netRetailSellingServiceBodyShop;
        this.profitServiceBodyShop = profitServiceBodyShop;
        this.percentageProfitServiceBodyShop = percentageProfitServiceBodyShop;
        this.netRetailDDLService = netRetailDDLService;
        this.netRetailSellingService = netRetailSellingService;
        this.profitService = profitService;
        this.percentageProfitService = percentageProfitService;
        this.netRetailDDLBodyShop = netRetailDDLBodyShop;
        this.netRetailSellingBodyShop = netRetailSellingBodyShop;
        this.profitBodyShop = profitBodyShop;
        this.percentageProfitBodyShop = percentageProfitBodyShop;
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

    public Double getNetRetailDDLServiceBodyShop() {
        return netRetailDDLServiceBodyShop;
    }

    public void setNetRetailDDLServiceBodyShop(Double netRetailDDLServiceBodyShop) {
        this.netRetailDDLServiceBodyShop = netRetailDDLServiceBodyShop;
    }

    public Double getNetRetailSellingServiceBodyShop() {
        return netRetailSellingServiceBodyShop;
    }

    public void setNetRetailSellingServiceBodyShop(Double netRetailSellingServiceBodyShop) {
        this.netRetailSellingServiceBodyShop = netRetailSellingServiceBodyShop;
    }

    public Double getProfitServiceBodyShop() {
        return profitServiceBodyShop;
    }

    public void setProfitServiceBodyShop(Double profitServiceBodyShop) {
        this.profitServiceBodyShop = profitServiceBodyShop;
    }

    public Double getPercentageProfitServiceBodyShop() {
        return percentageProfitServiceBodyShop;
    }

    public void setPercentageProfitServiceBodyShop(Double percentageProfitServiceBodyShop) {
        this.percentageProfitServiceBodyShop = percentageProfitServiceBodyShop;
    }

    public Double getNetRetailDDLService() {
        return netRetailDDLService;
    }

    public void setNetRetailDDLService(Double netRetailDDLService) {
        this.netRetailDDLService = netRetailDDLService;
    }

    public Double getNetRetailSellingService() {
        return netRetailSellingService;
    }

    public void setNetRetailSellingService(Double netRetailSellingService) {
        this.netRetailSellingService = netRetailSellingService;
    }

    public Double getProfitService() {
        return profitService;
    }

    public void setProfitService(Double profitService) {
        this.profitService = profitService;
    }

    public Double getPercentageProfitService() {
        return percentageProfitService;
    }

    public void setPercentageProfitService(Double percentageProfitService) {
        this.percentageProfitService = percentageProfitService;
    }

    public Double getNetRetailDDLBodyShop() {
        return netRetailDDLBodyShop;
    }

    public void setNetRetailDDLBodyShop(Double netRetailDDLBodyShop) {
        this.netRetailDDLBodyShop = netRetailDDLBodyShop;
    }

    public Double getNetRetailSellingBodyShop() {
        return netRetailSellingBodyShop;
    }

    public void setNetRetailSellingBodyShop(Double netRetailSellingBodyShop) {
        this.netRetailSellingBodyShop = netRetailSellingBodyShop;
    }

    public Double getProfitBodyShop() {
        return profitBodyShop;
    }

    public void setProfitBodyShop(Double profitBodyShop) {
        this.profitBodyShop = profitBodyShop;
    }

    public Double getPercentageProfitBodyShop() {
        return percentageProfitBodyShop;
    }

    public void setPercentageProfitBodyShop(Double percentageProfitBodyShop) {
        this.percentageProfitBodyShop = percentageProfitBodyShop;
    }

    @Override
    public String toString() {
        return "MSGPProfitSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", netRetailDDLServiceBodyShop=" + netRetailDDLServiceBodyShop +
                ", netRetailSellingServiceBodyShop=" + netRetailSellingServiceBodyShop +
                ", profitServiceBodyShop=" + profitServiceBodyShop +
                ", percentageProfitServiceBodyShop=" + percentageProfitServiceBodyShop +
                ", netRetailDDLService=" + netRetailDDLService +
                ", netRetailSellingService=" + netRetailSellingService +
                ", profitService=" + profitService +
                ", percentageProfitService=" + percentageProfitService +
                ", netRetailDDLBodyShop=" + netRetailDDLBodyShop +
                ", netRetailSellingBodyShop=" + netRetailSellingBodyShop +
                ", profitBodyShop=" + profitBodyShop +
                ", percentageProfitBodyShop=" + percentageProfitBodyShop +
                '}';
    }
}
