package com.example.adrbook.dto;

import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import com.example.adrbook.exception.NotFoundException;
import com.example.adrbook.repo.DepartmentRepo;
import com.example.adrbook.repo.PersonEntityRepo;
import com.example.adrbook.utility.DepartmentID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {PersonEntityMapper.class})
public abstract class DepartmentMapper {

    @Autowired
    PersonEntityMapper personEntityMapper;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    PersonEntityRepo personEntityRepo;

    @Mapping(target = "parentName", expression = "java(dep.getParentName())")
    @Mapping(target = "parentId", expression = "java(dep.getParentId())")
    @Mapping(target = "parentCode", expression = "java(dep.getParentCode())")
    @Mapping(target = "head", expression = "java(this.getHead(dep))")
    public abstract DepartmentData toDepartmentData(Department dep);

    @Mapping(target="data_type", expression="java(com.example.adrbook.utility.DataType.DEPARTMENT)")
    @Mapping(target="parent", expression="java(this.getParent(depData.getParentId()))")
    public abstract Department toDepartment(DepartmentData depData);

    @Mapping(target="data_type", expression="java(com.example.adrbook.utility.DataType.DEPARTMENT)")
    @Mapping(target="parent", expression="java(this.getParent(depData.getParentId()))")
    @Mapping(target="head", expression="java(this.getPersonById(depData.getHeadId()))")
    public abstract Department toDepartment(NewDepartmentData depData);

    @Mapping(target = "parentID", expression = "java(dep.getParentId())")
    public abstract DepartmentID toDepartmentId(Department dep);

    protected PersonData getHead(Department dep) {
        if (dep.getHead().isEmpty()) {
            return new PersonData();
        }
        return personEntityMapper.toPersonData(dep.getHead().get());
    }

    protected Department getParent(Long parentId){
        if(parentId!=null) {
            return departmentRepo.findDepartmentById(parentId)
                    .orElseThrow(() -> new NotFoundException("Родитель с id - " + parentId + " не найден"));
        }else{
            return null;
        }
    }

    protected PersonEntity getPersonById(Long Id){
        if(Id!=null) {
            return personEntityRepo.findPersonById(Id)
                    .orElseThrow(() -> new NotFoundException("Руководитель с id - " + Id + " не найден"));
        }else{
            return null;
        }
    }

}
