package com.acme.gym4u.comunity.domain.service;

import com.acme.gym4u.comunity.domain.model.entity.Follow;
import com.acme.gym4u.comunity.resource.CreateFollowResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FollowService {
    Follow create(CreateFollowResource resource);
    List<Follow> getAll();
    Page<Follow> getAll(Pageable pageable);
    Follow createFollowerByCoachId(Long coachId);
    List<Follow> getAllFollowersByCoachId(Long coachId);
    Page<Follow> getAllFollowersByCoachId(Long coachId, Pageable pageable);

    Follow createNewFollow(Long coachId, Long clientId);
    Follow getFollow(Long coachId, Long clientId);
    ResponseEntity<?> deleteFollow(Long coachId, Long clientId);
}
