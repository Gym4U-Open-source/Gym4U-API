package com.acme.gym4u.profile.domain.persistence;

import com.acme.gym4u.profile.domain.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}