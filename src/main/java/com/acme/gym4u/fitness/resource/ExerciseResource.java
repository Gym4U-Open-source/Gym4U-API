package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Tag;
import com.acme.gym4u.fitness.domain.model.enumeration.Aproaches;
import com.acme.gym4u.fitness.domain.model.enumeration.Categories;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResource {
    private Long id;
    private Categories category;
    private Aproaches aproach;
    private String name;
    private String assetUrl;
    private Tag tag;
}
