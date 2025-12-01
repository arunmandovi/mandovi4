package com.mandovi.DTO;

public class HoldUpSummaryDTO {
    private String city;
    private String branch;
    private long countService;
    private long countBodyShop;
    private long countPMS;
    private long countServiceBodyShop;

    public HoldUpSummaryDTO() {
    }

    public HoldUpSummaryDTO(String city, String branch, long countService, long countBodyShop, long countPMS, long countServiceBodyShop) {
        this.city = city;
        this.branch = branch;
        this.countService = countService;
        this.countBodyShop = countBodyShop;
        this.countPMS = countPMS;
        this.countServiceBodyShop = countServiceBodyShop;
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

    public long getCountService() {
        return countService;
    }

    public void setCountService(long countService) {
        this.countService = countService;
    }

    public long getCountBodyShop() {
        return countBodyShop;
    }

    public void setCountBodyShop(long countBodyShop) {
        this.countBodyShop = countBodyShop;
    }

    public long getCountPMS() {
        return countPMS;
    }

    public void setCountPMS(long countPMS) {
        this.countPMS = countPMS;
    }

    public long getCountServiceBodyShop() {
        return countServiceBodyShop;
    }

    public void setCountServiceBodyShop(long countServiceBodyShop) {
        this.countServiceBodyShop = countServiceBodyShop;
    }

    @Override
    public String toString() {
        return "HoldUpSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", countService=" + countService +
                ", countBodyShop=" + countBodyShop +
                ", countPMS=" + countPMS +
                ", countServiceBodyShop=" + countServiceBodyShop +
                '}';
    }
}
