package com.acme.gym4u.comunity.service;

import com.acme.gym4u.comunity.domain.model.entity.Follow;
import com.acme.gym4u.comunity.domain.persistence.FollowRepository;
import com.acme.gym4u.comunity.domain.service.FollowService;
import com.acme.gym4u.comunity.resource.CreateFollowResource;
import com.acme.gym4u.security.api.internal.UserContextFacade;
import com.acme.gym4u.security.domain.model.entity.Role;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.domain.model.enumns.Roles;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        User coachUser = userContextFacade.findByUserId(resource.getCoachId()).orElseThrow(() -> new ResourceNotFoundException(ENTITY, resource.getCoachId()));
        User normalUser = userContextFacade.findByUserToken().orElseThrow(() -> new ResourceNotFoundException(ENTITY));

        Follow follow = new Follow().withCoachUser(coachUser).withClientUser(normalUser);
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
        User coachUser = userContextFacade.findByUserId(coachId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, coachId));
        User normalUser = userContextFacade.findByUserToken().orElseThrow(() -> new ResourceNotFoundException(ENTITY));

        Follow follow = new Follow().withCoachUser(coachUser).withClientUser(normalUser);
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

    @Override
    public Follow createNewFollow(Long coachId, Long clientId) {
        User coach = userContextFacade.findByUserId(coachId).orElseThrow(() -> new ResourceNotFoundException("User", coachId));

        boolean hasCoachRole = coach.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.COACH));

        if (!hasCoachRole) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User haveen't coach role");

        User client = userContextFacade.findByUserId(clientId).orElseThrow(() -> new ResourceNotFoundException("User", clientId));

        Follow existingFollow = followRepository.findByCoachUserIdAndClientUserId(coachId, clientId);

        if (existingFollow != null) throw new ResponseStatusException(HttpStatus.CONFLICT, "Follow already exist");

        Follow newFollow = new Follow().withCoachUser(coach).withClientUser(client);

        return followRepository.save(newFollow);
    }

    @Override
    public Follow getFollow(Long coachId, Long clientId) {
        Follow follow = followRepository.findByCoachUserIdAndClientUserId(coachId, clientId);

        if (follow == null) throw new ResourceNotFoundException(ENTITY);

        return followRepository.findByCoachUserIdAndClientUserId(coachId, clientId);
    }

    @Override
    public ResponseEntity<?> deleteFollow(Long coachId, Long clientId) {
        Follow existingFollow = followRepository.findByCoachUserIdAndClientUserId(coachId, clientId);

        if (existingFollow == null) throw new ResourceNotFoundException(ENTITY + " NOT FOUND");

        followRepository.delete(existingFollow);

        return ResponseEntity.ok().build();
    }
}
