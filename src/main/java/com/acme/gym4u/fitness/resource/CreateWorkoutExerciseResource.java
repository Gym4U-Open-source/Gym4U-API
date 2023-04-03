package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutExerciseResource {
    @NotNull
    private Exercise exercise;

    @NotNull
    private Workout workout;

    @NotNull
    public Long repetitions;

    @NotNull
    private Long timePerRepeat;
}
