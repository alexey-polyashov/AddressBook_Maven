package com.example.adrbook.entity;

import com.example.adrbook.utility.DataType;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(columnDefinition = "varchar(255) default 'DEP'")
    private DataType data_type;

    @Column
    private String code;

    @Column
    private String name;

    @ManyToOne
    private Department parent;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<PersonEntity> employees;

    @OneToOne
    @JoinColumn(name = "head_id")
    private PersonEntity head;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataType getData_type() {
        return data_type;
    }

    public void setData_type(DataType data_type) {
        this.data_type = data_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Department> getParent() {
        return Optional.ofNullable(parent);
    }

    public String getParentName() {
        if (getParent().isEmpty()) {
            return "";
        }
        return getParent().get().getName();
    }

    public Long getParentId() {
        if (getParent().isEmpty()) {
            return 0L;
        }
        return getParent().get().getId();
    }

    public String getHeadFullName() {
        if (getHead().isEmpty()) {
            return "";
        }
        return getHead().get().getFullName();
    }

    public String getHeadPhoneNumber() {
        if (getHead().isEmpty()) {
            return "";
        }
        return getHead().get().getPhoneNumber();
    }

    public String getHeadEmail() {
        if (getHead().isEmpty()) {
            return "";
        }
        return getHead().get().getEmail();
    }
    public Long getHeadId() {
        if (getHead().isEmpty()) {
            return 0L;
        }
        return getHead().get().getId();
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public List<PersonEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<PersonEntity> employees) {
        this.employees = employees;
    }

    public Optional<PersonEntity> getHead() {
        return Optional.ofNullable(head);
    }

    public void setHead(PersonEntity head) {
        this.head = head;
    }
}
