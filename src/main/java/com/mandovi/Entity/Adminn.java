package com.mandovi.Entity;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "adminn")
public class Adminn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminnSINo")
    private Integer adminnSINo;

    @Column(name = "adminn_name")
    private String adminnName;

    @Column(name = "adminn_id")
    private String adminnId;

    @Column(name = "branch")
    private String branch;

    @Column(name = "adminn_password")
    private String adminnPassword;

    @Column(name = "adminn_status")
    @Enumerated(EnumType.STRING)
    private Status adminnStatus = Status.APPROVED;

    public enum Status{
        APPROVED,PENDING;
    }

    public Adminn() {
    }

    public Adminn(Integer adminnSINo, String adminnName, String adminnId, String branch, String adminnPassword) {
        this.adminnSINo = adminnSINo;
        this.adminnName = adminnName;
        this.adminnId = adminnId;
        this.branch = branch;
        this.adminnPassword = adminnPassword;
        this.adminnStatus = Status.APPROVED;
    }

    public Integer getAdminnSINo() {
        return adminnSINo;
    }

    public void setAdminnSINo(Integer adminnSINo) {
        this.adminnSINo = adminnSINo;
    }

    public String getAdminnName() {
        return adminnName;
    }

    public void setAdminnName(String adminnName) {
        this.adminnName = adminnName;
    }

    public String getAdminnId() {
        return adminnId;
    }

    public void setAdminnId(String adminnId) {
        this.adminnId = adminnId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAdminnPassword() {
        return adminnPassword;
    }

    public void setAdminnPassword(String adminnPassword) {
        this.adminnPassword = adminnPassword;
    }

    public Status getAdminnStatus() {
        return adminnStatus;
    }

    public void setAdminnStatus(Status adminnStatus) {
        this.adminnStatus = adminnStatus;
    }

    @Override
    public String toString() {
        return "Adminn{" +
                "adminnSINo=" + adminnSINo +
                ", adminnName='" + adminnName + '\'' +
                ", adminnId='" + adminnId + '\'' +
                ", branch='" + branch + '\'' +
                ", adminnPassword='" + adminnPassword + '\'' +
                ", adminnStatus='" + adminnStatus + '\'' +
                '}';
    }
}
