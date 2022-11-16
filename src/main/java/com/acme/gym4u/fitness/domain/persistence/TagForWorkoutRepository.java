package com.acme.gym4u.fitness.domain.persistence;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagForWorkoutRepository extends JpaRepository<TagForWorkout, Long> {
    TagForWorkout findByName(String name);
}
