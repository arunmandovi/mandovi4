package com.mandovi.DTO;

public class ServiceeSummaryDTO {
    private String city;
    private String branch;
    private Long serviceLoadd;

    public ServiceeSummaryDTO() {
    }

    public ServiceeSummaryDTO(String city, String branch, Long serviceLoadd) {
        this.city = city;
        this.branch = branch;
        this.serviceLoadd = serviceLoadd;
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

    public Long getServiceLoadd() {
        return serviceLoadd;
    }

    public void setServiceLoadd(Long serviceLoadd) {
        this.serviceLoadd = serviceLoadd;
    }

    @Override
    public String toString() {
        return "ServiceeSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", serviceLoadd=" + serviceLoadd +
                '}';
    }
}
