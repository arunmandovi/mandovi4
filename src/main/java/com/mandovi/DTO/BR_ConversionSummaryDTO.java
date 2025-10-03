package com.mandovi.DTO;

public class BR_ConversionSummaryDTO {
    private String city;
    private String branch;
    private Long fs_pms_load;
    private Long br_conversion;
    private Double percentageBR_conversion;

    public BR_ConversionSummaryDTO() {
    }

    public BR_ConversionSummaryDTO(String city, String branch, Long fs_pms_load, Long br_conversion, Double percentageBR_conversion) {
        this.city = city;
        this.branch = branch;
        this.fs_pms_load = fs_pms_load;
        this.br_conversion = br_conversion;
        this.percentageBR_conversion = percentageBR_conversion;
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

    public Long getFs_pms_load() {
        return fs_pms_load;
    }

    public void setFs_pms_load(Long fs_pms_load) {
        this.fs_pms_load = fs_pms_load;
    }

    public Long getBr_conversion() {
        return br_conversion;
    }

    public void setBr_conversion(Long br_conversion) {
        this.br_conversion = br_conversion;
    }

    public Double getPercentageBR_conversion() {
        return percentageBR_conversion;
    }

    public void setPercentageBR_conversion(Double percentageBR_conversion) {
        this.percentageBR_conversion = percentageBR_conversion;
    }

    @Override
    public String toString() {
        return "BR_ConversionSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", fs_pms_load=" + fs_pms_load +
                ", br_conversion=" + br_conversion +
                ", percentageBR_conversion=" + percentageBR_conversion +
                '}';
    }
}
