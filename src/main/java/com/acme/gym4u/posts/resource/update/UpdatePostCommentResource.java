package com.acme.gym4u.posts.resource.update;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UpdatePostCommentResource {

    private Long id;
    private String review;

}
