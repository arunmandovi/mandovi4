package com.mandovi.DTO;

public class PerVehicleReportSummaryDTO {
    private String city;
    private String branch;
    private Long srLoadd;
    private Double srLabour;
    private Double srSpares;
    private Double srLabourByVEH;
    private Double srSparesByVEH;
    private Double srRevenueByVEH;
    private Long brLoadd;
    private Double brLabour;
    private Double brSpares;
    private Double brLabourByVeh;
    private Double brSparesByVeh;
    private Double brRevenueByVeh;

    public PerVehicleReportSummaryDTO() {
    }

    public PerVehicleReportSummaryDTO(String city, String branch, Long srLoadd, Double srLabour, Double srSpares, Double srLabourByVEH, Double srSparesByVEH, Double srRevenueByVEH, Long brLoadd, Double brLabour, Double brSpares, Double brLabourByVeh, Double brSparesByVeh, Double brRevenueByVeh) {
        this.city = city;
        this.branch = branch;
        this.srLoadd = srLoadd;
        this.srLabour = srLabour;
        this.srSpares = srSpares;
        this.srLabourByVEH = srLabourByVEH;
        this.srSparesByVEH = srSparesByVEH;
        this.srRevenueByVEH = srRevenueByVEH;
        this.brLoadd = brLoadd;
        this.brLabour = brLabour;
        this.brSpares = brSpares;
        this.brLabourByVeh = brLabourByVeh;
        this.brSparesByVeh = brSparesByVeh;
        this.brRevenueByVeh = brRevenueByVeh;
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

    public Long getSrLoadd() {
        return srLoadd;
    }

    public void setSrLoadd(Long srLoadd) {
        this.srLoadd = srLoadd;
    }

    public Double getSrLabour() {
        return srLabour;
    }

    public void setSrLabour(Double srLabour) {
        this.srLabour = srLabour;
    }

    public Double getSrSpares() {
        return srSpares;
    }

    public void setSrSpares(Double srSpares) {
        this.srSpares = srSpares;
    }

    public Double getSrLabourByVEH() {
        return srLabourByVEH;
    }

    public void setSrLabourByVEH(Double srLabourByVEH) {
        this.srLabourByVEH = srLabourByVEH;
    }

    public Double getSrSparesByVEH() {
        return srSparesByVEH;
    }

    public void setSrSparesByVEH(Double srSparesByVEH) {
        this.srSparesByVEH = srSparesByVEH;
    }

    public Double getSrRevenueByVEH() {
        return srRevenueByVEH;
    }

    public void setSrRevenueByVEH(Double srRevenueByVEH) {
        this.srRevenueByVEH = srRevenueByVEH;
    }

    public Long getBrLoadd() {
        return brLoadd;
    }

    public void setBrLoadd(Long brLoadd) {
        this.brLoadd = brLoadd;
    }

    public Double getBrLabour() {
        return brLabour;
    }

    public void setBrLabour(Double brLabour) {
        this.brLabour = brLabour;
    }

    public Double getBrSpares() {
        return brSpares;
    }

    public void setBrSpares(Double brSpares) {
        this.brSpares = brSpares;
    }

    public Double getBrLabourByVeh() {
        return brLabourByVeh;
    }

    public void setBrLabourByVeh(Double brLabourByVeh) {
        this.brLabourByVeh = brLabourByVeh;
    }

    public Double getBrSparesByVeh() {
        return brSparesByVeh;
    }

    public void setBrSparesByVeh(Double brSparesByVeh) {
        this.brSparesByVeh = brSparesByVeh;
    }

    public Double getBrRevenueByVeh() {
        return brRevenueByVeh;
    }

    public void setBrRevenueByVeh(Double brRevenueByVeh) {
        this.brRevenueByVeh = brRevenueByVeh;
    }

    @Override
    public String toString() {
        return "PerVehicleReportSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", srLoadd=" + srLoadd +
                ", srLabour=" + srLabour +
                ", srSpares=" + srSpares +
                ", srLabourByVEH=" + srLabourByVEH +
                ", srSparesByVEH=" + srSparesByVEH +
                ", srRevenueByVEH=" + srRevenueByVEH +
                ", brLoadd=" + brLoadd +
                ", brLabour=" + brLabour +
                ", brSpares=" + brSpares +
                ", brLabourByVeh=" + brLabourByVeh +
                ", brSparesByVeh=" + brSparesByVeh +
                ", brRevenueByVeh=" + brRevenueByVeh +
                '}';
    }
}
