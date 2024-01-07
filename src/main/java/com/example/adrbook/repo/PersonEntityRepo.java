package com.example.adrbook.repo;

import com.example.adrbook.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonEntityRepo extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findAll();

    List<PersonEntity> findPersonByEmail(String email);

    List<PersonEntity> findPersonByFullName(String name);

    Optional<PersonEntity> findPersonById(Long id);

    Optional<PersonEntity> findPersonEntityByTabNumber(String tabNumber);
}
