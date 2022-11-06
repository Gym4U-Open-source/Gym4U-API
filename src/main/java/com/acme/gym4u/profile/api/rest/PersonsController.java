package com.acme.gym4u.profile.api.rest;

import com.acme.gym4u.profile.domain.service.PersonService;
import com.acme.gym4u.profile.mapping.PersonMapper;
import com.acme.gym4u.profile.resource.CreatePersonResource;
import com.acme.gym4u.profile.resource.PersonResource;
import com.acme.gym4u.profile.resource.UpdatePersonResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/persons", produces = "application/json")
public class PersonsController {
    private final PersonService personService;
    private final PersonMapper mapper;

    public PersonsController(PersonService personService, PersonMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }


    @GetMapping
    public Page<PersonResource> getAllPersons(Pageable pageable) {
        // GET METHOD IMPLEMENTED
        return mapper.modelListPage(personService.getAll(), pageable);
    }

    @GetMapping("{personId}")
    public PersonResource getPersonById(@PathVariable Long personId) {
        return mapper.toResource(personService.getById(personId));
    }

    @PostMapping
    public ResponseEntity<PersonResource> createPerson(@RequestBody CreatePersonResource resource) {
        System.out.println(resource.getEmail());
        return new ResponseEntity<>(mapper.toResource(
                personService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

    @PutMapping("{personId}")
    public PersonResource updatePerson(
            // UPDATE METHOD IMPLEMENTED
            @PathVariable Long personId,
            @RequestBody UpdatePersonResource resource) {
        return mapper.toResource(
                personService.update(personId,
                        mapper.toModel(resource)));
    }

    @DeleteMapping("{personId}")
    public ResponseEntity<?> deletePerson(
            @PathVariable Long personId) {
        // DELETE METHOD IMPLEMENTED
        return personService.delete(personId);
    }
}
