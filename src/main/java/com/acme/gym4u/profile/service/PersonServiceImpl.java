package com.acme.gym4u.profile.service;

import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.profile.domain.persistence.PersonRepository;
import com.acme.gym4u.profile.domain.service.PersonService;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import com.acme.gym4u.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private static final String ENTITY = "Person";

    private final PersonRepository personRepository;

    private final Validator validator;

    public PersonServiceImpl(PersonRepository personRepository, Validator validator) {
        this.personRepository = personRepository;
        this.validator = validator;
    }

    @Override
    public List<Profile> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Page<Profile> getAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    public Profile getById(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }

    @Override
    public Profile create(Profile profile) {

        // Constraints validation

        Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return personRepository.save(profile);
    }

    @Override
    public Profile update(Long personId, Profile request) {

        // Constraints validations
        Set<ConstraintViolation<Profile>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return personRepository.findById(personId).map(profile ->
                        personRepository.save(profile
                                .withName(request.getName())
                                .withLastName(request.getLastName())
                                .withEmail(request.getEmail())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }

    @Override
    public ResponseEntity<?> delete(Long personId) {
        return personRepository.findById(personId).map(profile -> {
            personRepository.delete(profile);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }
}
