package com.mandovi.DTO;

public class HoldUpRegNoComparisonDTO {
    private String city;
    private String branch;

    private int previousCount;
    private int newlyAddedCount;
    private int addedAgainCount;

    public HoldUpRegNoComparisonDTO() {
    }

    public HoldUpRegNoComparisonDTO(String city, String branch, int previousCount, int newlyAddedCount, int addedAgainCount) {
        this.city = city;
        this.branch = branch;
        this.previousCount = previousCount;
        this.newlyAddedCount = newlyAddedCount;
        this.addedAgainCount = addedAgainCount;
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

    public int getPreviousCount() {
        return previousCount;
    }

    public void setPreviousCount(int previousCount) {
        this.previousCount = previousCount;
    }

    public int getNewlyAddedCount() {
        return newlyAddedCount;
    }

    public void setNewlyAddedCount(int newlyAddedCount) {
        this.newlyAddedCount = newlyAddedCount;
    }

    public int getAddedAgainCount() {
        return addedAgainCount;
    }

    public void setAddedAgainCount(int addedAgainCount) {
        this.addedAgainCount = addedAgainCount;
    }

    @Override
    public String toString() {
        return "HoldUpRegNoComparisonDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", previousCount=" + previousCount +
                ", newlyAddedCount=" + newlyAddedCount +
                ", addedAgainCount=" + addedAgainCount +
                '}';
    }
}
