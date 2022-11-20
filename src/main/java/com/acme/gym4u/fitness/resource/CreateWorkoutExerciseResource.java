package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutExerciseResource {

    private Workout workout;


    private Exercise exercise;

    @NotNull
    @NotBlank
    private Long repetitions;

    @NotNull
    @NotBlank
    private Long timePerRepeat;
}
