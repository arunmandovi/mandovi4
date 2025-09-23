package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "msgp_profit")
public class MSGPProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msgp_profitSINo")
    private Integer msgp_profitSINo;

    @Column(name = "service_description")
    private String service_description;

    @Column(name = "location_code")
    private String location_code;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "net_retail_ddl")
    private Double net_retail_ddl;

    @Column(name = "net_retail_selling")
    private Double net_retail_selling;

    @Column(name = "sum_of_net_retail_ddl")
    private Double sum_of_net_retail_ddl;

    @Column(name = "sum_of_net_retail_selling")
    private Double sum_of_net_retail_selling;

    @Column(name = "date")
    private String date;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "qtr_wise")
    private String qtr_wise;

    @Column(name = "half_year")
    private String half_year;

    public MSGPProfit() {
    }

    public MSGPProfit(Integer msgp_profitSINo, String service_description, String location_code, String month, String year, Double net_retail_ddl, Double net_retail_selling, Double sum_of_net_retail_ddl, Double sum_of_net_retail_selling, String date, String city, String branch, String qtr_wise, String half_year) {
        this.msgp_profitSINo = msgp_profitSINo;
        this.service_description = service_description;
        this.location_code = location_code;
        this.month = month;
        this.year = year;
        this.net_retail_ddl = net_retail_ddl;
        this.net_retail_selling = net_retail_selling;
        this.sum_of_net_retail_ddl = sum_of_net_retail_ddl;
        this.sum_of_net_retail_selling = sum_of_net_retail_selling;
        this.date = date;
        this.city = city;
        this.branch = branch;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
    }

    public Integer getMsgp_profitSINo() {
        return msgp_profitSINo;
    }

    public void setMsgp_profitSINo(Integer msgp_profitSINo) {
        this.msgp_profitSINo = msgp_profitSINo;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description;
    }

    public String getLocation_code() {
        return location_code;
    }

    public void setLocation_code(String location_code) {
        this.location_code = location_code;
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

    public Double getNet_retail_ddl() {
        return net_retail_ddl;
    }

    public void setNet_retail_ddl(Double net_retail_ddl) {
        this.net_retail_ddl = net_retail_ddl;
    }

    public Double getNet_retail_selling() {
        return net_retail_selling;
    }

    public void setNet_retail_selling(Double net_retail_selling) {
        this.net_retail_selling = net_retail_selling;
    }

    public Double getSum_of_net_retail_ddl() {
        return sum_of_net_retail_ddl;
    }

    public void setSum_of_net_retail_ddl(Double sum_of_net_retail_ddl) {
        this.sum_of_net_retail_ddl = sum_of_net_retail_ddl;
    }

    public Double getSum_of_net_retail_selling() {
        return sum_of_net_retail_selling;
    }

    public void setSum_of_net_retail_selling(Double sum_of_net_retail_selling) {
        this.sum_of_net_retail_selling = sum_of_net_retail_selling;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "MSGPProfit{" +
                "msgp_profitSINo=" + msgp_profitSINo +
                ", service_description='" + service_description + '\'' +
                ", location_code='" + location_code + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", net_retail_ddl=" + net_retail_ddl +
                ", net_retail_selling=" + net_retail_selling +
                ", sum_of_net_retail_ddl=" + sum_of_net_retail_ddl +
                ", sum_of_net_retail_selling=" + sum_of_net_retail_selling +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                '}';
    }
}
