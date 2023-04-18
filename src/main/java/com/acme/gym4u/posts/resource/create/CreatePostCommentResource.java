package com.acme.gym4u.posts.resource.create;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.security.domain.model.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreatePostCommentResource {

    private String review;
    private Post post;
    private User user;
}
