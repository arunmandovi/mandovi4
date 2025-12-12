package com.mandovi.DTO;

public class ProductivitySummaryDTO {
    private String city;
    private String branch;
    private Long serviceUtilizedBay;
    private Long serviceLoadd;
    private Double serviceProductivity;
    private Long freeServiceLoadd;
    private Double freeServiceProductivity;
    private Long pmsLoadd;
    private Double pmsProductivity;
    private Long rrLoadd;
    private Double rrProductivity;
    private Long othersLoadd;
    private Double othersProductivity;
    private Long bodyShopUtilizedBay;
    private Long bodyShopLoadd;
    private Double bodyShopProductivity;
    private Integer workingDays;

    public ProductivitySummaryDTO() {
    }

    public ProductivitySummaryDTO(String city, String branch, Long serviceUtilizedBay, Long serviceLoadd, Double serviceProductivity, Long freeServiceLoadd, Double freeServiceProductivity, Long pmsLoadd, Double pmsProductivity, Long rrLoadd, Double rrProductivity, Long othersLoadd, Double othersProductivity, Long bodyShopUtilizedBay, Long bodyShopLoadd, Double bodyShopProductivity, Integer workingDays) {
        this.city = city;
        this.branch = branch;
        this.serviceUtilizedBay = serviceUtilizedBay;
        this.serviceLoadd = serviceLoadd;
        this.serviceProductivity = serviceProductivity;
        this.freeServiceLoadd = freeServiceLoadd;
        this.freeServiceProductivity = freeServiceProductivity;
        this.pmsLoadd = pmsLoadd;
        this.pmsProductivity = pmsProductivity;
        this.rrLoadd = rrLoadd;
        this.rrProductivity = rrProductivity;
        this.othersLoadd = othersLoadd;
        this.othersProductivity = othersProductivity;
        this.bodyShopUtilizedBay = bodyShopUtilizedBay;
        this.bodyShopLoadd = bodyShopLoadd;
        this.bodyShopProductivity = bodyShopProductivity;
        this.workingDays = workingDays;
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

    public Long getServiceUtilizedBay() {
        return serviceUtilizedBay;
    }

    public void setServiceUtilizedBay(Long serviceUtilizedBay) {
        this.serviceUtilizedBay = serviceUtilizedBay;
    }

    public Long getServiceLoadd() {
        return serviceLoadd;
    }

    public void setServiceLoadd(Long serviceLoadd) {
        this.serviceLoadd = serviceLoadd;
    }

    public Double getServiceProductivity() {
        return serviceProductivity;
    }

    public void setServiceProductivity(Double serviceProductivity) {
        this.serviceProductivity = serviceProductivity;
    }

    public Long getFreeServiceLoadd() {
        return freeServiceLoadd;
    }

    public void setFreeServiceLoadd(Long freeServiceLoadd) {
        this.freeServiceLoadd = freeServiceLoadd;
    }

    public Double getFreeServiceProductivity() {
        return freeServiceProductivity;
    }

    public void setFreeServiceProductivity(Double freeServiceProductivity) {
        this.freeServiceProductivity = freeServiceProductivity;
    }

    public Long getPmsLoadd() {
        return pmsLoadd;
    }

    public void setPmsLoadd(Long pmsLoadd) {
        this.pmsLoadd = pmsLoadd;
    }

    public Double getPmsProductivity() {
        return pmsProductivity;
    }

    public void setPmsProductivity(Double pmsProductivity) {
        this.pmsProductivity = pmsProductivity;
    }

    public Long getRrLoadd() {
        return rrLoadd;
    }

    public void setRrLoadd(Long rrLoadd) {
        this.rrLoadd = rrLoadd;
    }

    public Double getRrProductivity() {
        return rrProductivity;
    }

    public void setRrProductivity(Double rrProductivity) {
        this.rrProductivity = rrProductivity;
    }

    public Long getOthersLoadd() {
        return othersLoadd;
    }

    public void setOthersLoadd(Long othersLoadd) {
        this.othersLoadd = othersLoadd;
    }

    public Double getOthersProductivity() {
        return othersProductivity;
    }

    public void setOthersProductivity(Double othersProductivity) {
        this.othersProductivity = othersProductivity;
    }

    public Long getBodyShopUtilizedBay() {
        return bodyShopUtilizedBay;
    }

    public void setBodyShopUtilizedBay(Long bodyShopUtilizedBay) {
        this.bodyShopUtilizedBay = bodyShopUtilizedBay;
    }

    public Long getBodyShopLoadd() {
        return bodyShopLoadd;
    }

    public void setBodyShopLoadd(Long bodyShopLoadd) {
        this.bodyShopLoadd = bodyShopLoadd;
    }

    public Double getBodyShopProductivity() {
        return bodyShopProductivity;
    }

    public void setBodyShopProductivity(Double bodyShopProductivity) {
        this.bodyShopProductivity = bodyShopProductivity;
    }

    public Integer getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
    }

    @Override
    public String toString() {
        return "ProductivitySummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", serviceUtilizedBay=" + serviceUtilizedBay +
                ", serviceLoadd=" + serviceLoadd +
                ", serviceProductivity=" + serviceProductivity +
                ", freeServiceLoadd=" + freeServiceLoadd +
                ", freeServiceProductivity=" + freeServiceProductivity +
                ", pmsLoadd=" + pmsLoadd +
                ", pmsProductivity=" + pmsProductivity +
                ", rrLoadd=" + rrLoadd +
                ", rrProductivity=" + rrProductivity +
                ", othersLoadd=" + othersLoadd +
                ", othersProductivity=" + othersProductivity +
                ", bodyShopUtilizedBay=" + bodyShopUtilizedBay +
                ", bodyShopLoadd=" + bodyShopLoadd +
                ", bodyShopProductivity=" + bodyShopProductivity +
                ", workingDays=" + workingDays +
                '}';
    }
}
