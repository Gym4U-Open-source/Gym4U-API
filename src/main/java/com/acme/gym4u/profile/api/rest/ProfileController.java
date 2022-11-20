package com.acme.gym4u.profile.api.rest;

import com.acme.gym4u.profile.domain.service.ProfileService;
import com.acme.gym4u.profile.mapping.ProfileMapper;
import com.acme.gym4u.profile.resource.CreateProfileResource;
import com.acme.gym4u.profile.resource.ProfileResource;
import com.acme.gym4u.profile.resource.UpdateProfileResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/profiles", produces = "application/json")
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileMapper mapper;

    public ProfileController(ProfileService profileService, ProfileMapper mapper) {
        this.profileService = profileService;
        this.mapper = mapper;
    }

    @GetMapping("user")
    public ProfileResource getProfileByToken() {
        return mapper.toResource(profileService.getByToken());
    }

    @GetMapping
    public Page<ProfileResource> getAllProfiles(Pageable pageable) {
        // GET METHOD IMPLEMENTED
        return mapper.modelListPage(profileService.getAll(), pageable);
    }

    @GetMapping("{profileId}")
    public ProfileResource getProfileById(@PathVariable Long profileId) {
        return mapper.toResource(profileService.getById(profileId));
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        return new ResponseEntity<>(mapper.toResource(
                profileService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

    @PutMapping("{profileId}")
    public ProfileResource updateProfile(
            // UPDATE METHOD IMPLEMENTED
            @PathVariable Long profileId,
            @RequestBody UpdateProfileResource resource) {
        return mapper.toResource(
                profileService.update(profileId,
                        mapper.toModel(resource)));
    }

    @DeleteMapping("{profileId}")
    public ResponseEntity<?> deleteProfile(
            @PathVariable Long profileId) {
        // DELETE METHOD IMPLEMENTED
        return profileService.delete(profileId);
    }
}
