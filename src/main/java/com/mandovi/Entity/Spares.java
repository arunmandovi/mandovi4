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
    private Double srSparesLastYear;

    @Column(name = "sr_spares_current_year")
    private Double srSparesCurrentYear;

    @Column(name = "br_spares_last_year")
    private Double brSparesLastYear;

    @Column(name = "br_spares_current_year")
    private Double brSparesCurrentYear;

    @Column(name = "srbr_spares_last_year")
    private Double srBrSparesLastYear;

    @Column(name = "srbr_spares_current_year")
    private Double srBrSparesCurrentYear;

    @Column(name = "battery_last_year")
    private Double batteryLastYear;

    @Column(name = "battery_current_year")
    private Double batteryCurrentYear;

    @Column(name = "tyre_last_year")
    private Double tyreLastYear;

    @Column(name = "tyre_current_year")
    private Double tyreCurrentYear;

    @Column(name = "sr_spares_growth")
    private Double srSparesGrowth;

    @Column(name = "br_spares_growth")
    private Double brSparesGrowth;

    @Column(name = "srbr_spares_growth")
    private Double srBrSparesGrowth;

    @Column(name = "battery_growth")
    private Double batteryGrowth;

    @Column(name = "tyre_growth")
    private Double tyreGrowth;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public Spares() {
    }

    public Spares(int sparesSINo, String city, String month, String year, String period, String branch, Double srSparesLastYear, Double srSparesCurrentYear, Double brSparesLastYear, Double brSparesCurrentYear, Double srBrSparesLastYear, Double srBrSparesCurrentYear, Double batteryLastYear, Double batteryCurrentYear, Double tyreLastYear, Double tyreCurrentYear, Double srSparesGrowth, Double brSparesGrowth, Double srBrSparesGrowth, Double batteryGrowth, Double tyreGrowth, String qtrWise, String halfYear) {
        this.sparesSINo = sparesSINo;
        this.city = city;
        this.month = month;
        this.year = year;
        this.period = period;
        this.branch = branch;
        this.srSparesLastYear = srSparesLastYear;
        this.srSparesCurrentYear = srSparesCurrentYear;
        this.brSparesLastYear = brSparesLastYear;
        this.brSparesCurrentYear = brSparesCurrentYear;
        this.srBrSparesLastYear = srBrSparesLastYear;
        this.srBrSparesCurrentYear = srBrSparesCurrentYear;
        this.batteryLastYear = batteryLastYear;
        this.batteryCurrentYear = batteryCurrentYear;
        this.tyreLastYear = tyreLastYear;
        this.tyreCurrentYear = tyreCurrentYear;
        this.srSparesGrowth = srSparesGrowth;
        this.brSparesGrowth = brSparesGrowth;
        this.srBrSparesGrowth = srBrSparesGrowth;
        this.batteryGrowth = batteryGrowth;
        this.tyreGrowth = tyreGrowth;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
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

    public Double getSrBrSparesLastYear() {
        return srBrSparesLastYear;
    }

    public void setSrBrSparesLastYear(Double srBrSparesLastYear) {
        this.srBrSparesLastYear = srBrSparesLastYear;
    }

    public Double getSrBrSparesCurrentYear() {
        return srBrSparesCurrentYear;
    }

    public void setSrBrSparesCurrentYear(Double srBrSparesCurrentYear) {
        this.srBrSparesCurrentYear = srBrSparesCurrentYear;
    }

    public Double getBatteryLastYear() {
        return batteryLastYear;
    }

    public void setBatteryLastYear(Double batteryLastYear) {
        this.batteryLastYear = batteryLastYear;
    }

    public Double getBatteryCurrentYear() {
        return batteryCurrentYear;
    }

    public void setBatteryCurrentYear(Double batteryCurrentYear) {
        this.batteryCurrentYear = batteryCurrentYear;
    }

    public Double getTyreLastYear() {
        return tyreLastYear;
    }

    public void setTyreLastYear(Double tyreLastYear) {
        this.tyreLastYear = tyreLastYear;
    }

    public Double getTyreCurrentYear() {
        return tyreCurrentYear;
    }

    public void setTyreCurrentYear(Double tyreCurrentYear) {
        this.tyreCurrentYear = tyreCurrentYear;
    }

    public Double getSrSparesGrowth() {
        return srSparesGrowth;
    }

    public void setSrSparesGrowth(Double srSparesGrowth) {
        this.srSparesGrowth = srSparesGrowth;
    }

    public Double getBrSparesGrowth() {
        return brSparesGrowth;
    }

    public void setBrSparesGrowth(Double brSparesGrowth) {
        this.brSparesGrowth = brSparesGrowth;
    }

    public Double getSrBrSparesGrowth() {
        return srBrSparesGrowth;
    }

    public void setSrBrSparesGrowth(Double srBrSparesGrowth) {
        this.srBrSparesGrowth = srBrSparesGrowth;
    }

    public Double getBatteryGrowth() {
        return batteryGrowth;
    }

    public void setBatteryGrowth(Double batteryGrowth) {
        this.batteryGrowth = batteryGrowth;
    }

    public Double getTyreGrowth() {
        return tyreGrowth;
    }

    public void setTyreGrowth(Double tyreGrowth) {
        this.tyreGrowth = tyreGrowth;
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
        return "Spares{" +
                "sparesSINo=" + sparesSINo +
                ", city='" + city + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", period='" + period + '\'' +
                ", branch='" + branch + '\'' +
                ", srSparesLastYear=" + srSparesLastYear +
                ", srSparesCurrentYear=" + srSparesCurrentYear +
                ", brSparesLastYear=" + brSparesLastYear +
                ", brSparesCurrentYear=" + brSparesCurrentYear +
                ", srBrSparesLastYear=" + srBrSparesLastYear +
                ", srBrSparesCurrentYear=" + srBrSparesCurrentYear +
                ", batteryLastYear=" + batteryLastYear +
                ", batteryCurrentYear=" + batteryCurrentYear +
                ", tyreLastYear=" + tyreLastYear +
                ", tyreCurrentYear=" + tyreCurrentYear +
                ", srSparesGrowth=" + srSparesGrowth +
                ", brSparesGrowth=" + brSparesGrowth +
                ", srBrSparesGrowth=" + srBrSparesGrowth +
                ", batteryGrowth=" + batteryGrowth +
                ", tyreGrowth=" + tyreGrowth +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
