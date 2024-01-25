package com.example.adrbook.utility;

import com.example.adrbook.dto.*;
import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import com.example.adrbook.exception.NotFoundException;
import com.example.adrbook.repo.DepartmentRepo;
import com.example.adrbook.repo.PersonEntityRepo;
import com.example.adrbook.service.DepartmentService;
import com.example.adrbook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class LoadDataManager {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonEntityRepo personRepo;


    public String load(LoadData loadData) {

        int passedDepartments = 0;
        int passedEmployees = 0;
        Map<String, LoadDepartmentData> depList = loadData.getDepartmentList();
        Map<String, LoadEmployeeData> empList = loadData.getEmployeeList();

        //proceed departments
        for(Map.Entry<String,LoadDepartmentData> element: depList.entrySet()){
            LoadDepartmentData loadDep = element.getValue();
            Department department = getDepartmentByCode(element.getKey(), loadDep, depList);
            if(
                    !(Objects.equals(department.getCode(), loadDep.getCode())
                            && Objects.equals(department.getName(), loadDep.getName())
                    )
            ){
                department.setCode(loadDep.getCode());
                department.setName(loadDep.getName());
                departmentRepo.save(department);
            }
            passedDepartments++;
        }

        //proceed employees
        for(Map.Entry<String,LoadEmployeeData> element: empList.entrySet()){
            LoadEmployeeData loadEmp = element.getValue();
            String depCode = loadEmp.getDepartmentCode();
            LoadDepartmentData emplDep = depList.get(loadEmp.getDepartmentCode());
            if(emplDep==null){
                throw new NotFoundException("Подразделение сотрудника с кодом " + depCode + " не найдено во входящих данных");
            }
            Department department = getDepartmentByCode(depCode, emplDep, depList);
            PersonEntity person = getPersonByCode(element.getKey(), loadEmp, department, empList);
            if(
                    !(Objects.equals(person.getTabNumber(), loadEmp.getTabNumber())
                            && Objects.equals(person.getFullName(), loadEmp.getFullName())
                            && Objects.equals(person.getEmail(), loadEmp.getEmail())
                            && Objects.equals(person.getPhoneNumber(), loadEmp.getPhoneNumber())
                            && Objects.equals(person.getBirthDay(), loadEmp.getBirthDay())
                            && Objects.equals(person.getCellPhone(), loadEmp.getCellPhone())
                            && Objects.equals(person.getPosition(), loadEmp.getPosition())
                            && Objects.equals(person.getWorkSchedule(), loadEmp.getWorkSchedule())
                            && Objects.equals(person.getDepartment().getId(), department.getId())
                    )
            ){
                fillPerson(loadEmp, department, person);
            }
            passedEmployees++;
        }

        //proceed department head
        for(Map.Entry<String,LoadDepartmentData> element: depList.entrySet()){
            LoadDepartmentData loadDep = element.getValue();
            Department department = getDepartmentByCode(element.getKey(), loadDep, depList);
            PersonEntity person = personRepo.findPersonEntityByTabNumber(loadDep.getHeadTabNumber()).
                    orElseThrow(()->new NotFoundException("Руководитель с табельным номером " + loadDep.getHeadTabNumber() + " не найден"));
            if(department.getHead().isEmpty() && !loadDep.getHeadTabNumber().isEmpty()
                    || department.getHead().isPresent() && loadDep.getHeadTabNumber().isEmpty()
                    || !Objects.equals(department.getHead().get().getTabNumber(), loadDep.getHeadTabNumber())
            ){
                department.setHead(person);
                departmentRepo.save(department);
            }
        }

        //delete departments
        List<Department> fullDepList = departmentRepo.findAll();
        List<Long> deleteList = new ArrayList<>();

        for(Department element: fullDepList){
            if(depList.get(element.getCode())==null){
                deleteList.add(element.getId());
            }
        }
        for(Long depId:deleteList){
            try {
                departmentRepo.deleteById(depId);
            } catch (Exception e){
                continue;
            }
        }

        //finish
        return "Обработано подразделений - " + passedDepartments + ", сотрудников - " + passedEmployees;
    }

    private PersonEntity getPersonByCode(String key, LoadEmployeeData loadEmp, Department department, Map<String, LoadEmployeeData> empList) {
        Optional<PersonEntity> personOpt = personRepo.findPersonEntityByTabNumber(key);
        if(personOpt.isEmpty()){
            PersonEntity person = new PersonEntity();
            fillPerson(loadEmp, department, person);
            return person;
        }else{
            return personOpt.get();
        }
    }

    private void fillPerson(LoadEmployeeData loadEmp, Department department, PersonEntity person) {
        person.setTabNumber(loadEmp.getTabNumber());
        person.setFullName(loadEmp.getFullName());
        person.setEmail(loadEmp.getEmail());
        person.setPhoneNumber(loadEmp.getPhoneNumber());
        person.setBirthDay(loadEmp.getBirthDay());
        person.setCellPhone(loadEmp.getCellPhone());
        person.setPosition(loadEmp.getPosition());
        person.setWorkSchedule(loadEmp.getWorkSchedule());
        person.setDepartment(department);
        personRepo.save(person);
    }

    private Department getDepartmentByCode(String keyCode, LoadDepartmentData loadDepartmentData, Map<String, LoadDepartmentData> depList){
        List<Department> dep = departmentRepo.findDepartmentByCode(keyCode);
        if(dep.isEmpty()){
            NewDepartmentData newDepartmentData = new NewDepartmentData();
            newDepartmentData.setCode(loadDepartmentData.getCode());
            newDepartmentData.setName(loadDepartmentData.getName());
            if(!loadDepartmentData.getParentCode().isEmpty()){
                LoadDepartmentData parentDepartmentData = depList.get(loadDepartmentData.getParentCode());
                if(parentDepartmentData==null){
                    throw new NotFoundException("Родительское подразделение с кодом " + loadDepartmentData.getParentCode() + " не найдено во входящих данных");
                }
                Department newParent = getDepartmentByCode(loadDepartmentData.getParentCode(), parentDepartmentData, depList);
                newDepartmentData.setParentId(newParent.getId());
            }
            return departmentService.addRaw(newDepartmentData);
        }else{
            if(dep.size()>1){
                boolean f=true;
                for(int idx=1; idx<=dep.size(); idx++ ){
                    departmentRepo.delete(dep.get(idx));
                }
            }
            return dep.get(0);
        }
    }
}
