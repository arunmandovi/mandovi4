package com.mandovi.DTO;

public class BR_ConversionRevenueSummaryDTO {
    private String city;
    private String branch;
    private Double labourAmt;
    private Double partAmt;
    private Double totalAmt;

    public BR_ConversionRevenueSummaryDTO() {
    }

    public BR_ConversionRevenueSummaryDTO(String city, String branch, Double labourAmt, Double partAmt, Double totalAmt) {
        this.city = city;
        this.branch = branch;
        this.labourAmt = labourAmt;
        this.partAmt = partAmt;
        this.totalAmt = totalAmt;
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

    public Double getLabourAmt() {
        return labourAmt;
    }

    public void setLabourAmt(Double labourAmt) {
        this.labourAmt = labourAmt;
    }

    public Double getPartAmt() {
        return partAmt;
    }

    public void setPartAmt(Double partAmt) {
        this.partAmt = partAmt;
    }

    public Double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }

    @Override
    public String toString() {
        return "BR_ConversionRevenueSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", labourAmt=" + labourAmt +
                ", partAmt=" + partAmt +
                ", totalAmt=" + totalAmt +
                '}';
    }
}
