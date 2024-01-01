package com.example.adrbook.dto;

import java.util.ArrayList;
import java.util.List;

public class DepartmentsList {
    List<DepartmentData> departments = new ArrayList<>();

    public DepartmentsList() {
    }

    public List<DepartmentData> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentData> departments) {
        this.departments = departments;
    }
}
