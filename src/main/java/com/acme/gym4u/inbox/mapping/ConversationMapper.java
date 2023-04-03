package com.acme.gym4u.inbox.mapping;

import com.acme.gym4u.inbox.domain.model.entity.Conversation;
import com.acme.gym4u.inbox.resource.conversation.ConversationResource;
import com.acme.gym4u.inbox.resource.conversation.CreateConversationResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ConversationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ConversationResource toResource(Conversation model) {
        return mapper.map(model, ConversationResource.class);
    }

    public Conversation toModel(CreateConversationResource resource) { return mapper.map(resource, Conversation.class); }

    public Page<ConversationResource> modelListPage(List<Conversation> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ConversationResource.class), pageable, modelList.size());
    }
}
