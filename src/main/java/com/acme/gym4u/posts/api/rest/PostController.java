package com.acme.gym4u.posts.api.rest;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.service.PostService;
import com.acme.gym4u.posts.mapping.PostMapper;
import com.acme.gym4u.posts.resource.PostResource;
import com.acme.gym4u.posts.resource.CreatePostResource;
import com.acme.gym4u.posts.resource.UpdatePostResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/posts", produces = "application/json")
public class PostController {

    private final PostService postService;
    private final PostMapper mapper;

    public PostController(PostService postService, PostMapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<PostResource> getAllPosts() {
        return mapper.toResources(postService.getAll());
    }

    @GetMapping("/")
    public Page<PostResource> getAllStudents(Pageable pageable) {
        return mapper.modelListPage(postService.getAll(), pageable);
    }

    @GetMapping("{postId}")
    public PostResource getPostById(@PathVariable Long postId) {
        return mapper.toResource(postService.getById(postId));
    }

    @PostMapping("/create-post/")
    public ResponseEntity<PostResource> createPost(
            @RequestBody CreatePostResource resource) {
        return new ResponseEntity<> (mapper.toResource(
                postService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

    @PutMapping("{postId}")
    public PostResource updatePost(
            @PathVariable Long postId,
            @RequestBody UpdatePostResource resource) {
        return mapper.toResource(postService.update(postId,
                mapper.toModel(resource)));
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable Long postId) {
        return postService.delete(postId);
    }

}