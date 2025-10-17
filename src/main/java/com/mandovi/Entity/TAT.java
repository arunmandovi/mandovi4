package com.mandovi.Entity;


import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "tat")
public class TAT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tatSINo")
    private Integer tatSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "link_service_type")
    private String linkServiceType;

    @Column(name = "average_of_open_to_close")
    private LocalTime averageOfOpenToClose;

    @Column(name = "period")
    private String period;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public TAT() {
    }

    public TAT(Integer tatSINo, String city, String branch, String month, String year, String linkServiceType, LocalTime averageOfOpenToClose, String period, String qtrWise, String halfYear) {
        this.tatSINo = tatSINo;
        this.city = city;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.linkServiceType = linkServiceType;
        this.averageOfOpenToClose = averageOfOpenToClose;
        this.period = period;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getTatSINo() {
        return tatSINo;
    }

    public void setTatSINo(Integer tatSINo) {
        this.tatSINo = tatSINo;
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

    public String getLinkServiceType() {
        return linkServiceType;
    }

    public void setLinkServiceType(String linkServiceType) {
        this.linkServiceType = linkServiceType;
    }

    public LocalTime getAverageOfOpenToClose() {
        return averageOfOpenToClose;
    }

    public void setAverageOfOpenToClose(LocalTime averageOfOpenToClose) {
        this.averageOfOpenToClose = averageOfOpenToClose;
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
        return "TAT{" +
                "tatSINo=" + tatSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", linkServiceType='" + linkServiceType + '\'' +
                ", averageOfOpenToClose=" + averageOfOpenToClose +
                ", period='" + period + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
