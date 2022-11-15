package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Aproach;
import com.acme.gym4u.fitness.domain.model.entity.Category;
import com.acme.gym4u.fitness.domain.model.entity.Tag;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExerciseResource {

    private Long id;

    private Category category;
    private Aproach aproach;

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
