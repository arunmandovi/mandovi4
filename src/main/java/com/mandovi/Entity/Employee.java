package com.mandovi.Entity;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import jdk.jshell.Snippet;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeSINo")
    private Integer employeeSINo;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employeeId")
    private String employeeId;

    @Column(name = "designation")
    private String designation;

    @Column(name = "employee_password")
    private String employeePassword;

    @Column(name = "employee_status")
    @Enumerated(EnumType.STRING)
    private Status employeeStatus = Status.APPROVED;

    public enum Status{
        APPROVED,PENDING
    }

    public Employee() {
    }

    public Employee(Integer employeeSINo, String employeeName, String employeeId, String designation, String employeePassword) {
        this.employeeSINo = employeeSINo;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.designation = designation;
        this.employeePassword = employeePassword;
        this.employeeStatus = employeeStatus.APPROVED;

    }

    public Integer getEmployeeSINo() {
        return employeeSINo;
    }

    public void setEmployeeSINo(Integer employeeSINo) {
        this.employeeSINo = employeeSINo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public Status getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Status employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeSINo=" + employeeSINo +
                ", employeeName='" + employeeName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", designation='" + designation + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                ", employeeStatus='" + employeeStatus + '\'' +
                '}';
    }
}
