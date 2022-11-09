package com.acme.gym4u.profile.mapping;

import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.profile.resource.CreateProfileResource;
import com.acme.gym4u.profile.resource.ProfileResource;
import com.acme.gym4u.profile.resource.UpdateProfileResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ProfileMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ProfileResource toResource(Profile model) {
        return mapper.map(model, ProfileResource.class);
    }

    public Page<ProfileResource> modelListPage(List<Profile> modelList,
                                               Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList,
                ProfileResource.class), pageable, modelList.size());

    }

    public Profile toModel(CreateProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }

    public Profile toModel(UpdateProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }

}
