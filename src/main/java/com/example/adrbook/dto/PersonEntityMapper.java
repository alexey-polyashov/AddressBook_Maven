package com.example.adrbook.dto;

import com.example.adrbook.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonEntityMapper {

    @Mapping(target="departmentName", expression = "java(p.getDepartment().getName())")
    @Mapping(target="departmentId", expression = "java(p.getDepartment().getId())")
    PersonData toPersonData(PersonEntity p);
    @Mapping(target="managerName", expression = "java(p.getManagerFullName())")
    @Mapping(target="managerPhoneNumber", expression = "java(p.getManagerPhoneNumber())")
    @Mapping(target="managerEmail", expression = "java(p.getManagerEmail())")
    @Mapping(target="departmentName", expression = "java(p.getDepartment().getName())")
    @Mapping(target="departmentId", expression = "java(p.getDepartment().getId())")
    PersonDataExtended toPersonDataExtended(PersonEntity p);
}
