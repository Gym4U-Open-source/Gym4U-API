package com.acme.gym4u.fitness.domain.service;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExerciseService {
    List<Exercise> getAll();
    Page<Exercise> getAll(Pageable pageable);
    Exercise getById(Long exerciseId);
    Exercise create(Exercise exercise);
    Exercise update(Long exerciseId, Exercise exercise);
    ResponseEntity<?> delete(Long exerciseId);
}
