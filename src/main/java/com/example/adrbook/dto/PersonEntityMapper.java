package com.example.adrbook.dto;

import com.example.adrbook.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonEntityMapper {
    @Mapping(target="managerName", expression = "java(p.getDepartment().getHeadFullName())")
    @Mapping(target="departmentName", expression = "java(p.getDepartment().getName())")
    @Mapping(target="departmentId", expression = "java(p.getDepartment().getId())")
    PersonData toPersonData(PersonEntity p);
}
