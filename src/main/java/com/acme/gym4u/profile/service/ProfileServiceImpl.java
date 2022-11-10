package com.acme.gym4u.profile.service;

import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.profile.domain.persistence.ProfileRepository;
import com.acme.gym4u.profile.domain.service.ProfileService;
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
public class ProfileServiceImpl implements ProfileService {

    private static final String ENTITY = "Person";

    private final ProfileRepository profileRepository;

    private final Validator validator;

    public ProfileServiceImpl(ProfileRepository profileRepository, Validator validator) {
        this.profileRepository = profileRepository;
        this.validator = validator;
    }

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Page<Profile> getAll(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Profile getById(Long personId) {
        return profileRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }

    @Override
    public Profile create(Profile profile) {

        // Constraints validation

        Set<ConstraintViolation<Profile>> violations = validator.validate(profile);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Long personId, Profile request) {

        // Constraints validations
        Set<ConstraintViolation<Profile>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return profileRepository.findById(personId).map(profile ->
                        profileRepository.save(profile
                                .withName(request.getName())
                                .withLastName(request.getLastName())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }

    @Override
    public ResponseEntity<?> delete(Long personId) {
        return profileRepository.findById(personId).map(profile -> {
            profileRepository.delete(profile);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }
}