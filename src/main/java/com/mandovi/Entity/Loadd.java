package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loadd")
public class Loadd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loadSINo")
    private Integer loadSINo;

    @Column(name = "location")
    private String location;

    @Column(name = "service_type_code")
    private String serviceTypeCode;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "model_channel")
    private String modelChannel;

    @Column(name = "service_load")
    private Integer serviceLoad;

    @Column(name = "jc_bill_date")
    private LocalDate jcBillDate;

    @Column(name = "channel")
    private String channel;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "financial_year")
    private String financialYear;

    @Column(name = "load_type")
    private String loadType;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public Loadd() {
    }

    public Loadd(Integer loadSINo, String location, String serviceTypeCode, String year, String month, String modelChannel, Integer serviceLoad, LocalDate jcBillDate, String channel, String city, String branch, String financialYear, String loadType, String qtrWise, String halfYear) {
        this.loadSINo = loadSINo;
        this.location = location;
        this.serviceTypeCode = serviceTypeCode;
        this.year = year;
        this.month = month;
        this.modelChannel = modelChannel;
        this.serviceLoad = serviceLoad;
        this.jcBillDate = jcBillDate;
        this.channel = channel;
        this.city = city;
        this.branch = branch;
        this.financialYear = financialYear;
        this.loadType = loadType;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getLoadSINo() {
        return loadSINo;
    }

    public void setLoadSINo(Integer loadSINo) {
        this.loadSINo = loadSINo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getModelChannel() {
        return modelChannel;
    }

    public void setModelChannel(String modelChannel) {
        this.modelChannel = modelChannel;
    }

    public Integer getServiceLoad() {
        return serviceLoad;
    }

    public void setServiceLoad(Integer serviceLoad) {
        this.serviceLoad = serviceLoad;
    }

    public LocalDate getJcBillDate() {
        return jcBillDate;
    }

    public void setJcBillDate(LocalDate jcBillDate) {
        this.jcBillDate = jcBillDate;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
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
        return "Load{" +
                "loadSINo=" + loadSINo +
                ", location='" + location + '\'' +
                ", serviceTypeCode='" + serviceTypeCode + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", modelChannel='" + modelChannel + '\'' +
                ", serviceLoad='" + serviceLoad + '\'' +
                ", jcBillDate=" + jcBillDate +
                ", channel='" + channel + '\'' +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", financialYear='" + financialYear + '\'' +
                ", loadType='" + loadType + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
