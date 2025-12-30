package com.mandovi.DTO;

import java.time.LocalDate;

public class CCConversionDTO {
    private String branch;
    private String cceName;
    private LocalDate experience;
    private Double experienceDays;
    private Long pmsAppointment;
    private Long pmsConversion;
    private Double percentagePMS;

    public CCConversionDTO() {
    }

    public CCConversionDTO(String branch, String cceName, LocalDate experience, Double experienceDays, Long pmsAppointment, Long pmsConversion, Double percentagePMS) {
        this.branch = branch;
        this.cceName = cceName;
        this.experience = experience;
        this.experienceDays = experienceDays;
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

    public String getCceName() {
        return cceName;
    }

    public void setCceName(String cceName) {
        this.cceName = cceName;
    }

    public LocalDate getExperience() {
        return experience;
    }

    public void setExperience(LocalDate experience) {
        this.experience = experience;
    }

    public Double getExperienceDays() {
        return experienceDays;
    }

    public void setExperienceDays(Double experienceDays) {
        this.experienceDays = experienceDays;
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
        return "CCConversionDTO{" +
                "branch='" + branch + '\'' +
                ", cceName='" + cceName + '\'' +
                ", experience=" + experience +
                ", experienceDays=" + experienceDays +
                ", pmsAppointment=" + pmsAppointment +
                ", pmsConversion=" + pmsConversion +
                ", percentagePMS=" + percentagePMS +
                '}';
    }
}
