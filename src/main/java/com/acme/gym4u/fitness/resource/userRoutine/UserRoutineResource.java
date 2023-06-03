package com.acme.gym4u.fitness.resource.userRoutine;

import com.acme.gym4u.fitness.domain.model.entity.Routine;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.resource.UserResource;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserRoutineResource {
    private Long id;
    private Workout workout;
    //private UserResource user;
    private UserResource client;

}
