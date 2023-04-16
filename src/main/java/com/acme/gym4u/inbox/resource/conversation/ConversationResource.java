package com.acme.gym4u.inbox.resource.conversation;

import com.acme.gym4u.inbox.domain.model.entity.Message;
import com.acme.gym4u.inbox.resource.update.MessageResource;
import com.acme.gym4u.security.domain.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class ConversationResource {
    private Long id;
    @JsonIgnore
    private User coach;
    @JsonIgnore
    private User client;
    private List<MessageResource> messages;
}
