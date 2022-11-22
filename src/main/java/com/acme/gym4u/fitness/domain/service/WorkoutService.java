package com.acme.gym4u.fitness.domain.service;

import com.acme.gym4u.fitness.domain.model.entity.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WorkoutService {
    List<Workout> getAll();
    Page<Workout> getAll(Pageable pageable);
    Workout getById(Long workoutId);
    Workout create(Workout workout);
    Workout update(Long workoutId, Workout workout);
    ResponseEntity<?> delete(Long workoutId);
}
