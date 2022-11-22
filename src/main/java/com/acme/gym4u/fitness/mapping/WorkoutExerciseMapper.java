package com.acme.gym4u.fitness.mapping;

import com.acme.gym4u.fitness.domain.model.entity.WorkoutExercise;
import com.acme.gym4u.fitness.resource.CreateWorkoutExerciseResource;
import com.acme.gym4u.fitness.resource.UpdateWorkoutExerciseResource;
import com.acme.gym4u.fitness.resource.WorkoutExerciseResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class WorkoutExerciseMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public WorkoutExerciseResource toResource(WorkoutExercise model) {return mapper.map(model, WorkoutExerciseResource.class);}

    public Page<WorkoutExerciseResource> modelListPage(List<WorkoutExercise> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,
                WorkoutExerciseResource.class),pageable, modelList.size());
    }

    public WorkoutExercise toModel(CreateWorkoutExerciseResource resource) {return mapper.map(resource, WorkoutExercise.class);}

    public WorkoutExercise toModel(UpdateWorkoutExerciseResource resource) {return mapper.map(resource, WorkoutExercise.class);}

}
