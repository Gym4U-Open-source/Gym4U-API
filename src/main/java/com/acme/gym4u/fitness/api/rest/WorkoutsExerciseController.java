package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.WorkoutExerciseService;
import com.acme.gym4u.fitness.mapping.WorkoutExerciseMapper;
import com.acme.gym4u.fitness.resource.CreateWorkoutExerciseResource;
import com.acme.gym4u.fitness.resource.CreateWorkoutResource;
import com.acme.gym4u.fitness.resource.UpdateWorkoutExerciseResource;
import com.acme.gym4u.fitness.resource.WorkoutExerciseResource;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/workoutsExercise", produces = "application/json")
@Tag(name = "WorkoutsExercise", description = "Create, read, update and delete workoutsExercise")
public class WorkoutsExerciseController {

    private final WorkoutExerciseService workoutExerciseService;
    private final WorkoutExerciseMapper mapper;


    public WorkoutsExerciseController(WorkoutExerciseService workoutExerciseService, WorkoutExerciseMapper mapper) {
        this.workoutExerciseService = workoutExerciseService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<WorkoutExerciseResource> getAllWorkoutsExercise(Pageable pageable) {
        return mapper.modelListPage(workoutExerciseService.getAll(), pageable);
    }

    @GetMapping("{workoutExerciseId}")
    public WorkoutExerciseResource getWorkoutExerciseById(@PathVariable Long workoutExerciseId) {

        return mapper.toResource(workoutExerciseService.getById(workoutExerciseId));
    }

    @PostMapping
    public ResponseEntity<WorkoutExerciseResource> createWorkoutExercise(@RequestBody CreateWorkoutExerciseResource resource) {

        return new ResponseEntity<>(mapper.toResource(
                workoutExerciseService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

    @PutMapping("{workoutExerciseId}")
    public WorkoutExerciseResource updateWorkoutExercise(
            @PathVariable Long workoutExerciseId,
            @RequestBody UpdateWorkoutExerciseResource resource) {

        return mapper.toResource(
                workoutExerciseService.update(workoutExerciseId,
                        mapper.toModel(resource)));
    }

    @DeleteMapping("{workoutExerciseId}")
    public ResponseEntity<?> deleteWorkoutExercise(@PathVariable Long workoutExerciseId) {
        return workoutExerciseService.delete(workoutExerciseId);
    }
}
