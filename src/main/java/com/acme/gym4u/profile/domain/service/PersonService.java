package com.acme.gym4u.profile.domain.service;

import com.acme.gym4u.profile.domain.model.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    List<Person> getAll();
    Page<Person> getAll(Pageable pageable);
    Person getById(Long personId);
    Person create(Person person);
    Person update(Long personId, Person request);
    ResponseEntity<?> delete(Long personId);
}
