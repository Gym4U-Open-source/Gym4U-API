package com.acme.gym4u.profile.mapping;

import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.profile.resource.CreatePersonResource;
import com.acme.gym4u.profile.resource.PersonResource;
import com.acme.gym4u.profile.resource.UpdatePersonResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PersonMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PersonResource toResource(Profile model) {
        return mapper.map(model, PersonResource.class);
    }

    public Page<PersonResource> modelListPage(List<Profile> modelList,
                                               Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList,
                PersonResource.class), pageable, modelList.size());

    }

    public Profile toModel(CreatePersonResource resource) {
        return mapper.map(resource, Profile.class);
    }

    public Profile toModel(UpdatePersonResource resource) {
        return mapper.map(resource, Profile.class);
    }

}
