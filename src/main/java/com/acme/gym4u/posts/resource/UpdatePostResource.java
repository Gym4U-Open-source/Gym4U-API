package com.acme.gym4u.posts.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostResource {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 120)
    private String title;

    private String description;

    private String urlImage;
}
