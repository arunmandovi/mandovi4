package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "`reference`")
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referenceSINO")
    private Integer referenceSINO;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "group_designation")
    private String group_designation;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "period")
    private String period;

    @Column(name = "channel")
    private String channel;

    @Column(name = "reference")
    private Integer reference;

    @Column(name = "enquiry")
    private Integer enquiry;

    @Column(name = "booking")
    private Integer booking;

    @Column(name = "invoice")
    private Integer invoice;

    @Column(name = "financial_year")
    private String financial_year;

    @Column(name = "qtr_wise")
    private String qtr_wise;

    @Column(name = "half_year")
    private String half_year;

    public Reference() {
    }

    public Reference(Integer referenceSINO, String city, String branch, String group_designation, String month, String year, String period, String channel, Integer reference, Integer enquiry, Integer booking, Integer invoice, String financial_year, String qtr_wise, String half_year) {
        this.referenceSINO = referenceSINO;
        this.city = city;
        this.branch = branch;
        this.group_designation = group_designation;
        this.month = month;
        this.year = year;
        this.period = period;
        this.channel = channel;
        this.reference = reference;
        this.enquiry = enquiry;
        this.booking = booking;
        this.invoice = invoice;
        this.financial_year = financial_year;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
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

    public String getGroup_designation() {
        return group_designation;
    }

    public void setGroup_designation(String group_designation) {
        this.group_designation = group_designation;
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

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
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

    public String getFinancial_year() {
        return financial_year;
    }

    public void setFinancial_year(String financial_year) {
        this.financial_year = financial_year;
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
        return "Reference{" +
                "referenceSINO=" + referenceSINO +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", group_designation='" + group_designation + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", period='" + period + '\'' +
                ", channel='" + channel + '\'' +
                ", reference=" + reference +
                ", enquiry=" + enquiry +
                ", booking=" + booking +
                ", invoice=" + invoice +
                ", financial_year='" + financial_year + '\'' +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                '}';
    }
}
