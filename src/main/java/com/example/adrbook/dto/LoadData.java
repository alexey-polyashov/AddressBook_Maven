package com.example.adrbook.dto;

import java.util.List;

public class LoadData {

    private String organizationCode;
    private String organizationName;
    private String organizationINN;
    private List<LoadDepartmentData> departmentList;

    public LoadData() {
    }

    public List<LoadDepartmentData> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<LoadDepartmentData> departmentList) {
        this.departmentList = departmentList;
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
