package com.acme.gym4u.profile.domain.service;

import com.acme.gym4u.profile.domain.model.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfileService {
    List<Profile> getAll();
    Page<Profile> getAll(Pageable pageable);
    Profile getById(Long personId);
    Profile create(Profile profile);
    Profile update(Long personId, Profile request);
    ResponseEntity<?> delete(Long personId);

    // EXTRA
    Profile getByToken();
}
