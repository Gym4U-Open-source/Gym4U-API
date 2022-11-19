package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Tag;
import com.acme.gym4u.fitness.domain.model.enumeration.Aproaches;
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

    private Categories category;
    private Aproaches aproach;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String name;

    @NotNull
    @NotBlank
    @Size(max=120)
    private String assetUrl;

    private Tag tag;

}
