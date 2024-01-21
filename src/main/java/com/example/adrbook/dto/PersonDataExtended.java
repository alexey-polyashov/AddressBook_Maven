package com.example.adrbook.dto;

public class PersonDataExtended {

    private Long id;
    private String departmentName;
    private Long departmentId;
    private String tabNumber;
    private String fullName;
    private String position;
    private String phoneNumber;
    private String cellPhone;
    private String email;
    private PersonData manager;
    private String birthDay;
    private Boolean chief;
    private String workSchedule;

    public PersonDataExtended() {
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean isChief() {
        return chief;
    }

    public void setChief(Boolean chief) {
        this.chief = chief;
    }

    public PersonData getManager() {
        return manager;
    }

    public void setManager(PersonData manager) {
        this.manager = manager;
    }

    public Boolean getChief() {
        return chief;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

    public String getManagerName() {
        return this.manager==null ? "" : this.manager.getFullName();
    }

    public String getManagerCellPhone() {
        return this.manager==null ? "" : this.manager.getCellPhone();
    }
    public String getManagerPhoneNumber() {
        return this.manager==null ? "" : this.manager.getPhoneNumber();
    }

    public String getManagerEmail() {
        return this.manager==null ? "" : this.manager.getEmail();
    }

    public String getManagerPosition() {
        return this.manager==null ? "" : this.manager.getPosition();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
 }
