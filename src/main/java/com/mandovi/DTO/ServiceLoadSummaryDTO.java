package com.mandovi.DTO;

public class ServiceLoadSummaryDTO {
    private String city;
    private String branch;
    private Long serviceLoad;

    public ServiceLoadSummaryDTO() {
    }

    public ServiceLoadSummaryDTO(String city, String branch, Long serviceLoad) {
        this.city = city;
        this.branch = branch;
        this.serviceLoad = serviceLoad;
    }

    public Long getServiceLoad() {
        return serviceLoad;
    }

    public void setServiceLoad(Long serviceLoad) {
        this.serviceLoad = serviceLoad;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ServiceLoadSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", serviceLoad=" + serviceLoad +
                '}';
    }
}
