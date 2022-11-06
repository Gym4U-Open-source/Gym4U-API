package com.acme.gym4u.posts.service;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.persistence.PostRepository;
import com.acme.gym4u.posts.domain.service.PostService;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import com.acme.gym4u.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    private static final String ENTITY = "Post";
    private final PostRepository postRepository;
    private final Validator validator;


    public PostServiceImpl(PostRepository postRepository, Validator validator) {
        this.postRepository = postRepository;
        this.validator = validator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Post getById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, postId));
    }

    @Override
    @Transactional
    public Post create(Post post) {
        // Constraints validation
        Set<ConstraintViolation<Post>> violations = validator.validate(post);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post update(Long postId, Post request) {
        // Constraints validations
        Set<ConstraintViolation<Post>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return postRepository.findById(postId).map(post ->
                        postRepository.save(post
                                .withTitle(request.getTitle())
                                .withUrlImage(request.getUrlImage())
                                .withDescription(request.getDescription())))
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,postId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long postId) {
        return postRepository.findById(postId).map(post ->{
            postRepository.delete(post);
            return  ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, postId));
    }
}