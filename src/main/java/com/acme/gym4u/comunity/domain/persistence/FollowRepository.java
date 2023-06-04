package com.acme.gym4u.comunity.domain.persistence;

import com.acme.gym4u.comunity.domain.model.entity.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByCoachUserId(Long coachId);
    Page<Follow> findByCoachUserId(Long coachId, Pageable pageable);
    Follow findByCoachUserIdAndClientUserId(Long coachId, Long userId);
    Follow findByClientUserId(Long clientUserId);
}
