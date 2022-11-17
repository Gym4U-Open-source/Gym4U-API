package com.acme.gym4u.posts.mapping;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.posts.resource.CreatePostResource;
import com.acme.gym4u.posts.resource.PostResource;
import com.acme.gym4u.posts.resource.UpdatePostResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PostMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapperP;

    public List<PostResource> toResources(List<Post> modelList){
        return mapperP.mapList(modelList, PostResource.class);
    }

    public PostResource toResource(Post model){
        return mapperP.map(model, PostResource.class);
    }

    public Page<PostResource> modelListPage(List<Post> modelList, Pageable pageable){
        return new PageImpl<>(mapperP.mapList(modelList,
                PostResource.class), pageable, modelList.size());
    }

    public Post toModel(CreatePostResource resource){
        return mapperP.map(resource, Post.class);
    }

    public Post toModel(UpdatePostResource resource){
        return  mapperP.map(resource, Post.class);
    }
}
