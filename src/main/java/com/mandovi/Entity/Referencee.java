package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "referencee")
public class Referencee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referenceSINO")
    private Integer referenceSINO;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "group_designation")
    private String groupDesignation;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "period")
    private String period;

    @Column(name = "channel")
    private String channel;

    @Column(name = "referencee")
    private Integer referencee;

    @Column(name = "enquiry")
    private Integer enquiry;

    @Column(name = "booking")
    private Integer booking;

    @Column(name = "invoice")
    private Integer invoice;

    @Column(name = "financial_year")
    private String financialYear;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public Referencee() {
    }

    public Referencee(Integer referenceSINO, String city, String branch, String groupDesignation, String month, String year, String period, String channel, Integer referencee, Integer enquiry, Integer booking, Integer invoice, String financialYear, String qtrWise, String halfYear) {
        this.referenceSINO = referenceSINO;
        this.city = city;
        this.branch = branch;
        this.groupDesignation = groupDesignation;
        this.month = month;
        this.year = year;
        this.period = period;
        this.channel = channel;
        this.referencee = referencee;
        this.enquiry = enquiry;
        this.booking = booking;
        this.invoice = invoice;
        this.financialYear = financialYear;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getReferenceSINO() {
        return referenceSINO;
    }

    public void setReferenceSINO(Integer referenceSINO) {
        this.referenceSINO = referenceSINO;
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

    public String getGroupDesignation() {
        return groupDesignation;
    }

    public void setGroupDesignation(String groupDesignation) {
        this.groupDesignation = groupDesignation;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getReferencee() {
        return referencee;
    }

    public void setReferencee(Integer referencee) {
        this.referencee = referencee;
    }

    public Integer getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(Integer enquiry) {
        this.enquiry = enquiry;
    }

    public Integer getBooking() {
        return booking;
    }

    public void setBooking(Integer booking) {
        this.booking = booking;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
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
        return "Referencee{" +
                "referenceSINO=" + referenceSINO +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", groupDesignation='" + groupDesignation + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", period='" + period + '\'' +
                ", channel='" + channel + '\'' +
                ", referencee=" + referencee +
                ", enquiry=" + enquiry +
                ", booking=" + booking +
                ", invoice=" + invoice +
                ", financialYear='" + financialYear + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
