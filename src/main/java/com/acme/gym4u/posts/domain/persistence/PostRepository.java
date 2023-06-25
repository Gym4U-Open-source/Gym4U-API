package com.acme.gym4u.posts.domain.persistence;


import com.acme.gym4u.posts.domain.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Post findByTitle(String title);
    Page<Post> findByDescriptionContaining(String keyword, Pageable pageable);
    List<Post> findByDescriptionContaining(String keyword);
}