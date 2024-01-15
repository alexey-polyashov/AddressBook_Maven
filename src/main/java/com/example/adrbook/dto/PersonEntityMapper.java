package com.example.adrbook.dto;

import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import com.example.adrbook.exception.NotFoundException;
import com.example.adrbook.repo.DepartmentRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class PersonEntityMapper {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Mapping(target = "departmentName", expression = "java(p.getDepartment().getName())")
    @Mapping(target = "departmentId", expression = "java(p.getDepartment().getId())")
    @Mapping(target = "birthDay", source = "birthDay", dateFormat = "dd MMMM")
    @Mapping(target = "manager", expression = "java(p.isManager())")
    public abstract PersonData toPersonData(PersonEntity p);

    @Mapping(target = "managerName", expression = "java(p.getManagerFullName())")
    @Mapping(target = "managerPhoneNumber", expression = "java(p.getManagerPhoneNumber())")
    @Mapping(target = "managerCellPhone", expression = "java(p.getManagerCellPhone())")
    @Mapping(target = "managerEmail", expression = "java(p.getManagerEmail())")
    @Mapping(target = "departmentName", expression = "java(p.getDepartment().getName())")
    @Mapping(target = "departmentId", expression = "java(p.getDepartment().getId())")
    @Mapping(target = "birthDay", source = "birthDay", dateFormat = "dd MMMM")
    @Mapping(target = "manager", expression = "java(p.isManager())")
    public abstract PersonDataExtended toPersonDataExtended(PersonEntity p);

    @Mapping(target="dataType", expression="java(com.example.adrbook.utility.DataType.PERSON)")
    @Mapping(target="department", expression="java(this.getDepartment(newPersonData.getDepartmentId()))")
    public abstract PersonEntity toPerson(NewPersonData newPersonData);

    protected Department getDepartment(Long depId){
        if(depId!=null) {
            return departmentRepo.findDepartmentById(depId)
                    .orElseThrow(() -> new NotFoundException("Родитель с id - " + depId + " не найден"));
        }else{
            return null;
        }
    }

    protected String getManagerEmail(PersonEntity person) {
        return Objects.toString(person.getManagerEmail(), "Отсутствует");
    }

    protected String getManagerFullName(PersonEntity person) {
        return Objects.toString(person.getManagerFullName(), "Отсутствует");
    }

    protected String getManagerPhoneNumber(PersonEntity person) {
        return Objects.toString(person.getManagerPhoneNumber(), "Отсутствует");
    }

}
