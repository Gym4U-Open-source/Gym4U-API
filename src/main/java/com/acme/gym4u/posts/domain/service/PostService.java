package com.acme.gym4u.posts.domain.service;


import com.acme.gym4u.posts.domain.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Page<Post> getAll(Pageable pageable);
    Post getById(Long postId);
    Post create(Post post);
    Post update(Long postId, Post request);
    ResponseEntity<?> delete(Long postId);
}
