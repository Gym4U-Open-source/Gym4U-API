package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutResource {
    private Long id;
    private String name;
    private TagForWorkout tag;
}
