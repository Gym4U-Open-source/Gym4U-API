package com.acme.gym4u.comunity.service;

import com.acme.gym4u.comunity.domain.model.entity.Follow;
import com.acme.gym4u.comunity.domain.persistence.FollowRepository;
import com.acme.gym4u.comunity.domain.service.FollowService;
import com.acme.gym4u.comunity.resource.CreateFollowResource;
import com.acme.gym4u.security.api.internal.UserContextFacade;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    private static final String ENTITY = "Follow";
    private final FollowRepository followRepository;
    private final UserContextFacade userContextFacade;

    public FollowServiceImpl(FollowRepository followRepository, UserContextFacade userContextFacade) {
        this.followRepository = followRepository;
        this.userContextFacade = userContextFacade;
    }
    @Override
    public Follow create(CreateFollowResource resource) {
        User coachUser = userContextFacade.findById(resource.getCoachId()).orElseThrow(() -> new ResourceNotFoundException(ENTITY, resource.getCoachId()));
        User normalUser = userContextFacade.findByToken().orElseThrow(() -> new ResourceNotFoundException(ENTITY));

        Follow follow = new Follow().withCoachUser(coachUser).withNormalUser(normalUser);
        return followRepository.save(follow);
    }

    @Override
    public List<Follow> getAll() {
        return followRepository.findAll();
    }

    @Override
    public Page<Follow> getAll(Pageable pageable) {
        return followRepository.findAll(pageable);
    }

    @Override
    public Follow createFollowerByCoachId(Long coachId) {
        User coachUser = userContextFacade.findById(coachId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, coachId));
        User normalUser = userContextFacade.findByToken().orElseThrow(() -> new ResourceNotFoundException(ENTITY));

        Follow follow = new Follow().withCoachUser(coachUser).withNormalUser(normalUser);
        return followRepository.save(follow);
    }

    @Override
    public List<Follow> getAllFollowersByCoachId(Long coachId) {
        return followRepository.findByCoachUserId(coachId);
    }

    @Override
    public Page<Follow> getAllFollowersByCoachId(Long coachId, Pageable pageable) {
        return followRepository.findByCoachUserId(coachId, pageable);
    }
}
