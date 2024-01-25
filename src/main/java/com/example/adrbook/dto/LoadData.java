package com.example.adrbook.dto;

import java.util.List;
import java.util.Map;

public class LoadData {

    private String organizationCode;
    private String organizationName;
    private String organizationINN;
    private Map<String, LoadDepartmentData> departmentList;
    private Map<String, LoadEmployeeData> employeeList;

    public LoadData() {
    }

    public Map<String, LoadDepartmentData> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(Map<String, LoadDepartmentData> departmentList) {
        this.departmentList = departmentList;
    }

    public Map<String, LoadEmployeeData> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Map<String, LoadEmployeeData> employeeList) {
        this.employeeList = employeeList;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationINN() {
        return organizationINN;
    }

    public void setOrganizationINN(String organizationINN) {
        this.organizationINN = organizationINN;
    }
}
