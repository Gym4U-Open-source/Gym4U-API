package com.acme.gym4u.inbox.api.rest;


import com.acme.gym4u.inbox.domain.service.MessageService;
import com.acme.gym4u.inbox.mapping.MessageMapper;
import com.acme.gym4u.inbox.resource.create.CreateMessageResource;
import com.acme.gym4u.inbox.resource.update.MessageResource;
import com.acme.gym4u.inbox.resource.update.UpdateMessageResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/messages",produces = "application/json")
public class MessagesController {

    private final MessageService messageService;
    private final MessageMapper mapper;

    public MessagesController(MessageService messageService, MessageMapper mapper) {
        this.messageService = messageService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<MessageResource> getAllMessage(Pageable pageable){
        return mapper.modelListPage(messageService.getAll(),pageable);
    }

    @GetMapping("{messageId}")
    public MessageResource getMessageById(@PathVariable Long messageId){
        return mapper.toResource(messageService.getById(messageId));
    }

    @PostMapping("user/{userId}")
    public MessageResource createMessage(@PathVariable Long userId,
                                         @Valid @RequestBody CreateMessageResource resource){
        return mapper.toResource(messageService.create(userId, mapper.toModel(resource)));
    }

    @PutMapping("{messageId}")
    public MessageResource updateMessage(
            @PathVariable Long messageId,
            @RequestBody UpdateMessageResource resource) {
        return mapper.toResource(
                messageService.update(messageId,
                        mapper.toModel(resource)));
    }

    @DeleteMapping("{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId){
        return messageService.delete(messageId);
    }

}
