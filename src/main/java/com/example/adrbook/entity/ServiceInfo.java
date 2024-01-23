package com.example.adrbook.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class ServiceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    private Date updateDate;


    public ServiceInfo() {
    }

    public Long getId() {
        return id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
