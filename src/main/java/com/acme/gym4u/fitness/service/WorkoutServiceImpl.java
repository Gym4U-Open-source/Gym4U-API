package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import com.acme.gym4u.fitness.domain.persistence.TagForWorkoutRepository;
import com.acme.gym4u.fitness.domain.persistence.WorkoutRepository;
import com.acme.gym4u.fitness.domain.service.WorkoutService;
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
public class WorkoutServiceImpl implements WorkoutService {

    private static final String ENTITY = "Workout";
    private final WorkoutRepository workoutRepository;
    private final Validator validator;
    private final UserContextFacade userContextFacade;
    private final TagForWorkoutRepository tagForWorkoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, Validator validator, UserContextFacade userContextFacade, TagForWorkoutRepository tagForWorkoutRepository) {
        this.workoutRepository = workoutRepository;
        this.validator = validator;
        this.userContextFacade = userContextFacade;
        this.tagForWorkoutRepository = tagForWorkoutRepository;
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

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User user = userContextFacade.findByUserToken().orElseThrow(() -> new NotFoundException("User not found"));
        TagForWorkout tagForWorkout = tagForWorkoutRepository.findById(workout.getTagForWorkout().getId()).orElseThrow(() -> new NotFoundException("Tag for workout not found"));

        Workout newWorkout = new Workout();
        newWorkout.setName(workout.getName());
        newWorkout.setUser(user);
        newWorkout.setTagForWorkout(tagForWorkout);

        return workoutRepository.save(newWorkout);
    }

    @Override
    public Workout update(Long workoutId, Workout workout) {

        Set<ConstraintViolation<Workout>> violations = validator.validate(workout);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        TagForWorkout tagForWorkout = tagForWorkoutRepository.findById(workout.getTagForWorkout().getId()).orElseThrow(() -> new NotFoundException("Tag for workout not found"));

        return workoutRepository.findById(workoutId).map(element ->
                        workoutRepository.save(element
                                .withName(workout.getName())
                                .withTagForWorkout(tagForWorkout)))
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
