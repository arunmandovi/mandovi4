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

    @Column (name = "service_type")
    private String serviceType;

    @Column (name = "service")
    private String service;

    @Column (name = "hold_up_date")
    private LocalDate holdUpDate;

    @Column (name = "month")
    private String month;

    @Column (name = "day")
    private String day;

    @Column (name = "days")
    private String days;

    @Column (name = "count")
    private int count;

    public HoldUp() {
    }

    public HoldUp(Integer holdUpSINo, String city, String branch, String serviceType, String service, LocalDate holdUpDate, String month, String day, String days, int count) {
        this.holdUpSINo = holdUpSINo;
        this.city = city;
        this.branch = branch;
        this.serviceType = serviceType;
        this.service = service;
        this.holdUpDate = holdUpDate;
        this.month = month;
        this.day = day;
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
                ", serviceType='" + serviceType + '\'' +
                ", service='" + service + '\'' +
                ", holdUpDate=" + holdUpDate +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", days='" + days + '\'' +
                ", count=" + count +
                '}';
    }
}
