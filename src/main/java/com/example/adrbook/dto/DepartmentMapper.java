package com.example.adrbook.dto;

import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import com.example.adrbook.utility.DepartmentID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {PersonEntityMapper.class})
public abstract class DepartmentMapper {

    @Autowired
    PersonEntityMapper personEntityMapper;

    @Mapping(target="parentName", expression = "java(dep.getParentName())")
    @Mapping(target="parentId", expression = "java(dep.getParentId())")
    @Mapping(target="head", expression = "java(this.getHead(dep))")
    public abstract DepartmentData toDepartmentData(Department dep);
    public abstract Department toDepartment(DepartmentData depData);
    @Mapping(target="parentID", expression = "java(dep.getParentId())")
    public abstract DepartmentID toDepartmentId(Department dep);

    protected PersonData getHead(Department dep){
        if(dep.getHead().isEmpty()){
            return new PersonData();
        }
        return personEntityMapper.toPersonData(dep.getHead().get());
    }
}
