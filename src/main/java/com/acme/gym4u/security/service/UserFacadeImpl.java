package com.acme.gym4u.security.service;

import com.acme.gym4u.security.api.internal.UserContextFacade;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.domain.service.UserService;
import com.acme.gym4u.security.middleware.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFacadeImpl implements UserContextFacade {
    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> findByUserId(Long userId) {
        return userService.getById(userId);
    }

    @Override
    public Optional<User> findByUserToken() {
        Long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return userService.getById(userId);
    }
}
