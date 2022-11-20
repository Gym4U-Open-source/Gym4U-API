package com.acme.gym4u.profile.service;


import com.acme.gym4u.posts.api.rest.PostController;
import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.profile.domain.persistence.ProfileRepository;
import com.acme.gym4u.profile.domain.service.ProfileService;
import com.acme.gym4u.security.api.internal.UserContextFacade;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import com.acme.gym4u.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private static final String ENTITY = "Profile";

    private final ProfileRepository profileRepository;

    private final Validator validator;

    private final UserContextFacade userContextFacade;

    private final PostController postController ;

    public ProfileServiceImpl(ProfileRepository profileRepository, Validator validator, UserContextFacade userContextFacade,PostController postController) {
        this.profileRepository = profileRepository;
        this.validator = validator;
        this.userContextFacade = userContextFacade;
        this.postController = postController;
    }

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Page<Profile> getAll(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Profile getById(Long personId) {
        return profileRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }

    @Override
    public Profile create(Profile request) {

        // Constraints validation

        Set<ConstraintViolation<Profile>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User user = userContextFacade.findByUserToken()
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY));

        request.setUser(user);

        return profileRepository.save(request);
    }

    @Override
    public Profile update(Long personId, Profile request) {

        // Constraints validations
        Set<ConstraintViolation<Profile>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return profileRepository.findById(personId).map(profile ->
                        profileRepository.save(profile
                                .withName(request.getName())
                                .withLastName(request.getLastName())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }

    @Override
    public ResponseEntity<?> delete(Long personId) {
        return profileRepository.findById(personId).map(profile -> {
            profileRepository.delete(profile);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, personId));
    }

    @Override
    public Profile getByToken() {
        return profileRepository.findByUserId(userContextFacade.findByUserToken().orElseThrow(() -> new ResourceNotFoundException(ENTITY)).getId()).orElseThrow(() -> new ResourceNotFoundException(ENTITY));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> findByIdWithPosts(Long id) {
        Optional<Profile> o = profileRepository.findById(id);
        if(o.isPresent()){
            Profile profile = o.get();
            if(!profile.getPosts().isEmpty()){
                List<Long> ids = profile.getPosts().stream().map(
                        posts -> posts.getId()).collect(Collectors.toList());
                List<Post> posts = postController.getPostsByProfile(ids);
                profile.setPosts(posts);
            }
            return Optional.of(profile);
        }
        return Optional.empty();
    }

}
