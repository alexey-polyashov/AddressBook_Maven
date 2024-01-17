package com.example.adrbook.apicontroller;

import com.example.adrbook.dto.*;
import com.example.adrbook.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/", produces = "application/json")
    DepartmentsList getAllDepartments(@RequestParam(required = false) Optional<Boolean> employees, @RequestParam(required = false) Optional<Boolean> flat) {
        if(employees.orElse(false)){
            return departmentService.getDepartmentList(flat.orElse(false));
        }else {
            return departmentService.getDepartmentListWithEmployees(flat.orElse(false));
        }
    }

    @GetMapping(value = "/code/{departmentCode}", produces = "application/json")
    List<DepartmentData> findByCode(@PathVariable String departmentCode) {
        return departmentService.findByCode(departmentCode);
    }

    @GetMapping(value = "/{departmentId}", produces = "application/json")
    DepartmentData getDepartment(@PathVariable Long departmentId,@RequestParam(required = false) Optional<Boolean> employees) {
        return departmentService.getSubDepartmentsList(departmentId, employees.orElse(false));
    }

    @GetMapping(value = "/{departmentId}/employees", produces = "application/json")
    List<PersonData> getDepartmentEmployees(@PathVariable Long departmentId) {
        return departmentService.getEmployees(departmentId, false);
    }

    @GetMapping(value = "/{departmentId}/allemployees", produces = "application/json")
    List<PersonData> getAllDepartmentEmployees(@PathVariable Long departmentId) {
        return departmentService.getEmployees(departmentId, true);
    }

    @DeleteMapping(value="/{departmentId}")
    void deleteDepartment(@PathVariable Long departmentId){
        departmentService.delete(departmentId);
    }

    @PutMapping(value="/", produces = "application/json")
    Long updateDepartment(@RequestBody UpdateDepartmentData updateDepartmentData){
        return departmentService.update(updateDepartmentData);
    }

    @PostMapping(value="/", produces = "application/json")
    Long addDepartment(@RequestBody NewDepartmentData newDepartmentData){
        return departmentService.add(newDepartmentData);
    }

    @PostMapping(value="/{departmentId}/head/{headId}", produces = "application/json")
    void updateDepartmentHead(@PathVariable Long departmentId, @PathVariable Long headId){
        departmentService.setHead(departmentId, headId);
    }

}
