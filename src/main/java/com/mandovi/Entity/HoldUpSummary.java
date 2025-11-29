package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "hold_up_summary")
public class HoldUpSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "hold_up_summarySINo")
    private Integer holdUpSummarySINo;

    @Column (name = "city")
    private String city;

    @Column (name = "branch")
    private String branch;

    @Column (name = "service")
    private String service;

    @Column (name = "hold_up_summary_date")
    private LocalDate holdUpSummaryDate;

    @Column (name = "count")
    private int count;

    public HoldUpSummary() {
    }

    public HoldUpSummary(Integer holdUpSummarySINo, String city, String branch, String service, LocalDate holdUpSummaryDate, int count) {
        this.holdUpSummarySINo = holdUpSummarySINo;
        this.city = city;
        this.branch = branch;
        this.service = service;
        this.holdUpSummaryDate = holdUpSummaryDate;
        this.count = count;
    }

    public Integer getHoldUpSummarySINo() {
        return holdUpSummarySINo;
    }

    public void setHoldUpSummarySINo(Integer holdUpSummarySINo) {
        this.holdUpSummarySINo = holdUpSummarySINo;
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

    public LocalDate getHoldUpSummaryDate() {
        return holdUpSummaryDate;
    }

    public void setHoldUpSummaryDate(LocalDate holdUpSummaryDate) {
        this.holdUpSummaryDate = holdUpSummaryDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HoldUpSummary{" +
                "holdUpSummarySINo=" + holdUpSummarySINo +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", service='" + service + '\'' +
                ", date=" + holdUpSummaryDate +
                ", count=" + count +
                '}';
    }
}
