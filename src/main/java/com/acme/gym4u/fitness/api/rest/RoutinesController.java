package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.RoutineService;
import com.acme.gym4u.fitness.mapping.RoutineMapper;
import com.acme.gym4u.fitness.resource.routine.CreateRoutineResource;
import com.acme.gym4u.fitness.resource.routine.RoutineResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/routines")
@Tag(name = "Routines", description = "CRUD Routines")
public class RoutinesController {
    private final RoutineService routineService;
    private final RoutineMapper mapper;

    public RoutinesController(RoutineService routineService, RoutineMapper mapper) {
        this.routineService = routineService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<RoutineResource> createRoutine(@RequestBody CreateRoutineResource resource) {
        return new ResponseEntity<>(mapper.toResource(routineService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @GetMapping("{workoutId}")
    public Page<RoutineResource> getRoutinesByWorkoutId(@PathVariable Long workoutId, Pageable pageable){
        return mapper.modelListPage(routineService.getAllByWorkoutId(workoutId),pageable);
    }
}
