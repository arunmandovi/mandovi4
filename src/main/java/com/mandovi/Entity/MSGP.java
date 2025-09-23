package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "msgp")
public class MSGP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msgpSINo")
    private Integer msgpSINo;

    @Column(name = "city")
    private String city;

    @Column(name = "location_code")
    private String location_code;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "service_description")
    private String service_description;

    @Column(name = "net_retail_ddl")
    private Double net_retail_ddl;

    @Column(name = "sum_of_net_retail_ddl")
    private Double sum_of_net_retail_ddl;

    @Column(name = "branch")
    private String branch;

    @Column(name = "period")
    private String period;

    @Column(name = "qtr_wise")
    private String qtr_wise;

    @Column(name = "half_year")
    private String half_year;

    @Column(name = "financial_year")
    private String financial_year;

    public MSGP() {
    }

    public MSGP(Integer msgpSINo, String city, String location_code, String year, String month, String service_description, Double net_retail_ddl, Double sum_of_net_retail_ddl, String branch, String period, String qtr_wise, String half_year, String financial_year) {
        this.msgpSINo = msgpSINo;
        this.city = city;
        this.location_code = location_code;
        this.year = year;
        this.month = month;
        this.service_description = service_description;
        this.net_retail_ddl = net_retail_ddl;
        this.sum_of_net_retail_ddl = sum_of_net_retail_ddl;
        this.branch = branch;
        this.period = period;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
        this.financial_year = financial_year;
    }

    public Integer getMsgpSINo() {
        return msgpSINo;
    }

    public void setMsgpSINo(Integer msgpSINo) {
        this.msgpSINo = msgpSINo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation_code() {
        return location_code;
    }

    public void setLocation_code(String location_code) {
        this.location_code = location_code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description;
    }

    public Double getNet_retail_ddl() {
        return net_retail_ddl;
    }

    public void setNet_retail_ddl(Double net_retail_ddl) {
        this.net_retail_ddl = net_retail_ddl;
    }

    public Double getSum_of_net_retail_ddl() {
        return sum_of_net_retail_ddl;
    }

    public void setSum_of_net_retail_ddl(Double sum_of_net_retail_ddl) {
        this.sum_of_net_retail_ddl = sum_of_net_retail_ddl;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public String getFinancial_year() {
        return financial_year;
    }

    public void setFinancial_year(String financial_year) {
        this.financial_year = financial_year;
    }

    @Override
    public String toString() {
        return "MSGP{" +
                "msgpSINo=" + msgpSINo +
                ", city='" + city + '\'' +
                ", location_code='" + location_code + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", service_description='" + service_description + '\'' +
                ", net_retail_ddl=" + net_retail_ddl +
                ", sum_of_net_retail_ddl=" + sum_of_net_retail_ddl +
                ", branch='" + branch + '\'' +
                ", period='" + period + '\'' +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                ", financial_year='" + financial_year + '\'' +
                '}';
    }
}
