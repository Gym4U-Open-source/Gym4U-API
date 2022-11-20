package com.acme.gym4u.posts.api.rest;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/posts", produces = "application/json")
public class PostController {

    private final PostService postService;
    private final PostMapper mapper;

    public PostController(PostService postService, PostMapper mapperP) {
        this.postService = postService;
        this.mapper = mapperP;
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAll();
    }

    @GetMapping("/")
    public Page<PostResource> getAllPostsPageable(Pageable pageable) {
        return mapper.modelListPage(postService.getAll(), pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        Optional<Post> o = Optional.ofNullable(postService.getById(id));
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create-post/{postId}")
    public Post createPost(
            @RequestBody Post resource, @PathVariable Long postId) {
        return postService.create(resource,postId);
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
