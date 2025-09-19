package com.mandovi.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "mga")
public class MGA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    private Integer mgaSINo;
    @Column(name = "mga_date")
    private Date mgaDate;
    @Column(name = "dealer_code")
    private String dealerCode;
    @Column(name = "for_code")
    private String forCode;
    @Column(name = "outlet_code")
    private String outletCode;
    @Column(name = "dealer_for_outlet_code")
    private String dealerForOutletCode;
    @Column(name = "city")
    private String city;
    @Column(name = "location")
    private String location;
    @Column(name = "dealer_name")
    private String dealerName;
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
    @Column(name = "channel")
    private String channel;
    @Column(name = "qtr_wise")
    private String qtrWise;
    @Column(name = "half_year")
    private String halfYear;

    public MGA() {
    }

    public MGA(Integer mgaSINo, Date mgaDate, String dealerCode, String forCode, String outletCode, String dealerForOutletCode, String city, String location, String dealerName, String serviceAdvisor, Double consumption, Integer loadd, Double mgaLoad, String branch, String month, String channel, String qtrWise, String halfYear) {
        this.mgaSINo = mgaSINo;
        this.mgaDate = mgaDate;
        this.dealerCode = dealerCode;
        this.forCode = forCode;
        this.outletCode = outletCode;
        this.dealerForOutletCode = dealerForOutletCode;
        this.city = city;
        this.location = location;
        this.dealerName = dealerName;
        this.serviceAdvisor = serviceAdvisor;
        this.consumption = consumption;
        this.loadd = loadd;
        this.mgaLoad = mgaLoad;
        this.branch = branch;
        this.month = month;
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

    public Date getMgaDate() {
        return mgaDate;
    }

    public void setMgaDate(Date mgaDate) {
        this.mgaDate = mgaDate;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getForCode() {
        return forCode;
    }

    public void setForCode(String forCode) {
        this.forCode = forCode;
    }

    public String getOutletCode() {
        return outletCode;
    }

    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
    }

    public String getDealerForOutletCode() {
        return dealerForOutletCode;
    }

    public void setDealerForOutletCode(String dealerForOutletCode) {
        this.dealerForOutletCode = dealerForOutletCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
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
                ", dealerCode='" + dealerCode + '\'' +
                ", forCode='" + forCode + '\'' +
                ", outletCode='" + outletCode + '\'' +
                ", dealerForOutletCode='" + dealerForOutletCode + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", dealerName='" + dealerName + '\'' +
                ", serviceAdvisor='" + serviceAdvisor + '\'' +
                ", consumption=" + consumption +
                ", loadd=" + loadd +
                ", mgaLoad=" + mgaLoad +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", channel='" + channel + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
