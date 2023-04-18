package com.acme.gym4u.posts.mapping;

import com.acme.gym4u.inbox.resource.conversation.ConversationResource;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.posts.resource.PostCommentResource;
import com.acme.gym4u.posts.resource.create.CreatePostCommentResource;
import com.acme.gym4u.posts.resource.update.UpdatePostCommentResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PostCommentMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PostCommentResource toResource(PostComment model){ return mapper.map(model, PostCommentResource.class);}

    public PostComment toModel(CreatePostCommentResource resource) { return mapper.map(resource, PostComment.class);}

    public PostComment toModel(UpdatePostCommentResource resource) { return mapper.map(resource, PostComment.class);}

    public Page<PostCommentResource> modelListPage(List<PostComment> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, PostCommentResource.class), pageable, modelList.size());
    }
}
