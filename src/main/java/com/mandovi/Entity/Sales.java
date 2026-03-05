package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "salesSINo")
    private Integer salesSINo;

    @Column (name = "branch")
    private String branch;

    @Column (name = "city")
    private String city;

    @Column (name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "channel")
    private String channel;

    @Column (name = "vin")
    private Integer vin;

    public Sales() {
    }

    public Sales(Integer salesSINo, String branch, String city, String month, String year, String channel, Integer vin) {
        this.salesSINo = salesSINo;
        this.branch = branch;
        this.city = city;
        this.month = month;
        this.year = year;
        this.channel = channel;
        this.vin = vin;
    }

    public Integer getSalesSINo() {
        return salesSINo;
    }

    public void setSalesSINo(Integer salesSINo) {
        this.salesSINo = salesSINo;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getVin() {
        return vin;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "salesSINo=" + salesSINo +
                ", branch='" + branch + '\'' +
                ", city='" + city + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", channel='" + channel + '\'' +
                ", vin=" + vin +
                '}';
    }
}
