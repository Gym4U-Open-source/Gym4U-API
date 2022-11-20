package com.acme.gym4u.fitness.domain.persistence;

import com.acme.gym4u.fitness.domain.model.entity.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    WorkoutExercise findByWorkout(String workoutId);

}
