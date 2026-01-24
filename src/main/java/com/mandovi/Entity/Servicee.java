package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "servicee")
public class Servicee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "serviceSINo")
    private Integer serviceSINo;

    @Column (name = "city")
    private String city;

    @Column (name = "branch")
    private String branch;

    @Column (name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "service_code")
    private String serviceCode;

    @Column (name = "channel")
    private String channel;

    @Column (name = "service_loadd")
    private Integer serviceLoadd;

    public Servicee() {
    }

    public Servicee(Integer serviceSINo, String city, String branch, String month, String year, String serviceCode, String channel, Integer serviceLoadd) {
        this.serviceSINo = serviceSINo;
        this.city = city;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.serviceCode = serviceCode;
        this.channel = channel;
        this.serviceLoadd = serviceLoadd;
    }

    public Integer getServiceSINo() {
        return serviceSINo;
    }

    public void setServiceSINo(Integer serviceSINo) {
        this.serviceSINo = serviceSINo;
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

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getServiceLoadd() {
        return serviceLoadd;
    }

    public void setServiceLoadd(Integer serviceLoadd) {
        this.serviceLoadd = serviceLoadd;
    }

    @Override
    public String toString() {
        return "Servicee{" +
                "serviceSINo=" + serviceSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", channel='" + channel + '\'' +
                ", serviceLoadd=" + serviceLoadd +
                '}';
    }
}
