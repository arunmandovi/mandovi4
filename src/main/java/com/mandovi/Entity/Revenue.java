package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "revenue")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revenueSINo")
    private int revenueSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "period")
    private String period;

    @Column(name = "branch")
    private String branch;

    @Column(name = "branchSINo")
    private Integer branchSINo;

    @Column(name = "sr_labour_last_year")
    private Double  srLabourLastYear;

    @Column(name = "sr_labour_current_year")
    private Double  srLabourCurrentYear;

    @Column(name = "sr_labour_growth")
    private Double  srLabourGrowth;

    @Column(name = "br_labour_last_year")
    private Double brLabourLastYear;

    @Column(name = "br_labour_current_year")
    private Double brLabourCurrentYear;

    @Column(name = "br_labour_growth")
    private Double brLabourGrowth;

    @Column(name = "sr_and_br_labour_last_year")
    private Double srAndBrLabourLastYear;

    @Column(name = "sr_and_br_labour_current_year")
    private Double srAndBrLabourCurrentYear;

    @Column(name = "sr_and_br_labour_growth")
    private Double srAndBrLabourGrowth;

    @Column(name = "sr_spares_last_year")
    private Double srSparesLastYear;

    @Column(name = "sr_spares_current_year")
    private Double srSparesCurrentYear;

    @Column(name = "sr_spares_growth")
    private Double srSparesGrowth;

    @Column(name = "br_spares_last_year")
    private Double brSparesLastYear;

    @Column(name = "br_spares_current_year")
    private Double brSparesCurrentYear;

    @Column(name = "br_spares_growth")
    private Double brSparesGrowth;

    @Column(name = "sr_and_br_spares_last_year")
    private Double srAndBrSparesLastYear;

    @Column(name = "sr_and_br_spares_current_year")
    private Double srAndBrSparesCurrentYear;

    @Column(name = "sr_and_br_spares_growth")
    private Double srAndBrSparesGrowth;

    @Column(name = "sr_and_br_total_last_year")
    private Double srAndBrTotalLastYear;

    @Column(name = "sr_and_br_total_current_year")
    private Double srAndBrTotalCurrentYear;

    @Column(name = "sr_and_br_total_growth")
    private Double srAndBrTotalGrowth;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public Revenue() {
    }

    public Revenue(int revenueSINo, String city, String month, String year, String period, String branch, Integer branchSINo, Double srLabourLastYear, Double srLabourCurrentYear, Double srLabourGrowth, Double brLabourLastYear, Double brLabourCurrentYear, Double brLabourGrowth, Double srAndBrLabourLastYear, Double srAndBrLabourCurrentYear, Double srAndBrLabourGrowth, Double srSparesLastYear, Double srSparesCurrentYear, Double srSparesGrowth, Double brSparesLastYear, Double brSparesCurrentYear, Double brSparesGrowth, Double srAndBrSparesLastYear, Double srAndBrSparesCurrentYear, Double srAndBrSparesGrowth, Double srAndBrTotalLastYear, Double srAndBrTotalCurrentYear, Double srAndBrTotalGrowth, String qtrWise, String halfYear) {
        this.revenueSINo = revenueSINo;
        this.city = city;
        this.month = month;
        this.year = year;
        this.period = period;
        this.branch = branch;
        this.branchSINo = branchSINo;
        this.srLabourLastYear = srLabourLastYear;
        this.srLabourCurrentYear = srLabourCurrentYear;
        this.srLabourGrowth = srLabourGrowth;
        this.brLabourLastYear = brLabourLastYear;
        this.brLabourCurrentYear = brLabourCurrentYear;
        this.brLabourGrowth = brLabourGrowth;
        this.srAndBrLabourLastYear = srAndBrLabourLastYear;
        this.srAndBrLabourCurrentYear = srAndBrLabourCurrentYear;
        this.srAndBrLabourGrowth = srAndBrLabourGrowth;
        this.srSparesLastYear = srSparesLastYear;
        this.srSparesCurrentYear = srSparesCurrentYear;
        this.srSparesGrowth = srSparesGrowth;
        this.brSparesLastYear = brSparesLastYear;
        this.brSparesCurrentYear = brSparesCurrentYear;
        this.brSparesGrowth = brSparesGrowth;
        this.srAndBrSparesLastYear = srAndBrSparesLastYear;
        this.srAndBrSparesCurrentYear = srAndBrSparesCurrentYear;
        this.srAndBrSparesGrowth = srAndBrSparesGrowth;
        this.srAndBrTotalLastYear = srAndBrTotalLastYear;
        this.srAndBrTotalCurrentYear = srAndBrTotalCurrentYear;
        this.srAndBrTotalGrowth = srAndBrTotalGrowth;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public int getRevenueSINo() {
        return revenueSINo;
    }

    public void setRevenueSINo(int revenueSINo) {
        this.revenueSINo = revenueSINo;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getBranchSINo() {
        return branchSINo;
    }

    public void setBranchSINo(Integer branchSINo) {
        this.branchSINo = branchSINo;
    }

    public Double getSrLabourLastYear() {
        return srLabourLastYear;
    }

    public void setSrLabourLastYear(Double srLabourLastYear) {
        this.srLabourLastYear = srLabourLastYear;
    }

    public Double getSrLabourCurrentYear() {
        return srLabourCurrentYear;
    }

    public void setSrLabourCurrentYear(Double srLabourCurrentYear) {
        this.srLabourCurrentYear = srLabourCurrentYear;
    }

    public Double getSrLabourGrowth() {
        return srLabourGrowth;
    }

    public void setSrLabourGrowth(Double srLabourGrowth) {
        this.srLabourGrowth = srLabourGrowth;
    }

    public Double getBrLabourLastYear() {
        return brLabourLastYear;
    }

    public void setBrLabourLastYear(Double brLabourLastYear) {
        this.brLabourLastYear = brLabourLastYear;
    }

    public Double getBrLabourCurrentYear() {
        return brLabourCurrentYear;
    }

    public void setBrLabourCurrentYear(Double brLabourCurrentYear) {
        this.brLabourCurrentYear = brLabourCurrentYear;
    }

    public Double getBrLabourGrowth() {
        return brLabourGrowth;
    }

    public void setBrLabourGrowth(Double brLabourGrowth) {
        this.brLabourGrowth = brLabourGrowth;
    }

    public Double getSrAndBrLabourLastYear() {
        return srAndBrLabourLastYear;
    }

    public void setSrAndBrLabourLastYear(Double srAndBrLabourLastYear) {
        this.srAndBrLabourLastYear = srAndBrLabourLastYear;
    }

    public Double getSrAndBrLabourCurrentYear() {
        return srAndBrLabourCurrentYear;
    }

    public void setSrAndBrLabourCurrentYear(Double srAndBrLabourCurrentYear) {
        this.srAndBrLabourCurrentYear = srAndBrLabourCurrentYear;
    }

    public Double getSrAndBrLabourGrowth() {
        return srAndBrLabourGrowth;
    }

    public void setSrAndBrLabourGrowth(Double srAndBrLabourGrowth) {
        this.srAndBrLabourGrowth = srAndBrLabourGrowth;
    }

    public Double getSrSparesLastYear() {
        return srSparesLastYear;
    }

    public void setSrSparesLastYear(Double srSparesLastYear) {
        this.srSparesLastYear = srSparesLastYear;
    }

    public Double getSrSparesCurrentYear() {
        return srSparesCurrentYear;
    }

    public void setSrSparesCurrentYear(Double srSparesCurrentYear) {
        this.srSparesCurrentYear = srSparesCurrentYear;
    }

    public Double getSrSparesGrowth() {
        return srSparesGrowth;
    }

    public void setSrSparesGrowth(Double srSparesGrowth) {
        this.srSparesGrowth = srSparesGrowth;
    }

    public Double getBrSparesLastYear() {
        return brSparesLastYear;
    }

    public void setBrSparesLastYear(Double brSparesLastYear) {
        this.brSparesLastYear = brSparesLastYear;
    }

    public Double getBrSparesCurrentYear() {
        return brSparesCurrentYear;
    }

    public void setBrSparesCurrentYear(Double brSparesCurrentYear) {
        this.brSparesCurrentYear = brSparesCurrentYear;
    }

    public Double getBrSparesGrowth() {
        return brSparesGrowth;
    }

    public void setBrSparesGrowth(Double brSparesGrowth) {
        this.brSparesGrowth = brSparesGrowth;
    }

    public Double getSrAndBrSparesLastYear() {
        return srAndBrSparesLastYear;
    }

    public void setSrAndBrSparesLastYear(Double srAndBrSparesLastYear) {
        this.srAndBrSparesLastYear = srAndBrSparesLastYear;
    }

    public Double getSrAndBrSparesCurrentYear() {
        return srAndBrSparesCurrentYear;
    }

    public void setSrAndBrSparesCurrentYear(Double srAndBrSparesCurrentYear) {
        this.srAndBrSparesCurrentYear = srAndBrSparesCurrentYear;
    }

    public Double getSrAndBrSparesGrowth() {
        return srAndBrSparesGrowth;
    }

    public void setSrAndBrSparesGrowth(Double srAndBrSparesGrowth) {
        this.srAndBrSparesGrowth = srAndBrSparesGrowth;
    }

    public Double getSrAndBrTotalLastYear() {
        return srAndBrTotalLastYear;
    }

    public void setSrAndBrTotalLastYear(Double srAndBrTotalLastYear) {
        this.srAndBrTotalLastYear = srAndBrTotalLastYear;
    }

    public Double getSrAndBrTotalCurrentYear() {
        return srAndBrTotalCurrentYear;
    }

    public void setSrAndBrTotalCurrentYear(Double srAndBrTotalCurrentYear) {
        this.srAndBrTotalCurrentYear = srAndBrTotalCurrentYear;
    }

    public Double getSrAndBrTotalGrowth() {
        return srAndBrTotalGrowth;
    }

    public void setSrAndBrTotalGrowth(Double srAndBrTotalGrowth) {
        this.srAndBrTotalGrowth = srAndBrTotalGrowth;
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
        return "Revenue{" +
                "revenueSINo=" + revenueSINo +
                ", city='" + city + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", period='" + period + '\'' +
                ", branch='" + branch + '\'' +
                ", branchSINo=" + branchSINo +
                ", srLabourLastYear=" + srLabourLastYear +
                ", srLabourCurrentYear=" + srLabourCurrentYear +
                ", srLabourGrowth=" + srLabourGrowth +
                ", brLabourLastYear=" + brLabourLastYear +
                ", brLabourCurrentYear=" + brLabourCurrentYear +
                ", brLabourGrowth=" + brLabourGrowth +
                ", srAndBrLabourLastYear=" + srAndBrLabourLastYear +
                ", srAndBrLabourCurrentYear=" + srAndBrLabourCurrentYear +
                ", srAndBrLabourGrowth=" + srAndBrLabourGrowth +
                ", srSparesLastYear=" + srSparesLastYear +
                ", srSparesCurrentYear=" + srSparesCurrentYear +
                ", srSparesGrowth=" + srSparesGrowth +
                ", brSparesLastYear=" + brSparesLastYear +
                ", brSparesCurrentYear=" + brSparesCurrentYear +
                ", brSparesGrowth=" + brSparesGrowth +
                ", srAndBrSparesLastYear=" + srAndBrSparesLastYear +
                ", srAndBrSparesCurrentYear=" + srAndBrSparesCurrentYear +
                ", srAndBrSparesGrowth=" + srAndBrSparesGrowth +
                ", srAndBrTotalLastYear=" + srAndBrTotalLastYear +
                ", srAndBrTotalCurrentYear=" + srAndBrTotalCurrentYear +
                ", srAndBrTotalGrowth=" + srAndBrTotalGrowth +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
