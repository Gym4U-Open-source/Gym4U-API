package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.domain.model.entity.Tag;
import com.acme.gym4u.fitness.domain.persistence.ExerciseRepository;
import com.acme.gym4u.fitness.domain.persistence.TagRepository;
import com.acme.gym4u.fitness.domain.service.ExerciseService;
import com.acme.gym4u.security.api.internal.UserContextFacade;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import com.acme.gym4u.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private static final String ENTITY = "Exercise";
    private final ExerciseRepository exerciseRepository;
    private final Validator validator;
    private final UserContextFacade userContextFacade;
    private final TagRepository tagRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, Validator validator, UserContextFacade userContextFacade, TagRepository tagRepository) {
        this.exerciseRepository = exerciseRepository;
        this.validator = validator;
        this.userContextFacade = userContextFacade;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Page<Exercise> getAll(Pageable pageable) {
        return exerciseRepository.findAll(pageable);
    }

    @Override
    public Exercise getById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, exerciseId));
    }

    @Override
    public Exercise create(Exercise exercise) {

        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        User user = userContextFacade.findByUserToken().orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Tag tag = tagRepository.findById(exercise.getTag().getId()).orElseThrow(() -> new NotFoundException("Tag not found."));

        Exercise newExercise = new Exercise();
        newExercise.setUser(user);
        newExercise.setTag(tag);
        newExercise.setApproach(exercise.getApproach());
        newExercise.setName(exercise.getName());
        newExercise.setCategory(exercise.getCategory());
        newExercise.setAssetUrl(exercise.getAssetUrl());

        return exerciseRepository.save(newExercise);
    }

    @Override
    public Exercise update(Long exerciseId, Exercise exercise) {

        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Tag tag = tagRepository.findById(exercise.getTag().getId()).orElseThrow(() -> new NotFoundException("Tag not found."));

        return exerciseRepository.findById(exerciseId).map(element ->
                        exerciseRepository.save(element
                                .withName(exercise.getName())
                                .withCategory(exercise.getCategory())
                                .withTag(tag)
                                .withApproach(exercise.getApproach())
                                .withAssetUrl(exercise.getAssetUrl())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, exerciseId));

    }

    @Override
    public ResponseEntity<?> delete(Long exerciseId) {
        return exerciseRepository.findById(exerciseId).map(exercise -> {
            exerciseRepository.delete(exercise);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, exerciseId));
    }
}
