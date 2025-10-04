package com.mandovi.DTO;

public class LabourSummaryDTO {
    private String city;
    private String branch;
    private Double previousLabour;
    private Double currentLabour;
    private Double growth;

    public LabourSummaryDTO() {
    }

    public LabourSummaryDTO(String city, String branch, Double previousLabour, Double currentLabour, Double growth) {
        this.city = city;
        this.branch = branch;
        this.previousLabour = previousLabour;
        this.currentLabour = currentLabour;
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

    public Double getPreviousLabour() {
        return previousLabour;
    }

    public void setPreviousLabour(Double previousLabour) {
        this.previousLabour = previousLabour;
    }

    public Double getCurrentLabour() {
        return currentLabour;
    }

    public void setCurrentLabour(Double currentLabour) {
        this.currentLabour = currentLabour;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    @Override
    public String toString() {
        return "LabourSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", previousLabour=" + previousLabour +
                ", currentLabour=" + currentLabour +
                ", growth=" + growth +
                '}';
    }
}
