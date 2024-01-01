package com.example.adrbook.dto;

public class FullTextResult {
    private String presentation;
    private String objectTypePresentation;
    private Long uid;
    private Integer dataType;

    public FullTextResult() {
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getObjectTypePresentation() {
        return objectTypePresentation;
    }

    public void setObjectTypePresentation(String objectTypePresentation) {
        this.objectTypePresentation = objectTypePresentation;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }


}
