package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mga_profit")
public class MGAProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mga_profitSINo")
    private Integer magProfitSINo;

    @Column (name = "service_description")
    private String serviceDescription;

    @Column (name = "location_code")
    private String locationCode;

    @Column (name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "net_retail_dd")
    private Double netRetailDD;

    @Column (name = "net_retail_sell")
    private Double netRetailSell;

    @Column (name = "net_retail_ddl")
    private Double netRetailDDL;

    @Column (name = "net_retail_selling")
    private Double netRetailSelling;

    @Column (name = "service_type")
    private String serviceType;

    @Column (name = "city")
    private String city;

    @Column (name = "branch")
    private String branch;

    @Column (name = "qtr_wise")
    private String qtrWise;

    @Column (name = "half_year")
    private String halfYear;

    public MGAProfit() {
    }

    public MGAProfit(Integer magProfitSINo, String serviceDescription, String locationCode, String month, String year, Double netRetailDD, Double netRetailSell, Double netRetailDDL, Double netRetailSelling, String serviceType, String city, String branch, String qtrWise, String halfYear) {
        this.magProfitSINo = magProfitSINo;
        this.serviceDescription = serviceDescription;
        this.locationCode = locationCode;
        this.month = month;
        this.year = year;
        this.netRetailDD = netRetailDD;
        this.netRetailSell = netRetailSell;
        this.netRetailDDL = netRetailDDL;
        this.netRetailSelling = netRetailSelling;
        this.serviceType = serviceType;
        this.city = city;
        this.branch = branch;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getMagProfitSINo() {
        return magProfitSINo;
    }

    public void setMagProfitSINo(Integer magProfitSINo) {
        this.magProfitSINo = magProfitSINo;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
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

    public Double getNetRetailDD() {
        return netRetailDD;
    }

    public void setNetRetailDD(Double netRetailDD) {
        this.netRetailDD = netRetailDD;
    }

    public Double getNetRetailSell() {
        return netRetailSell;
    }

    public void setNetRetailSell(Double netRetailSell) {
        this.netRetailSell = netRetailSell;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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
        return "MGAProfit{" +
                "magProfitSINo=" + magProfitSINo +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", netRetailDD=" + netRetailDD +
                ", netRetailSell=" + netRetailSell +
                ", netRetailDDL=" + netRetailDDL +
                ", netRetailSelling=" + netRetailSelling +
                ", serviceType='" + serviceType + '\'' +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
