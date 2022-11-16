package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.TagForWorkoutService;
import com.acme.gym4u.fitness.mapping.TagForWorkoutMapper;
import com.acme.gym4u.fitness.resource.TagForWorkoutResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/tagsForWorkout", produces = "application/json")
@Tag(name = "TagsForWorkout", description = "Create, read, update and delete tags for workout")
public class TagsForWorkoutController {

    private final TagForWorkoutService tagForWorkoutService;
    private final TagForWorkoutMapper mapper;

    public TagsForWorkoutController(TagForWorkoutService tagForWorkoutService, TagForWorkoutMapper mapper) {
        this.tagForWorkoutService = tagForWorkoutService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<TagForWorkoutResource> getAllTagsForWorkout(Pageable pageable){
        return mapper.modelListPage(tagForWorkoutService.getAll(),pageable);
    }

    @GetMapping("{tagForWorkoutId}")
    public TagForWorkoutResource getTagForWorkoutById(@PathVariable Long tagForWorkoutId){
        return mapper.toResource(tagForWorkoutService.getById(tagForWorkoutId));
    }

    

}
