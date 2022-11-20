package com.acme.gym4u.fitness.domain.service;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagForWorkoutService {
    List<TagForWorkout> getAll();
    Page<TagForWorkout> getAl(Pageable pageable);
    TagForWorkout getById(Long tagForWorkoutId);
    TagForWorkout create(TagForWorkout tagForWorkout);
    TagForWorkout update(Long tagForWorkoutId, TagForWorkout tagForWorkout);
    ResponseEntity<?> delete(Long tagForWorkoutId);
}
