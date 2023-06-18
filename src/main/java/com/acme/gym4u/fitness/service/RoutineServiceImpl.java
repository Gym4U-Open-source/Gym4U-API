package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.domain.model.entity.Routine;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import com.acme.gym4u.fitness.domain.persistence.ExerciseRepository;
import com.acme.gym4u.fitness.domain.persistence.RoutineRepository;
import com.acme.gym4u.fitness.domain.persistence.WorkoutRepository;
import com.acme.gym4u.fitness.domain.service.RoutineService;
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
public class RoutineServiceImpl implements RoutineService {
    private static final String ENTITY = "Routine";
    private final RoutineRepository routineRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final Validator validator;

    public RoutineServiceImpl(RoutineRepository routineRepository, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository, Validator validator) {
        this.routineRepository = routineRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.validator = validator;
    }

    @Override
    public List<Routine> getAll() {
        return null;
    }

    @Override
    public List<Routine> getAllByWorkoutId(Long workoutId) {
        return routineRepository.findAllByWorkoutId(workoutId);
    }

    @Override
    public Page<Routine> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Routine create(Routine routine) {
        Set<ConstraintViolation<Routine>> violations = validator.validate(routine);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        Workout workout = workoutRepository.findById(routine.getWorkout().getId()).orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
        Exercise exercise = exerciseRepository.findById(routine.getExercise().getId()).orElseThrow(() -> new ResourceNotFoundException("Exercise not fount"));

        Routine newRoutine = new Routine().withExercise(exercise).withWorkout(workout).withRepetitions(routine.getRepetitions()).withTimePerRepeat(routine.getTimePerRepeat());

        return routineRepository.save(newRoutine);
    }

    @Override
    public Routine update(Long routineId, Routine routine) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long routineId) {
        return null;
    }
}
