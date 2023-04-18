package com.acme.gym4u.posts.service;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.posts.domain.persistence.PostCommentRepository;
import com.acme.gym4u.posts.domain.persistence.PostRepository;
import com.acme.gym4u.posts.domain.service.PostCommentService;
import com.acme.gym4u.security.api.internal.UserContextFacade;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import com.acme.gym4u.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PostCommentImpl implements PostCommentService {

    private static final String ENTITY = "Post Comment";
    private final PostCommentRepository postCommentRepository;

    private final PostRepository postRepository;
    private final Validator validator;
    private UserContextFacade userContextFacade;

    public PostCommentImpl(PostCommentRepository postCommentRepository, PostRepository postRepository, Validator validator, UserContextFacade userContextFacade) {
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
        this.validator = validator;
        this.userContextFacade = userContextFacade;
    }

    @Override
    public List<PostComment> getAll() {
        return postCommentRepository.findAll();
    }

    @Override
    public Page<PostComment> getAll(Pageable pageable) {
        return postCommentRepository.findAll(pageable);
    }

    @Override
    public PostComment getById(Long postCommentId) {
        return postCommentRepository.findById(postCommentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, postCommentId));
    }

    @Override
    public PostComment create(PostComment postComment) {
        Set<ConstraintViolation<PostComment>> violations = validator.validate(postComment);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User user = userContextFacade.findByUserToken().orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Post post = postRepository.findById(postComment.getPost().getId()).orElseThrow( () -> new NotFoundException("Post not found"));

        PostComment newPostComment = new PostComment();
        newPostComment.setUser(user);
        newPostComment.setPost(post);
        newPostComment.setReview(postComment.getReview());

        return postCommentRepository.save(newPostComment);
    }

    @Override
    public PostComment update(Long postCommentId, PostComment request) {

        Set<ConstraintViolation<PostComment>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return postCommentRepository.findById(postCommentId).map(postComment ->
                postCommentRepository.save(postComment
                        .withReview(request.getReview())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY,postCommentId));
    }

    @Override
    public ResponseEntity<?> delete(Long postCommentId) {
        return postCommentRepository.findById(postCommentId).map(postComment -> {
            postCommentRepository.delete(postComment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, postCommentId));
    }
}
