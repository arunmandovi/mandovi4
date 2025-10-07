package com.mandovi.DTO;

public class OilSummaryDTO {
    private String city;
    private String branch;
    private Double  fullSynthetic;
    private Double semiSynthetic;
    private Double fullSemiSynthetic;
    private Double mineral;
    private Double grandTotal;

    public OilSummaryDTO() {
    }

    public OilSummaryDTO(String city, String branch, Double fullSynthetic, Double semiSynthetic, Double fullSemiSynthetic, Double mineral, Double grandTotal) {
        this.city = city;
        this.branch = branch;
        this.fullSynthetic = fullSynthetic;
        this.semiSynthetic = semiSynthetic;
        this.fullSemiSynthetic = fullSemiSynthetic;
        this.mineral = mineral;
        this.grandTotal = grandTotal;
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

    public Double getFullSynthetic() {
        return fullSynthetic;
    }

    public void setFullSynthetic(Double fullSynthetic) {
        this.fullSynthetic = fullSynthetic;
    }

    public Double getSemiSynthetic() {
        return semiSynthetic;
    }

    public void setSemiSynthetic(Double semiSynthetic) {
        this.semiSynthetic = semiSynthetic;
    }

    public Double getFullSemiSynthetic() {
        return fullSemiSynthetic;
    }

    public void setFullSemiSynthetic(Double fullSemiSynthetic) {
        this.fullSemiSynthetic = fullSemiSynthetic;
    }

    public Double getMineral() {
        return mineral;
    }

    public void setMineral(Double mineral) {
        this.mineral = mineral;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() {
        return "OilSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", fullSynthetic='" + fullSynthetic + '\'' +
                ", semiSynthetic='" + semiSynthetic + '\'' +
                ", fullSemiSynthetic='" + fullSemiSynthetic + '\'' +
                ", mineral='" + mineral + '\'' +
                ", grandTotal='" + grandTotal + '\'' +
                '}';
    }
}
