package com.example.adrbook.apicontroller;

import com.example.adrbook.dto.DepartmentData;
import com.example.adrbook.dto.DepartmentsList;
import com.example.adrbook.dto.PersonData;
import com.example.adrbook.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/all", produces = "application/json")
    DepartmentsList getAllDepartments(){
        return departmentService.getDepartmentList();
    }

    @GetMapping(value = "/{departmentId}", produces = "application/json")
    DepartmentData getDepartment(@PathVariable Long departmentId){
        return departmentService.getSubDepartmentsList(departmentId);
    }

    @GetMapping(value = "/{departmentId}/employees", produces = "application/json")
    List<PersonData> getDepartmentEmployees(@PathVariable Long departmentId){
        return departmentService.getEmployees(departmentId, false);

    }

    @GetMapping(value = "/{departmentId}/allemployees", produces = "application/json")
    List<PersonData> getAllDepartmentEmployees(@PathVariable Long departmentId){
        return departmentService.getEmployees(departmentId, true);
    }
}
