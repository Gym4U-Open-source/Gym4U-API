package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.WorkoutExercise;
import com.acme.gym4u.fitness.domain.persistence.WorkoutExerciseRepository;
import com.acme.gym4u.fitness.domain.service.WorkoutExerciseService;
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
public class WorkoutExerciseServiceImpl implements WorkoutExerciseService {

    private static final String ENTITY = "WorkoutExercise";
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final Validator validator;

    public WorkoutExerciseServiceImpl(WorkoutExerciseRepository workoutExerciseRepository, Validator validator) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.validator = validator;
    }

    @Override
    public List<WorkoutExercise> getAll() {
        return workoutExerciseRepository.findAll();
    }

    @Override
    public Page<WorkoutExercise> getAll(Pageable pageable) {
        return workoutExerciseRepository.findAll(pageable);
    }

    @Override
    public WorkoutExercise getById(Long workoutExerciseId) {
        return workoutExerciseRepository.findById(workoutExerciseId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutExerciseId));
    }

    @Override
    public WorkoutExercise create(WorkoutExercise workoutExercise) {

        Set<ConstraintViolation<WorkoutExercise>> violations = validator.validate(workoutExercise);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return workoutExerciseRepository.save(workoutExercise);
    }

    @Override
    public WorkoutExercise update(Long workoutExerciseId, WorkoutExercise workoutExercise) {

        Set<ConstraintViolation<WorkoutExercise>> violations = validator.validate(workoutExercise);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return workoutExerciseRepository.findById(workoutExerciseId).map(element->
                workoutExerciseRepository.save(element
                        .withExercise(workoutExercise.getExercise())
                        .withWorkout(workoutExercise.getWorkout())
                        .withRepetitions(workoutExercise.getRepetitions())
                        .withTimePerRepeat(workoutExercise.getTimePerRepeat())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutExerciseId));
    }

    @Override
    public ResponseEntity<?> delete(Long workoutExerciseId) {
        return workoutExerciseRepository.findById(workoutExerciseId).map(element -> {
            workoutExerciseRepository.delete(element);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutExerciseId));
    }
}
