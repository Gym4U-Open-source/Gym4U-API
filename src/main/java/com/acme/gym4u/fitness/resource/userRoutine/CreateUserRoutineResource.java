package com.acme.gym4u.fitness.resource.userRoutine;

import com.acme.gym4u.fitness.domain.model.entity.Routine;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.resource.UserResource;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRoutineResource {
    @NotNull
    private Workout workout;

    /*
    @NotNull
    private User user;


     */
    @NotNull
    private User client;
}
