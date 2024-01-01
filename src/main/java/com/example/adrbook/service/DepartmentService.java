package com.example.adrbook.service;

import com.example.adrbook.dto.*;
import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import com.example.adrbook.exception.NotFoundException;
import com.example.adrbook.repo.DepartmentRepo;
import com.example.adrbook.utility.DepartmentID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PersonEntityMapper personMapper;

    private List<DepartmentData> deepDepartmentsPass(List<Department> depList, Department parent){
        List<DepartmentData> depListDto = new ArrayList<>();
        for(Department d: depList){
            if(d.getParent().orElse(null) == parent) {
                DepartmentData depData = departmentMapper.toDepartmentData(d);
                depData.setDepartments(deepDepartmentsPass(depList, d));
                depListDto.add(depData);
            }
        }
        return depListDto;
    }

    private List<Long> getDepartmentsIDs(List<DepartmentID> depList, Long parentId){
        List<Long> depIDs= new LinkedList<>();
        for(DepartmentID d: depList){
            if(parentId.equals(d.getParentID())) {
                depIDs.add(d.getId());
                depIDs.addAll(getDepartmentsIDs(depList, d.getId()));
            }
        }
        return depIDs;
    }

    public DepartmentsList getDepartmentList(){
        List<Department> depList = departmentRepo.findAll();
        DepartmentsList depListDto = new DepartmentsList();
        depListDto.setDepartments(deepDepartmentsPass(depList, null));
        return  depListDto;
    }

    public DepartmentData getSubDepartmentsList(Long departmentId){
        Department department = departmentRepo.findDepartmentById(departmentId)
                .orElseThrow(()->new NotFoundException("Подразделение с id '" + departmentId + "' не найдено"));
        return  departmentMapper.toDepartmentData(department);
    }

    public List<Long> collectSunDepartmentsIDs(Long parentId){
        List<Department> depList = departmentRepo.findAll();
        List<DepartmentID> depIDList = depList.stream().map(p->departmentMapper.toDepartmentId(p)).collect(Collectors.toList());
        getDepartmentsIDs(depIDList, parentId);
        return  getDepartmentsIDs(depIDList, parentId);
    }

    public List<PersonData> getEmployees(Long departmentId, Boolean deeply){
        List<Long> departmentIDs = new LinkedList<>();
        if(deeply){
            departmentIDs = collectSunDepartmentsIDs(departmentId);
        }else{
            departmentIDs.add(departmentId);
        }
        List<PersonEntity> employees = departmentRepo.getEmployees(departmentIDs);
        return employees.stream()
                .map(personMapper::toPersonData)
                .collect(Collectors.toList());
    }

    public PersonData getHead(Long departmentId){
        return personMapper.toPersonData(departmentRepo.findDepartmentById(departmentId)
                .orElseThrow(()->new NotFoundException("Подразделение с id '" + departmentId + "' не найдено")).getHead().orElse(new PersonEntity()));
    }

}
