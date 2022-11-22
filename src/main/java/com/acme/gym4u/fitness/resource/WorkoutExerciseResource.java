package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExerciseResource {
    private Long id;
    private Workout workout;
    private Exercise exercise;
    private Long repetitions;
    private Long timePerRepeat;
}
