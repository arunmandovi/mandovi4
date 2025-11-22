package com.mandovi.DTO;

public class DueDoneSummaryDTO {
    private String city;
    private String branch;
    private Long due;
    private Long done;
    private Double percentageDone;

    public DueDoneSummaryDTO() {
    }

    public DueDoneSummaryDTO(String city, String branch, Long due, Long done, Double percentageDone) {
        this.city = city;
        this.branch = branch;
        this.due = due;
        this.done = done;
        this.percentageDone = percentageDone;
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

    public Long getDue() {
        return due;
    }

    public void setDue(Long due) {
        this.due = due;
    }

    public Long getDone() {
        return done;
    }

    public void setDone(Long done) {
        this.done = done;
    }

    public Double getPercentageDone() {
        return percentageDone;
    }

    public void setPercentageDone(Double percentageDone) {
        this.percentageDone = percentageDone;
    }

    @Override
    public String toString() {
        return "DueDoneSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", due=" + due +
                ", done=" + done +
                ", percentageDone=" + percentageDone +
                '}';
    }
}
