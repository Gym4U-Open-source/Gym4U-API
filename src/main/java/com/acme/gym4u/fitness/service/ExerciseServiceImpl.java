package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.domain.persistence.ExerciseRepository;
import com.acme.gym4u.fitness.domain.service.ExerciseService;
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
public class ExerciseServiceImpl implements ExerciseService {

    private static final String ENTITY = "Exercise";
    private final ExerciseRepository exerciseRepository;
    private final Validator validator;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, Validator validator) {
        this.exerciseRepository = exerciseRepository;
        this.validator = validator;
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

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Exercise exerciseWithName = exerciseRepository.findByName(exercise.getName());

        if(exerciseWithName != null)
            throw new ResourceValidationException(ENTITY, "An exercise with the name already exists.");

        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise update(Long exerciseId, Exercise exercise) {

        Set<ConstraintViolation<Exercise>> violations = validator.validate(exercise);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Exercise exerciseWithName = exerciseRepository.findByName(exercise.getName());

        if(exerciseWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An exercise with the same name already exists.");

        return exerciseRepository.findById(exerciseId).map(element ->
                        exerciseRepository.save(element
                                .withName(exercise.getName())
                                .withCategory(exercise.getCategory())
                                .withTag(exercise.getTag())
                                .withFocus(exercise.getFocus())
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
