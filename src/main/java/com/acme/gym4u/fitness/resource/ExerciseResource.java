package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Aproach;
import com.acme.gym4u.fitness.domain.model.entity.Category;
import com.acme.gym4u.fitness.domain.model.entity.Tag;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResource {
    private Long id;
    private Category category;
    private Aproach aproach;
    private String name;
    private String assetUrl;
    private Tag tag;
}
