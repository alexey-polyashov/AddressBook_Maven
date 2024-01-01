package com.example.adrbook.service;

import com.example.adrbook.dto.PersonData;
import com.example.adrbook.dto.PersonEntityMapper;
import com.example.adrbook.exception.NotFoundException;
import com.example.adrbook.repo.PersonEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonEntityRepo personRepo;
    @Autowired
    private PersonEntityMapper personMapper;

    public PersonData getPersonInfo(Long personId) throws NotFoundException {
        return personMapper.toPersonData(personRepo.findPersonById(personId).orElseThrow(
                ()->new NotFoundException("Person with id " + personId + " not found")));
    }
}
