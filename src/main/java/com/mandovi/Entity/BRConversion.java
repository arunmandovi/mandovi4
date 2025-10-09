package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "br_conversion")
public class BRConversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "br_conversionSINo")
    private Integer br_conversionSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "channel")
    private String channel;

    @Column(name = "labour_amt")
    private Double labourAmt;

    @Column(name = "part_amount")
    private Double partAmount;

    @Column(name = "bill_amount")
    private Double billAmount;

    @Column(name = "no")
    private int no;

    @Column(name = "br_conversion")
    private int brConversion;

    @Column(name = "grand_total")
    private int grandTotal;

    @Column(name = "period")
    private String period;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public BRConversion() {
    }

    public BRConversion(Integer br_conversionSINo, String city, String branch, String month, String year, String channel, Double labourAmt, Double partAmount, Double billAmount, int no, int brConversion, int grandTotal, String period, String qtrWise, String halfYear) {
        this.br_conversionSINo = br_conversionSINo;
        this.city = city;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.channel = channel;
        this.labourAmt = labourAmt;
        this.partAmount = partAmount;
        this.billAmount = billAmount;
        this.no = no;
        this.brConversion = brConversion;
        this.grandTotal = grandTotal;
        this.period = period;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getBr_conversionSINo() {
        return br_conversionSINo;
    }

    public void setBr_conversionSINo(Integer br_conversionSINo) {
        this.br_conversionSINo = br_conversionSINo;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Double getLabourAmt() {
        return labourAmt;
    }

    public void setLabourAmt(Double labourAmt) {
        this.labourAmt = labourAmt;
    }

    public Double getPartAmount() {
        return partAmount;
    }

    public void setPartAmount(Double partAmount) {
        this.partAmount = partAmount;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getBrConversion() {
        return brConversion;
    }

    public void setBrConversion(int brConversion) {
        this.brConversion = brConversion;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
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
        return "BR_Conversion{" +
                "br_conversionSINo=" + br_conversionSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", channel='" + channel + '\'' +
                ", labourAmt=" + labourAmt +
                ", partAmount=" + partAmount +
                ", billAmount=" + billAmount +
                ", no=" + no +
                ", brConversion=" + brConversion +
                ", grandTotal=" + grandTotal +
                ", period='" + period + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
