package com.acme.gym4u.fitness.mapping;

import com.acme.gym4u.fitness.domain.model.entity.Routine;
import com.acme.gym4u.fitness.resource.routine.CreateRoutineResource;
import com.acme.gym4u.fitness.resource.routine.RoutineResource;
import com.acme.gym4u.fitness.resource.routine.UpdateRoutineResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RoutineMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public RoutineResource toResource(Routine model) { return mapper.map(model, RoutineResource.class); }
    public Routine toModel(CreateRoutineResource resource) { return mapper.map(resource, Routine.class); }

    public Page<RoutineResource> modelListPage(List<Routine> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, RoutineResource.class),pageable,modelList.size());
    }
}
