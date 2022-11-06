package com.acme.gym4u.fitness.domain.service;

import com.acme.gym4u.fitness.domain.model.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService {
    List<Tag> getAll();
    Page<Tag> getAll(Pageable pageable);
    Tag getById(Long tagId);
    Tag create(Tag tag);
    Tag update(Long tagId, Tag tag);
    ResponseEntity<?> delete(Long tagId);
}
