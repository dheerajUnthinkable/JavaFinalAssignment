package com.harbilas.JavaAssignment.DTO;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "email")
    private String email;

    private List<Long> departmentIDs = new ArrayList<>();

    public EmployeeDTO() {

    }

    public EmployeeDTO(String name, String phoneNumber, LocalDate dob, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Long> getDepartmentIDs() {
        return departmentIDs;
    }

    public void setDepartmentIDs(List<Long> departmentIDs) {
        this.departmentIDs = departmentIDs;
    }
}
