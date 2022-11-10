package com.acme.gym4u.inbox.resource.update;


import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class MessageResource {
    private Long id;
    private Long userId;
    private String message;
}
