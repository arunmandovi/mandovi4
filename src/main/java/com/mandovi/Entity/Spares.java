package com.mandovi.Entity;

import jakarta.persistence.*;
import org.apache.commons.math3.analysis.function.Identity;

@Entity
@Table(name = "spares")
public class Spares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sparesSINo")
    private int sparesSINo;

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

    @Column(name = "sr_spares_last_year")
    private Double sr_spares_last_year;

    @Column(name = "sr_spares_current_year")
    private Double sr_spares_current_year;

    @Column(name = "br_spares_last_year")
    private Double br_spares_last_year;

    @Column(name = "br_spares_current_year")
    private Double br_spares_current_year;

    @Column(name = "srbr_spares_last_year")
    private Double srbr_spares_last_year;

    @Column(name = "srbr_spares_current_year")
    private Double srbr_spares_current_year;

    @Column(name = "battery_last_year")
    private Double battery_last_year;

    @Column(name = "battery_current_year")
    private Double battery_current_year;

    @Column(name = "tyre_last_year")
    private Double tyre_last_year;

    @Column(name = "tyre_current_year")
    private Double tyre_current_year;

    @Column(name = "sr_spares_growth")
    private Double sr_spares_growth;

    @Column(name = "br_spares_growth")
    private Double br_spares_growth;

    @Column(name = "srbr_spares_growth")
    private Double srbr_spares_growth;

    @Column(name = "battery_growth")
    private Double battery_growth;

    @Column(name = "tyre_growth")
    private Double tyre_growth;

    @Column(name = "qtr_wise")
    private String qtr_wise;

    @Column(name = "half_year")
    private String half_year;

    public Spares() {
    }

    public Spares(int sparesSINo, String city, String month, String year, String period, String branch, Double sr_spares_last_year, Double sr_spares_current_year, Double br_spares_last_year, Double br_spares_current_year, Double srbr_spares_last_year, Double srbr_spares_current_year, Double battery_last_year, Double battery_current_year, Double tyre_last_year, Double tyre_current_year, Double sr_spares_growth, Double br_spares_growth, Double srbr_spares_growth, Double battery_growth, Double tyre_growth, String qtr_wise, String half_year) {
        this.sparesSINo = sparesSINo;
        this.city = city;
        this.month = month;
        this.year = year;
        this.period = period;
        this.branch = branch;
        this.sr_spares_last_year = sr_spares_last_year;
        this.sr_spares_current_year = sr_spares_current_year;
        this.br_spares_last_year = br_spares_last_year;
        this.br_spares_current_year = br_spares_current_year;
        this.srbr_spares_last_year = srbr_spares_last_year;
        this.srbr_spares_current_year = srbr_spares_current_year;
        this.battery_last_year = battery_last_year;
        this.battery_current_year = battery_current_year;
        this.tyre_last_year = tyre_last_year;
        this.tyre_current_year = tyre_current_year;
        this.sr_spares_growth = sr_spares_growth;
        this.br_spares_growth = br_spares_growth;
        this.srbr_spares_growth = srbr_spares_growth;
        this.battery_growth = battery_growth;
        this.tyre_growth = tyre_growth;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
    }

    public int getSparesSINo() {
        return sparesSINo;
    }

    public void setSparesSINo(int sparesSINo) {
        this.sparesSINo = sparesSINo;
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

    public Double getSrbr_spares_last_year() {
        return srbr_spares_last_year;
    }

    public void setSrbr_spares_last_year(Double srbr_spares_last_year) {
        this.srbr_spares_last_year = srbr_spares_last_year;
    }

    public Double getSrbr_spares_current_year() {
        return srbr_spares_current_year;
    }

    public void setSrbr_spares_current_year(Double srbr_spares_current_year) {
        this.srbr_spares_current_year = srbr_spares_current_year;
    }

    public Double getBattery_last_year() {
        return battery_last_year;
    }

    public void setBattery_last_year(Double battery_last_year) {
        this.battery_last_year = battery_last_year;
    }

    public Double getBattery_current_year() {
        return battery_current_year;
    }

    public void setBattery_current_year(Double battery_current_year) {
        this.battery_current_year = battery_current_year;
    }

    public Double getTyre_last_year() {
        return tyre_last_year;
    }

    public void setTyre_last_year(Double tyre_last_year) {
        this.tyre_last_year = tyre_last_year;
    }

    public Double getTyre_current_year() {
        return tyre_current_year;
    }

    public void setTyre_current_year(Double tyre_current_year) {
        this.tyre_current_year = tyre_current_year;
    }

    public Double getSr_spares_growth() {
        return sr_spares_growth;
    }

    public void setSr_spares_growth(Double sr_spares_growth) {
        this.sr_spares_growth = sr_spares_growth;
    }

    public Double getBr_spares_growth() {
        return br_spares_growth;
    }

    public void setBr_spares_growth(Double br_spares_growth) {
        this.br_spares_growth = br_spares_growth;
    }

    public Double getSrbr_spares_growth() {
        return srbr_spares_growth;
    }

    public void setSrbr_spares_growth(Double srbr_spares_growth) {
        this.srbr_spares_growth = srbr_spares_growth;
    }

    public Double getBattery_growth() {
        return battery_growth;
    }

    public void setBattery_growth(Double battery_growth) {
        this.battery_growth = battery_growth;
    }

    public Double getTyre_growth() {
        return tyre_growth;
    }

    public void setTyre_growth(Double tyre_growth) {
        this.tyre_growth = tyre_growth;
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
        return "Spares{" +
                "sparesSINo=" + sparesSINo +
                ", city='" + city + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", period='" + period + '\'' +
                ", branch='" + branch + '\'' +
                ", sr_spares_last_year=" + sr_spares_last_year +
                ", sr_spares_current_year=" + sr_spares_current_year +
                ", br_spares_last_year=" + br_spares_last_year +
                ", br_spares_current_year=" + br_spares_current_year +
                ", srbr_spares_last_year=" + srbr_spares_last_year +
                ", srbr_spares_current_year=" + srbr_spares_current_year +
                ", battery_last_year=" + battery_last_year +
                ", battery_current_year=" + battery_current_year +
                ", tyre_last_year=" + tyre_last_year +
                ", tyre_current_year=" + tyre_current_year +
                ", sr_spares_growth=" + sr_spares_growth +
                ", br_spares_growth=" + br_spares_growth +
                ", srbr_spares_growth=" + srbr_spares_growth +
                ", battery_growth=" + battery_growth +
                ", tyre_growth=" + tyre_growth +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                '}';
    }
}
