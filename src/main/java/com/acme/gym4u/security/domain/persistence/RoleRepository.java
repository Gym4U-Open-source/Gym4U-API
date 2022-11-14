package com.acme.gym4u.security.domain.persistence;

import com.acme.gym4u.security.domain.model.entity.Role;
import com.acme.gym4u.security.domain.model.enumns.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
    boolean existsByName(Roles name);
}
