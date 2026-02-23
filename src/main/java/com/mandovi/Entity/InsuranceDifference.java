package com.mandovi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "insurance_difference")
public class InsuranceDifference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "insurance_differenceSINo")
    private Integer insuranceDifferenceSINo;

    @Column (name = "segment")
    private String segment;

    @Column (name = "ledger_name")
    private String ledgerName;

    @Column (name = "party_name")
    private String partyName;

    @Column (name = "insurance_party")
    private String insuranceParty;

    @Column (name = "insurance_difference_date")
    private String insuranceDifferenceDate;

    @Column (name = "bill_no")
    private String billNo;

    @Column (name = "bill_amt")
    private Double billAmt;

    @Column (name = "paid_amt")
    private Double paidAmt;

    @Column (name = "balance_amt")
    private Double balanceAmt;

    @Column (name = "insurance_amt")
    private Double insuranceAmt;

    @Column (name = "difference_amt")
    private Double differenceAmt;

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

    public InsuranceDifference() {
    }

    public InsuranceDifference(Integer insuranceDifferenceSINo, String segment, String ledgerName, String partyName, String insuranceParty, String insuranceDifferenceDate, String billNo, Double billAmt, Double paidAmt, Double balanceAmt, Double insuranceAmt, Double differenceAmt, Integer dueSince, Double upToSeven, Double eightToThirty, Double thirtyOneToNinty, Double moreThanNinty, String salesMan) {
        this.insuranceDifferenceSINo = insuranceDifferenceSINo;
        this.segment = segment;
        this.ledgerName = ledgerName;
        this.partyName = partyName;
        this.insuranceParty = insuranceParty;
        this.insuranceDifferenceDate = insuranceDifferenceDate;
        this.billNo = billNo;
        this.billAmt = billAmt;
        this.paidAmt = paidAmt;
        this.balanceAmt = balanceAmt;
        this.insuranceAmt = insuranceAmt;
        this.differenceAmt = differenceAmt;
        this.dueSince = dueSince;
        this.upToSeven = upToSeven;
        this.eightToThirty = eightToThirty;
        this.thirtyOneToNinty = thirtyOneToNinty;
        this.moreThanNinty = moreThanNinty;
        this.salesMan = salesMan;
    }

    public Integer getInsuranceDifferenceSINo() {
        return insuranceDifferenceSINo;
    }

    public void setInsuranceDifferenceSINo(Integer insuranceDifferenceSINo) {
        this.insuranceDifferenceSINo = insuranceDifferenceSINo;
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

    public String getInsuranceParty() {
        return insuranceParty;
    }

    public void setInsuranceParty(String insuranceParty) {
        this.insuranceParty = insuranceParty;
    }

    public String getInsuranceDifferenceDate() {
        return insuranceDifferenceDate;
    }

    public void setInsuranceDifferenceDate(String insuranceDifferenceDate) {
        this.insuranceDifferenceDate = insuranceDifferenceDate;
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

    public Double getInsuranceAmt() {
        return insuranceAmt;
    }

    public void setInsuranceAmt(Double insuranceAmt) {
        this.insuranceAmt = insuranceAmt;
    }

    public Double getDifferenceAmt() {
        return differenceAmt;
    }

    public void setDifferenceAmt(Double differenceAmt) {
        this.differenceAmt = differenceAmt;
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
        return "InsuranceDifference{" +
                "insuranceDifferenceSINo=" + insuranceDifferenceSINo +
                ", segment='" + segment + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                ", partyName='" + partyName + '\'' +
                ", insuranceParty='" + insuranceParty + '\'' +
                ", insuranceDifferenceDate='" + insuranceDifferenceDate + '\'' +
                ", billNo='" + billNo + '\'' +
                ", billAmt=" + billAmt +
                ", paidAmt=" + paidAmt +
                ", balanceAmt=" + balanceAmt +
                ", insuranceAmt=" + insuranceAmt +
                ", differenceAmt=" + differenceAmt +
                ", dueSince=" + dueSince +
                ", upToSeven=" + upToSeven +
                ", eightToThirty=" + eightToThirty +
                ", thirtyOneToNinty=" + thirtyOneToNinty +
                ", moreThanNinty=" + moreThanNinty +
                ", salesMan='" + salesMan + '\'' +
                '}';
    }
}
