package com.acme.gym4u.posts.domain.service;

import com.acme.gym4u.posts.domain.model.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostCommentService {
    List<PostComment> getAll();
    Page<PostComment> getAll(Pageable pageable);
    PostComment getById(Long commentId);
    PostComment create( PostComment post,Long postId);

    PostComment update(Long postId, PostComment request);
    ResponseEntity<?> delete(Long commentId);

    List<PostComment> listCommentsByIds(Iterable<Long> ids);
}
