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

    @Column (name = "location_code")
    private String locationCode;

    @Column (name = "branch")
    private String branch;

    @Column (name = "city")
    private String city;

    @Column (name = "inv_date")
    private LocalDate invDate;

    @Column (name = "day")
    private String day;

    @Column (name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "model")
    private String model;

    @Column (name = "channel")
    private String channel;

    @Column (name = "variant_desc")
    private String variantDesc;

    @Column (name = "fuel_type")
    private String fuelType;

    @Column (name = "reg_no")
    private String regNo;

    @Column (name = "pin_code")
    private String pinCode;

    @Column (name = "pin_desc")
    private String pinDesc;

    @Column (name = "vin")
    private String vin;

    public Sales() {
    }

    public Sales(Integer salesSINo, String locationCode, String branch, String city, LocalDate invDate, String day, String month, String year, String model, String channel, String variantDesc, String fuelType, String regNo, String pinCode, String pinDesc, String vin) {
        this.salesSINo = salesSINo;
        this.locationCode = locationCode;
        this.branch = branch;
        this.city = city;
        this.invDate = invDate;
        this.day = day;
        this.month = month;
        this.year = year;
        this.model = model;
        this.channel = channel;
        this.variantDesc = variantDesc;
        this.fuelType = fuelType;
        this.regNo = regNo;
        this.pinCode = pinCode;
        this.pinDesc = pinDesc;
        this.vin = vin;
    }

    public Integer getSalesSINo() {
        return salesSINo;
    }

    public void setSalesSINo(Integer salesSINo) {
        this.salesSINo = salesSINo;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
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

    public LocalDate getInvDate() {
        return invDate;
    }

    public void setInvDate(LocalDate invDate) {
        this.invDate = invDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVariantDesc() {
        return variantDesc;
    }

    public void setVariantDesc(String variantDesc) {
        this.variantDesc = variantDesc;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPinDesc() {
        return pinDesc;
    }

    public void setPinDesc(String pinDesc) {
        this.pinDesc = pinDesc;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "salesSINo=" + salesSINo +
                ", locationCode='" + locationCode + '\'' +
                ", branch='" + branch + '\'' +
                ", city='" + city + '\'' +
                ", invDate=" + invDate +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", model='" + model + '\'' +
                ", channel='" + channel + '\'' +
                ", variantDesc='" + variantDesc + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", regNo='" + regNo + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", pinDesc='" + pinDesc + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
