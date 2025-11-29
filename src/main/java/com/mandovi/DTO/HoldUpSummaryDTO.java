package com.mandovi.DTO;

public class HoldUpSummaryDTO {
    private String city;
    private String branch;
    private long countService;
    private long countBodySHop;
    private long countPMS;

    public HoldUpSummaryDTO() {
    }

    public HoldUpSummaryDTO(String city, String branch, long countService, long countBodySHop, long countPMS) {
        this.city = city;
        this.branch = branch;
        this.countService = countService;
        this.countBodySHop = countBodySHop;
        this.countPMS = countPMS;
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

    public long getCountBodySHop() {
        return countBodySHop;
    }

    public void setCountBodySHop(long countBodySHop) {
        this.countBodySHop = countBodySHop;
    }

    public long getCountPMS() {
        return countPMS;
    }

    public void setCountPMS(long countPMS) {
        this.countPMS = countPMS;
    }

    @Override
    public String toString() {
        return "HoldUpSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", countService=" + countService +
                ", countBodySHop=" + countBodySHop +
                ", countPMS=" + countPMS +
                '}';
    }
}
