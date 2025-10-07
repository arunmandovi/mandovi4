package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "msgp_profit")
public class MSGPProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msgp_profitSINo")
    private Integer msgp_profitSINo;

    @Column(name = "service_description")
    private String serviceDescription;

    @Column(name = "location_code")
    private String locationCode;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "net_retail_ddl")
    private Double netRetailDDL;

    @Column(name = "net_retail_selling")
    private Double netRetailSelling;

    @Column(name = "sum_of_net_retail_ddl")
    private Double sumOfNetRetailDDL;

    @Column(name = "sum_of_net_retail_selling")
    private Double sumOfNetRetailSelling;

    @Column(name = "date")
    private String date;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public MSGPProfit() {
    }

    public MSGPProfit(Integer msgp_profitSINo, String serviceDescription, String locationCode, String month, String year, Double netRetailDDL, Double netRetailSelling, Double sumOfNetRetailDDL, Double sumOfNetRetailSelling, String date, String city, String branch, String qtrWise, String halfYear) {
        this.msgp_profitSINo = msgp_profitSINo;
        this.serviceDescription = serviceDescription;
        this.locationCode = locationCode;
        this.month = month;
        this.year = year;
        this.netRetailDDL = netRetailDDL;
        this.netRetailSelling = netRetailSelling;
        this.sumOfNetRetailDDL = sumOfNetRetailDDL;
        this.sumOfNetRetailSelling = sumOfNetRetailSelling;
        this.date = date;
        this.city = city;
        this.branch = branch;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getMsgp_profitSINo() {
        return msgp_profitSINo;
    }

    public void setMsgp_profitSINo(Integer msgp_profitSINo) {
        this.msgp_profitSINo = msgp_profitSINo;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
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

    public Double getNetRetailDDL() {
        return netRetailDDL;
    }

    public void setNetRetailDDL(Double netRetailDDL) {
        this.netRetailDDL = netRetailDDL;
    }

    public Double getNetRetailSelling() {
        return netRetailSelling;
    }

    public void setNetRetailSelling(Double netRetailSelling) {
        this.netRetailSelling = netRetailSelling;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "MSGPProfit{" +
                "msgp_profitSINo=" + msgp_profitSINo +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", netRetailDDL=" + netRetailDDL +
                ", netRetailSelling=" + netRetailSelling +
                ", sumOfNetRetailDDL=" + sumOfNetRetailDDL +
                ", sumOfNetRetailSelling=" + sumOfNetRetailSelling +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
