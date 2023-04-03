package com.acme.gym4u.comunity.resource;

import com.acme.gym4u.security.domain.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowResource {
    private Long id;
    private User coachUser;
    private User clientUser;
}
