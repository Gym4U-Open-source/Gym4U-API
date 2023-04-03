package com.acme.gym4u.fitness.resource.routine;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoutineResource {
    @NotNull
    private Workout workout;

    @NotNull
    private Exercise exercise;

    @NotNull
    private Long repetitions;

    @NotNull
    private Long timePerRepeat;
}
