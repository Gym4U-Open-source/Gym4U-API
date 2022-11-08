package com.acme.gym4u.security.service;

import com.acme.gym4u.security.domain.model.entity.Role;
import com.acme.gym4u.security.domain.model.enumns.Roles;
import com.acme.gym4u.security.domain.persistence.RoleRepository;
import com.acme.gym4u.security.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    private static String[] DEFAULT_ROLES = {"NORMAL", "COACH", "ADMIN"};

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_ROLES).forEach(name -> {
            Roles roleName = Roles.valueOf(name);
            if(!roleRepository.existsByName(roleName)) {
                roleRepository.save(new Role().withName(roleName));
            }
        });
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
