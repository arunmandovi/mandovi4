package com.mandovi.DTO;

import java.time.LocalDate;

public class SAConversionDTO {
    private String branch;
    private String saName;
    private Long pmsAppointment;
    private Long pmsConversion;
    private Double percentagePMS;

    public SAConversionDTO() {
    }

    public SAConversionDTO(String branch, String saName, Long pmsAppointment, Long pmsConversion, Double percentagePMS) {
        this.branch = branch;
        this.saName = saName;
        this.pmsAppointment = pmsAppointment;
        this.pmsConversion = pmsConversion;
        this.percentagePMS = percentagePMS;
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

    public Long getPmsAppointment() {
        return pmsAppointment;
    }

    public void setPmsAppointment(Long pmsAppointment) {
        this.pmsAppointment = pmsAppointment;
    }

    public Long getPmsConversion() {
        return pmsConversion;
    }

    public void setPmsConversion(Long pmsConversion) {
        this.pmsConversion = pmsConversion;
    }

    public Double getPercentagePMS() {
        return percentagePMS;
    }

    public void setPercentagePMS(Double percentagePMS) {
        this.percentagePMS = percentagePMS;
    }

    @Override
    public String toString() {
        return "SAConversionDTO{" +
                "branch='" + branch + '\'' +
                ", saName='" + saName + '\'' +
                ", pmsAppointment=" + pmsAppointment +
                ", pmsConversion=" + pmsConversion +
                ", percentagePMS=" + percentagePMS +
                '}';
    }
}
