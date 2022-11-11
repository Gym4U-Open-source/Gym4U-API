package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.ExerciseService;
import com.acme.gym4u.fitness.mapping.ExerciseMapper;
import com.acme.gym4u.fitness.resource.CreateExerciseResource;
import com.acme.gym4u.fitness.resource.ExerciseResource;
import com.acme.gym4u.fitness.resource.UpdateExerciseResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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

    @GetMapping
    public Page<ExerciseResource> getAllExercises(Pageable pageable){
        return mapper.modelListPage(exerciseService.getAll(), pageable);
    }

    @GetMapping("{exerciseId}")
    public ExerciseResource getExerciseById(@PathVariable Long exerciseId)
    {
        return mapper.toResource(exerciseService.getById(exerciseId));
    }

    @PostMapping
    public ResponseEntity<ExerciseResource> createExercise(
            @RequestBody CreateExerciseResource resource) {
        return new ResponseEntity<>(mapper.toResource(
                exerciseService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

    @PutMapping("{exerciseId}")
    public ExerciseResource updateExercise(
            @PathVariable Long exerciseId,
            @RequestBody UpdateExerciseResource resource)
    {
        return mapper.toResource(
                exerciseService.update(exerciseId,
                        mapper.toModel(resource)));
    }

    @DeleteMapping("{exerciseId}")
    public ResponseEntity<?> deleteExercise(
            @PathVariable Long exerciseId) {
        return exerciseService.delete(exerciseId);
    }

}
