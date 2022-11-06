package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.TagService;
import com.acme.gym4u.fitness.mapping.TagMapper;
import com.acme.gym4u.fitness.resource.TagResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
