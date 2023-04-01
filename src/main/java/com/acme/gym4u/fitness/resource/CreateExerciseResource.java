package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.enumeration.Approaches;
import com.acme.gym4u.fitness.domain.model.enumeration.Categories;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseResource {
    @NotNull
    @NotBlank
    @Size(max = 50)
    private Categories category;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private Approaches approach;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 120)
    private String assetUrl;

    @NotNull
    private Long tagId;
}
