package com.example.adrbook.service;

import com.example.adrbook.dto.*;
import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import com.example.adrbook.exception.NotFoundException;
import com.example.adrbook.repo.DepartmentRepo;
import com.example.adrbook.utility.DepartmentID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    private List<Department> expandDepartmentsList(List<Department> depList) {
        List<Department> parents = new ArrayList<>();
        for(Department d: depList){
            parents.addAll(getAllParents(d));

        }
        for(Department parent: parents){
            if(!depList.contains(parent)){
                depList.add(parent);
            }
        }
        return depList;
    }

    private List<Department> getAllParents(Department d) {
        List<Department> parents = new ArrayList<>();
        if(d.getParent().isPresent()){
            Department tempd = d.getParent().get();
            tempd.setEmployees(new ArrayList<>());
            parents.add(tempd);
            parents.addAll(getAllParents(d.getParent().get()));
        }
        return parents;
    }

    public DepartmentsList getDepartmentList(){
        List<Department> depList = departmentRepo.findAll();
        DepartmentsList depListDto = new DepartmentsList();
        depListDto.setDepartments(deepDepartmentsPass(depList, null));
        return  depListDto;
    }

    public DepartmentsList getDepartmentListWithEmployees(){
        List<Department> depList = departmentRepo.getDepartmentsAndEmployees();
        DepartmentsList depListDto = new DepartmentsList();
        depListDto.setDepartments(deepDepartmentsPass(depList, null));
        return  depListDto;
    }

    public DepartmentsList getDepartmentListWithEmployees(String searchtext){
        List<Department> depList = departmentRepo.getDepartmentsAndEmployees(searchtext);
        expandDepartmentsList(depList);
        DepartmentsList depListDto = new DepartmentsList();
        depListDto.setDepartments(deepDepartmentsPass(depList, null));
        return  depListDto;
    }

    public DepartmentData getSubDepartmentsList(Long departmentId, Boolean showEmployees){
        Department department = new Department();
        departmentRepo.findDepartmentById(departmentId)
                .orElseThrow(()->new NotFoundException("Подразделение с id '" + departmentId + "' не найдено"));
        if(showEmployees){
            departmentRepo.getDepartmentsAndEmployees(departmentId)
                    .orElseThrow(()->new NotFoundException("Подразделение с id '" + departmentId + "' не найдено"));
        }else{
            department = departmentRepo.findDepartmentById(departmentId)
                    .orElseThrow(()->new NotFoundException("Подразделение с id '" + departmentId + "' не найдено"));
        }

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
