package com.mandovi.DTO;

public class MSGPSummaryDTO {
    private String city;
    private String branch;
    private Double previousNetRetailDDL;
    private Double currentNetRetailDDL;
    private Double growth;

    public MSGPSummaryDTO() {
    }

    public MSGPSummaryDTO(String city, String branch, Double previousNetRetailDDL, Double currentNetRetailDDL, Double growth) {
        this.city = city;
        this.branch = branch;
        this.previousNetRetailDDL = previousNetRetailDDL;
        this.currentNetRetailDDL = currentNetRetailDDL;
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

    public Double getPreviousNetRetailDDL() {
        return previousNetRetailDDL;
    }

    public void setPreviousNetRetailDDL(Double previousNetRetailDDL) {
        this.previousNetRetailDDL = previousNetRetailDDL;
    }

    public Double getCurrentNetRetailDDL() {
        return currentNetRetailDDL;
    }

    public void setCurrentNetRetailDDL(Double currentNetRetailDDL) {
        this.currentNetRetailDDL = currentNetRetailDDL;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    @Override
    public String toString() {
        return "MSGPSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", previousNetRetailDDL=" + previousNetRetailDDL +
                ", currentNetRetailDDL=" + currentNetRetailDDL +
                ", growth=" + growth +
                '}';
    }
}
