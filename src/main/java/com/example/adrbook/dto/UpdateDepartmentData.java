package com.example.adrbook.dto;

public class UpdateDepartmentData {
    private Long id;
    private String name;
    private String code;
    private Long parentId;
    private Long headId;


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHeadId(Long headId) {
        this.headId = headId;
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

    public Long getHeadId() {
        return headId;
    }

    public UpdateDepartmentData() {
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
