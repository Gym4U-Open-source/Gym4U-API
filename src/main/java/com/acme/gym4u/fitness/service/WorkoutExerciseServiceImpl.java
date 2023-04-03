package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import com.acme.gym4u.fitness.domain.model.entity.WorkoutExercise;
import com.acme.gym4u.fitness.domain.persistence.ExerciseRepository;
import com.acme.gym4u.fitness.domain.persistence.WorkoutExerciseRepository;
import com.acme.gym4u.fitness.domain.persistence.WorkoutRepository;
import com.acme.gym4u.fitness.domain.service.WorkoutExerciseService;
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
public class WorkoutExerciseServiceImpl implements WorkoutExerciseService {

    private static final String ENTITY = "WorkoutExercise";
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final Validator validator;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public WorkoutExerciseServiceImpl(WorkoutExerciseRepository workoutExerciseRepository, Validator validator, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.validator = validator;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
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
        return workoutExerciseRepository.findById(workoutExerciseId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutExerciseId));
    }

    @Override
    public WorkoutExercise create(WorkoutExercise workoutExercise) {
        Set<ConstraintViolation<WorkoutExercise>> violations = validator.validate(workoutExercise);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        Workout existingWorkout = workoutRepository.findById(workoutExercise.getExercise().getId()).orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
        Exercise existingExercise = exerciseRepository.findById(workoutExercise.getWorkout().getId()).orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));

        WorkoutExercise newWorkoutExercise = new WorkoutExercise();
        newWorkoutExercise.setExercise(existingExercise);
        newWorkoutExercise.setWorkout(existingWorkout);
        newWorkoutExercise.setRepetitions(workoutExercise.getRepetitions());
        newWorkoutExercise.setTimePerRepeat(workoutExercise.getTimePerRepeat());

        return workoutExerciseRepository.save(newWorkoutExercise);
    }

    @Override
    public WorkoutExercise update(Long workoutExerciseId, WorkoutExercise workoutExercise) {

        Set<ConstraintViolation<WorkoutExercise>> violations = validator.validate(workoutExercise);

        if (!violations.isEmpty()) throw new ResourceValidationException(ENTITY, violations);

        return workoutExerciseRepository.findById(workoutExerciseId).map(element -> workoutExerciseRepository.save(element.withExercise(workoutExercise.getExercise()).withWorkout(workoutExercise.getWorkout()).withRepetitions(workoutExercise.getRepetitions()).withTimePerRepeat(workoutExercise.getTimePerRepeat()))).orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutExerciseId));
    }

    @Override
    public ResponseEntity<?> delete(Long workoutExerciseId) {
        return workoutExerciseRepository.findById(workoutExerciseId).map(element -> {
            workoutExerciseRepository.delete(element);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, workoutExerciseId));
    }
}
