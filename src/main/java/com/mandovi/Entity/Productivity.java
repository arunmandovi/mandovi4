package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table (name = "productivity")
public class Productivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "productivitySINo")
    private Integer productivitySINo;

    @Column (name = "city")
    private String city;

    @Column (name = "branch")
    private String branch;

    @Column (name = "service_utilized_bay")
    private Integer serviceUtilizedBay;

    @Column (name = "bodyshop_utilized_bay")
    private Integer bodyShopUtilizedBay;

    @Column (name = "month")
    private String month;

    @Column (name = "worked_days")
    private Integer workedDays;

    public Productivity() {
    }

    public Productivity(Integer productivitySINo, String city, String branch, Integer serviceUtilizedBay, Integer bodyShopUtilizedBay, String month, Integer workedDays) {
        this.productivitySINo = productivitySINo;
        this.city = city;
        this.branch = branch;
        this.serviceUtilizedBay = serviceUtilizedBay;
        this.bodyShopUtilizedBay = bodyShopUtilizedBay;
        this.month = month;
        this.workedDays = workedDays;
    }

    public Integer getProductivitySINo() {
        return productivitySINo;
    }

    public void setProductivitySINo(Integer productivitySINo) {
        this.productivitySINo = productivitySINo;
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

    public Integer getServiceUtilizedBay() {
        return serviceUtilizedBay;
    }

    public void setServiceUtilizedBay(Integer serviceUtilizedBay) {
        this.serviceUtilizedBay = serviceUtilizedBay;
    }

    public Integer getBodyShopUtilizedBay() {
        return bodyShopUtilizedBay;
    }

    public void setBodyShopUtilizedBay(Integer bodyShopUtilizedBay) {
        this.bodyShopUtilizedBay = bodyShopUtilizedBay;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(Integer workedDays) {
        this.workedDays = workedDays;
    }

    @Override
    public String toString() {
        return "Productivity{" +
                "productivitySINo=" + productivitySINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", serviceUtilizedBay=" + serviceUtilizedBay +
                ", bodyShopUtilizedBay=" + bodyShopUtilizedBay +
                ", month='" + month + '\'' +
                ", workedDays=" + workedDays +
                '}';
    }
}
