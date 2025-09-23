package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profit_loss")
public class ProfitLoss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profit_lossSINo")
    private Integer profit_lossSIno;
    @Column(name = "city")
    private String city;
    @Column(name = "branch")
    private String branch;

    public ProfitLoss() {
    }

    public ProfitLoss(Integer profit_lossSIno, String city, String branch) {
        this.profit_lossSIno = profit_lossSIno;
        this.city = city;
        this.branch = branch;
    }

    public Integer getProfit_lossSIno() {
        return profit_lossSIno;
    }

    public void setProfit_lossSIno(Integer profit_lossSIno) {
        this.profit_lossSIno = profit_lossSIno;
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

    @Override
    public String toString() {
        return "Profit_Loss{" +
                "profit_lossSIno=" + profit_lossSIno +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
