package com.example.adrbook.dto;

import java.util.List;

public class LoadDepartmentData {
    private String name;
    private String code;
    private Long parentCode;
    private Long headTabNumber;

    private List<LoadEmployeeData> emloyeeList;

    public LoadDepartmentData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getParentCode() {
        return parentCode;
    }

    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }

    public Long getHeadTabNumber() {
        return headTabNumber;
    }

    public void setHeadTabNumber(Long headTabNumber) {
        this.headTabNumber = headTabNumber;
    }

    public List<LoadEmployeeData> getEmloyeesList() {
        return emloyeeList;
    }

    public void setEmloyeesList(List<LoadEmployeeData> emloyeesList) {
        this.emloyeeList = emloyeesList;
    }
}
