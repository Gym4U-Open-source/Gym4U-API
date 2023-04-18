package com.acme.gym4u.posts.api;

import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.posts.domain.service.PostCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/postsComments", produces = "application/json")
public class PostCommentController {

    private final PostCommentService postCommentService;

    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }


    @GetMapping("/")
    public List<PostComment> getAllPostComments() {return postCommentService.getAll();}

    @PostMapping("{postCommentId}")
    public PostComment getPostCommentById(@PathVariable Long postCommentId){
        return postCommentService.getById(postCommentId);
    }

    @PostMapping("/createPost/")
    public ResponseEntity<PostComment> createPostComment(@RequestBody PostComment resource){
        return new ResponseEntity<>(postCommentService.create(resource), HttpStatus.CREATED);
    }

    @PutMapping("{postCommentId}")
    public PostComment updatePostComment(@PathVariable Long postCommentId, @RequestBody PostComment resource){
        return postCommentService.update(postCommentId, resource);
    }

    @DeleteMapping("{postCommentId}")
    public ResponseEntity<?> deletePostComment(@PathVariable Long postCommentId){
        return postCommentService.delete(postCommentId);
    }
}
