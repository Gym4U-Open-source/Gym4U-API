package com.acme.gym4u.posts.resource;

import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.resource.UserResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<PostCommentResource> comments;
    private UserResource user;
}
