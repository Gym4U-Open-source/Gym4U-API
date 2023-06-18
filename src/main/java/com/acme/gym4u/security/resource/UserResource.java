package com.acme.gym4u.security.resource;

import com.acme.gym4u.security.domain.model.enumns.Roles;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResource {
    private Long id;
    private String username;
    private String email;
    private List<Roles> roles;
}
