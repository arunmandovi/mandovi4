package com.mandovi.DTO;

import java.time.LocalTime;

public class TATSummaryDTO {
    private String city;
    private String branch;
    private String firstFreeService;
    private String secondFreeService;
    private String thirdFreeService;
    private String paidService;

    public TATSummaryDTO() {
    }

    public TATSummaryDTO(String city, String branch, String firstFreeService, String secondFreeService, String thirdFreeService, String paidService) {
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

    public String getFirstFreeService() {
        return firstFreeService;
    }

    public void setFirstFreeService(String firstFreeService) {
        this.firstFreeService = firstFreeService;
    }

    public String getSecondFreeService() {
        return secondFreeService;
    }

    public void setSecondFreeService(String secondFreeService) {
        this.secondFreeService = secondFreeService;
    }

    public String getThirdFreeService() {
        return thirdFreeService;
    }

    public void setThirdFreeService(String thirdFreeService) {
        this.thirdFreeService = thirdFreeService;
    }

    public String getPaidService() {
        return paidService;
    }

    public void setPaidService(String paidService) {
        this.paidService = paidService;
    }

    @Override
    public String toString() {
        return "TATSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", firstFreeService='" + firstFreeService + '\'' +
                ", secondFreeService='" + secondFreeService + '\'' +
                ", thirdFreeService='" + thirdFreeService + '\'' +
                ", paidService='" + paidService + '\'' +
                '}';
    }
}
