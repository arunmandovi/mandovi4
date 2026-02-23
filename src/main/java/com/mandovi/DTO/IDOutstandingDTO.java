package com.mandovi.DTO;

public class IDOutstandingDTO {
    private String segment;
    private String insuranceParty;
    private String partyName;
    private String salesMan;
    private String billNo;
    private Double billAmt;
    private Double balanceAmt;
    private Double insuranceAmt;
    private Double differenceAmt;
    private Double upToSeven;
    private Double eightToThirty;
    private Double thirtyOneToNinty;
    private Double grtNinty;

    public IDOutstandingDTO() {
    }

    public IDOutstandingDTO(String segment, String insuranceParty, String partyName, String salesMan, String billNo, Double billAmt, Double balanceAmt, Double insuranceAmt, Double differenceAmt, Double upToSeven, Double eightToThirty, Double thirtyOneToNinty, Double grtNinty) {
        this.segment = segment;
        this.insuranceParty = insuranceParty;
        this.partyName = partyName;
        this.salesMan = salesMan;
        this.billNo = billNo;
        this.billAmt = billAmt;
        this.balanceAmt = balanceAmt;
        this.insuranceAmt = insuranceAmt;
        this.differenceAmt = differenceAmt;
        this.upToSeven = upToSeven;
        this.eightToThirty = eightToThirty;
        this.thirtyOneToNinty = thirtyOneToNinty;
        this.grtNinty = grtNinty;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getInsuranceParty() {
        return insuranceParty;
    }

    public void setInsuranceParty(String insuranceParty) {
        this.insuranceParty = insuranceParty;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
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

    public Double getGrtNinty() {
        return grtNinty;
    }

    public void setGrtNinty(Double grtNinty) {
        this.grtNinty = grtNinty;
    }

    @Override
    public String toString() {
        return "IDOutstandingDTO{" +
                "segment='" + segment + '\'' +
                ", insuranceParty='" + insuranceParty + '\'' +
                ", partyName='" + partyName + '\'' +
                ", salesMan='" + salesMan + '\'' +
                ", billNo='" + billNo + '\'' +
                ", billAmt=" + billAmt +
                ", balanceAmt=" + balanceAmt +
                ", insuranceAmt=" + insuranceAmt +
                ", differenceAmt=" + differenceAmt +
                ", upToSeven=" + upToSeven +
                ", eightToThirty=" + eightToThirty +
                ", thirtyOneToNinty=" + thirtyOneToNinty +
                ", grtNinty=" + grtNinty +
                '}';
    }
}
