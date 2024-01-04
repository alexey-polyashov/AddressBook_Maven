package com.example.adrbook.entity;

import com.example.adrbook.utility.DataType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class FullTextIndex {
    @Id
    @GeneratedValue()
    @Column(nullable = false)
    private Long id;

    @Column
    private String text;

    @Column(name = "data_type")
    private DataType dataType;

    @Column
    private Long uid;

    @Column
    private String presentation;

    public FullTextIndex() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
}
