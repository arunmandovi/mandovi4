package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "br_conversion")
public class BR_Conversion {
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
    private Double labour_amt;

    @Column(name = "part_amount")
    private Double part_amount;

    @Column(name = "bill_amount")
    private Double bill_amount;

    @Column(name = "no")
    private int no;

    @Column(name = "br_conversion")
    private int br_conversion;

    @Column(name = "grand_total")
    private int grand_total;

    @Column(name = "period")
    private String period;

    @Column(name = "qtr_wise")
    private String qtr_wise;

    @Column(name = "half_year")
    private String half_year;

    public BR_Conversion() {
    }

    public BR_Conversion(Integer br_conversionSINo, String city, String branch, String month, String year, String channel, Double labour_amt, Double part_amount, Double bill_amount, int no, int br_conversion, int grand_total, String period, String qtr_wise, String half_year) {
        this.br_conversionSINo = br_conversionSINo;
        this.city = city;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.channel = channel;
        this.labour_amt = labour_amt;
        this.part_amount = part_amount;
        this.bill_amount = bill_amount;
        this.no = no;
        this.br_conversion = br_conversion;
        this.grand_total = grand_total;
        this.period = period;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
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

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Double getLabour_amt() {
        return labour_amt;
    }

    public void setLabour_amt(Double labour_amt) {
        this.labour_amt = labour_amt;
    }

    public Double getPart_amount() {
        return part_amount;
    }

    public void setPart_amount(Double part_amount) {
        this.part_amount = part_amount;
    }

    public Double getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(Double bill_amount) {
        this.bill_amount = bill_amount;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getBr_conversion() {
        return br_conversion;
    }

    public void setBr_conversion(int br_conversion) {
        this.br_conversion = br_conversion;
    }

    public int getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(int grand_total) {
        this.grand_total = grand_total;
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
        return "BR_Conversion{" +
                "br_conversionSINo=" + br_conversionSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", channel='" + channel + '\'' +
                ", labour_amt=" + labour_amt +
                ", part_amount=" + part_amount +
                ", bill_amount=" + bill_amount +
                ", no=" + no +
                ", br_conversion=" + br_conversion +
                ", grand_total=" + grand_total +
                ", period='" + period + '\'' +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                '}';
    }
}
