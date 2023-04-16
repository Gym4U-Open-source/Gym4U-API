package com.acme.gym4u.inbox.mapping;

import com.acme.gym4u.inbox.domain.model.entity.Message;
import com.acme.gym4u.inbox.resource.create.CreateMessageResource;
import com.acme.gym4u.inbox.resource.update.MessageResource;
import com.acme.gym4u.inbox.resource.update.UpdateMessageResource;
import com.acme.gym4u.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class MessageMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public MessageResource toResource(Message model){
        return mapper.map(model, MessageResource.class);
    }

    public Page<MessageResource> modelListPage(List<Message> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,MessageResource.class),pageable,modelList.size());
    }

    public Message toModel(CreateMessageResource resource) {
        return mapper.map(resource, Message.class);
    }

    public Message toModel(UpdateMessageResource resource){
        return mapper.map(resource,Message.class);
    }

}
