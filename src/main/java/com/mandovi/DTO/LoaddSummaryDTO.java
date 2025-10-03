package com.mandovi.DTO;

public class LoaddSummaryDTO {
    private String city;
    private String branch;
    private Long previousServiceLoadd;
    private Long currentServiceLoadd;
    private Double growth;

    public LoaddSummaryDTO() {
    }

    public LoaddSummaryDTO(String city, String branch, Long previousServiceLoadd, Long currentServiceLoadd, Double growth) {
        this.city = city;
        this.branch = branch;
        this.previousServiceLoadd = previousServiceLoadd;
        this.currentServiceLoadd = currentServiceLoadd;
        this.growth = growth;
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

    public Long getPreviousServiceLoadd() {
        return previousServiceLoadd;
    }

    public void setPreviousServiceLoadd(Long previousServiceLoadd) {
        this.previousServiceLoadd = previousServiceLoadd;
    }

    public Long getCurrentServiceLoadd() {
        return currentServiceLoadd;
    }

    public void setCurrentServiceLoadd(Long currentServiceLoadd) {
        this.currentServiceLoadd = currentServiceLoadd;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    @Override
    public String toString() {
        return "LoaddSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", previousServiceLoadd=" + previousServiceLoadd +
                ", currentServiceLoadd=" + currentServiceLoadd +
                ", growth=" + growth +
                '}';
    }
}
