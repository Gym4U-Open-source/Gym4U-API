package com.acme.gym4u.profile.api.internal;

import com.acme.gym4u.profile.domain.model.entity.Profile;

public interface ProfileContextFacade {
    Profile create(Profile profile);
}
