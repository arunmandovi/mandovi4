package com.mandovi.DTO;

public class BR_ConversionSummaryDTO {
    private String city;
    private String branch;
    private String qtr_wise;
    private String half_year;
    private String  channel;
    private Long fs_pms_load;
    private Long br_conversion;
    private Double percentageBR_conversion;
    private Double labour_amt;
    private Double part_amt;
    private Double total_amt;

    public BR_ConversionSummaryDTO() {
    }

    public BR_ConversionSummaryDTO(String city, String branch, String qtr_wise, String half_year, String channel, Long fs_pms_load, Long br_conversion, Double percentageBR_conversion, Double labour_amt, Double part_amt, Double total_amt) {
        this.city = city;
        this.branch = branch;
        this.qtr_wise = qtr_wise;
        this.half_year = half_year;
        this.channel = channel;
        this.fs_pms_load = fs_pms_load;
        this.br_conversion = br_conversion;
        this.percentageBR_conversion = percentageBR_conversion;
        this.labour_amt = labour_amt;
        this.part_amt = part_amt;
        this.total_amt = total_amt;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public Double getLabour_amt() {
        return labour_amt;
    }

    public void setLabour_amt(Double labour_amt) {
        this.labour_amt = labour_amt;
    }

    public Double getPart_amt() {
        return part_amt;
    }

    public void setPart_amt(Double part_amt) {
        this.part_amt = part_amt;
    }

    public Double getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(Double total_amt) {
        this.total_amt = total_amt;
    }

    @Override
    public String toString() {
        return "BR_ConversionSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", qtr_wise='" + qtr_wise + '\'' +
                ", half_year='" + half_year + '\'' +
                ", channel='" + channel + '\'' +
                ", fs_pms_load=" + fs_pms_load +
                ", br_conversion=" + br_conversion +
                ", percentageBR_conversion=" + percentageBR_conversion +
                ", labour_amt=" + labour_amt +
                ", part_amt=" + part_amt +
                ", total_amt=" + total_amt +
                '}';
    }
}
