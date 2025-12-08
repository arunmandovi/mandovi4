package com.mandovi.DTO;

public class ProductivitySummaryDTO {
    private String city;
    private String branch;
    private Long serviceUtilizedBay;
    private Long serviceLoadd;
    private Double serviceProductivity;
    private Long bodyShopUtilizedBay;
    private Long bodyShopLoadd;
    private Double bodyShopProductivity;

    public ProductivitySummaryDTO() {
    }

    public ProductivitySummaryDTO(String city, String branch, Long serviceUtilizedBay, Long serviceLoadd, Double serviceProductivity, Long bodyShopUtilizedBay, Long bodyShopLoadd, Double bodyShopProductivity) {
        this.city = city;
        this.branch = branch;
        this.serviceUtilizedBay = serviceUtilizedBay;
        this.serviceLoadd = serviceLoadd;
        this.serviceProductivity = serviceProductivity;
        this.bodyShopUtilizedBay = bodyShopUtilizedBay;
        this.bodyShopLoadd = bodyShopLoadd;
        this.bodyShopProductivity = bodyShopProductivity;
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

    @Override
    public String toString() {
        return "ProductivitySummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", serviceUtilizedBay=" + serviceUtilizedBay +
                ", serviceLoadd=" + serviceLoadd +
                ", serviceProductivity=" + serviceProductivity +
                ", bodyShopUtilizedBay=" + bodyShopUtilizedBay +
                ", bodyShopLoadd=" + bodyShopLoadd +
                ", bodyShopProductivity=" + bodyShopProductivity +
                '}';
    }
}
