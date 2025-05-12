package com.genspark.employee.employee;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Employee {
    @Id
    @SequenceGenerator(
            sequenceName = "employee_sequence",
            allocationSize = 1,
            name = "employee_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long employeeId;
    private String employeeName;
    private String employeeEmail;
    private LocalDate employeeDOB;
    @Transient
    private Integer employeeAge;

    public Employee(Long employeeId, String employeeName, String employeeEmail, LocalDate employeeDOB) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeDOB = employeeDOB;
    }

    public Employee(String employeeName, String employeeEmail, LocalDate employeeDOB) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeDOB = employeeDOB;
    }

    public Employee() {

    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public LocalDate getEmployeeDOB() {
        return employeeDOB;
    }

    public void setEmployeeDOB(LocalDate employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public Integer getEmployeeAge() {
        return Period.between(employeeDOB,LocalDate.now()).getYears();
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }
}
