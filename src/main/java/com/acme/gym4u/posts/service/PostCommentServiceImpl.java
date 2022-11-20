package com.acme.gym4u.posts.service;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.posts.domain.persistence.PostCommentRepository;
import com.acme.gym4u.posts.domain.persistence.PostRepository;
import com.acme.gym4u.posts.domain.service.PostCommentService;
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
public class PostCommentServiceImpl implements PostCommentService {

    private static final String ENTITY = "PostComment";

    private final PostCommentRepository commentRepository;
    private final Validator validator;

    @Autowired
    private PostRepository postRepository;

    public PostCommentServiceImpl(PostCommentRepository commentRepository, Validator validator) {
        this.commentRepository = commentRepository;
        this.validator = validator;
    }


    @Override
    @Transactional(readOnly = true)
    public List<PostComment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostComment> getAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public PostComment getById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,commentId));
    }

    @Override
    @Transactional
    public PostComment create( PostComment comment,Long postId) {
        Post o = postRepository.getReferenceById(postId);
        Set<ConstraintViolation<PostComment>> violations = validator.validate(comment);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        comment.setPost(o);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public PostComment update(Long commentId, PostComment request) {
        Set<ConstraintViolation<PostComment>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return commentRepository.findById(commentId).map(comment ->
                commentRepository.save(comment
                        .withReview(request.getReview())))
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,commentId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long commentId) {

        return commentRepository.findById(commentId).map(comment ->{
            commentRepository.delete(comment);
            return  ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, commentId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostComment> listCommentsByIds(Iterable<Long> ids) {
        return commentRepository.findAllById(ids);
    }
}
