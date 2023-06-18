package com.acme.gym4u.fitness.domain.service;

import com.acme.gym4u.fitness.domain.model.entity.Routine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoutineService {
    List<Routine> getAll();

    List<Routine> getAllByWorkoutId(Long workoutId);
    Page<Routine> getAll(Pageable pageable);
    Routine create(Routine routine);
    Routine update(Long routineId, Routine routine);
    ResponseEntity<?> delete(Long routineId);
}
