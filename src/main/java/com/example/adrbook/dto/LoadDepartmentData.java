package com.example.adrbook.dto;

import java.util.List;

public class LoadDepartmentData {
    private String name;
    private String code;
    private String parentCode;
    private String headTabNumber;

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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getHeadTabNumber() {
        return headTabNumber;
    }

    public void setHeadTabNumber(String headTabNumber) {
        this.headTabNumber = headTabNumber;
    }

}
