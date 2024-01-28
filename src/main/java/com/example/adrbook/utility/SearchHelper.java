package com.example.adrbook.utility;

import com.example.adrbook.dto.DepartmentData;
import com.example.adrbook.dto.PersonData;
import com.example.adrbook.service.DepartmentService;
import com.example.adrbook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchHelper {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PersonService personService;

    public Map<String, String> getSearchTags(){
        Map<String, String> tags = new HashMap<>();
        List<String> depNames = departmentService.findAllNames();
        List<String> persNames = personService.findAllNames();
        for(String name: depNames){
            tags.put(name, "department");
        }
        for(String name: persNames){
            tags.put(name, "person");
        }
        return tags;
    }

    public String getTypePresentation(String udentificationString){
        String result="";
        switch (udentificationString){
            case "person":
                result="Сотрудник";
                break;
            case "department":
                result="Подразделение";
                break;
            default:
        }
        return result;
    }
}
