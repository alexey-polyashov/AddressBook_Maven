package com.example.adrbook.service;

import com.example.adrbook.dto.*;
import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import com.example.adrbook.exception.NotFoundException;
import com.example.adrbook.repo.DepartmentRepo;
import com.example.adrbook.repo.PersonEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonEntityRepo personRepo;
    @Autowired
    private PersonEntityMapper personMapper;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ServiceInfoService serviceInfoService;

    public PersonData getPersonInfo(Long personId) {
        return personMapper.toPersonData(personRepo.findPersonById(personId).orElseThrow(
                ()->new NotFoundException("Сотрудник с id " + personId + " не найден")));
    }
    public PersonDataExtended getExtendedPersonInfo(Long personId) {
        return personMapper.toPersonDataExtended(personRepo.findPersonById(personId).orElseThrow(
                ()->new NotFoundException("Сотрудник с id " + personId + " не найден")));
    }

    public PersonDataExtended getExtendedPersonInfo(PersonEntity person) {
        return personMapper.toPersonDataExtended(person);
    }

    public List<PersonData> getAllEmployees(){
        return personRepo.findAll()
                .stream()
                .map(p->personMapper.toPersonData(p))
                .collect(Collectors.toList());
    }

    public List<Department> findSubOrdinateDepartments(Long employeeId){
        return departmentRepo.findByHeadId(employeeId);
    }

    public List<PersonDataExtended> getAllEmployeesWithManagers() {
        return personRepo.findAll()
                .stream()
                .map(p->personMapper.toPersonDataExtended(p))
                .collect(Collectors.toList());
    }

    public List<PersonDataExtended> findByTabNumber(String tabNumber) {
        return personRepo.findPersonEntityByTabNumber(tabNumber)
                .stream()
                .map(p->personMapper.toPersonDataExtended(p))
                .collect(Collectors.toList());
    }

    public PersonDataExtended getById(Long personId) {
        return personMapper.toPersonDataExtended(personRepo.findPersonById(personId)
                .orElseThrow(()->new NotFoundException("Сотрудник с id " + personId + " не найден")));
    }

    public void delete(Long personId) {
        PersonEntity p = personRepo.findPersonById(personId)
                .orElseThrow(()->new NotFoundException("Сотрудник с id " + personId + " не найден"));        //check - is an employee a head of department
        List<Department> subordinateDepartments = findSubOrdinateDepartments(personId);
        for (Department d : subordinateDepartments){
            //clear head of department
            d.setHead(null);
            departmentRepo.save(d);
        }
        personRepo.delete(p);
        serviceInfoService.setUpdateData();
    }

    public Long update(UpdatePersonData updatePersonData) {
        Long personId = updatePersonData.getId();
        PersonEntity p = personRepo.findPersonById(personId)
                .orElseThrow(()->new NotFoundException("Сотрудник с id " + personId + " не найден"));
        Long departmentId = updatePersonData.getDepartmentId();
        p.setDepartment(departmentRepo.findDepartmentById(departmentId)
                .orElseThrow(()->new NotFoundException("Подразделение с id " + personId + " не найдено")));
        p.setTabNumber(updatePersonData.getTabNumber());
        p.setFullName(updatePersonData.getFullName());
        p.setPosition(updatePersonData.getPosition());
        p.setEmail(updatePersonData.getEmail());
        p.setPhoneNumber(updatePersonData.getPhoneNumber());
        p.setCellPhone(updatePersonData.getCellPhone());
        personRepo.save(p);
        serviceInfoService.setUpdateData();
        return p.getId();
    }

    public Long add(NewPersonData newPersonData) {
            PersonEntity newPerson = personMapper.toPerson(newPersonData);
            personRepo.save(newPerson);
            serviceInfoService.setUpdateData();
            return newPerson.getId();
    }

    public List<String> findAllNames() {
        return personRepo.findAll()
                .stream()
                .map(personMapper::toPersonName)
                .collect(Collectors.toList());
    }
}
