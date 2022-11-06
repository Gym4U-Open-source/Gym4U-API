package com.acme.gym4u.fitness.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateTagResource {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String name;
}
