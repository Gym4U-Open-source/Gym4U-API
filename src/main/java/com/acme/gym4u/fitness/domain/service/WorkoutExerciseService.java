package com.acme.gym4u.fitness.domain.service;

import com.acme.gym4u.fitness.domain.model.entity.WorkoutExercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WorkoutExerciseService {

    List<WorkoutExercise> getAll();
    Page<WorkoutExercise> getAll(Pageable pageable);
    WorkoutExercise getById(Long workoutExerciseId);
    WorkoutExercise create(WorkoutExercise workoutExercise);
    WorkoutExercise update(Long workoutExerciseId, WorkoutExercise workoutExercise);
    ResponseEntity<?> delete(Long workoutExerciseId);
}
