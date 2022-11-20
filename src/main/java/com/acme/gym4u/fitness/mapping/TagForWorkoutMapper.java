package com.acme.gym4u.fitness.mapping;

import com.acme.gym4u.fitness.domain.model.entity.TagForWorkout;
import com.acme.gym4u.fitness.resource.CreateTagForWorkoutResource;
import com.acme.gym4u.fitness.resource.TagForWorkoutResource;
import com.acme.gym4u.fitness.resource.UpdateTagForWorkoutResource;
import com.acme.gym4u.fitness.resource.UpdateTagResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TagForWorkoutMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TagForWorkoutResource toResource(TagForWorkout model){
        return mapper.map(model, TagForWorkoutResource.class);
    }

    public Page<TagForWorkoutResource> modelListPage(List<TagForWorkout> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,
                TagForWorkoutResource.class),pageable, modelList.size());
    }

    public TagForWorkout toModel(CreateTagForWorkoutResource resource) {return mapper.map(resource, TagForWorkout.class);}

    public TagForWorkout toModel(UpdateTagForWorkoutResource resource) {return mapper.map(resource, TagForWorkout.class);}


}
