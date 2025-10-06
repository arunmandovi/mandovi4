package com.mandovi.DTO;

public class MGASummaryDTO {
    private String city;
    private String branch;
    private Long mgaLoadd;
    private Double mgaValue;
    private Double mgaVeh;
    private Double mgaShortfallValue;
    private Double mgaShortfallVeh;

    public MGASummaryDTO() {
    }

    public MGASummaryDTO(String city, String branch, Long mgaLoadd, Double mgaValue, Double mgaVeh, Double mgaShortfallValue, Double mgaShortfallVeh) {
        this.city = city;
        this.branch = branch;
        this.mgaLoadd = mgaLoadd;
        this.mgaValue = mgaValue;
        this.mgaVeh = mgaVeh;
        this.mgaShortfallValue = mgaShortfallValue;
        this.mgaShortfallVeh = mgaShortfallVeh;
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

    public Long getMgaLoadd() {
        return mgaLoadd;
    }

    public void setMgaLoadd(Long mgaLoadd) {
        this.mgaLoadd = mgaLoadd;
    }

    public Double getMgaValue() {
        return mgaValue;
    }

    public void setMgaValue(Double mgaValue) {
        this.mgaValue = mgaValue;
    }

    public Double getMgaVeh() {
        return mgaVeh;
    }

    public void setMgaVeh(Double mgaVeh) {
        this.mgaVeh = mgaVeh;
    }

    public Double getMgaShortfallValue() {
        return mgaShortfallValue;
    }

    public void setMgaShortfallValue(Double mgaShortfallValue) {
        this.mgaShortfallValue = mgaShortfallValue;
    }

    public Double getMgaShortfallVeh() {
        return mgaShortfallVeh;
    }

    public void setMgaShortfallVeh(Double mgaShortfallVeh) {
        this.mgaShortfallVeh = mgaShortfallVeh;
    }

    @Override
    public String toString() {
        return "MGASummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", mgaLoadd=" + mgaLoadd +
                ", mgaValue=" + mgaValue +
                ", mgaVeh=" + mgaVeh +
                ", mgaShortfallValue=" + mgaShortfallValue +
                ", mgaShortfallVeh=" + mgaShortfallVeh +
                '}';
    }
}
