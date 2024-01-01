package com.example.adrbook.dto;

import java.util.List;

public class DepartmentData {
    private Long id;
    private String name;
    private String code;
    private Long parentId;
    private String parentName;
    private PersonData head;
    List<DepartmentData> departments;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHead(PersonData head) {
        this.head = head;
    }

    public void setDepartments(List<DepartmentData> subDepartments) {
        this.departments = subDepartments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public PersonData getHead() {
        return head;
    }

    public List<DepartmentData> getDepartments() {
        return departments;
    }

    public DepartmentData() {
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
