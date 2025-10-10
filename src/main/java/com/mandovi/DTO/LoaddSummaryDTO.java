package com.mandovi.DTO;

public class LoaddSummaryDTO {
    private String city;
    private String branch;
    private Long previousService;
    private Long currentService;
    private Double growthService;
    private Long previousBodyShop;
    private Long currentBodyShop;
    private Double growthBodyShop;
    private Long previousFreeService;
    private Long currentFreeService;
    private Double growthFreeService;
    private Long previousPMS;
    private Long currentPMS;
    private Double growthPMS;
    private Long previousFPR;
    private Long currentFPR;
    private Double growthFPR;
    private Long previousRR;
    private Long currentRR;
    private Double growthRR;
    private Long previousOthers;
    private Long currentOthers;
    private Double growthOthers;
    private Double previousBSFPR;
    private Double currentBSFPR;
    private Double growthBSFPR;

    public LoaddSummaryDTO() {
    }

    public LoaddSummaryDTO(String city, String branch, Long previousService, Long currentService, Double growthService, Long previousBodyShop, Long currentBodyShop, Double growthBodyShop, Long previousFreeService, Long currentFreeService, Double growthFreeService, Long previousPMS, Long currentPMS, Double growthPMS, Long previousFPR, Long currentFPR, Double growthFPR, Long previousRR, Long currentRR, Double growthRR, Long previousOthers, Long currentOthers, Double growthOthers, Double previousBSFPR, Double currentBSFPR, Double growthBSFPR) {
        this.city = city;
        this.branch = branch;
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
        this.previousFPR = previousFPR;
        this.currentFPR = currentFPR;
        this.growthFPR = growthFPR;
        this.previousRR = previousRR;
        this.currentRR = currentRR;
        this.growthRR = growthRR;
        this.previousOthers = previousOthers;
        this.currentOthers = currentOthers;
        this.growthOthers = growthOthers;
        this.previousBSFPR = previousBSFPR;
        this.currentBSFPR = currentBSFPR;
        this.growthBSFPR = growthBSFPR;
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

    public Long getPreviousService() {
        return previousService;
    }

    public void setPreviousService(Long previousService) {
        this.previousService = previousService;
    }

    public Long getCurrentService() {
        return currentService;
    }

    public void setCurrentService(Long currentService) {
        this.currentService = currentService;
    }

    public Double getGrowthService() {
        return growthService;
    }

    public void setGrowthService(Double growthService) {
        this.growthService = growthService;
    }

    public Long getPreviousBodyShop() {
        return previousBodyShop;
    }

    public void setPreviousBodyShop(Long previousBodyShop) {
        this.previousBodyShop = previousBodyShop;
    }

    public Long getCurrentBodyShop() {
        return currentBodyShop;
    }

    public void setCurrentBodyShop(Long currentBodyShop) {
        this.currentBodyShop = currentBodyShop;
    }

    public Double getGrowthBodyShop() {
        return growthBodyShop;
    }

    public void setGrowthBodyShop(Double growthBodyShop) {
        this.growthBodyShop = growthBodyShop;
    }

    public Long getPreviousFreeService() {
        return previousFreeService;
    }

    public void setPreviousFreeService(Long previousFreeService) {
        this.previousFreeService = previousFreeService;
    }

    public Long getCurrentFreeService() {
        return currentFreeService;
    }

    public void setCurrentFreeService(Long currentFreeService) {
        this.currentFreeService = currentFreeService;
    }

    public Double getGrowthFreeService() {
        return growthFreeService;
    }

    public void setGrowthFreeService(Double growthFreeService) {
        this.growthFreeService = growthFreeService;
    }

    public Long getPreviousPMS() {
        return previousPMS;
    }

    public void setPreviousPMS(Long previousPMS) {
        this.previousPMS = previousPMS;
    }

    public Long getCurrentPMS() {
        return currentPMS;
    }

    public void setCurrentPMS(Long currentPMS) {
        this.currentPMS = currentPMS;
    }

    public Double getGrowthPMS() {
        return growthPMS;
    }

    public void setGrowthPMS(Double growthPMS) {
        this.growthPMS = growthPMS;
    }

    public Long getPreviousFPR() {
        return previousFPR;
    }

    public void setPreviousFPR(Long previousFPR) {
        this.previousFPR = previousFPR;
    }

    public Long getCurrentFPR() {
        return currentFPR;
    }

    public void setCurrentFPR(Long currentFPR) {
        this.currentFPR = currentFPR;
    }

    public Double getGrowthFPR() {
        return growthFPR;
    }

    public void setGrowthFPR(Double growthFPR) {
        this.growthFPR = growthFPR;
    }

    public Long getPreviousRR() {
        return previousRR;
    }

    public void setPreviousRR(Long previousRR) {
        this.previousRR = previousRR;
    }

    public Long getCurrentRR() {
        return currentRR;
    }

    public void setCurrentRR(Long currentRR) {
        this.currentRR = currentRR;
    }

    public Double getGrowthRR() {
        return growthRR;
    }

    public void setGrowthRR(Double growthRR) {
        this.growthRR = growthRR;
    }

    public Long getPreviousOthers() {
        return previousOthers;
    }

    public void setPreviousOthers(Long previousOthers) {
        this.previousOthers = previousOthers;
    }

    public Long getCurrentOthers() {
        return currentOthers;
    }

    public void setCurrentOthers(Long currentOthers) {
        this.currentOthers = currentOthers;
    }

    public Double getGrowthOthers() {
        return growthOthers;
    }

    public void setGrowthOthers(Double growthOthers) {
        this.growthOthers = growthOthers;
    }

    public Double getPreviousBSFPR() {
        return previousBSFPR;
    }

    public void setPreviousBSFPR(Double previousBSFPR) {
        this.previousBSFPR = previousBSFPR;
    }

    public Double getCurrentBSFPR() {
        return currentBSFPR;
    }

    public void setCurrentBSFPR(Double currentBSFPR) {
        this.currentBSFPR = currentBSFPR;
    }

    public Double getGrowthBSFPR() {
        return growthBSFPR;
    }

    public void setGrowthBSFPR(Double growthBSFPR) {
        this.growthBSFPR = growthBSFPR;
    }

    @Override
    public String toString() {
        return "LoaddSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
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
                ", previousFPR=" + previousFPR +
                ", currentFPR=" + currentFPR +
                ", growthFPR=" + growthFPR +
                ", previousRR=" + previousRR +
                ", currentRR=" + currentRR +
                ", growthRR=" + growthRR +
                ", previousOthers=" + previousOthers +
                ", currentOthers=" + currentOthers +
                ", growthOthers=" + growthOthers +
                ", previousBSFPR=" + previousBSFPR +
                ", currentBSFPR=" + currentBSFPR +
                ", growthBSFPR=" + growthBSFPR +
                '}';
    }
}