package com.mandovi.DTO;

public class SalesSummaryDTO {
    private String city;
    private String branch;
    private Long count;

    public SalesSummaryDTO() {
    }

    public SalesSummaryDTO(String city, String branch, Long count) {
        this.city = city;
        this.branch = branch;
        this.count = count;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SalesSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", count=" + count +
                '}';
    }
}
