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

    @Column(name = "year")
    private String year;

    @Column (name = "delete_year")
    private String deleteYear;

    @Column(name = "month")
    private String month;

    @Column(name = "service_description")
    private String serviceDescription;

    @Column(name = "net_retail_ddl")
    private Double netRetailDDL;

    @Column(name = "sum_of_net_retail_ddl")
    private Double sumOfNetRetailDDL;

    @Column(name = "branch")
    private String branch;

    @Column(name = "load_type")
    private String loadType;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    @Column(name = "financial_year")
    private String financialYear;

    public MSGP() {
    }

    public MSGP(Integer msgpSINo, String city, String year, String deleteYear, String month, String serviceDescription, Double netRetailDDL, Double sumOfNetRetailDDL, String branch, String loadType, String qtrWise, String halfYear, String financialYear) {
        this.msgpSINo = msgpSINo;
        this.city = city;
        this.year = year;
        this.deleteYear = deleteYear;
        this.month = month;
        this.serviceDescription = serviceDescription;
        this.netRetailDDL = netRetailDDL;
        this.sumOfNetRetailDDL = sumOfNetRetailDDL;
        this.branch = branch;
        this.loadType = loadType;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
        this.financialYear = financialYear;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDeleteYear() {
        return deleteYear;
    }

    public void setDeleteYear(String deleteYear) {
        this.deleteYear = deleteYear;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Double getNetRetailDDL() {
        return netRetailDDL;
    }

    public void setNetRetailDDL(Double netRetailDDL) {
        this.netRetailDDL = netRetailDDL;
    }

    public Double getSumOfNetRetailDDL() {
        return sumOfNetRetailDDL;
    }

    public void setSumOfNetRetailDDL(Double sumOfNetRetailDDL) {
        this.sumOfNetRetailDDL = sumOfNetRetailDDL;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
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

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    @Override
    public String toString() {
        return "MSGP{" +
                "msgpSINo=" + msgpSINo +
                ", city='" + city + '\'' +
                ", year='" + year + '\'' +
                ", deleteYear='" + deleteYear + '\'' +
                ", month='" + month + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", netRetailDDL=" + netRetailDDL +
                ", sumOfNetRetailDDL=" + sumOfNetRetailDDL +
                ", branch='" + branch + '\'' +
                ", loadType='" + loadType + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                ", financialYear='" + financialYear + '\'' +
                '}';
    }
}
