package com.acme.gym4u.security.domain.service;

import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.domain.service.communication.AuthenticateRequest;
import com.acme.gym4u.security.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterRequest request);
    List<User> getAll();

    Optional<User> getById(Long userId);
}
