package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "hold_up")
public class HoldUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hold_upSINo")
    private Integer holdUpSINo;

    @Column (name = "city")
    private String city;

    @Column (name = "branch")
    private String branch;

    @Column (name = "reg_no")
    private String regNo;

    @Column (name = "service_type")
    private String serviceType;

    @Column (name = "service")
    private String service;

    @Column (name = "channel")
    private String channel;

    @Column (name = "hold_up_date")
    private LocalDate holdUpDate;

    @Column (name = "month")
    private String month;

    @Column (name = "day")
    private String day;

    @Column (name = "year")
    private String year;

    @Column (name = "days")
    private String days;

    @Column (name = "count")
    private int count;

    public HoldUp() {
    }

    public HoldUp(Integer holdUpSINo, String city, String branch, String regNo, String serviceType, String service, String channel, LocalDate holdUpDate, String month, String day, String year, String days, int count) {
        this.holdUpSINo = holdUpSINo;
        this.city = city;
        this.branch = branch;
        this.regNo = regNo;
        this.serviceType = serviceType;
        this.service = service;
        this.channel = channel;
        this.holdUpDate = holdUpDate;
        this.month = month;
        this.day = day;
        this.year = year;
        this.days = days;
        this.count = count;
    }

    public Integer getHoldUpSINo() {
        return holdUpSINo;
    }

    public void setHoldUpSINo(Integer holdUpSINo) {
        this.holdUpSINo = holdUpSINo;
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

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public LocalDate getHoldUpDate() {
        return holdUpDate;
    }

    public void setHoldUpDate(LocalDate holdUpDate) {
        this.holdUpDate = holdUpDate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HoldUp{" +
                "holdUpSINo=" + holdUpSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", regNo='" + regNo + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", service='" + service + '\'' +
                ", channel='" + channel + '\'' +
                ", holdUpDate=" + holdUpDate +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", year='" + year + '\'' +
                ", days='" + days + '\'' +
                ", count=" + count +
                '}';
    }
}
