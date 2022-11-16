package com.acme.gym4u.fitness.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTagForWorkoutResource {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String name;
}
