package com.mandovi.DTO;

public class ReferenceeTableDTO {
    private String groupDesignation;
    private long referencee;
    private long enquiry;
    private long booking;
    private long invoice;

    public ReferenceeTableDTO() {
    }

    public ReferenceeTableDTO(String groupDesignation, long referencee, long enquiry, long booking, long invoice) {
        this.groupDesignation = groupDesignation;
        this.referencee = referencee;
        this.enquiry = enquiry;
        this.booking = booking;
        this.invoice = invoice;
    }

    public String getGroupDesignation() {
        return groupDesignation;
    }

    public void setGroupDesignation(String groupDesignation) {
        this.groupDesignation = groupDesignation;
    }

    public long getReferencee() {
        return referencee;
    }

    public void setReferencee(long referencee) {
        this.referencee = referencee;
    }

    public long getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(long enquiry) {
        this.enquiry = enquiry;
    }

    public long getBooking() {
        return booking;
    }

    public void setBooking(long booking) {
        this.booking = booking;
    }

    public long getInvoice() {
        return invoice;
    }

    public void setInvoice(long invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "ReferenceeTableDTO{" +
                "groupDesignation='" + groupDesignation + '\'' +
                ", referencee=" + referencee +
                ", enquiry=" + enquiry +
                ", booking=" + booking +
                ", invoice=" + invoice +
                '}';
    }
}
