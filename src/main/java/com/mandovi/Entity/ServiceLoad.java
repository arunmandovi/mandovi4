package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table (name = "service_load")
public class ServiceLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_loadSINo")
    private Integer serviceLoadSINo;

    @Column (name = "city")
    private String city;

    @Column (name = "branch")
    private String branch;

    @Column (name = "service_type")
    private String serviceType;

    @Column (name = "service_main_type")
    private String serviceMainType;

    @Column (name = "service_sub_type")
    private String serviceSubType;

    @Column (name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "financial_year")
    private String financialYear;

    @Column (name = "channel")
    private String channel;

    @Column (name = "service_load")
    private Integer serviceLoad;

    public ServiceLoad() {
    }

    public ServiceLoad(Integer serviceLoadSINo, String city, String branch, String serviceType, String serviceMainType, String serviceSubType, String month, String year, String financialYear, String channel, Integer serviceLoad) {
        this.serviceLoadSINo = serviceLoadSINo;
        this.city = city;
        this.branch = branch;
        this.serviceType = serviceType;
        this.serviceMainType = serviceMainType;
        this.serviceSubType = serviceSubType;
        this.month = month;
        this.year = year;
        this.financialYear = financialYear;
        this.channel = channel;
        this.serviceLoad = serviceLoad;
    }

    public Integer getServiceLoadSINo() {
        return serviceLoadSINo;
    }

    public void setServiceLoadSINo(Integer serviceLoadSINo) {
        this.serviceLoadSINo = serviceLoadSINo;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceMainType() {
        return serviceMainType;
    }

    public void setServiceMainType(String serviceMainType) {
        this.serviceMainType = serviceMainType;
    }

    public String getServiceSubType() {
        return serviceSubType;
    }

    public void setServiceSubType(String serviceSubType) {
        this.serviceSubType = serviceSubType;
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

    public Integer getServiceLoad() {
        return serviceLoad;
    }

    public void setServiceLoad(Integer serviceLoad) {
        this.serviceLoad = serviceLoad;
    }

    @Override
    public String toString() {
        return "ServiceLoad{" +
                "serviceLoadSINo=" + serviceLoadSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", serviceMainType='" + serviceMainType + '\'' +
                ", serviceSubType='" + serviceSubType + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", financialYear='" + financialYear + '\'' +
                ", channel='" + channel + '\'' +
                ", serviceLoad=" + serviceLoad +
                '}';
    }
}
