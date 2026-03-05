package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "sa_conversion")
public class SAConversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "sa_conversionSINo")
    private Integer saConversionSINo;

    @Column (name = "branch")
    private String branch;

    @Column (name = "sa_name")
    private String saName;

    @Column (name = "month")
    private String month;

    @Column (name = "year")
    private String year;

    @Column (name = "pms_appt")
    private Integer pmsAppt;

    @Column (name = "pms_convesrion")
    private Integer pmsConversion;

    @Column (name = "percentage_pms_conversion")
    private Double percentagePMSConversion;

    @Column (name = "frs_appt")
    private Integer frsAppt;

    @Column (name = "frs_conversion")
    private Integer frsConversion;

    @Column (name = "percentage_frs_conversion")
    private Double percentageFRSConversion;

    public SAConversion() {
    }

    public SAConversion(Integer saConversionSINo, String branch, String saName, String month, String year, Integer pmsAppt, Integer pmsConversion, Double percentagePMSConversion, Integer frsAppt, Integer frsConversion, Double percentageFRSConversion) {
        this.saConversionSINo = saConversionSINo;
        this.branch = branch;
        this.saName = saName;
        this.month = month;
        this.year = year;
        this.pmsAppt = pmsAppt;
        this.pmsConversion = pmsConversion;
        this.percentagePMSConversion = percentagePMSConversion;
        this.frsAppt = frsAppt;
        this.frsConversion = frsConversion;
        this.percentageFRSConversion = percentageFRSConversion;
    }

    public Integer getSaConversionSINo() {
        return saConversionSINo;
    }

    public void setSaConversionSINo(Integer saConversionSINo) {
        this.saConversionSINo = saConversionSINo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
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
        return "SAConversion{" +
                "saConversionSINo=" + saConversionSINo +
                ", branch='" + branch + '\'' +
                ", saName='" + saName + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", pmsAppt=" + pmsAppt +
                ", pmsConversion=" + pmsConversion +
                ", percentagePMSConversion=" + percentagePMSConversion +
                ", frsAppt=" + frsAppt +
                ", frsConversion=" + frsConversion +
                ", percentageFRSConversion=" + percentageFRSConversion +
                '}';
    }
}
