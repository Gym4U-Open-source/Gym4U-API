package com.acme.gym4u.profile.service;

import com.acme.gym4u.profile.api.internal.ProfileContextFacade;
import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.profile.domain.service.ProfileService;
import com.acme.gym4u.security.domain.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileFacadeImpl  implements ProfileContextFacade {
    private final ProfileService profileService;

    public ProfileFacadeImpl(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public Profile create(Profile profile) {
        return profileService.create(profile);
    }

    @Override
    public Profile findByUser(Long userId) {
        return profileService.getById(userId);
    }
}
