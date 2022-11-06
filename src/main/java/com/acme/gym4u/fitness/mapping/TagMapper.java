package com.acme.gym4u.fitness.mapping;

import com.acme.gym4u.fitness.domain.model.entity.Tag;
import com.acme.gym4u.fitness.resource.CreateTagResource;
import com.acme.gym4u.fitness.resource.TagResource;
import com.acme.gym4u.fitness.resource.UpdateTagResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TagMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TagResource toResource(Tag model) {return mapper.map(model, TagResource.class);}

    public Page<TagResource> modelListPage(List<Tag> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,
                TagResource.class), pageable, modelList.size());
    }

    public Tag toModel(CreateTagResource resource) {return mapper.map(resource, Tag.class);}

    public Tag toModel(UpdateTagResource resource) {return mapper.map(resource, Tag.class);}
}
