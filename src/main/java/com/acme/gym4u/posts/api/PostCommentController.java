package com.acme.gym4u.posts.api;

import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.posts.domain.persistence.PostCommentRepository;
import com.acme.gym4u.posts.domain.service.PostCommentService;
import com.acme.gym4u.posts.mapping.PostCommentMapper;
import com.acme.gym4u.posts.resource.PostCommentResource;
import com.acme.gym4u.posts.resource.create.CreatePostCommentResource;
import com.acme.gym4u.posts.resource.update.UpdatePostCommentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/posts-comments", produces = "application/json")
public class PostCommentController {

    private final PostCommentService postCommentService;
    private final PostCommentMapper mapper;

    public PostCommentController(PostCommentService postCommentService, PostCommentMapper mapper) {
        this.postCommentService = postCommentService;
        this.mapper = mapper;
    }

    @GetMapping("post/{postId}")
    public Page<PostCommentResource> getAllPostCommentsByPostId(@PathVariable Long postId, Pageable pageable) {
        return mapper.modelListPage(postCommentService.getAllByPostId(postId), pageable);
    }

    @GetMapping
    public Page<PostCommentResource> getAllPostComments(Pageable pageable) {
        return mapper.modelListPage(postCommentService.getAll(),pageable);
    }

    @GetMapping("{postCommentId}")
    public PostCommentResource getPostCommentById(@PathVariable Long postCommentId){
        return mapper.toResource(postCommentService.getById(postCommentId));
    }

    @PostMapping
    public ResponseEntity<PostCommentResource> createPostComment(@RequestBody CreatePostCommentResource resource){
        return new ResponseEntity<>(mapper.toResource(postCommentService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{postCommentId}")
    public PostCommentResource updatePostComment(@PathVariable Long postCommentId, @RequestBody UpdatePostCommentResource resource){
        return mapper.toResource(postCommentService.update(postCommentId, mapper.toModel(resource)));
    }

    @DeleteMapping("{postCommentId}")
    public ResponseEntity<?> deletePostComment(@PathVariable Long postCommentId){
        return postCommentService.delete(postCommentId);
    }
}
