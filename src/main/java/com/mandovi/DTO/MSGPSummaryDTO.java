package com.mandovi.DTO;

public class MSGPSummaryDTO {
    private String city;
    private String branch;
    private Double previousSRBS;
    private Double currentSRBS;
    private Double growthSRBS;
    private Double previousService;
    private Double currentService;
    private Double growthService;
    private Double previousBodyShop;
    private Double currentBodyShop;
    private Double growthBodyShop;
    private Double previousFreeService;
    private Double currentFreeService;
    private Double growthFreeService;
    private Double previousPMS;
    private Double currentPMS;
    private Double growthPMS;
    private Double previousRR;
    private Double currentRR;
    private Double growthRR;
    private Double previousOthers;
    private Double currentOthers;
    private Double growthOthers;

    public MSGPSummaryDTO() {
    }

    public MSGPSummaryDTO(String city, String branch, Double previousSRBS, Double currentSRBS, Double growthSRBS, Double previousService, Double currentService, Double growthService, Double previousBodyShop, Double currentBodyShop, Double growthBodyShop, Double previousFreeService, Double currentFreeService, Double growthFreeService, Double previousPMS, Double currentPMS, Double growthPMS, Double previousRR, Double currentRR, Double growthRR, Double previousOthers, Double currentOthers, Double growthOthers) {
        this.city = city;
        this.branch = branch;
        this.previousSRBS = previousSRBS;
        this.currentSRBS = currentSRBS;
        this.growthSRBS = growthSRBS;
        this.previousService = previousService;
        this.currentService = currentService;
        this.growthService = growthService;
        this.previousBodyShop = previousBodyShop;
        this.currentBodyShop = currentBodyShop;
        this.growthBodyShop = growthBodyShop;
        this.previousFreeService = previousFreeService;
        this.currentFreeService = currentFreeService;
        this.growthFreeService = growthFreeService;
        this.previousPMS = previousPMS;
        this.currentPMS = currentPMS;
        this.growthPMS = growthPMS;
        this.previousRR = previousRR;
        this.currentRR = currentRR;
        this.growthRR = growthRR;
        this.previousOthers = previousOthers;
        this.currentOthers = currentOthers;
        this.growthOthers = growthOthers;
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

    public Double getPreviousSRBS() {
        return previousSRBS;
    }

    public void setPreviousSRBR(Double previousSRBS) {
        this.previousSRBS = previousSRBS;
    }

    public Double getCurrentSRBS() {
        return currentSRBS;
    }

    public void setCurrentSRBS(Double currentSRBS) {
        this.currentSRBS = currentSRBS;
    }

    public Double getGrowthSRBS() {
        return growthSRBS;
    }

    public void setGrowthSRBS(Double growthSRBS) {
        this.growthSRBS = growthSRBS;
    }

    public Double getPreviousService() {
        return previousService;
    }

    public void setPreviousService(Double previousService) {
        this.previousService = previousService;
    }

    public Double getCurrentService() {
        return currentService;
    }

    public void setCurrentService(Double currentService) {
        this.currentService = currentService;
    }

    public Double getGrowthService() {
        return growthService;
    }

    public void setGrowthService(Double growthService) {
        this.growthService = growthService;
    }

    public Double getPreviousBodyShop() {
        return previousBodyShop;
    }

    public void setPreviousBodyShop(Double previousBodyShop) {
        this.previousBodyShop = previousBodyShop;
    }

    public Double getCurrentBodyShop() {
        return currentBodyShop;
    }

    public void setCurrentBodyShop(Double currentBodyShop) {
        this.currentBodyShop = currentBodyShop;
    }

    public Double getGrowthBodyShop() {
        return growthBodyShop;
    }

    public void setGrowthBodyShop(Double growthBodyShop) {
        this.growthBodyShop = growthBodyShop;
    }

    public Double getPreviousFreeService() {
        return previousFreeService;
    }

    public void setPreviousFreeService(Double previousFreeService) {
        this.previousFreeService = previousFreeService;
    }

    public Double getCurrentFreeService() {
        return currentFreeService;
    }

    public void setCurrentFreeService(Double currentFreeService) {
        this.currentFreeService = currentFreeService;
    }

    public Double getGrowthFreeService() {
        return growthFreeService;
    }

    public void setGrowthFreeService(Double growthFreeService) {
        this.growthFreeService = growthFreeService;
    }

    public Double getPreviousPMS() {
        return previousPMS;
    }

    public void setPreviousPMS(Double previousPMS) {
        this.previousPMS = previousPMS;
    }

    public Double getCurrentPMS() {
        return currentPMS;
    }

    public void setCurrentPMS(Double currentPMS) {
        this.currentPMS = currentPMS;
    }

    public Double getGrowthPMS() {
        return growthPMS;
    }

    public void setGrowthPMS(Double growthPMS) {
        this.growthPMS = growthPMS;
    }

    public Double getPreviousRR() {
        return previousRR;
    }

    public void setPreviousRR(Double previousRR) {
        this.previousRR = previousRR;
    }

    public Double getCurrentRR() {
        return currentRR;
    }

    public void setCurrentRR(Double currentRR) {
        this.currentRR = currentRR;
    }

    public Double getGrowthRR() {
        return growthRR;
    }

    public void setGrowthRR(Double growthRR) {
        this.growthRR = growthRR;
    }

    public Double getPreviousOthers() {
        return previousOthers;
    }

    public void setPreviousOthers(Double previousOthers) {
        this.previousOthers = previousOthers;
    }

    public Double getCurrentOthers() {
        return currentOthers;
    }

    public void setCurrentOthers(Double currentOthers) {
        this.currentOthers = currentOthers;
    }

    public Double getGrowthOthers() {
        return growthOthers;
    }

    public void setGrowthOthers(Double growthOthers) {
        this.growthOthers = growthOthers;
    }

    @Override
    public String toString() {
        return "MSGPSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", previousSRBS=" + previousSRBS +
                ", currentSRBS=" + currentSRBS +
                ", growthSRBS=" + growthSRBS +
                ", previousService=" + previousService +
                ", currentService=" + currentService +
                ", growthService=" + growthService +
                ", previousBodyShop=" + previousBodyShop +
                ", currentBodyShop=" + currentBodyShop +
                ", growthBodyShop=" + growthBodyShop +
                ", previousFreeService=" + previousFreeService +
                ", currentFreeService=" + currentFreeService +
                ", growthFreeService=" + growthFreeService +
                ", previousPMS=" + previousPMS +
                ", currentPMS=" + currentPMS +
                ", growthPMS=" + growthPMS +
                ", previousRR=" + previousRR +
                ", currentRR=" + currentRR +
                ", growthRR=" + growthRR +
                ", previousOthers=" + previousOthers +
                ", currentOthers=" + currentOthers +
                ", growthOthers=" + growthOthers +
                '}';
    }
}
