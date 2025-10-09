package com.mandovi.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "battery_tyre")
public class BatteryTyre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battery_tyreSINo")
    private Integer batteryTyreSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "oil_type")
    private String oilType;

    @Column(name = "sum_of_net_retail_qty")
    private int sumOfNetRetailQTY;

    @Column(name = "sum_of_net_retail_ddl")
    private Double sumOfNetRetailDDL;

    @Column(name = "sum_of_net_retail_selling")
    private Double sumOfNetRetailSelling;

    @Column(name = "period")
    private String period;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public BatteryTyre() {
    }

    public BatteryTyre(Integer batteryTyreSINo, String city, String branch, String month, String year, String oilType, int sumOfNetRetailQTY, Double sumOfNetRetailDDL, Double sumOfNetRetailSelling, String period, String qtrWise, String halfYear) {
        this.batteryTyreSINo = batteryTyreSINo;
        this.city = city;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.oilType = oilType;
        this.sumOfNetRetailQTY = sumOfNetRetailQTY;
        this.sumOfNetRetailDDL = sumOfNetRetailDDL;
        this.sumOfNetRetailSelling = sumOfNetRetailSelling;
        this.period = period;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getBatteryTyreSINo() {
        return batteryTyreSINo;
    }

    public void setBatteryTyreSINo(Integer batteryTyreSINo) {
        this.batteryTyreSINo = batteryTyreSINo;
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

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public int getSumOfNetRetailQTY() {
        return sumOfNetRetailQTY;
    }

    public void setSumOfNetRetailQTY(int sumOfNetRetailQTY) {
        this.sumOfNetRetailQTY = sumOfNetRetailQTY;
    }

    public Double getSumOfNetRetailDDL() {
        return sumOfNetRetailDDL;
    }

    public void setSumOfNetRetailDDL(Double sumOfNetRetailDDL) {
        this.sumOfNetRetailDDL = sumOfNetRetailDDL;
    }

    public Double getSumOfNetRetailSelling() {
        return sumOfNetRetailSelling;
    }

    public void setSumOfNetRetailSelling(Double sumOfNetRetailSelling) {
        this.sumOfNetRetailSelling = sumOfNetRetailSelling;
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
        return "BatteryTyre{" +
                "batteryTyreSINo=" + batteryTyreSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", oilType='" + oilType + '\'' +
                ", sumOfNetRetailQTY=" + sumOfNetRetailQTY +
                ", sumOfNetRetailDDL=" + sumOfNetRetailDDL +
                ", sumOfNetRetailSelling=" + sumOfNetRetailSelling +
                ", period='" + period + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
