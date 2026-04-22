package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "mga")
public class MGA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    private Integer mgaSINo;
    @Column(name = "mga_date")
    private LocalDate mgaDate;
    @Column(name = "city")
    private String city;
    @Column(name = "service_advisor")
    private String serviceAdvisor;
    @Column(name = "consumption")
    private Double consumption;
    @Column(name = "loadd")
    private Integer loadd;
    @Column(name = "mga_load")
    private Double mgaLoad;
    @Column(name = "branch")
    private String branch;
    @Column(name = "month")
    private String month;
    @Column (name = "year")
    private String year;
    @Column (name = "financial_year")
    private String financialYear;
    @Column(name = "channel")
    private String channel;
    @Column(name = "qtr_wise")
    private String qtrWise;
    @Column(name = "half_year")
    private String halfYear;

    public MGA() {
    }

    public MGA(Integer mgaSINo, LocalDate mgaDate, String city, String serviceAdvisor, Double consumption, Integer loadd, Double mgaLoad, String branch, String month, String year, String financialYear, String channel, String qtrWise, String halfYear) {
        this.mgaSINo = mgaSINo;
        this.mgaDate = mgaDate;
        this.city = city;
        this.serviceAdvisor = serviceAdvisor;
        this.consumption = consumption;
        this.loadd = loadd;
        this.mgaLoad = mgaLoad;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.financialYear = financialYear;
        this.channel = channel;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getMgaSINo() {
        return mgaSINo;
    }

    public void setMgaSINo(Integer mgaSINo) {
        this.mgaSINo = mgaSINo;
    }

    public LocalDate getMgaDate() {
        return mgaDate;
    }

    public void setMgaDate(LocalDate mgaDate) {
        this.mgaDate = mgaDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getServiceAdvisor() {
        return serviceAdvisor;
    }

    public void setServiceAdvisor(String serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Integer getLoadd() {
        return loadd;
    }

    public void setLoadd(Integer loadd) {
        this.loadd = loadd;
    }

    public Double getMgaLoad() {
        return mgaLoad;
    }

    public void setMgaLoad(Double mgaLoad) {
        this.mgaLoad = mgaLoad;
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

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
        return "MGA{" +
                "mgaSINo=" + mgaSINo +
                ", mgaDate=" + mgaDate +
                ", city='" + city + '\'' +
                ", serviceAdvisor='" + serviceAdvisor + '\'' +
                ", consumption=" + consumption +
                ", loadd=" + loadd +
                ", mgaLoad=" + mgaLoad +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", financialYear='" + financialYear + '\'' +
                ", channel='" + channel + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
