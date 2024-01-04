package com.example.adrbook.service;

import com.example.adrbook.dto.PersonData;
import com.example.adrbook.dto.PersonDataExtended;
import com.example.adrbook.dto.PersonEntityMapper;
import com.example.adrbook.exception.NotFoundException;
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

    public PersonData getPersonInfo(Long personId) {
        return personMapper.toPersonData(personRepo.findPersonById(personId).orElseThrow(
                ()->new NotFoundException("Person with id " + personId + " not found")));
    }
    public PersonDataExtended getExtendedPersonInfo(Long personId) {
        return personMapper.toPersonDataExtended(personRepo.findPersonById(personId).orElseThrow(
                ()->new NotFoundException("Person with id " + personId + " not found")));
    }

    public List<PersonData> getAllEmployees(){
        return personRepo.findAll()
                .stream()
                .map(p->personMapper.toPersonData(p))
                .collect(Collectors.toList());
    }
}
