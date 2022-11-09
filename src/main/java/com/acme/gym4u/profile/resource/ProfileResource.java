package com.acme.gym4u.profile.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResource {
    private Long id;
    private String name;
    private String lastName;
    private String email;
}
