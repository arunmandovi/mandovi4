package com.mandovi.Entity;

import jakarta.persistence.*;
import org.apache.commons.math3.analysis.function.Identity;

@Entity
@Table(name = "labour")
public class Labour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "labourSINo")
    private Integer labourSINo;

    @Column(name = "branch")
    private String branch;

    @Column(name = "channel")
    private String channel;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "service_type_code")
    private String serviceTypeCode;

    @Column(name = "sum_of_basic_amt")
    private Double sumOfBasicAmt;

    @Column(name = "labour")
    private Double labour;

    @Column(name = "period")
    private String period;

    @Column(name = "city")
    private String city;

    @Column(name = "financial_year")
    private String financialYear;

    @Column(name = "load_type")
    private String loadType;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public Labour() {
    }

    public Labour(Integer labourSINo, String branch, String channel, String year, String month, String serviceTypeCode, Double sumOfBasicAmt, Double labour, String period, String city, String financialYear, String loadType, String qtrWise, String halfYear) {
        this.labourSINo = labourSINo;
        this.branch = branch;
        this.channel = channel;
        this.year = year;
        this.month = month;
        this.serviceTypeCode = serviceTypeCode;
        this.sumOfBasicAmt = sumOfBasicAmt;
        this.labour = labour;
        this.period = period;
        this.city = city;
        this.branch = branch;
        this.financialYear = financialYear;
        this.loadType = loadType;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getLabourSINo() {
        return labourSINo;
    }

    public void setLabourSINo(Integer labourSINo) {
        this.labourSINo = labourSINo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    public Double getSumOfBasicAmt() {
        return sumOfBasicAmt;
    }

    public void setSumOfBasicAmt(Double sumOfBasicAmt) {
        this.sumOfBasicAmt = sumOfBasicAmt;
    }

    public Double getLabour() {
        return labour;
    }

    public void setLabour(Double labour) {
        this.labour = labour;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
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
        return "Labour{" +
                "labourSINo=" + labourSINo +
                ", branch='" + branch + '\'' +
                ", channel='" + channel + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", serviceTypeCode='" + serviceTypeCode + '\'' +
                ", sumOfBasicAmt=" + sumOfBasicAmt +
                ", labour=" + labour +
                ", period='" + period + '\'' +
                ", city='" + city + '\'' +
                ", financialYear='" + financialYear + '\'' +
                ", loadType='" + loadType + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
