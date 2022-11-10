package com.acme.gym4u.inbox.resource.update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateMessageResource {
    private long id;


    private Long userId;

    @NotBlank
    @NotNull
    @Size(max = 200)
    private String content;
}
