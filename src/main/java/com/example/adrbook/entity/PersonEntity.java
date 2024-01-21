package com.example.adrbook.entity;

import com.example.adrbook.utility.DataType;
import javax.persistence.*;

import java.sql.Date;
import java.util.Optional;

@Entity
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(name = "data_type", columnDefinition = "varchar(255) default 'P'")
    private DataType dataType;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "tab_number")
    private String tabNumber;
    @Column(name = "full_name")
    private String fullName;
    @Column
    private String position;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "cell_phone")
    private String cellPhone;
    @Column
    private String email;
    @Column
    private Date birthDay;
    @Column
    private String workSchedule;

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public PersonEntity() {
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getTabNumber() {
        return tabNumber;
    }

    public void setTabNumber(String tabNumber) {
        this.tabNumber = tabNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

    public PersonEntity getManager() {
        Department currDep = this.getDepartment();
        PersonEntity manager = null;
        while (manager == null) {
            if (currDep != null) {
                manager = currDep.getHead().orElse(null);
            } else {
                break;
            }
            if(manager == this || manager==null){
                manager=null;
                Optional<Department> tempDep = currDep.getParent();
                if(tempDep.isPresent()) {
                    currDep = tempDep.get();
                }else{
                    break;
                }
            }
        }
        return manager;
    }

    public Boolean isManager(){
        return this.getDepartment().getHead().orElse(null) == this;
    }

}
