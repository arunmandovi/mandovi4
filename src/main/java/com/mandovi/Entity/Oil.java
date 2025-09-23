package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "oil")
public class Oil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oilSINo")
    private Integer oilSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "oil_type")
    private String oilType;

    @Column(name = "net_retail_qty")
    private Double netRetailQty;

    @Column(name = "net_retail_ddl")
    private Double netRetailDDL;

    @Column(name = "net_retail_selling")
    private Double netRetailSelling;

    @Column(name = "period")
    private String period;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public Oil() {
    }

    public Oil(Integer oilSINo, String city, String branch, String month, String year, String oilType, Double netRetailQty, Double netRetailDDL, Double netRetailSelling, String period, String qtrWise, String halfYear) {
        this.oilSINo = oilSINo;
        this.city = city;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.oilType = oilType;
        this.netRetailQty = netRetailQty;
        this.netRetailDDL = netRetailDDL;
        this.netRetailSelling = netRetailSelling;
        this.period = period;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getOilSINo() {
        return oilSINo;
    }

    public void setOilSINo(Integer oilSINo) {
        this.oilSINo = oilSINo;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public Double getNetRetailQty() {
        return netRetailQty;
    }

    public void setNetRetailQty(Double netRetailQty) {
        this.netRetailQty = netRetailQty;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getQtrWise() {
        return qtrWise;
    }

    public void setQtrWise(String qtrWise) {
        this.qtrWise = qtrWise;
    }

    public String getHalfYear() {
        return halfYear;
    }

    public void setHalfYear(String halfYear) {
        this.halfYear = halfYear;
    }

    @Override
    public String toString() {
        return "Oil{" +
                "oilSINo=" + oilSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", oilType='" + oilType + '\'' +
                ", netRetailQty=" + netRetailQty +
                ", netRetailDDL=" + netRetailDDL +
                ", netRetailSelling=" + netRetailSelling +
                ", period='" + period + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
