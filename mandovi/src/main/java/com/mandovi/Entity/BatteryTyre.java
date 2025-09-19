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
    private int sum_of_net_retail_qty;

    @Column(name = "sum_of_net_retail_ddl")
    private Double sum_of_net_retail_ddl;

    @Column(name = "sum_of_net_retail_selling")
    private Double sum_of_net_retail_selling;

    @Column(name = "period")
    private String period;

    @Column(name = "qtr_wise")
    private String qtr_wise;

    @Column(name = "half_year")
    private String half_year;

    public BatteryTyre() {
    }

    public BatteryTyre(Integer batteryTyreSINo, String city, String branch, String month, String year, String oilType, int sum_of_net_retail_qty, Double sum_of_net_retail_ddl, Double sum_of_net_retail_selling, String period, String qtr_wise, String half_year) {
        this.batteryTyreSINo = batteryTyreSINo;
        this.city = city;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.oilType = oilType;
        this.sum_of_net_retail_qty = sum_of_net_retail_qty;
        this.sum_of_net_retail_ddl = sum_of_net_retail_ddl;
        this.sum_of_net_retail_selling = sum_of_net_retail_selling;
        this.period = period;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
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

    public int getSum_of_net_retail_qty() {
        return sum_of_net_retail_qty;
    }

    public void setSum_of_net_retail_qty(int sum_of_net_retail_qty) {
        this.sum_of_net_retail_qty = sum_of_net_retail_qty;
    }

    public Double getSum_of_net_retail_ddl() {
        return sum_of_net_retail_ddl;
    }

    public void setSum_of_net_retail_ddl(Double sum_of_net_retail_ddl) {
        this.sum_of_net_retail_ddl = sum_of_net_retail_ddl;
    }

    public Double getSum_of_net_retail_selling() {
        return sum_of_net_retail_selling;
    }

    public void setSum_of_net_retail_selling(Double sum_of_net_retail_selling) {
        this.sum_of_net_retail_selling = sum_of_net_retail_selling;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getQtr_wise() {
        return qtr_wise;
    }

    public void setQtr_wise(String qtr_wise) {
        this.qtr_wise = qtr_wise;
    }

    public String getHalf_year() {
        return half_year;
    }

    public void setHalf_year(String half_year) {
        this.half_year = half_year;
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
                ", sum_of_net_retail_qty=" + sum_of_net_retail_qty +
                ", sum_of_net_retail_ddl=" + sum_of_net_retail_ddl +
                ", sum_of_net_retail_selling=" + sum_of_net_retail_selling +
                ", period='" + period + '\'' +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                '}';
    }
}
