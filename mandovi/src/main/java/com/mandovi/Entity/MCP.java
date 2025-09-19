package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mcp")
public class MCP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcpSINo")
    private Integer mcpSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "location")
    private String location;

    @Column(name = "model")
    private String model;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "mcp_quantity")
    private Integer mcpQuantity;

    @Column(name = "amount_collected")
    private Double amountCollected;

    @Column(name = "branch")
    private String branch;

    @Column(name = "date")
    private String date;

    @Column(name = "channel")
    private String channel;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public MCP() {
    }

    public MCP(Integer mcpSINo, String city, String location, String model, String month, String year, Integer mcpQuantity, Double amountCollected, String branch, String date, String channel, String qtrWise, String halfYear) {
        this.mcpSINo = mcpSINo;
        this.city = city;
        this.location = location;
        this.model = model;
        this.month = month;
        this.year = year;
        this.mcpQuantity = mcpQuantity;
        this.amountCollected = amountCollected;
        this.branch = branch;
        this.date = date;
        this.channel = channel;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getMcpSINo() {
        return mcpSINo;
    }

    public void setMcpSINo(Integer mcpSINo) {
        this.mcpSINo = mcpSINo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Integer getMcpQuantity() {
        return mcpQuantity;
    }

    public void setMcpQuantity(Integer mcpQuantity) {
        this.mcpQuantity = mcpQuantity;
    }

    public Double getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(Double amountCollected) {
        this.amountCollected = amountCollected;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
        return "MCP{" +
                "mcpSINo=" + mcpSINo +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", model='" + model + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", mcpQuantity=" + mcpQuantity +
                ", amountCollected=" + amountCollected +
                ", branch='" + branch + '\'' +
                ", date='" + date + '\'' +
                ", channel='" + channel + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
