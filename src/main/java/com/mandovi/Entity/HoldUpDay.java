package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table (name = "hold_up_day_summary")
public class HoldUpDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "hold_up_day_summarySINo")
    private Integer holdUpDaySINo;

    @Column (name = "city")
    private String city;

    @Column (name = "branch")
    private String branch;

    @Column (name = "service")
    private String service;

    @Column (name = "reg_no")
    private String regNo;

    @Column (name = "till_previous_day")
    private Integer tillPreviousDay;

    @Column (name = "cleared_previous_day")
    private Integer clearedPreviousDay;

    @Column (name = "added_today")
    private Integer addedToday;

    public HoldUpDay() {
    }

    public HoldUpDay(Integer holdUpDaySINo, String city, String branch, String service, String regNo, Integer tillPreviousDay, Integer clearedPreviousDay, Integer addedToday) {
        this.holdUpDaySINo = holdUpDaySINo;
        this.city = city;
        this.branch = branch;
        this.service = service;
        this.regNo = regNo;
        this.tillPreviousDay = tillPreviousDay;
        this.clearedPreviousDay = clearedPreviousDay;
        this.addedToday = addedToday;
    }

    public Integer getHoldUpDaySINo() {
        return holdUpDaySINo;
    }

    public void setHoldUpDaySINo(Integer holdUpDaySINo) {
        this.holdUpDaySINo = holdUpDaySINo;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Integer getTillPreviousDay() {
        return tillPreviousDay;
    }

    public void setTillPreviousDay(Integer tillPreviousDay) {
        this.tillPreviousDay = tillPreviousDay;
    }

    public Integer getClearedPreviousDay() {
        return clearedPreviousDay;
    }

    public void setClearedPreviousDay(Integer clearedPreviousDay) {
        this.clearedPreviousDay = clearedPreviousDay;
    }

    public Integer getAddedToday() {
        return addedToday;
    }

    public void setAddedToday(Integer addedToday) {
        this.addedToday = addedToday;
    }

    @Override
    public String toString() {
        return "HoldUpDay{" +
                "holdUpDaySINo=" + holdUpDaySINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", service='" + service + '\'' +
                ", regNo='" + regNo + '\'' +
                ", tillPreviousDay=" + tillPreviousDay +
                ", clearedPreviousDay=" + clearedPreviousDay +
                ", addedToday=" + addedToday +
                '}';
    }
}
