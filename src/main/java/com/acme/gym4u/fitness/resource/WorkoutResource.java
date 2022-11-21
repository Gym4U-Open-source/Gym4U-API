package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import com.acme.gym4u.fitness.domain.model.enumeration.TagForExercise;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutResource {
    private Long id;
    private String name;
    private String content;
    private String owner;
    private TagForExercise tag;
}
