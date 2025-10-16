package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vas")
public class VAS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vasSINo")
    private Integer vasSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "labour_type")
    private String labourType;

    @Column(name = "wheels")
    private Integer wheels;

    @Column(name = "vas")
    private String vas;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "job_card_no")
    private Integer jobCardNo;

    @Column(name = "basic_amt")
    private Double basicAmt;

    public VAS() {
    }

    public VAS(Integer vasSINo, String city, String branch, String labourType, Integer wheels, String vas, String month, String year, Integer jobCardNo, Double basicAmt) {
        this.vasSINo = vasSINo;
        this.city = city;
        this.branch = branch;
        this.labourType = labourType;
        this.wheels = wheels;
        this.vas = vas;
        this.month = month;
        this.year = year;
        this.jobCardNo = jobCardNo;
        this.basicAmt = basicAmt;
    }

    public Integer getVasSINo() {
        return vasSINo;
    }

    public void setVasSINo(Integer vasSINo) {
        this.vasSINo = vasSINo;
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

    public String getLabourType() {
        return labourType;
    }

    public void setLabourType(String labourType) {
        this.labourType = labourType;
    }

    public Integer getWheels() {
        return wheels;
    }

    public void setWheels(Integer wheels) {
        this.wheels = wheels;
    }

    public String getVas() {
        return vas;
    }

    public void setVas(String vas) {
        this.vas = vas;
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

    public Integer getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(Integer jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public Double getBasicAmt() {
        return basicAmt;
    }

    public void setBasicAmt(Double basicAmt) {
        this.basicAmt = basicAmt;
    }

    @Override
    public String toString() {
        return "VAS{" +
                "vasSINo=" + vasSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", labourType='" + labourType + '\'' +
                ", wheels=" + wheels +
                ", vas='" + vas + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", jobCardNo=" + jobCardNo +
                ", basicAmt=" + basicAmt +
                '}';
    }
}
