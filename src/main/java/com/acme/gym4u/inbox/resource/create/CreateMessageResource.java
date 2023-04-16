package com.acme.gym4u.inbox.resource.create;


import com.acme.gym4u.inbox.domain.model.entity.Conversation;
import com.acme.gym4u.security.domain.model.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateMessageResource {
    @NotBlank
    @NotNull
    @Size(max = 240)
    private String message;

    @NotNull
    private Conversation conversation;

    @NotNull
    private User user;
}
