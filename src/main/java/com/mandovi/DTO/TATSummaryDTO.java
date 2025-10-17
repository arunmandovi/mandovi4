package com.mandovi.DTO;

import java.time.LocalTime;

public class TATSummaryDTO {
    private String city;
    private String branch;
    private LocalTime firstFreeService;
    private LocalTime secondFreeService;
    private LocalTime thirdFreeService;
    private LocalTime paidService;

    public TATSummaryDTO() {
    }

    public TATSummaryDTO(String city, String branch, LocalTime firstFreeService, LocalTime secondFreeService, LocalTime thirdFreeService, LocalTime paidService) {
        this.city = city;
        this.branch = branch;
        this.firstFreeService = firstFreeService;
        this.secondFreeService = secondFreeService;
        this.thirdFreeService = thirdFreeService;
        this.paidService = paidService;
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

    public LocalTime getFirstFreeService() {
        return firstFreeService;
    }

    public void setFirstFreeService(LocalTime firstFreeService) {
        this.firstFreeService = firstFreeService;
    }

    public LocalTime getSecondFreeService() {
        return secondFreeService;
    }

    public void setSecondFreeService(LocalTime secondFreeService) {
        this.secondFreeService = secondFreeService;
    }

    public LocalTime getThirdFreeService() {
        return thirdFreeService;
    }

    public void setThirdFreeService(LocalTime thirdFreeService) {
        this.thirdFreeService = thirdFreeService;
    }

    public LocalTime getPaidService() {
        return paidService;
    }

    public void setPaidService(LocalTime paidService) {
        this.paidService = paidService;
    }

    @Override
    public String toString() {
        return "TATSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", firstFreeService=" + firstFreeService +
                ", secondFreeService=" + secondFreeService +
                ", thirdFreeService=" + thirdFreeService +
                ", paidService=" + paidService +
                '}';
    }
}
