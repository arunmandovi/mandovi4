package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "cc_conversion")
public class CCConversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cc_conversionSINo")
    private Integer ccConversionSINo;

    @Column (name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "financial_year")
    private String financialYear;

    @Column (name = "date_of_join")
    private LocalDate dateOfJoin;

    @Column (name = "branch")
    private String branch;

    @Column (name = "cce_name")
    private String cceName;

    @Column (name = "pms_appt")
    private Integer pmsAppt;

    @Column (name = "pms_conversion")
    private Integer pmsConversion;

    @Column (name = "percentage_pms_conversion")
    private Double percentagePMSConversion;

    @Column (name = "frs_appt")
    private Integer frsAppt;

    @Column (name = "frs_conversion")
    private Integer frsConversion;

    @Column (name = "percentage_frs_conversion")
    private Double percentageFRSConversion;

    public CCConversion() {
    }

    public CCConversion(Integer ccConversionSINo, String month, String year, String financialYear, LocalDate dateOfJoin, String branch, String cceName, Integer pmsAppt, Integer pmsConversion, Double percentagePMSConversion, Integer frsAppt, Integer frsConversion, Double percentageFRSConversion) {
        this.ccConversionSINo = ccConversionSINo;
        this.month = month;
        this.year = year;
        this.financialYear = financialYear;
        this.dateOfJoin = dateOfJoin;
        this.branch = branch;
        this.cceName = cceName;
        this.pmsAppt = pmsAppt;
        this.pmsConversion = pmsConversion;
        this.percentagePMSConversion = percentagePMSConversion;
        this.frsAppt = frsAppt;
        this.frsConversion = frsConversion;
        this.percentageFRSConversion = percentageFRSConversion;
    }

    public Integer getCcConversionSINo() {
        return ccConversionSINo;
    }

    public void setCcConversionSINo(Integer ccConversionSINo) {
        this.ccConversionSINo = ccConversionSINo;
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

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public LocalDate getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCceName() {
        return cceName;
    }

    public void setCceName(String cceName) {
        this.cceName = cceName;
    }

    public Integer getPmsAppt() {
        return pmsAppt;
    }

    public void setPmsAppt(Integer pmsAppt) {
        this.pmsAppt = pmsAppt;
    }

    public Integer getPmsConversion() {
        return pmsConversion;
    }

    public void setPmsConversion(Integer pmsConversion) {
        this.pmsConversion = pmsConversion;
    }

    public Double getPercentagePMSConversion() {
        return percentagePMSConversion;
    }

    public void setPercentagePMSConversion(Double percentagePMSConversion) {
        this.percentagePMSConversion = percentagePMSConversion;
    }

    public Integer getFrsAppt() {
        return frsAppt;
    }

    public void setFrsAppt(Integer frsAppt) {
        this.frsAppt = frsAppt;
    }

    public Integer getFrsConversion() {
        return frsConversion;
    }

    public void setFrsConversion(Integer frsConversion) {
        this.frsConversion = frsConversion;
    }

    public Double getPercentageFRSConversion() {
        return percentageFRSConversion;
    }

    public void setPercentageFRSConversion(Double percentageFRSConversion) {
        this.percentageFRSConversion = percentageFRSConversion;
    }

    @Override
    public String toString() {
        return "CCConversion{" +
                "ccConversionSINo=" + ccConversionSINo +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", financialYear='" + financialYear + '\'' +
                ", dateOfJoin=" + dateOfJoin +
                ", branch='" + branch + '\'' +
                ", cceName='" + cceName + '\'' +
                ", pmsAppt=" + pmsAppt +
                ", pmsConversion=" + pmsConversion +
                ", percentagePMSConversion=" + percentagePMSConversion +
                ", frsAppt=" + frsAppt +
                ", frsConversion=" + frsConversion +
                ", percentageFRSConversion=" + percentageFRSConversion +
                '}';
    }
}
