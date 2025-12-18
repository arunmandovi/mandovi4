package com.mandovi.DTO;

public class HoldUpDayDTO {
    private String city;
    private String branch;
    private Long serviceTillYesterday;
    private Long serviceClearedYesterday;
    private Long serviceBalance;
    private Long serviceAddedYesterday;
    private Long serviceTodayOpening;
    private Long bodyShopTillYesterday;
    private Long bodyShopClearedYesterday;
    private Long bodyShopBalance;
    private Long bodyShopAddedYesterday;
    private Long bodyShopTodayOpening;

    public HoldUpDayDTO() {
    }

    public HoldUpDayDTO(String city, String branch, Long serviceTillYesterday, Long serviceClearedYesterday, Long serviceBalance, Long serviceAddedYesterday, Long serviceTodayOpening, Long bodyShopTillYesterday, Long bodyShopClearedYesterday, Long bodyShopBalance, Long bodyShopAddedYesterday, Long bodyShopTodayOpening) {
        this.city = city;
        this.branch = branch;
        this.serviceTillYesterday = serviceTillYesterday;
        this.serviceClearedYesterday = serviceClearedYesterday;
        this.serviceBalance = serviceBalance;
        this.serviceAddedYesterday = serviceAddedYesterday;
        this.serviceTodayOpening = serviceTodayOpening;
        this.bodyShopTillYesterday = bodyShopTillYesterday;
        this.bodyShopClearedYesterday = bodyShopClearedYesterday;
        this.bodyShopBalance = bodyShopBalance;
        this.bodyShopAddedYesterday = bodyShopAddedYesterday;
        this.bodyShopTodayOpening = bodyShopTodayOpening;
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

    public Long getServiceTillYesterday() {
        return serviceTillYesterday;
    }

    public void setServiceTillYesterday(Long serviceTillYesterday) {
        this.serviceTillYesterday = serviceTillYesterday;
    }

    public Long getServiceClearedYesterday() {
        return serviceClearedYesterday;
    }

    public void setServiceClearedYesterday(Long serviceClearedYesterday) {
        this.serviceClearedYesterday = serviceClearedYesterday;
    }

    public Long getServiceBalance() {
        return serviceBalance;
    }

    public void setServiceBalance(Long serviceBalance) {
        this.serviceBalance = serviceBalance;
    }

    public Long getServiceAddedYesterday() {
        return serviceAddedYesterday;
    }

    public void setServiceAddedYesterday(Long serviceAddedYesterday) {
        this.serviceAddedYesterday = serviceAddedYesterday;
    }

    public Long getServiceTodayOpening() {
        return serviceTodayOpening;
    }

    public void setServiceTodayOpening(Long serviceTodayOpening) {
        this.serviceTodayOpening = serviceTodayOpening;
    }

    public Long getBodyShopTillYesterday() {
        return bodyShopTillYesterday;
    }

    public void setBodyShopTillYesterday(Long bodyShopTillYesterday) {
        this.bodyShopTillYesterday = bodyShopTillYesterday;
    }

    public Long getBodyShopClearedYesterday() {
        return bodyShopClearedYesterday;
    }

    public void setBodyShopClearedYesterday(Long bodyShopClearedYesterday) {
        this.bodyShopClearedYesterday = bodyShopClearedYesterday;
    }

    public Long getBodyShopBalance() {
        return bodyShopBalance;
    }

    public void setBodyShopBalance(Long bodyShopBalance) {
        this.bodyShopBalance = bodyShopBalance;
    }

    public Long getBodyShopAddedYesterday() {
        return bodyShopAddedYesterday;
    }

    public void setBodyShopAddedYesterday(Long bodyShopAddedYesterday) {
        this.bodyShopAddedYesterday = bodyShopAddedYesterday;
    }

    public Long getBodyShopTodayOpening() {
        return bodyShopTodayOpening;
    }

    public void setBodyShopTodayOpening(Long bodyShopTodayOpening) {
        this.bodyShopTodayOpening = bodyShopTodayOpening;
    }

    @Override
    public String toString() {
        return "HoldUpDayDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", serviceTillYesterday=" + serviceTillYesterday +
                ", serviceClearedYesterday=" + serviceClearedYesterday +
                ", serviceBalance=" + serviceBalance +
                ", serviceAddedYesterday=" + serviceAddedYesterday +
                ", serviceTodayOpening=" + serviceTodayOpening +
                ", bodyShopTillYesterday=" + bodyShopTillYesterday +
                ", bodyShopClearedYesterday=" + bodyShopClearedYesterday +
                ", bodyShopBalance=" + bodyShopBalance +
                ", bodyShopAddedYesterday=" + bodyShopAddedYesterday +
                ", bodyShopTodayOpening=" + bodyShopTodayOpening +
                '}';
    }
}
