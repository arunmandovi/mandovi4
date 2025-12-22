package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "outstanding")
public class Outstanding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "outstandingSINo")
    private Integer outstandingSINo;

    @Column (name = "segment")
    private String segment;

    @Column (name = "ledger_group_name")
    private String ledgerGroupName;

    @Column (name = "party_name")
    private String partyName;

    @Column (name = "outstanding_date")
    private LocalDate outstandingDate;

    @Column (name = "bill_no")
    private String billNo;

    @Column (name = "bill_amt")
    private Double billAmt;

    @Column (name = "paid_amt")
    private Double paidAmt;

    @Column (name = "balance_amt")
    private Double balanceAmt;

    @Column (name = "due_since")
    private Integer dueSince;

    @Column (name = "upto_seven")
    private Double upToSeven;

    @Column (name = "eight_to_thirty")
    private Double eightToThirty;

    @Column (name = "thirtyone_to_ninty")
    private Double thirtyOneToNinty;

    @Column (name = "more_than_ninty")
    private Double moreThanNinty;

    @Column (name = "sales_man")
    private String salesMan;

    public Outstanding() {
    }

    public Outstanding(Integer outstandingSINo, String segment, String ledgerGroupName, String partyName, LocalDate outstandingDate, String billNo, Double billAmt, Double paidAmt, Double balanceAmt, Integer dueSince, Double upToSeven, Double eightToThirty, Double thirtyOneToNinty, Double moreThanNinty, String salesMan) {
        this.outstandingSINo = outstandingSINo;
        this.segment = segment;
        this.ledgerGroupName = ledgerGroupName;
        this.partyName = partyName;
        this.outstandingDate = outstandingDate;
        this.billNo = billNo;
        this.billAmt = billAmt;
        this.paidAmt = paidAmt;
        this.balanceAmt = balanceAmt;
        this.dueSince = dueSince;
        this.upToSeven = upToSeven;
        this.eightToThirty = eightToThirty;
        this.thirtyOneToNinty = thirtyOneToNinty;
        this.moreThanNinty = moreThanNinty;
        this.salesMan = salesMan;
    }

    public Integer getOutstandingSINo() {
        return outstandingSINo;
    }

    public void setOutstandingSINo(Integer outstandingSINo) {
        this.outstandingSINo = outstandingSINo;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getLedgerGroupName() {
        return ledgerGroupName;
    }

    public void setLedgerGroupName(String ledgerGroupName) {
        this.ledgerGroupName = ledgerGroupName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public LocalDate getOutstandingDate() {
        return outstandingDate;
    }

    public void setOutstandingDate(LocalDate outstandingDate) {
        this.outstandingDate = outstandingDate;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Double getBillAmt() {
        return billAmt;
    }

    public void setBillAmt(Double billAmt) {
        this.billAmt = billAmt;
    }

    public Double getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(Double paidAmt) {
        this.paidAmt = paidAmt;
    }

    public Double getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(Double balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    public Integer getDueSince() {
        return dueSince;
    }

    public void setDueSince(Integer dueSince) {
        this.dueSince = dueSince;
    }

    public Double getUpToSeven() {
        return upToSeven;
    }

    public void setUpToSeven(Double upToSeven) {
        this.upToSeven = upToSeven;
    }

    public Double getEightToThirty() {
        return eightToThirty;
    }

    public void setEightToThirty(Double eightToThirty) {
        this.eightToThirty = eightToThirty;
    }

    public Double getThirtyOneToNinty() {
        return thirtyOneToNinty;
    }

    public void setThirtyOneToNinty(Double thirtyOneToNinty) {
        this.thirtyOneToNinty = thirtyOneToNinty;
    }

    public Double getMoreThanNinty() {
        return moreThanNinty;
    }

    public void setMoreThanNinty(Double moreThanNinty) {
        this.moreThanNinty = moreThanNinty;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    @Override
    public String toString() {
        return "Outstanding{" +
                "outstandingSINo=" + outstandingSINo +
                ", segment='" + segment + '\'' +
                ", ledgerGroupName='" + ledgerGroupName + '\'' +
                ", partyName='" + partyName + '\'' +
                ", outstandingDate=" + outstandingDate +
                ", billNo='" + billNo + '\'' +
                ", billAmt=" + billAmt +
                ", paidAmt=" + paidAmt +
                ", balanceAmt=" + balanceAmt +
                ", dueSince=" + dueSince +
                ", upToSeven=" + upToSeven +
                ", eightToThirty=" + eightToThirty +
                ", thirtyOneToNinty=" + thirtyOneToNinty +
                ", moreThanNinty=" + moreThanNinty +
                ", salesMan='" + salesMan + '\'' +
                '}';
    }
}
