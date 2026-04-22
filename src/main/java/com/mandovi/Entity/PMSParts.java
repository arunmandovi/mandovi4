package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pms_parts")
public class PMSParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pms_partsSINo")
    private Integer pms_partsSINo;

    @Column(name = "period")
    private LocalDate period;

    @Column(name = "part_group")
    private String partGroup;

    @Column(name = "required")
    private Integer required;

    @Column(name = "changed")
    private Integer changed;

    @Column(name = "pms")
    private Double pms;

    @Column(name = "branch")
    private String branch;

    @Column(name = "city")
    private String city;

    @Column(name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "financial_year")
    private String financialYear;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public PMSParts() {
    }

    public PMSParts(Integer pms_partsSINo, LocalDate period, String partGroup, Integer required, Integer changed, Double pms, String branch, String city, String month, String year, String financialYear, String qtrWise, String halfYear) {
        this.pms_partsSINo = pms_partsSINo;
        this.period = period;
        this.partGroup = partGroup;
        this.required = required;
        this.changed = changed;
        this.pms = pms;
        this.branch = branch;
        this.city = city;
        this.month = month;
        this.year = year;
        this.financialYear = financialYear;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getPms_partsSINo() {
        return pms_partsSINo;
    }

    public void setPms_partsSINo(Integer pms_partsSINo) {
        this.pms_partsSINo = pms_partsSINo;
    }

    public LocalDate getPeriod() {
        return period;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    public String getPartGroup() {
        return partGroup;
    }

    public void setPartGroup(String partGroup) {
        this.partGroup = partGroup;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public Integer getChanged() {
        return changed;
    }

    public void setChanged(Integer changed) {
        this.changed = changed;
    }

    public Double getPms() {
        return pms;
    }

    public void setPms(Double pms) {
        this.pms = pms;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        return "PMSParts{" +
                "pms_partsSINo=" + pms_partsSINo +
                ", period=" + period +
                ", partGroup='" + partGroup + '\'' +
                ", required=" + required +
                ", changed=" + changed +
                ", pms=" + pms +
                ", branch='" + branch + '\'' +
                ", city='" + city + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", financialYear='" + financialYear + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
