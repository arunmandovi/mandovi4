package com.mandovi.DTO;

public class TotalOutstandingDTO {
    private String segment;
    private String salesMan;
    private String partyName;
    private String billNo;
    private Integer dueSince;
    private Double billAmt;
    private Double balanceAmt;
    private Double upToSeven;
    private Double eightToThirty;
    private Double thirtyOneToNinty;
    private Double grtNinty;

    public TotalOutstandingDTO() {
    }

    public TotalOutstandingDTO(String segment, String salesMan, String partyName, String billNo, Integer dueSince, Double billAmt, Double balanceAmt, Double upToSeven, Double eightToThirty, Double thirtyOneToNinty, Double grtNinty) {
        this.segment = segment;
        this.salesMan = salesMan;
        this.partyName = partyName;
        this.billNo = billNo;
        this.dueSince = dueSince;
        this.billAmt = billAmt;
        this.balanceAmt = balanceAmt;
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

    public Integer getDueSince() {
        return dueSince;
    }

    public void setDueSince(Integer dueSince) {
        this.dueSince = dueSince;
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
        return "TotalOutstandingDTO{" +
                "segment='" + segment + '\'' +
                ", salesMan='" + salesMan + '\'' +
                ", partyName='" + partyName + '\'' +
                ", billNo='" + billNo + '\'' +
                ", dueSince=" + dueSince +
                ", billAmt=" + billAmt +
                ", balanceAmt=" + balanceAmt +
                ", upToSeven=" + upToSeven +
                ", eightToThirty=" + eightToThirty +
                ", thirtyOneToNinty=" + thirtyOneToNinty +
                ", grtNinty=" + grtNinty +
                '}';
    }
}
