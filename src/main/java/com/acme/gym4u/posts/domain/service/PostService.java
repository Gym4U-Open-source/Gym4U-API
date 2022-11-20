package com.acme.gym4u.posts.domain.service;


import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAll();
    Page<Post> getAll(Pageable pageable);
    Post getById(Long postId);
    Post create(Post post);
    Post update(Long postId, Post request);
    ResponseEntity<?> delete(Long postId);

    Optional<Post> findByIdWithComments(Long id);

    List<Post> listPostsByIds(Iterable<Long> ids);


}
