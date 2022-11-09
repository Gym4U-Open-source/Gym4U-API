package com.acme.gym4u.profile.api.rest;

import com.acme.gym4u.profile.domain.service.PersonService;
import com.acme.gym4u.profile.mapping.ProfileMapper;
import com.acme.gym4u.profile.resource.CreateProfileResource;
import com.acme.gym4u.profile.resource.ProfileResource;
import com.acme.gym4u.profile.resource.UpdateProfileResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/persons", produces = "application/json")
public class PersonsController {
    private final PersonService personService;
    private final ProfileMapper mapper;

    public PersonsController(PersonService personService, ProfileMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }


    @GetMapping
    public Page<ProfileResource> getAllPersons(Pageable pageable) {
        // GET METHOD IMPLEMENTED
        return mapper.modelListPage(personService.getAll(), pageable);
    }

    @GetMapping("{personId}")
    public ProfileResource getPersonById(@PathVariable Long personId) {
        return mapper.toResource(personService.getById(personId));
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createPerson(@RequestBody CreateProfileResource resource) {
        System.out.println(resource.getEmail());
        return new ResponseEntity<>(mapper.toResource(
                personService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

    @PutMapping("{personId}")
    public ProfileResource updatePerson(
            // UPDATE METHOD IMPLEMENTED
            @PathVariable Long personId,
            @RequestBody UpdateProfileResource resource) {
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
