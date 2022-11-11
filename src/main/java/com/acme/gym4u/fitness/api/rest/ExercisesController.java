package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.ExerciseService;
import com.acme.gym4u.fitness.mapping.ExerciseMapper;
import com.acme.gym4u.fitness.resource.CreateExerciseResource;
import com.acme.gym4u.fitness.resource.ExerciseResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/exercise", produces = "application/json")
@Tag(name = "Exercises", description = "Create, read, update and delete exercises")
public class ExercisesController {
    private final ExerciseService exerciseService;
    private final ExerciseMapper mapper;

    public ExercisesController(ExerciseService exerciseService, ExerciseMapper mapper) {
        this.exerciseService = exerciseService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ExerciseResource> createExercise(
            @RequestBody CreateExerciseResource resource) {
        return new ResponseEntity<>(mapper.toResource(
                exerciseService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

}
