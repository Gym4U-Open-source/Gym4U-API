package com.acme.gym4u.comunity.mapping;

import com.acme.gym4u.comunity.domain.model.entity.Follow;
import com.acme.gym4u.comunity.resource.FollowResource;
import com.acme.gym4u.comunity.resource.CreateFollowResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FollowMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public Page<FollowResource> modelListPage(List<Follow> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, FollowResource.class), pageable, modelList.size());
    }

    public Follow toModel(CreateFollowResource resource) {
        return mapper.map(resource, Follow.class);
    }

    public FollowResource toResource(Follow model) {
        return mapper.map(model, FollowResource.class);
    }
}
