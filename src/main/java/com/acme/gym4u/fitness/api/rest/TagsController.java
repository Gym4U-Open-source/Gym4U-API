package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.TagService;
import com.acme.gym4u.fitness.mapping.TagMapper;
import com.acme.gym4u.fitness.resource.CreateTagResource;
import com.acme.gym4u.fitness.resource.TagResource;
import com.acme.gym4u.fitness.resource.UpdateTagResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/tags", produces = "application/json")
@Tag(name = "Tags", description = "Create, read, update and delete tags")
public class TagsController {

    private final TagService tagService;
    private final TagMapper mapper;

    public TagsController(TagService tagService, TagMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all tags")
    public Page<TagResource> getAllTags(Pageable pageable) {
        return mapper.modelListPage(tagService.getAll(), pageable);
    }

    @GetMapping("{tagId}")
    public TagResource getTagById(@PathVariable Long tagId) {
        return mapper.toResource(tagService.getById(tagId));
    }

    @PostMapping
    @Operation(summary = "Create Tag",
            responses = {@ApiResponse(description = "Tag successfully created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TagResource.class)))})
    public ResponseEntity<TagResource> createTag(
            @RequestBody CreateTagResource resource) {
        return new ResponseEntity<>(mapper.toResource(
                tagService.create(mapper.toModel(resource))),
                HttpStatus.CREATED);
    }

    @PutMapping("{tagId}")
    public TagResource updateTag(
            @PathVariable Long tagId,
            @RequestBody UpdateTagResource resource) {
        return mapper.toResource(
                tagService.update(tagId,
                        mapper.toModel(resource)));
    }

    @DeleteMapping("{tagId}")
    public ResponseEntity<?> deleteTag(
            @PathVariable Long tagId) {
        return tagService.delete(tagId);
    }

}
