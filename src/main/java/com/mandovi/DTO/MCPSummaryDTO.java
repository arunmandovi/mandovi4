package com.mandovi.DTO;

public class MCPSummaryDTO {
    private String city;
    private String branch;
    private Long mcp;
    private  Double amount;

    public MCPSummaryDTO(String city, String branch, Long mcp, Double amount) {
        this.city = city;
        this.branch = branch;
        this.mcp = mcp;
        this.amount = amount;
    }

    public MCPSummaryDTO() {
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

    public Long getMcp() {
        return mcp;
    }

    public void setMcp(Long mcp) {
        this.mcp = mcp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MCPSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", mcp=" + mcp +
                ", amount=" + amount +
                '}';
    }
}
