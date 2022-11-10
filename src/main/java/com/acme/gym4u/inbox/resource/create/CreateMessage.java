package com.acme.gym4u.inbox.resource.create;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateMessage {

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String content;
}
