package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import com.acme.gym4u.fitness.domain.model.enumeration.TagForExercise;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkoutResource {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String name;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String content;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String owner;

    private TagForExercise tag;
}
