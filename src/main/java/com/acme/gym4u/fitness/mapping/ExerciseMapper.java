package com.acme.gym4u.fitness.mapping;

import com.acme.gym4u.fitness.domain.model.entity.Exercise;
import com.acme.gym4u.fitness.resource.CreateExerciseResource;
import com.acme.gym4u.fitness.resource.ExerciseResource;
import com.acme.gym4u.fitness.resource.UpdateExerciseResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ExerciseMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ExerciseResource toResource(Exercise model) {return mapper.map(model, ExerciseResource.class);}

    public Page<ExerciseResource> modelListPage(List<Exercise> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,
                ExerciseResource.class),pageable, modelList.size());
    }

    public Exercise toModel(CreateExerciseResource resource) {return mapper.map(resource, Exercise.class);}

    public Exercise toModel(UpdateExerciseResource resource) {return mapper.map(resource, Exercise.class);}
}
