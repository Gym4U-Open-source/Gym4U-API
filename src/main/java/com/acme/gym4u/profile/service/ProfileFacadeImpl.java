package com.acme.gym4u.profile.service;

import com.acme.gym4u.profile.api.internal.ProfileContextFacade;
import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.profile.domain.service.ProfileService;
import org.springframework.stereotype.Service;

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
}
