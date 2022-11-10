package com.acme.gym4u.security.api.internal;

import com.acme.gym4u.security.domain.model.entity.User;

import java.util.Optional;

public interface UserContextFacade {
    Optional<User> findById(Long id);
}
