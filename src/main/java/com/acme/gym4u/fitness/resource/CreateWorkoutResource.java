package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutResource {
    @NotNull
    @NotBlank
    @Size(max=50)
    private String name;

    @NotNull
    private Long tagForWorkoutId;
}
