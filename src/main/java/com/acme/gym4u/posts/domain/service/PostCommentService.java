package com.acme.gym4u.posts.domain.service;

import com.acme.gym4u.posts.domain.model.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostCommentService {

    List<PostComment> getAll();
    Page<PostComment> getAll(Pageable pageable);
    PostComment getById(Long postCommentId);
    PostComment create(PostComment postComment);
    PostComment update(Long postCommentId, PostComment request);
    ResponseEntity<?> delete(Long postCommentId);

}
