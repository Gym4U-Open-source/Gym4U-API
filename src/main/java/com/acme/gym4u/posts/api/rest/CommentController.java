package com.acme.gym4u.posts.api.rest;

import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.posts.domain.service.PostCommentService;
import com.acme.gym4u.posts.mapping.PostCommentMapper;
import com.acme.gym4u.posts.resource.PostCommentResource;
import com.acme.gym4u.posts.resource.UpdatePostCommentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/comments", produces = "application/json")
public class CommentController {
    private final PostCommentService commentService;
    private final PostCommentMapper mapper;

    public CommentController(PostCommentService commentService, PostCommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<PostCommentResource> getAllComments() {
        return mapper.toResources(commentService.getAll());
    }
    @GetMapping("/")
    public Page<PostCommentResource> getAllCommentsPageable(Pageable pageable) {
        return mapper.modelListPage(commentService.getAll(), pageable);
    }

    @GetMapping("{commentId}")
    public PostCommentResource getCommentById(@PathVariable Long commentId) {
        return mapper.toResource(commentService.getById(commentId));
    }

    @PostMapping("/create-comment/")
    public PostComment createComment(@RequestBody PostComment resource) {
      return commentService.create(resource);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId) {
        return commentService.delete(commentId);
    }

    @GetMapping(value="/comments-by-post")
    public List<PostComment> getCommentsByPost(@RequestParam Iterable<Long> ids){
        return commentService.listCommentsByIds(ids);
    }

    @PutMapping(value="/update/{commentId}")
    public PostCommentResource updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdatePostCommentResource resource){
        return mapper.toResource(commentService.update(commentId,
                mapper.toModel(resource)));
    }


}
