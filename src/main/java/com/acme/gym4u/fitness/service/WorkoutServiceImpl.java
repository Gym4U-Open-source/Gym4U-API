package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.Workout;
import com.acme.gym4u.fitness.domain.persistence.WorkoutRepository;
import com.acme.gym4u.fitness.domain.service.WorkoutService;
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
public class WorkoutServiceImpl implements WorkoutService {

    private static final String ENTITY = "Workout";
    private final WorkoutRepository workoutRepository;
    private final Validator validator;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, Validator validator) {
        this.workoutRepository = workoutRepository;
        this.validator = validator;
    }

    @Override
    public List<Workout> getAll() {
        return workoutRepository.findAll();
    }

    @Override
    public Page<Workout> getAll(Pageable pageable) {
        return workoutRepository.findAll(pageable);
    }

    @Override
    public Workout getById(Long workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutId));
    }

    @Override
    public Workout create(Workout workout) {

        Set<ConstraintViolation<Workout>> violations = validator.validate(workout);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Workout workoutWithName = workoutRepository.findByName(workout.getName());

        if(workoutWithName != null)
            throw new ResourceValidationException(ENTITY, "An workout with the same name already exists.");

        return workoutRepository.save(workout);
    }

    @Override
    public Workout update(Long workoutId, Workout workout) {

        Set<ConstraintViolation<Workout>> violations = validator.validate(workout);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Workout workoutWithName = workoutRepository.findByName(workout.getName());

        if(workoutWithName != null)
            throw new ResourceValidationException(ENTITY, "An workout with the same name already exists.");

        return workoutRepository.findById(workoutId).map(element ->
                workoutRepository.save(element
                        .withName(workout.getName())
                        .withTag(workout.getTag())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutId));
    }

    @Override
    public ResponseEntity<?> delete(Long workoutId) {
        return workoutRepository.findById(workoutId).map(element -> {
            workoutRepository.delete(element);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutId));
    }
}
