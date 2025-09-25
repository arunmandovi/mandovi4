package com.mandovi.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "tat")
public class TAT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tatSINo")
    private Integer tatSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "link_service_type")
    private String link_service_type;

    @Column(name = "average_of_open_to_close")
    private Double  average_of_open_to_close;

    @Column(name = "period")
    private String period;

    @Column(name = "qtr_wise")
    private String qtr_wise;

    @Column(name = "half_year")
    private String half_year;

    public TAT() {
    }

    public TAT(Integer tatSINo, String city, String branch, String month, String year, String link_service_type, Double average_of_open_to_close, String period, String qtr_wise, String half_year) {
        this.tatSINo = tatSINo;
        this.city = city;
        this.branch = branch;
        this.month = month;
        this.year = year;
        this.link_service_type = link_service_type;
        this.average_of_open_to_close = average_of_open_to_close;
        this.period = period;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
    }

    public Integer getTatSINo() {
        return tatSINo;
    }

    public void setTatSINo(Integer tatSINo) {
        this.tatSINo = tatSINo;
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

    public String getLink_service_type() {
        return link_service_type;
    }

    public void setLink_service_type(String link_service_type) {
        this.link_service_type = link_service_type;
    }

    public Double getAverage_of_open_to_close() {
        return average_of_open_to_close;
    }

    public void setAverage_of_open_to_close(Double average_of_open_to_close) {
        this.average_of_open_to_close = average_of_open_to_close;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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
        return "Tat{" +
                "tatSINo=" + tatSINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", link_service_type='" + link_service_type + '\'' +
                ", average_of_open_to_close=" + average_of_open_to_close +
                ", period='" + period + '\'' +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                '}';
    }
}
