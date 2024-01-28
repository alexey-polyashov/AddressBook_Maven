package com.example.adrbook.service;

import com.example.adrbook.dto.*;
import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import com.example.adrbook.exception.NotFoundException;
import com.example.adrbook.repo.DepartmentRepo;
import com.example.adrbook.repo.PersonEntityRepo;
import com.example.adrbook.utility.DataType;
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
    @Autowired
    private PersonEntityRepo personEntityRepo;
    @Autowired
    private ServiceInfoService serviceInfoService;

    private List<DepartmentData> deepDepartmentsPass(Set<Department> depList, Department parent, boolean withEmployees){
        List<Department> tempDepList = new ArrayList<>(depList);
        return deepDepartmentsPass(tempDepList, parent, withEmployees);
    }

    private List<DepartmentData> deepDepartmentsPass(List<Department> depList, Department parent, boolean withEmployees){
        List<DepartmentData> depListDto = new ArrayList<>();
        for(Department d: depList){
            if(d.getParent().orElse(null) == parent) {
                DepartmentData depData = departmentMapper.toDepartmentData(d);
                if(withEmployees) {
                    depData.getEmployees().sort((s1, s2) -> {
                        int res = s2.isChief().compareTo(s1.isChief());
                        if (res == 0) {
                            res = s1.getPosition().compareTo(s2.getPosition());
                        }
                        if (res == 0) {
                            res = s1.getFullName().compareTo(s2.getFullName());
                        }
                        return res;
                    });
                }else{
                    depData.getEmployees().clear();
                }
                depData.setDepartments(deepDepartmentsPass(depList, d, withEmployees));
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

    private List<Department> expandDepartmentsList(Set<Department> depList) {
        List<Department> tempDepList = new ArrayList<>(depList);
        return expandDepartmentsList(tempDepList);
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

    public DepartmentsList getDepartmentList(boolean flat){
        List<Department> depList = departmentRepo.findAll();
        DepartmentsList depListDto = new DepartmentsList();
        if(!flat) {
            depListDto.setDepartments(deepDepartmentsPass(depList, null, false));
        }else{
            depListDto.setDepartments(depList.stream()
                    .map(departmentMapper::toDepartmentData)
                    .collect(Collectors.toList()));
        }
        return  depListDto;
    }

    public DepartmentsList getDepartmentListWithEmployees(Boolean flat){
        Set<Department> depList = departmentRepo.getDepartmentsAndEmployees();
        DepartmentsList depListDto = new DepartmentsList();
        if(!flat) {
            depListDto.setDepartments(deepDepartmentsPass(depList, null, true));
        }else{
            depListDto.setDepartments(depList.stream()
                    .map(departmentMapper::toDepartmentData)
                    .collect(Collectors.toList()));
        }
        return  depListDto;
    }

    public DepartmentsList getDepartmentListWithEmployees(String searchtext){
        Set<Department> depList = departmentRepo.getDepartmentsAndEmployees(searchtext.toLowerCase());
        List<Department> departmentsArrayList =  expandDepartmentsList(depList);
        DepartmentsList depListDto = new DepartmentsList();
        depListDto.setDepartments(deepDepartmentsPass(departmentsArrayList, null, true));
        return  depListDto;
    }

    public DepartmentsList getDepartmentListByNameWithEmployees(String searchtext){
        Set<Department> allDepartments = departmentRepo.getDepartmentsAndEmployees();
        Set<Department> findedList = departmentRepo.getDepartmentsByNameAndEmployees(searchtext.toLowerCase());
        DepartmentsList depListDto = new DepartmentsList();
        for(Department department: findedList) {
            DepartmentData curDep = departmentMapper.toDepartmentData(department);
            curDep.setDepartments(new ArrayList<>());
            depListDto.getDepartments().add(curDep);
            curDep.getDepartments().addAll(deepDepartmentsPass(allDepartments, department, true));
        }
        List<Department> departmentsArrayList =  expandDepartmentsList(findedList);
//        depListDto.setDepartments(deepDepartmentsPass(departmentsArrayList, null, true));
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

    public void delete(Long departmentId){
        Department department = departmentRepo.findDepartmentById(departmentId)
                .orElseThrow(()->new NotFoundException("Подразделение с id '" + departmentId + "' не найдено"));
        departmentRepo.delete(department);
        serviceInfoService.setUpdateData();
    }

    public Long add(NewDepartmentData newDepartmentData){
        Department newDepartment = departmentMapper.toDepartment(newDepartmentData);
        departmentRepo.save(newDepartment);
        serviceInfoService.setUpdateData();
        return newDepartment.getId();
    }
    public Department addRaw(NewDepartmentData newDepartmentData){
        Department newDepartment = departmentMapper.toDepartment(newDepartmentData);
        departmentRepo.save(newDepartment);
        serviceInfoService.setUpdateData();
        return newDepartment;
    }

    public List<DepartmentData> findByCode(String code){
        List<Department> departments = departmentRepo.findDepartmentByCode(code);
        return departments.stream()
                .map(p->departmentMapper.toDepartmentData(p))
                .collect(Collectors.toList());
    }

    public Long update(UpdateDepartmentData departmentData){

        Long id = departmentData.getId();
        PersonEntity head = null;
        Department parent = null;
        Long parentId = departmentData.getParentId();
        Long headId = departmentData.getHeadId();

        Department department = departmentRepo.findDepartmentById(id)
                .orElseThrow(()->new NotFoundException("Подразделение с id '" + id + "' не найдено"));
        if(parentId!=null) {
            parent = departmentRepo.findDepartmentById(parentId)
                    .orElseThrow(() -> new NotFoundException("Родительское подразделение с id '" + parentId + "' не найдено"));
        }
        if(headId!=null) {
            head = personEntityRepo.findPersonById(headId)
                    .orElseThrow(() -> new NotFoundException("Руководитель с id - " + headId + " не найден"));
        }

        department.setHead(head);
        department.setName(departmentData.getName());
        department.setCode(departmentData.getCode());
        department.setParent(parent);
        department.setHead(head);

        departmentRepo.save(department);
        serviceInfoService.setUpdateData();

        return department.getId();
    }

    public void setHead(Long departmentId, Long headId){
        Department department = departmentRepo.findDepartmentById(departmentId)
                .orElseThrow(()->new NotFoundException("Подразделение с id '" + departmentId + "' не найдено"));
        PersonEntity head = null;
        if(headId!=null) {
            head = personEntityRepo.findPersonById(headId)
                    .orElseThrow(() -> new NotFoundException("Руководитель с id - " + headId + " не найден"));
        }
        department.setHead(head);
        departmentRepo.save(department);
        serviceInfoService.setUpdateData();
    }

    public void setHeadByTabNumber(Long departmentId, String headTabNumber) {
        Department department = departmentRepo.findDepartmentById(departmentId)
                .orElseThrow(()->new NotFoundException("Подразделение с id '" + departmentId + "' не найдено"));
        PersonEntity head = null;
        if(headTabNumber!=null) {
            head = personEntityRepo.findPersonEntityByTabNumber(headTabNumber)
                    .orElseThrow(() -> new NotFoundException("Руководитель с табельным номером - " + headTabNumber + " не найден"));
        }
        department.setHead(head);
        departmentRepo.save(department);
        serviceInfoService.setUpdateData();
    }

    public List<String> findAllNames() {
        return departmentRepo.findAll().stream()
                .map(departmentMapper::toDepartmentNames)
                .collect(Collectors.toList());
    }
}
