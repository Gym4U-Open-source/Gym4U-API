package com.acme.gym4u.security.domain.service;

import com.acme.gym4u.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {
    void seed();
    List<Role> getAll();
}
