package com.acme.gym4u.comunity.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateFollowResource {
    @NotNull
    private Long coachId;
}
