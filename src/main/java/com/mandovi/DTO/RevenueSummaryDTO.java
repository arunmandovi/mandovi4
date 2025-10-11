package com.mandovi.DTO;

public class RevenueSummaryDTO {
    private String city;
    private String branch;
    private Double previousSRBRLabour;
    private Double currentSRBRLabour;
    private Double growthSRBRLabour;
    private Double previousSRSpares;
    private Double currentSRSpares;
    private Double growthSRSpares;
    private Double previousBRSpares;
    private Double currentBRSpares;
    private Double growthBRSpares;
    private Double previousSRBRSpares;
    private Double currentSRBRSpares;
    private Double growthSRBRSpares;
    private Double previousSRBRTotal;
    private Double currentSRBRTotal;
    private Double growthSRBRTotal;

    public RevenueSummaryDTO() {
    }

    public RevenueSummaryDTO(String city, String branch, Double previousSRBRLabour, Double currentSRBRLabour, Double growthSRBRLabour, Double previousSRSpares, Double currentSRSpares, Double growthSRSpares, Double previousBRSpares, Double currentBRSpares, Double growthBRSpares, Double previousSRBRSpares, Double currentSRBRSpares, Double growthSRBRSpares, Double previousSRBRTotal, Double currentSRBRTotal, Double growthSRBRTotal) {
        this.city = city;
        this.branch = branch;
        this.previousSRBRLabour = previousSRBRLabour;
        this.currentSRBRLabour = currentSRBRLabour;
        this.growthSRBRLabour = growthSRBRLabour;
        this.previousSRSpares = previousSRSpares;
        this.currentSRSpares = currentSRSpares;
        this.growthSRSpares = growthSRSpares;
        this.previousBRSpares = previousBRSpares;
        this.currentBRSpares = currentBRSpares;
        this.growthBRSpares = growthBRSpares;
        this.previousSRBRSpares = previousSRBRSpares;
        this.currentSRBRSpares = currentSRBRSpares;
        this.growthSRBRSpares = growthSRBRSpares;
        this.previousSRBRTotal = previousSRBRTotal;
        this.currentSRBRTotal = currentSRBRTotal;
        this.growthSRBRTotal = growthSRBRTotal;
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

    public Double getPreviousSRBRLabour() {
        return previousSRBRLabour;
    }

    public void setPreviousSRBRLabour(Double previousSRBRLabour) {
        this.previousSRBRLabour = previousSRBRLabour;
    }

    public Double getCurrentSRBRLabour() {
        return currentSRBRLabour;
    }

    public void setCurrentSRBRLabour(Double currentSRBRLabour) {
        this.currentSRBRLabour = currentSRBRLabour;
    }

    public Double getGrowthSRBRLabour() {
        return growthSRBRLabour;
    }

    public void setGrowthSRBRLabour(Double growthSRBRLabour) {
        this.growthSRBRLabour = growthSRBRLabour;
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

    public Double getPreviousSRBRTotal() {
        return previousSRBRTotal;
    }

    public void setPreviousSRBRTotal(Double previousSRBRTotal) {
        this.previousSRBRTotal = previousSRBRTotal;
    }

    public Double getCurrentSRBRTotal() {
        return currentSRBRTotal;
    }

    public void setCurrentSRBRTotal(Double currentSRBRTotal) {
        this.currentSRBRTotal = currentSRBRTotal;
    }

    public Double getGrowthSRBRTotal() {
        return growthSRBRTotal;
    }

    public void setGrowthSRBRTotal(Double growthSRBRTotal) {
        this.growthSRBRTotal = growthSRBRTotal;
    }

    @Override
    public String toString() {
        return "RevenueSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", previousSRBRLabour=" + previousSRBRLabour +
                ", currentSRBRLabour=" + currentSRBRLabour +
                ", growthSRBRLabour=" + growthSRBRLabour +
                ", previousSRSpares=" + previousSRSpares +
                ", currentSRSpares=" + currentSRSpares +
                ", growthSRSpares=" + growthSRSpares +
                ", previousBRSpares=" + previousBRSpares +
                ", currentBRSpares=" + currentBRSpares +
                ", growthBRSpares=" + growthBRSpares +
                ", previousSRBRSpares=" + previousSRBRSpares +
                ", currentSRBRSpares=" + currentSRBRSpares +
                ", growthSRBRSpares=" + growthSRBRSpares +
                ", previousSRBRTotal=" + previousSRBRTotal +
                ", currentSRBRTotal=" + currentSRBRTotal +
                ", growthSRBRTotal=" + growthSRBRTotal +
                '}';
    }
}
