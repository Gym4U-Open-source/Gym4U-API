package com.acme.gym4u.fitness.domain.persistence;

import com.acme.gym4u.fitness.domain.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
