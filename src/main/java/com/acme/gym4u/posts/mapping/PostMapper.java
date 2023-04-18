package com.acme.gym4u.posts.mapping;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.posts.resource.PostCommentResource;
import com.acme.gym4u.posts.resource.PostResource;
import com.acme.gym4u.posts.resource.create.CreatePostCommentResource;
import com.acme.gym4u.posts.resource.create.CreatePostResource;
import com.acme.gym4u.posts.resource.update.UpdatePostResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PostMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public PostResource toResource(Post model){ return mapper.map(model, PostResource.class);}

    public Post toModel(CreatePostResource resource) { return mapper.map(resource, Post.class);}

    public Post toModel(UpdatePostResource resource) { return mapper.map(resource, Post.class);}

    public Page<PostResource> modelListPage(List<Post> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, PostResource.class), pageable, modelList.size());
    }
}
