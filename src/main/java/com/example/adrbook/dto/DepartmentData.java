package com.example.adrbook.dto;

import com.example.adrbook.entity.Department;

import java.util.List;

public class DepartmentData {
    private Long id;
    private String name;
    private String code;
    private Long parentId;
    private String parentName;
    private PersonData head;
    List<DepartmentData> subDepartments;

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

    public void setSubDepartments(List<DepartmentData> subDepartments) {
        this.subDepartments = subDepartments;
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

    public List<DepartmentData> getSubDepartments() {
        return subDepartments;
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
