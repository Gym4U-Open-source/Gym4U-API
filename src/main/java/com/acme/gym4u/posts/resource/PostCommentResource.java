package com.acme.gym4u.posts.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentResource {
    private Long id;
    private Long postId;
    private String review;
}
