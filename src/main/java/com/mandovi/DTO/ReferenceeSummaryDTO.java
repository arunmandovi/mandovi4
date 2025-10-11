package com.mandovi.DTO;

public class ReferenceeSummaryDTO {
    private String city;
    private String branch;
    private Long referencee;
    private Long enquiry;
    private Long booking;
    private Long invoice;
    private Double percentageEnquiryBooking;
    private Double percentageEnquiryInvoice;
    private Double percentageBookingInvoice;

    public ReferenceeSummaryDTO() {
    }

    public ReferenceeSummaryDTO(String city, String branch, Long referencee, Long enquiry, Long booking, Long invoice, Double percentageEnquiryBooking, Double percentageEnquiryInvoice, Double percentageBookingInvoice) {
        this.city = city;
        this.branch = branch;
        this.referencee = referencee;
        this.enquiry = enquiry;
        this.booking = booking;
        this.invoice = invoice;
        this.percentageEnquiryBooking = percentageEnquiryBooking;
        this.percentageEnquiryInvoice = percentageEnquiryInvoice;
        this.percentageBookingInvoice = percentageBookingInvoice;
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

    public Long getReferencee() {
        return referencee;
    }

    public void setReferencee(Long referencee) {
        this.referencee = referencee;
    }

    public Long getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(Long enquiry) {
        this.enquiry = enquiry;
    }

    public Long getBooking() {
        return booking;
    }

    public void setBooking(Long booking) {
        this.booking = booking;
    }

    public Long getInvoice() {
        return invoice;
    }

    public void setInvoice(Long invoice) {
        this.invoice = invoice;
    }

    public Double getPercentageEnquiryBooking() {
        return percentageEnquiryBooking;
    }

    public void setPercentageEnquiryBooking(Double percentageEnquiryBooking) {
        this.percentageEnquiryBooking = percentageEnquiryBooking;
    }

    public Double getPercentageEnquiryInvoice() {
        return percentageEnquiryInvoice;
    }

    public void setPercentageEnquiryInvoice(Double percentageEnquiryInvoice) {
        this.percentageEnquiryInvoice = percentageEnquiryInvoice;
    }

    public Double getPercentageBookingInvoice() {
        return percentageBookingInvoice;
    }

    public void setPercentageBookingInvoice(Double percentageBookingInvoice) {
        this.percentageBookingInvoice = percentageBookingInvoice;
    }

    @Override
    public String toString() {
        return "ReferenceeSummaryDTO{" +
                "city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", referencee=" + referencee +
                ", enquiry=" + enquiry +
                ", booking=" + booking +
                ", invoice=" + invoice +
                ", percentageEnquiryBooking=" + percentageEnquiryBooking +
                ", percentageEnquiryInvoice=" + percentageEnquiryInvoice +
                ", percentageBookingInvoice=" + percentageBookingInvoice +
                '}';
    }
}
