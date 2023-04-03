package com.acme.gym4u.fitness.resource;

import com.acme.gym4u.fitness.domain.model.entity.Routine;
import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutResource {
    private Long id;
    private String name;
    private TagForWorkout tag;
    private List<Routine> routines;
}
