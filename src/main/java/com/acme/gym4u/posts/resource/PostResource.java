package com.acme.gym4u.posts.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor

public class PostResource {
    private Long id;
    private String title;
    private String description;
    private String urlImage;
}
