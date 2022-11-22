package com.acme.gym4u.fitness.mapping;


import com.acme.gym4u.fitness.domain.model.entity.Workout;
import com.acme.gym4u.fitness.resource.CreateWorkoutResource;
import com.acme.gym4u.fitness.resource.UpdateWorkoutResource;
import com.acme.gym4u.fitness.resource.WorkoutResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class WorkoutMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public WorkoutResource toResource(Workout model) {return mapper.map(model, WorkoutResource.class);}

    public Page<WorkoutResource> modelListPage(List<Workout> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,
                WorkoutResource.class),pageable, modelList.size());
    }

    public Workout toModel(CreateWorkoutResource resource) {return mapper.map(resource, Workout.class);}

    public Workout toModel(UpdateWorkoutResource resource) {return mapper.map(resource, Workout.class);}

}
