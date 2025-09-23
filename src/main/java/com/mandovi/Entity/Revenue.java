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
    private Double  sr_labour_last_year;

    @Column(name = "sr_labour_current_year")
    private Double  sr_labour_current_year;

    @Column(name = "sr_labour_growth")
    private Double  sr_labour_growth;

    @Column(name = "br_labour_last_year")
    private Double br_labour_last_year;

    @Column(name = "br_labour_current_year")
    private Double br_labour_current_year;

    @Column(name = "br_labour_growth")
    private Double br_labour_growth;

    @Column(name = "sr_and_br_labour_last_year")
    private Double sr_and_br_labour_last_year;

    @Column(name = "sr_and_br_labour_current_year")
    private Double sr_and_br_labour_current_year;

    @Column(name = "sr_and_br_labour_growth")
    private Double sr_and_br_labour_growth;

    @Column(name = "sr_spares_last_year")
    private Double sr_spares_last_year;

    @Column(name = "sr_spares_current_year")
    private Double sr_spares_current_year;

    @Column(name = "sr_spares_growth")
    private Double sr_spares_growth;

    @Column(name = "br_spares_last_year")
    private Double br_spares_last_year;

    @Column(name = "br_spares_current_year")
    private Double br_spares_current_year;

    @Column(name = "br_spares_growth")
    private Double br_spares_growth;

    @Column(name = "sr_and_br_spares_last_year")
    private Double sr_and_br_spares_last_year;

    @Column(name = "sr_and_br_spares_current_year")
    private Double sr_and_br_spares_current_year;

    @Column(name = "sr_and_br_spares_growth")
    private Double sr_and_br_spares_growth;

    @Column(name = "sr_and_br_total_last_year")
    private Double sr_and_br_total_last_year;

    @Column(name = "sr_and_br_total_current_year")
    private Double sr_and_br_total_current_year;

    @Column(name = "sr_and_br_total_growth")
    private Double sr_and_br_total_growth;

    @Column(name = "qtr_wise")
    private String qtr_wise;

    @Column(name = "half_year")
    private String half_year;

    public Revenue() {
    }

    public Revenue(int revenueSINo, String city, String month, String year, String period, String branch, Integer branchSINo, Double sr_labour_last_year, Double sr_labour_current_year, Double sr_labour_growth, Double br_labour_last_year, Double br_labour_current_year, Double br_labour_growth, Double sr_and_br_labour_last_year, Double sr_and_br_labour_current_year, Double sr_and_br_labour_growth, Double sr_spares_last_year, Double sr_spares_current_year, Double sr_spares_growth, Double br_spares_last_year, Double br_spares_current_year, Double br_spares_growth, Double sr_and_br_spares_last_year, Double sr_and_br_spares_current_year, Double sr_and_br_spares_growth, Double sr_and_br_total_last_year, Double sr_and_br_total_current_year, Double sr_and_br_total_growth, String qtr_wise, String half_year) {
        this.revenueSINo = revenueSINo;
        this.city = city;
        this.month = month;
        this.year = year;
        this.period = period;
        this.branch = branch;
        this.branchSINo = branchSINo;
        this.sr_labour_last_year = sr_labour_last_year;
        this.sr_labour_current_year = sr_labour_current_year;
        this.sr_labour_growth = sr_labour_growth;
        this.br_labour_last_year = br_labour_last_year;
        this.br_labour_current_year = br_labour_current_year;
        this.br_labour_growth = br_labour_growth;
        this.sr_and_br_labour_last_year = sr_and_br_labour_last_year;
        this.sr_and_br_labour_current_year = sr_and_br_labour_current_year;
        this.sr_and_br_labour_growth = sr_and_br_labour_growth;
        this.sr_spares_last_year = sr_spares_last_year;
        this.sr_spares_current_year = sr_spares_current_year;
        this.sr_spares_growth = sr_spares_growth;
        this.br_spares_last_year = br_spares_last_year;
        this.br_spares_current_year = br_spares_current_year;
        this.br_spares_growth = br_spares_growth;
        this.sr_and_br_spares_last_year = sr_and_br_spares_last_year;
        this.sr_and_br_spares_current_year = sr_and_br_spares_current_year;
        this.sr_and_br_spares_growth = sr_and_br_spares_growth;
        this.sr_and_br_total_last_year = sr_and_br_total_last_year;
        this.sr_and_br_total_current_year = sr_and_br_total_current_year;
        this.sr_and_br_total_growth = sr_and_br_total_growth;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
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

    public Double getSr_labour_last_year() {
        return sr_labour_last_year;
    }

    public void setSr_labour_last_year(Double sr_labour_last_year) {
        this.sr_labour_last_year = sr_labour_last_year;
    }

    public Double getSr_labour_current_year() {
        return sr_labour_current_year;
    }

    public void setSr_labour_current_year(Double sr_labour_current_year) {
        this.sr_labour_current_year = sr_labour_current_year;
    }

    public Double getSr_labour_growth() {
        return sr_labour_growth;
    }

    public void setSr_labour_growth(Double sr_labour_growth) {
        this.sr_labour_growth = sr_labour_growth;
    }

    public Double getBr_labour_last_year() {
        return br_labour_last_year;
    }

    public void setBr_labour_last_year(Double br_labour_last_year) {
        this.br_labour_last_year = br_labour_last_year;
    }

    public Double getBr_labour_current_year() {
        return br_labour_current_year;
    }

    public void setBr_labour_current_year(Double br_labour_current_year) {
        this.br_labour_current_year = br_labour_current_year;
    }

    public Double getBr_labour_growth() {
        return br_labour_growth;
    }

    public void setBr_labour_growth(Double br_labour_growth) {
        this.br_labour_growth = br_labour_growth;
    }

    public Double getSr_and_br_labour_last_year() {
        return sr_and_br_labour_last_year;
    }

    public void setSr_and_br_labour_last_year(Double sr_and_br_labour_last_year) {
        this.sr_and_br_labour_last_year = sr_and_br_labour_last_year;
    }

    public Double getSr_and_br_labour_current_year() {
        return sr_and_br_labour_current_year;
    }

    public void setSr_and_br_labour_current_year(Double sr_and_br_labour_current_year) {
        this.sr_and_br_labour_current_year = sr_and_br_labour_current_year;
    }

    public Double getSr_and_br_labour_growth() {
        return sr_and_br_labour_growth;
    }

    public void setSr_and_br_labour_growth(Double sr_and_br_labour_growth) {
        this.sr_and_br_labour_growth = sr_and_br_labour_growth;
    }

    public Double getSr_spares_last_year() {
        return sr_spares_last_year;
    }

    public void setSr_spares_last_year(Double sr_spares_last_year) {
        this.sr_spares_last_year = sr_spares_last_year;
    }

    public Double getSr_spares_current_year() {
        return sr_spares_current_year;
    }

    public void setSr_spares_current_year(Double sr_spares_current_year) {
        this.sr_spares_current_year = sr_spares_current_year;
    }

    public Double getSr_spares_growth() {
        return sr_spares_growth;
    }

    public void setSr_spares_growth(Double sr_spares_growth) {
        this.sr_spares_growth = sr_spares_growth;
    }

    public Double getBr_spares_last_year() {
        return br_spares_last_year;
    }

    public void setBr_spares_last_year(Double br_spares_last_year) {
        this.br_spares_last_year = br_spares_last_year;
    }

    public Double getBr_spares_current_year() {
        return br_spares_current_year;
    }

    public void setBr_spares_current_year(Double br_spares_current_year) {
        this.br_spares_current_year = br_spares_current_year;
    }

    public Double getBr_spares_growth() {
        return br_spares_growth;
    }

    public void setBr_spares_growth(Double br_spares_growth) {
        this.br_spares_growth = br_spares_growth;
    }

    public Double getSr_and_br_spares_last_year() {
        return sr_and_br_spares_last_year;
    }

    public void setSr_and_br_spares_last_year(Double sr_and_br_spares_last_year) {
        this.sr_and_br_spares_last_year = sr_and_br_spares_last_year;
    }

    public Double getSr_and_br_spares_current_year() {
        return sr_and_br_spares_current_year;
    }

    public void setSr_and_br_spares_current_year(Double sr_and_br_spares_current_year) {
        this.sr_and_br_spares_current_year = sr_and_br_spares_current_year;
    }

    public Double getSr_and_br_spares_growth() {
        return sr_and_br_spares_growth;
    }

    public void setSr_and_br_spares_growth(Double sr_and_br_spares_growth) {
        this.sr_and_br_spares_growth = sr_and_br_spares_growth;
    }

    public Double getSr_and_br_total_last_year() {
        return sr_and_br_total_last_year;
    }

    public void setSr_and_br_total_last_year(Double sr_and_br_total_last_year) {
        this.sr_and_br_total_last_year = sr_and_br_total_last_year;
    }

    public Double getSr_and_br_total_current_year() {
        return sr_and_br_total_current_year;
    }

    public void setSr_and_br_total_current_year(Double sr_and_br_total_current_year) {
        this.sr_and_br_total_current_year = sr_and_br_total_current_year;
    }

    public Double getSr_and_br_total_growth() {
        return sr_and_br_total_growth;
    }

    public void setSr_and_br_total_growth(Double sr_and_br_total_growth) {
        this.sr_and_br_total_growth = sr_and_br_total_growth;
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
        return "Revenue{" +
                "revenueSINo=" + revenueSINo +
                ", city='" + city + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", period='" + period + '\'' +
                ", branch='" + branch + '\'' +
                ", branchSINo='" + branchSINo + '\'' +
                ", sr_labour_last_year=" + sr_labour_last_year +
                ", sr_labour_current_year=" + sr_labour_current_year +
                ", sr_labour_growth=" + sr_labour_growth +
                ", br_labour_last_year=" + br_labour_last_year +
                ", br_labour_current_year=" + br_labour_current_year +
                ", br_labour_growth=" + br_labour_growth +
                ", sr_and_br_labour_last_year=" + sr_and_br_labour_last_year +
                ", sr_and_br_labour_current_year=" + sr_and_br_labour_current_year +
                ", sr_and_br_labour_growth=" + sr_and_br_labour_growth +
                ", sr_spares_last_year=" + sr_spares_last_year +
                ", sr_spares_current_year=" + sr_spares_current_year +
                ", sr_spares_growth=" + sr_spares_growth +
                ", br_spares_last_year=" + br_spares_last_year +
                ", br_spares_current_year=" + br_spares_current_year +
                ", br_spares_growth=" + br_spares_growth +
                ", sr_and_br_spares_last_year=" + sr_and_br_spares_last_year +
                ", sr_and_br_spares_current_year=" + sr_and_br_spares_current_year +
                ", sr_and_br_spares_growth=" + sr_and_br_spares_growth +
                ", sr_and_br_total_last_year=" + sr_and_br_total_last_year +
                ", sr_and_br_total_current_year=" + sr_and_br_total_current_year +
                ", sr_and_br_total_growth=" + sr_and_br_total_growth +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                '}';
    }
}
