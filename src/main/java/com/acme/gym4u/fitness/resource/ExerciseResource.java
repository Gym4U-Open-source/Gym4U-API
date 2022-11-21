package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Tag;
import com.acme.gym4u.fitness.domain.model.enumeration.Aproaches;
import com.acme.gym4u.fitness.domain.model.enumeration.Categories;
import com.acme.gym4u.fitness.domain.model.enumeration.TagForExercise;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResource {
    private Long id;
    private Categories category;
    private String focus;
    private String name;
    private String assetUrl;
    private TagForExercise tag;
}
