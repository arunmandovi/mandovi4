package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "due_done")
public class DueDone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "due_doneSINo")
    private Integer dueDoneSINo;

    @Column(name = "city")
    private String city;

    @Column (name = "branch")
    private String branch;

    @Column (name = "channel")
    private String channel;

    @Column (name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "total_due")
    private int totalDue;

    @Column (name = "total_done")
    private int totalDone;

    @Column (name = "qtr_wise")
    private String qtrWise;

    @Column (name = "half_year")
    private String halfYear;

    public DueDone() {
    }

    public DueDone(Integer dueDoneSINo, String city, String branch, String channel, String month, String year, int totalDue, int totalDone, String qtrWise, String halfYear) {
        this.dueDoneSINo = dueDoneSINo;
        this.city = city;
        this.branch = branch;
        this.channel = channel;
        this.month = month;
        this.year = year;
        this.totalDue = totalDue;
        this.totalDone = totalDone;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getDueDoneSINo() {
        return dueDoneSINo;
    }

    public void setDueDoneSINo(Integer dueDoneSINo) {
        this.dueDoneSINo = dueDoneSINo;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public int getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(int totalDue) {
        this.totalDue = totalDue;
    }

    public int getTotalDone() {
        return totalDone;
    }

    public void setTotalDone(int totalDone) {
        this.totalDone = totalDone;
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
        return "DueDone{" +
                "dueDoneSINo=" + dueDoneSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", channel='" + channel + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", totalDue=" + totalDue +
                ", totalDone=" + totalDone +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
