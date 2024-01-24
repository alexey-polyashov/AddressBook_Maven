package com.example.adrbook.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ServiceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    private Timestamp updateDate;


    public ServiceInfo() {
    }

    public Long getId() {
        return id;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
