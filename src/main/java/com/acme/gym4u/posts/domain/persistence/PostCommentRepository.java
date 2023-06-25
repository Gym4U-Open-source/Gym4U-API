package com.acme.gym4u.posts.domain.persistence;

import com.acme.gym4u.posts.domain.model.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
    Page<PostComment> getAllByPostId(Long postId, Pageable pageable);

    List<PostComment> getAllByPostId(Long postId);
}
