package com.acme.gym4u.comunity.api.rest;

import com.acme.gym4u.comunity.domain.service.FollowService;
import com.acme.gym4u.comunity.mapping.FollowMapper;
import com.acme.gym4u.comunity.resource.FollowResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users/{coachId}/followers", produces = "application/json")
@Tag(name = "UserFollowers", description = "Add & remove coach clients")
public class UserFollowersController {
    private final FollowService followService;
    private final FollowMapper mapper;

    public UserFollowersController(FollowService followService, FollowMapper mapper) {
        this.followService = followService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<FollowResource> getAllFollowersByCoachId(@PathVariable Long coachId, Pageable pageable) {
        return mapper.modelListPage(followService.getAllFollowersByCoachId(coachId), pageable);
    }

    @PostMapping
    public FollowResource createFollowerByCoachId(@PathVariable Long coachId) {
        return mapper.toResource(followService.createFollowerByCoachId(coachId));
    }
}
