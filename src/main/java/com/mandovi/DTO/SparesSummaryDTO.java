package com.mandovi.DTO;

public class SparesSummaryDTO {
    private String city;
    private String branch;
    private Double previousSRSpares;
    private Double currentSRSpares;
    private Double growthSRSpares;
    private Double previousBRSpares;
    private Double currentBRSpares;
    private Double growthBRSpares;
    private Double previousSRBRSpares;
    private Double currentSRBRSpares;
    private Double growthSRBRSpares;
    private Double previousBattery;
    private Double currentBattery;
    private Double growthBattery;
    private Double previousTyre;
    private Double currentTyre;
    private Double growthTyre;

    public SparesSummaryDTO() {
    }

    public SparesSummaryDTO(String city, String branch, Double previousSRSpares, Double currentSRSpares, Double growthSRSpares, Double previousBRSpares, Double currentBRSpares, Double growthBRSpares, Double previousSRBRSpares, Double currentSRBRSpares, Double growthSRBRSpares, Double previousBattery, Double currentBattery, Double growthBattery, Double previousTyre, Double currentTyre, Double growthTyre) {
        this.city = city;
        this.branch = branch;
        this.previousSRSpares = previousSRSpares;
        this.currentSRSpares = currentSRSpares;
        this.growthSRSpares = growthSRSpares;
        this.previousBRSpares = previousBRSpares;
        this.currentBRSpares = currentBRSpares;
        this.growthBRSpares = growthBRSpares;
        this.previousSRBRSpares = previousSRBRSpares;
        this.currentSRBRSpares = currentSRBRSpares;
        this.growthSRBRSpares = growthSRBRSpares;
        this.previousBattery = previousBattery;
        this.currentBattery = currentBattery;
        this.growthBattery = growthBattery;
        this.previousTyre = previousTyre;
        this.currentTyre = currentTyre;
        this.growthTyre = growthTyre;
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

    public Double getPreviousSRSpares() {
        return previousSRSpares;
    }

    public void setPreviousSRSpares(Double previousSRSpares) {
        this.previousSRSpares = previousSRSpares;
    }

    public Double getCurrentSRSpares() {
        return currentSRSpares;
    }

    public void setCurrentSRSpares(Double currentSRSpares) {
        this.currentSRSpares = currentSRSpares;
    }

    public Double getGrowthSRSpares() {
        return growthSRSpares;
    }

    public void setGrowthSRSpares(Double growthSRSpares) {
        this.growthSRSpares = growthSRSpares;
    }

    public Double getPreviousBRSpares() {
        return previousBRSpares;
    }

    public void setPreviousBRSpares(Double previousBRSpares) {
        this.previousBRSpares = previousBRSpares;
    }

    public Double getCurrentBRSpares() {
        return currentBRSpares;
    }

    public void setCurrentBRSpares(Double currentBRSpares) {
        this.currentBRSpares = currentBRSpares;
    }

    public Double getGrowthBRSpares() {
        return growthBRSpares;
    }

    public void setGrowthBRSpares(Double growthBRSpares) {
        this.growthBRSpares = growthBRSpares;
    }

    public Double getPreviousSRBRSpares() {
        return previousSRBRSpares;
    }

    public void setPreviousSRBRSpares(Double previousSRBRSpares) {
        this.previousSRBRSpares = previousSRBRSpares;
    }

    public Double getCurrentSRBRSpares() {
        return currentSRBRSpares;
    }

    public void setCurrentSRBRSpares(Double currentSRBRSpares) {
        this.currentSRBRSpares = currentSRBRSpares;
    }

    public Double getGrowthSRBRSpares() {
        return growthSRBRSpares;
    }

    public void setGrowthSRBRSpares(Double growthSRBRSpares) {
        this.growthSRBRSpares = growthSRBRSpares;
    }

    public Double getPreviousBattery() {
        return previousBattery;
    }

    public void setPreviousBattery(Double previousBattery) {
        this.previousBattery = previousBattery;
    }

    public Double getCurrentBattery() {
        return currentBattery;
    }

    public void setCurrentBattery(Double currentBattery) {
        this.currentBattery = currentBattery;
    }

    public Double getGrowthBattery() {
        return growthBattery;
    }

    public void setGrowthBattery(Double growthBattery) {
        this.growthBattery = growthBattery;
    }

    public Double getPreviousTyre() {
        return previousTyre;
    }

    public void setPreviousTyre(Double previousTyre) {
        this.previousTyre = previousTyre;
    }

    public Double getCurrentTyre() {
        return currentTyre;
    }

    public void setCurrentTyre(Double currentTyre) {
        this.currentTyre = currentTyre;
    }

    public Double getGrowthTyre() {
        return growthTyre;
    }

    public void setGrowthTyre(Double growthTyre) {
        this.growthTyre = growthTyre;
    }

    @Override
    public String toString() {
        return "SparesSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", previousSRSpares=" + previousSRSpares +
                ", currentSRSpares=" + currentSRSpares +
                ", growthSRSpares=" + growthSRSpares +
                ", previousBRSpares=" + previousBRSpares +
                ", currentBRSpares=" + currentBRSpares +
                ", growthBRSpares=" + growthBRSpares +
                ", previousSRBRSpares=" + previousSRBRSpares +
                ", currentSRBRSpares=" + currentSRBRSpares +
                ", growthSRBRSpares=" + growthSRBRSpares +
                ", previousBattery=" + previousBattery +
                ", currentBattery=" + currentBattery +
                ", growthBattery=" + growthBattery +
                ", previousTyre=" + previousTyre +
                ", currentTyre=" + currentTyre +
                ", growthTyre=" + growthTyre +
                '}';
    }
}
