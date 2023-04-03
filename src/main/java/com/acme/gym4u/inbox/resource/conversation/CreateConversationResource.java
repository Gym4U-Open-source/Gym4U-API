package com.acme.gym4u.inbox.resource.conversation;

import com.acme.gym4u.security.domain.model.entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class CreateConversationResource {
    @NotNull
    private User coach;

    @NotNull
    private User client;
}
