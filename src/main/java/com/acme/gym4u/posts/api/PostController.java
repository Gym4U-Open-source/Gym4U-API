package com.acme.gym4u.posts.api.rest;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.service.PostService;
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

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public List<Post> getAllPosts() {
        return postService.getAll();
    }

    @GetMapping("/aaa")
    public Page<Post> getAllStudents(Pageable pageable) {
        return postService.getAll(pageable);
    }

    @GetMapping("{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getById(postId);
    }

    @PostMapping("/create-post/")
    public ResponseEntity<Post> createPost(
            @RequestBody Post resource) {
        return new ResponseEntity<> (postService.create(resource), HttpStatus.CREATED);
    }

    @PutMapping("{postId}")
    public Post updatePost(
            @PathVariable Long postId,
            @RequestBody Post resource) {
        return postService.update(postId, resource);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable Long postId) {
        return postService.delete(postId);
    }

}
