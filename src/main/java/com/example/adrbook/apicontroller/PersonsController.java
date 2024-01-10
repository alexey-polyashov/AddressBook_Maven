package com.example.adrbook.apicontroller;

import com.example.adrbook.dto.*;
import com.example.adrbook.service.DepartmentService;
import com.example.adrbook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/persons")
public class PersonsController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/", produces = "application/json")
    public List<PersonData> getAllPersons() {
        return personService.getAllEmployees();
    }

    @GetMapping(value = "/extended", produces = "application/json")
    public List<PersonDataExtended> getAllPersons(@RequestParam Boolean with_managers) {
        return personService.getAllEmployeesWithManagers();
    }

    @GetMapping(value = "/tabnumber/{tabNumber}", produces = "application/json")
    public List<PersonDataExtended> findByTabNumber(@PathVariable String tabNumber) {
        return personService.findByTabNumber(tabNumber);
    }

    @GetMapping(value = "/{personId}", produces = "application/json")
    public PersonDataExtended getById(@PathVariable Long personId) {
        return personService.getById(personId);
    }

    @DeleteMapping(value = "/{personId}")
    public void deletePerson(@PathVariable Long personId) {
        personService.delete(personId);
    }

    @PutMapping(value = "/", produces = "application/json")
    public Long updatePerson(@RequestBody UpdatePersonData updatePersonData) {
        return personService.update(updatePersonData);
    }

    @PostMapping(value = "/", produces = "application/json")
    public Long addDepartment(@RequestBody NewPersonData newPersonData) {
        return personService.add(newPersonData);
    }

}
