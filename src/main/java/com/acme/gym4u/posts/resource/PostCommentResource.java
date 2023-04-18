package com.acme.gym4u.posts.resource;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.resource.UserResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentResource {

    private Long id;
    private String review;
    @JsonIgnore
    private Post post;
    private UserResource user;
}
