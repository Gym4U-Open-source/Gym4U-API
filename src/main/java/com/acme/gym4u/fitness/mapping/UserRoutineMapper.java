package com.acme.gym4u.fitness.mapping;

import com.acme.gym4u.fitness.domain.model.entity.UserRoutine;
import com.acme.gym4u.fitness.resource.userRoutine.CreateUserRoutineResource;
import com.acme.gym4u.fitness.resource.userRoutine.UserRoutineResource;
import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserRoutineMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public UserRoutineResource toResource(UserRoutine model) {return mapper.map(model, UserRoutineResource.class); }
    public UserRoutine toModel(CreateUserRoutineResource resource) {return mapper.map(resource, UserRoutine.class); }

    public Page<UserRoutineResource> modelListPage(List<UserRoutine> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, UserRoutineResource.class),pageable, modelList.size());
    }
}
