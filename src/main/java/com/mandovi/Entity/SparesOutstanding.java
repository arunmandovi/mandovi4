package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "spares_outstanding")
public class SparesOutstanding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "spares_outstandingSINo")
    private Integer sparesOutstandingSINo;

    @Column (name = "segment")
    private String segment;

    @Column (name = "ledger_name")
    private String ledgerName;

    @Column (name = "party_name")
    private String partyName;

    @Column (name = "spares_outstanding_date")
    private String partyOutstandingDate;

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

    @Column (name = "thityone_to_ninty")
    private Double thirtyOneToNinty;

    @Column (name = "more_than_ninty")
    private Double moreThanNinty;

    @Column (name = "sales_man")
    private String salesMan;

    @Transient
    private LocalDate tempDate;

    public SparesOutstanding() {
    }

    public SparesOutstanding(Integer sparesOutstandingSINo, String segment, String ledgerName, String partyName, String partyOutstandingDate, String billNo, Double billAmt, Double paidAmt, Double balanceAmt, Integer dueSince, Double upToSeven, Double eightToThirty, Double thirtyOneToNinty, Double moreThanNinty, String salesMan) {
        this.sparesOutstandingSINo = sparesOutstandingSINo;
        this.segment = segment;
        this.ledgerName = ledgerName;
        this.partyName = partyName;
        this.partyOutstandingDate = partyOutstandingDate;
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

    public Integer getSparesOutstandingSINo() {
        return sparesOutstandingSINo;
    }

    public void setSparesOutstandingSINo(Integer sparesOutstandingSINo) {
        this.sparesOutstandingSINo = sparesOutstandingSINo;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyOutstandingDate() {
        return partyOutstandingDate;
    }

    public void setPartyOutstandingDate(String partyOutstandingDate) {
        this.partyOutstandingDate = partyOutstandingDate;
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

    public LocalDate getTempDate() {
        return tempDate;
    }

    public void setTempDate(LocalDate tempDate) {
        this.tempDate = tempDate;
    }

    @Override
    public String toString() {
        return "SparesOutstanding{" +
                "sparesOutstandingSINo=" + sparesOutstandingSINo +
                ", segment='" + segment + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                ", partyName='" + partyName + '\'' +
                ", partyOutstandingDate='" + partyOutstandingDate + '\'' +
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
