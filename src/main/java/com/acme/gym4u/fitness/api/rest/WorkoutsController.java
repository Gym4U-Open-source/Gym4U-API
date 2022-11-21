package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.WorkoutService;
import com.acme.gym4u.fitness.mapping.WorkoutMapper;
import com.acme.gym4u.fitness.resource.CreateWorkoutResource;
import com.acme.gym4u.fitness.resource.UpdateWorkoutResource;
import com.acme.gym4u.fitness.resource.WorkoutResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/workouts", produces = "application/json")
@Tag(name = "Workouts", description = "Create, read, update and delete workouts")
public class WorkoutsController {

    private final WorkoutService workoutService;
    private final WorkoutMapper mapper;


    public WorkoutsController(WorkoutService workoutService, WorkoutMapper mapper) {
        this.workoutService = workoutService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<WorkoutResource> getAllWorkouts(Pageable pageable){
        return mapper.modelListPage(workoutService.getAll(),pageable);
    }

    @GetMapping("{workoutId}")
    public WorkoutResource getWorkoutById(@PathVariable Long workoutId){
        return mapper.toResource(workoutService.getById(workoutId));
    }

    @PostMapping
    public ResponseEntity<WorkoutResource> createWorkout(@RequestBody CreateWorkoutResource resource){
        return new ResponseEntity<>(mapper.toResource(
                workoutService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

    @PutMapping("{workoutId}")
    public WorkoutResource updateWorkout(@PathVariable Long workoutId, @RequestBody UpdateWorkoutResource resource){
        return mapper.toResource(
                workoutService.update(workoutId,
                        mapper.toModel(resource)));
    }

    @DeleteMapping("{workoutId}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long workoutId){
        return workoutService.delete(workoutId);
    }

}
