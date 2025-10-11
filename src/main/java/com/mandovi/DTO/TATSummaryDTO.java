package com.mandovi.DTO;

public class TATSummaryDTO {
    private String city;
    private String branch;
    private Double firstFreeService;
    private Double secondFreeService;
    private Double thirdFreeService;
    private Double paidService;

    public TATSummaryDTO() {
    }

    public TATSummaryDTO(String city, String branch, Double firstFreeService, Double secondFreeService, Double thirdFreeService, Double paidService) {
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

    public Double getFirstFreeService() {
        return firstFreeService;
    }

    public void setFirstFreeService(Double firstFreeService) {
        this.firstFreeService = firstFreeService;
    }

    public Double getSecondFreeService() {
        return secondFreeService;
    }

    public void setSecondFreeService(Double secondFreeService) {
        this.secondFreeService = secondFreeService;
    }

    public Double getThirdFreeService() {
        return thirdFreeService;
    }

    public void setThirdFreeService(Double thirdFreeService) {
        this.thirdFreeService = thirdFreeService;
    }

    public Double getPaidService() {
        return paidService;
    }

    public void setPaidService(Double paidService) {
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
