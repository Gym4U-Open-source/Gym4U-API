package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import com.acme.gym4u.fitness.domain.persistence.TagForWorkoutRepository;
import com.acme.gym4u.fitness.domain.service.TagForWorkoutService;
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
public class TagForWorkoutServiceImpl implements TagForWorkoutService {

    private static final String ENTITY = "TagForWorkout";
    private final TagForWorkoutRepository tagForWorkoutRepository;
    private final Validator validator;

    public TagForWorkoutServiceImpl(TagForWorkoutRepository tagForWorkoutRepository, Validator validator) {
        this.tagForWorkoutRepository = tagForWorkoutRepository;
        this.validator = validator;
    }

    @Override
    public List<TagForWorkout> getAll() {
        return tagForWorkoutRepository.findAll();
    }

    @Override
    public Page<TagForWorkout> getAl(Pageable pageable) {
        return tagForWorkoutRepository.findAll(pageable);
    }

    @Override
    public TagForWorkout getById(Long tagForWorkoutId) {
        return tagForWorkoutRepository.findById(tagForWorkoutId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, tagForWorkoutId));
    }

    @Override
    public TagForWorkout create(TagForWorkout tagForWorkout) {

        Set<ConstraintViolation<TagForWorkout>> violations = validator.validate(tagForWorkout);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        TagForWorkout tagForWorkoutWithName = tagForWorkoutRepository.findByName(tagForWorkout.getName());

        if(tagForWorkoutWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An tag for workout with the same name already exists.");

        return tagForWorkoutRepository.save(tagForWorkout);
    }

    @Override
    public TagForWorkout update(Long tagForWorkoutId, TagForWorkout tagForWorkout) {

        Set<ConstraintViolation<TagForWorkout>> violations = validator.validate(tagForWorkout);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        TagForWorkout tagForWorkoutWithName = tagForWorkoutRepository.findByName(tagForWorkout.getName());

        if(tagForWorkoutWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An tag for workout with the same name already exists.");

        return tagForWorkoutRepository.findById(tagForWorkoutId).map(element ->
                tagForWorkoutRepository.save(element
                        .withName(tagForWorkout.getName())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY,tagForWorkoutId));
    }

    @Override
    public ResponseEntity<?> delete(Long tagForWorkoutId) {
        return tagForWorkoutRepository.findById(tagForWorkoutId).map(element -> {
            tagForWorkoutRepository.delete(element);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, tagForWorkoutId));
    }
}
