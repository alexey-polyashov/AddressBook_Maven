package com.example.adrbook.entity;

import com.example.adrbook.utility.DataType;
import jakarta.persistence.*;

@Entity
public class PersonEntity {

    @GeneratedValue
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(name = "data_type",columnDefinition = "varchar(255) default 'PERSON'")
    private DataType dataType;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "tab_number")
    private String tabNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String position;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column
    private String email;

    public PersonEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getTabNumber() {
        return tabNumber;
    }

    public void setTabNumber(String tabNumber) {
        this.tabNumber = tabNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
